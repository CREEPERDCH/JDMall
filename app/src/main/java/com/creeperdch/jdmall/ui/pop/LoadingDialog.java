package com.creeperdch.jdmall.ui.pop;
/*
 * Created by CREEPER_D on 2017/8/16.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.creeperdch.jdmall.R;

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_view);
        ImageView iv = findViewById(R.id.loading_iv);
        AnimationDrawable drawable = (AnimationDrawable) iv.getDrawable();
        drawable.start();
    }
}
