package com.creeperdch.jdmall.ui.pop;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.listener.IProductSorChangeListener;

public class ProductSortPop extends JDBasePop implements View.OnClickListener {

    private TextView mAllSort;
    private TextView mNewSort;
    private TextView mCommentSort;
    private View mLeftV;
    private IProductSorChangeListener mListener;

    public ProductSortPop(Context context) {
        super(context);
    }

    public void setListener(IProductSorChangeListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void initViews() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null);
        mAllSort = (TextView) contentView.findViewById(R.id.all_sort);
        mNewSort = (TextView) contentView.findViewById(R.id.new_sort);
        mCommentSort = (TextView) contentView.findViewById(R.id.comment_sort);
        mLeftV = (View) contentView.findViewById(R.id.left_v);

        mAllSort.setOnClickListener(this);
        mNewSort.setOnClickListener(this);
        mCommentSort.setOnClickListener(this);
        mLeftV.setOnClickListener(this);

        mPopupWindow = new PopupWindow(contentView, -1, -1);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.update();
    }

    @Override
    public void onClick(View v) {
        onDismiss();
        switch (v.getId()) {
            case R.id.all_sort:
            case R.id.new_sort:
            case R.id.comment_sort:
                if (mListener != null) {
                    mListener.onSortChanged(v.getId());
                }
                break;
        }
    }

    @Override
    public void onShow(View anchorView) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAsDropDown(anchorView, 0, 2);
        }
    }

}
