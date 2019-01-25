package com.hualala.server.media;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaUtils {

    public static void getLoadMedia(Context context, JSONArray jsonArray) {

        Cursor cursor = context.getApplicationContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)); // id
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM)); // 专辑
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)); // 艺术家
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)); // 显示名称
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)); // 路径
                long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)); // 时长
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)); // 大小
                String resolution = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION));

                Cursor thumbCursor = null;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", title);
                    jsonObject.put("path", path);
                    jsonObject.put("duration", duration);
                    jsonObject.put("size", size);
                    thumbCursor = context.getApplicationContext().getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null, MediaStore.Video.Thumbnails.VIDEO_ID + "=" + id, null, null);
                    if (thumbCursor.moveToFirst()) {
                        String thumbPath = thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                        jsonObject.put("thumbPath", thumbPath);
                    }
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (null != thumbCursor) {
                        thumbCursor.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public static void getLoadAudio(Context context, JSONArray jsonArray) {

        Cursor cursor = context.getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)); // id
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)); // 专辑
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)); // 艺术家
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)); // 显示名称
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)); // 路径
                long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)); // 时长
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)); // 大小

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", title);
                    jsonObject.put("path", path);
                    jsonObject.put("duration", duration);
                    jsonObject.put("size", size);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }


    public static void getLoadImages(Context context, JSONArray jsonArray) {

        Cursor cursor = context.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)); // id
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)); // 显示名称
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)); // 路径
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)); // 大小
                long width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)); // 大小
                long height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)); // 大小

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", title);
                    jsonObject.put("path", path);
                    jsonObject.put("size", size);
                    jsonObject.put("width", width);
                    jsonObject.put("height", height);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }
}
