package com.hualala.server.api;

import android.content.Context;

import com.google.gson.Gson;
import com.hualala.data.entity.reponse.MBPhoneListResponse;
import com.hualala.data.entity.reponse.MBVideoListResponse;
import com.hualala.domain.model.MVideo;
import com.hualala.libserver.R;
import com.hualala.server.media.MediaUtils;
import com.hualala.server.phone.ContactsBean;
import com.hualala.server.phone.PhoneUtils;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.server.AsyncHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


public class ServerApi {

    private AsyncHttpServer server = new AsyncHttpServer();
    private AsyncServer asyncServer = new AsyncServer();
    private Context context;

    private static class ServerApiHolder {
        public static ServerApi server = new ServerApi();
    }

    public static ServerApi getInstance() {
        return ServerApiHolder.server;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private ServerApi() {
        getFiles();
        getPhoneInfo();
        addLocalFileResource();
        this.server.listen(asyncServer, 8888);
    }

    private void getPhoneInfo() {
        server.get("/getPhoneInfo", (request, response) -> {
            List<ContactsBean> list = PhoneUtils.getContactInfo(context);
            MBPhoneListResponse data = new MBPhoneListResponse();
            data.setCode("200");
            data.setMsg("success");
            data.setData(list);
            response.send(new Gson().toJson(data));
        });
    }

    private void getFiles() {
        server.get("/files", (request, response) -> {

            new NotificationUtils(context,
                    "channel_api", context.getString(R.string.notify_channel_id_api),
                    context.getString(R.string.notify_channel_name_api))
                    .sendNotification(context.getString(R.string.notify_channel_title_api) + ":" + request.getPath(),
                            context.getString(R.string.notify_channel_content_api), 3);

            String format = request.getQuery().getString("format");
            List<MVideo> array = new ArrayList<>();
            if ("apk".equals(format)) {
                MediaUtils.getApk(context, array);
            } else if ("mp4".equals(format)) {
                MediaUtils.getLoadMedia(context, array);
            } else if ("mp3".equals(format)) {
                MediaUtils.getLoadAudio(context, array);
            } else if ("jpg".equals(format)) {
                MediaUtils.getLoadImages(context, array);
            }

            MBVideoListResponse data = new MBVideoListResponse();
            data.setCode("200");
            data.setMsg("success");
            data.setData(array);
            response.send(new Gson().toJson(data));
        });
    }

    private void addLocalFileResource() {
        server.get("/files/.*", (request, response) -> {

            new NotificationUtils(context,
                    "channel_api", context.getString(R.string.notify_channel_id_api),
                    context.getString(R.string.notify_channel_name_api))
                    .sendNotification(context.getString(R.string.notify_channel_title_api) + ":" + request.getPath(),
                            context.getString(R.string.notify_channel_content_api), 2);

            String path = request.getPath().replace("/files/", "");
            try {
                path = URLDecoder.decode(path, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    response.sendStream(fis, fis.available());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            response.code(404).send("file Not found!");
        });
    }

    private static final String TEXT_CONTENT_TYPE = "text/html;charset=utf-8";
    private static final String CSS_CONTENT_TYPE = "text/css;charset=utf-8";
    private static final String BINARY_CONTENT_TYPE = "application/octet-stream";
    private static final String JS_CONTENT_TYPE = "application/javascript";
    private static final String PNG_CONTENT_TYPE = "application/x-png";
    private static final String JPG_CONTENT_TYPE = "application/jpeg";
    private static final String SWF_CONTENT_TYPE = "application/x-shockwave-flash";
    private static final String WOFF_CONTENT_TYPE = "application/x-font-woff";
    private static final String TTF_CONTENT_TYPE = "application/x-font-truetype";
    private static final String SVG_CONTENT_TYPE = "image/svg+xml";
    private static final String EOT_CONTENT_TYPE = "image/vnd.ms-fontobject";
    private static final String MP3_CONTENT_TYPE = "audio/mp3";
    private static final String MP4_CONTENT_TYPE = "video/mpeg4";

    private String getContentTypeByResourceName(String resourceName) {
        if (resourceName.endsWith(".css")) {
            return CSS_CONTENT_TYPE;
        } else if (resourceName.endsWith(".js")) {
            return JS_CONTENT_TYPE;
        } else if (resourceName.endsWith(".swf")) {
            return SWF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".png")) {
            return PNG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".jpg") || resourceName.endsWith(".jpeg")) {
            return JPG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".woff")) {
            return WOFF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".ttf")) {
            return TTF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".svg")) {
            return SVG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".eot")) {
            return EOT_CONTENT_TYPE;
        } else if (resourceName.endsWith(".mp3")) {
            return MP3_CONTENT_TYPE;
        } else if (resourceName.endsWith(".mp4")) {
            return MP4_CONTENT_TYPE;
        }
        return "";
    }


}
