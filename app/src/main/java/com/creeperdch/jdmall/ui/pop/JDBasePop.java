package com.creeperdch.jdmall.ui.pop;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public abstract class JDBasePop {

    protected Context mContext;
    protected PopupWindow mPopupWindow;

    public JDBasePop(Context context) {
        this.mContext = context;
        initViews();
    }

    protected abstract void initViews();

    public abstract void onShow(View anchorView);

    public void onDismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

}
