package com.creeperdch.jdmall.ui;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.ProductListActivity;
import com.creeperdch.jdmall.bean.SubCateGoryBean;
import com.creeperdch.jdmall.bean.TopCategoryBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.BaseController;
import com.creeperdch.jdmall.controller.CateGoryController;
import com.creeperdch.jdmall.listener.IModelChangedListener;
import com.creeperdch.jdmall.utils.ImageUtil;

import java.util.List;

public class SubCategoryView extends ScrollView implements IModelChangedListener {

    private static final int MAX_COUNT_PERLINE = 3;

    private LinearLayout mContainerLl;
    private TopCategoryBean mTopCategory;
    private ImageView mbannerIv;
    private BaseController mController;

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == IDiyMessage.SUB_CATEGORY_ACTION) {
                showCategoryModule((List<SubCateGoryBean>) msg.obj);
            }
        }
    };

    public SubCategoryView(Context context) {
        super(context);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContainerLl = (LinearLayout) findViewById(R.id.child_container_ll);
        createBannerView();
        initController();
    }

    private void initController() {
        mController = new CateGoryController(getContext());
        mController.setListener(this);
    }

    public void onShow(TopCategoryBean bean) {
        mTopCategory = bean;
        removeLastAddedComponent();
        showNewestBanner();
        //请求二级分类的数据
        mController.sendAsyncMessage(IDiyMessage.SUB_CATEGORY_ACTION, mTopCategory.getId());
    }

    /**
     * 创建一个图片控件 并且添加到mContainerLl容器中
     */
    private void showNewestBanner() {
        ImageUtil.loadImage(getContext(), mTopCategory.getBannerUrl(), mbannerIv);
        mContainerLl.addView(mbannerIv);
    }

    /**
     * 移除上一次添加的组件
     */
    private void removeLastAddedComponent() {
        mContainerLl.removeAllViews();
    }

    /**
     * 创建顶部的图片控件
     */
    private void createBannerView() {
        mbannerIv = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        mbannerIv.setLayoutParams(params);
        mbannerIv.setScaleType(ImageView.ScaleType.FIT_XY);
    }


    /**
     * 展示所有的二级分类组件
     */
    private void showCategoryModule(List<SubCateGoryBean> secondCategoryDatas) {
        for (SubCateGoryBean item : secondCategoryDatas) {
            showSecondCategoryTitle(item.getName());
            List<TopCategoryBean> thirdCategories = getThirdCategoryArr(item);
            int datasCount = thirdCategories.size();
            int lines = calculateThirdCategoryLinesCount(datasCount);
            for (int lineIndex = 0; lineIndex < lines; lineIndex++) {
                LinearLayout lineContainerLl = initThirdCategoryLines();
                if (lineIndex * MAX_COUNT_PERLINE <= datasCount - 1) {
                    addColumns(thirdCategories.get(lineIndex * MAX_COUNT_PERLINE), lineContainerLl);
                }
                if (lineIndex * MAX_COUNT_PERLINE + 1 <= datasCount - 1) {
                    addColumns(thirdCategories.get(lineIndex * MAX_COUNT_PERLINE + 1), lineContainerLl);
                }
                if (lineIndex * MAX_COUNT_PERLINE + 2 <= datasCount - 1) {
                    addColumns(thirdCategories.get(lineIndex * MAX_COUNT_PERLINE + 2), lineContainerLl);
                }
            }
        }
    }

    @NonNull
    private LinearLayout initThirdCategoryLines() {
        LinearLayout lineContainerLl = new LinearLayout(getContext());
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lineParams.setMargins(0, 10, 0, 0);
        lineContainerLl.setLayoutParams(lineParams);
        mContainerLl.addView(lineContainerLl);
        return lineContainerLl;
    }

    private int calculateThirdCategoryLinesCount(int datasCount) {
        int lines = datasCount / MAX_COUNT_PERLINE;
        lines = datasCount % MAX_COUNT_PERLINE == 0 ? lines : (lines + 1);
        return lines;
    }

    private List<TopCategoryBean> getThirdCategoryArr(SubCateGoryBean item) {
        return JSON.parseArray(item.getThirdCategory(), TopCategoryBean.class);
    }

    /**
     * 显示二级分类的标题
     */
    private void showSecondCategoryTitle(String titleName) {
        TextView secondTitleTv = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        secondTitleTv.setLayoutParams(params);
        secondTitleTv.getPaint().setFakeBoldText(true);
        secondTitleTv.setText(titleName);
        mContainerLl.addView(secondTitleTv);
    }

    private void addColumns(TopCategoryBean thirdCategory, LinearLayout lineContainerLl) {
        LinearLayout columnsLl = initThirdCategoryColumnsContainer(thirdCategory.getId());
        initThirdCategoryIv(thirdCategory.getBannerUrl(), columnsLl);
        initThirdCategoryTitleTv(thirdCategory.getName(), columnsLl);
        lineContainerLl.addView(columnsLl);
    }

    private void initThirdCategoryTitleTv(String titleName, LinearLayout columnsLl) {
        TextView thirdCategoryTitleTv = new TextView(getContext());
        LinearLayout.LayoutParams thirdCategoryTitleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        thirdCategoryTitleTv.setLayoutParams(thirdCategoryTitleParams);
        thirdCategoryTitleTv.setGravity(Gravity.CENTER);
        thirdCategoryTitleTv.setText(titleName);
        columnsLl.addView(thirdCategoryTitleTv);
    }

    private void initThirdCategoryIv(String bannerUrl, LinearLayout columnsLl) {
        ImageView categoryIv = new ImageView(getContext());
        LinearLayout.LayoutParams categoryIvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mContainerLl.getWidth() / 3);
        categoryIv.setLayoutParams(categoryIvParams);
        ImageUtil.loadImage(getContext(), bannerUrl, categoryIv);
        columnsLl.addView(categoryIv);
    }

    @NonNull
    private LinearLayout initThirdCategoryColumnsContainer(final long thirdCategoryId) {
        LinearLayout columnsLl = new LinearLayout(getContext());
        LinearLayout.LayoutParams columnsParams = new LinearLayout.LayoutParams(mContainerLl.getWidth() / 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        columnsLl.setOrientation(LinearLayout.VERTICAL);
        columnsLl.setLayoutParams(columnsParams);
        columnsLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductListActivity.class);
                intent.putExtra(ProductListActivity.ThirdCategoryId, thirdCategoryId);
                intent.putExtra(ProductListActivity.TopCategoryId, mTopCategory.getId());
                getContext().startActivity(intent);
            }
        });
        return columnsLl;
    }

    @Override
    public void onModelChanged(int action, Object returnBean) {
        mHandler.obtainMessage(action, returnBean).sendToTarget();
    }
}
