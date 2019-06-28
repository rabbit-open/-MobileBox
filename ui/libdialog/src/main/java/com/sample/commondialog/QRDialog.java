package com.sample.commondialog;

import android.graphics.Bitmap;
import android.view.View;

import com.sample.commondialog.widget.BaseDialog;
import com.sample.commondialog.widget.ViewHolder;


public class QRDialog extends BaseDialog {

    public String title;
    public String content;
    private Bitmap image;

    public static QRDialog newInstance() {
        return new QRDialog();
    }

    @Override
    public int setUpLayoutId() {
        return R.layout.dialog_qrcode;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseDialog dialog) {
        holder.setText(R.id.title, title);
        holder.setText(R.id.message, content);
        holder.setImageView(R.id.image, image);
    }

    public QRDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public QRDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public QRDialog setImage(Bitmap image) {
        this.image = image;
        return this;
    }
}
