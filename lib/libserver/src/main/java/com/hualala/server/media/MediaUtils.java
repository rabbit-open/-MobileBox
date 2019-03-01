package com.hualala.server.media;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import com.hualala.domain.model.MVideo;

import java.io.File;
import java.util.List;

public class MediaUtils {

    public static void getLoadMedia(Context context, List<MVideo> jsonArray) {

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

                    if (new File(path).exists()) {
                        MVideo jsonObject = new MVideo();
                        jsonObject.setName(title);
                        jsonObject.setPath(path);
                        jsonObject.setDuration(duration);
                        jsonObject.setSize(size);
                        thumbCursor = context.getApplicationContext().getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null, MediaStore.Video.Thumbnails.VIDEO_ID + "=" + id, null, null);
                        if (thumbCursor.moveToFirst()) {
                            String thumbPath = thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                            if (new File(thumbPath).exists()) {
                                jsonObject.setThumbPath(thumbPath);
                            }
                        }
                        jsonArray.add(jsonObject);
                        jsonObject.setType(MVideo.MVideoTypeVideo);
                    }

                } catch (Exception e) {
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


    public static void getLoadAudio(Context context, List<MVideo> jsonArray) {

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

                if (new File(path).exists()) {

                    try {
                        MVideo jsonObject = new MVideo();
                        jsonObject.setName(title);
                        jsonObject.setPath(path);
                        jsonObject.setDuration(duration);
                        jsonObject.setSize(size);
                        jsonArray.add(jsonObject);
                        jsonObject.setType(MVideo.MVideoTypeAudio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }


    public static void getLoadImages(Context context, List<MVideo> jsonArray) {

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
                int width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)); // 大小
                int height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)); // 大小
                if (new File(path).exists()) {
                    try {
                        MVideo jsonObject = new MVideo();
                        jsonObject.setName(title);
                        jsonObject.setPath(path);
                        jsonObject.setSize(size);
                        jsonObject.setWidth(width);
                        jsonObject.setHeight(height);
                        jsonArray.add(jsonObject);
                        jsonObject.setType(MVideo.MVideoTypePicture);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }


    public static void getApk(Context context, List<MVideo> array) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
        try {
            for (PackageInfo packageInfo : packageInfoList) {
                String name = (String) context.getPackageManager().getApplicationLabel(packageInfo.applicationInfo);
                String path = packageInfo.applicationInfo.sourceDir;
                float size = new File(path).length() / 1024f / 1024f;
                Drawable icon = context.getPackageManager().getApplicationIcon(packageInfo.applicationInfo);
                MVideo jsonObject = new MVideo();
                jsonObject.setName(name);
                jsonObject.setPath(path);
                jsonObject.setType(MVideo.MVideoTypeApk);
                jsonObject.setSize((long) size);
                array.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
