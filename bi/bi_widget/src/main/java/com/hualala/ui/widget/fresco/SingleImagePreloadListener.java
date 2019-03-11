package com.hualala.ui.widget.fresco;


import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SingleImagePreloadListener implements ImagePreloadListener {

    public int status = 0;//0 失败 1 成功
    public String url;//保存路径
    public String savepath;//保存路径
    public String fileName;//保存路径

   public SingleImagePreloadListener(String url,String savepath,String fileName){
        this.url=url;
        this.savepath=savepath;
        this.fileName=fileName;
    }

    @Override
    public void onLoadingSuccess(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                saveBitmap(bitmap);
                status = 1;
                onSuccess(status,savepath,url);
            } catch (IOException e) {
                e.printStackTrace();
                status = 0;
                onSuccess(status,savepath,url);
            }
        } else {
            status = 0;
            onSuccess(status,savepath,url);
        }
    }

    @Override
    public void onLoadingFailed() {
        status=0;
        onSuccess(status,savepath,url);
    }

    public void onSuccess(int status,String savepath,String url){

    }

    private Boolean saveBitmap(Bitmap bitmap) throws IOException {

        File copypath = new File(savepath);

        if (!copypath.exists()) {
            copypath.mkdirs();
        }

        File f = new File(copypath, fileName);
        if (f.exists()) {// 如果本来存在的话，删除
            f.delete();
        }

        f.createNewFile();

        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}
