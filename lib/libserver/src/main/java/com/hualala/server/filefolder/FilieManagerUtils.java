package com.hualala.server.filefolder;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class FilieManagerUtils {

    public static String rootPath = Environment.getRootDirectory().getAbsolutePath();


    public static ArrayList<FileData> getFileList(String root) {
        ArrayList<FileData> data = new ArrayList<>();
        File rootFile = new File(root);//当前目录
        if (rootFile.isDirectory()) {
            if (rootFile.getAbsolutePath().equals(rootPath)) {
                data.add(new FileData("根目录:", rootPath, true));
            } else {
                data.add(new FileData("当前所在目录：" + rootFile.getName() + "  上一级目录:" + rootFile.getParentFile().getName(), rootFile.getParentFile().getAbsolutePath(), true));
            }
            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File temp : files) {
                    data.add(new FileData(temp.getName(), temp.getAbsolutePath(), temp.isDirectory()));
                }
            }
        }
        return data;
    }


    public static ArrayList<FileData> getFileListSdCard() {
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        return getFileList(rootPath);
    }

    public static ArrayList<FileData> getFileListRoot() {
        rootPath = Environment.getDataDirectory().getAbsolutePath();
        return getFileList(rootPath);
    }

}
