package com.hualala.data.net;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CloudClient {

    public static final String TRACE_ID_PRE_TERMINAL = "10";

    private static final int CONNECT_TIMEOUT_SECOND = 15;
    private static final int READ_TIMEOUT_SECOND = 15;
    private static final int WRITE_TIMEOUT_SECOND = 15;

    private CloudApi mCloudApi;
    private ClientConfig mClientConfig;
    private HashMap<String, String> header;

    public CloudClient(ClientConfig clientConfig) {
        mClientConfig = clientConfig;
        mCloudApi = createApi(mClientConfig);
    }

    public void changeServerAddr(String serverAddr) {
        mClientConfig.apiBaseUrl = serverAddr;
        mCloudApi = createApi(mClientConfig);
    }

    public void addHeader(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        if (header == null) {
            header = new HashMap<>();
        }
        header.put(key, value);
    }

    private CloudApi createApi(final ClientConfig clientConfig) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.v("app-[request]", message);
                    }
                })
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        if (header != null && header.size() != 0) {
                            Set<Map.Entry<String, String>> headers = header.entrySet();
                            for (Map.Entry<String, String> entry : headers) {
                                builder.header(entry.getKey(), entry.getValue());
                            }
                        }
                        builder.header("X-Trace-ID", getTraceId(clientConfig.traceIdPre));
                        builder.method(original.method(), original.body());
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
                .connectTimeout(CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(clientConfig.apiBaseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(CloudApi.class);
    }


    public CloudApi getCloudApi() {
        return mCloudApi;
    }

    public static class ClientConfig {
        String apiBaseUrl;
        String traceIdPre;

        public ClientConfig(String apiBaseUrl, String traceIdPre) {
            this.apiBaseUrl = apiBaseUrl;
            this.traceIdPre = traceIdPre;
        }
    }

    public String getTraceId(String traceIdPre) {
        return new StringBuilder(traceIdPre).append(System.currentTimeMillis()).append(UUID.randomUUID()).toString().replaceAll("-", "");
    }
}
