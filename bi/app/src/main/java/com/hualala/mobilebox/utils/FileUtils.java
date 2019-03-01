package com.hualala.mobilebox.utils;

import android.os.Environment;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public static File getAppFolder() {
        if (!isSDCardAvailable()) {
            return null;
        }
        String folderName = MBContext.getContext().getString(R.string.app_name);
        File folder = new File(Environment.getExternalStorageDirectory(), folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile() && file.exists()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 检测SD卡是否可用
     *
     * @return True if SD card is can be written, false otherwise.
     */
    public static boolean isSDCardAvailable() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            ToastUtils.showToastCenter(MBContext.getContext(), "SD卡不可用");
            return false;
        }
        return true;
    }

    public static File createPhotoSavedFile() {
        File folder = getAppFolder();
        if (folder == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String fname = sdf.format(new Date()).toString() + ".jpg";
        return new File(folder, fname);
    }

    public static String createMp3SavedFile() {
        File appFolder = getAppFolder();
        if (appFolder == null) {
            return null;
        }

        File voiceFolder = new File(appFolder, "voice");
        if (!voiceFolder.exists()) {
            voiceFolder.mkdirs();
        }

        String fname = "record.mp3";
        File voice = new File(voiceFolder, fname);

        return voice.getAbsolutePath();
    }

    public static String readStringFromAsset(String filename) {
        try {
            InputStream inputStream = MBContext.getContext().getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringWriter writer = new StringWriter();

            String line = null;

            while ((line = reader.readLine()) != null) {
                writer.append(line);
            }

            reader.close();
            writer.close();

            return writer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
