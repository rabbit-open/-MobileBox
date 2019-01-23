package com.hualala.libserver;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.server.AsyncHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class ServerApi {

    private AsyncHttpServer server = new AsyncHttpServer();
    private AsyncServer asyncServer = new AsyncServer();

    private static class ServerApiHolder {
        public static ServerApi server = new ServerApi();
    }

    public static ServerApi getInstance() {
        return ServerApiHolder.server;
    }

    private ServerApi() {
        addLocalFileResource();
        this.server.listen(asyncServer, 80);
    }

    private void addLocalFileResource() {
        server.get("/files/.*", (request, response) -> {
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
