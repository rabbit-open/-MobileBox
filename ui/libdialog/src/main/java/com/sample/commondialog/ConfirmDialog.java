package com.sample.commondialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.sample.commondialog.widget.BaseDialog;
import com.sample.commondialog.widget.ViewHolder;


/**
 * 确定样式Dialog
 */
public class ConfirmDialog extends BaseDialog {

    private String type;

    public static ConfirmDialog newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static ConfirmDialog newInstance() {
        return new ConfirmDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getString("type");
    }

    @Override
    public int setUpLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseDialog dialog) {

        if (TextUtils.isEmpty(type)) {
            holder.setText(R.id.title, title);
            holder.setText(R.id.message, content);
        }
        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        holder.setOnClickListener(R.id.confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ConfirmDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ConfirmDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public String title;
    public String content;
}
