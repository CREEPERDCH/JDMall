package com.creeperdch.jdmall.fragment;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.TopCategoryAdapter;
import com.creeperdch.jdmall.bean.TopCategoryBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.CateGoryController;
import com.creeperdch.jdmall.ui.SubCategoryView;

import java.util.List;

public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView mTopCategoryLv;
    private SubCategoryView mSubcategory;
    private TopCategoryAdapter mTopCategoryAdapter;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.TOP_CATEGORY_ACTION:
                loadTopCategory((List<TopCategoryBean>) msg.obj);
                break;
        }
    }

    private void loadTopCategory(List<TopCategoryBean> datas) {
        mTopCategoryAdapter.setDatas(datas);
        mTopCategoryAdapter.notifyDataSetChanged();
        if (datas.size() > 0) {
            mTopCategoryLv.performItemClick(null, 0, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_category, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        mController.sendAsyncMessage(IDiyMessage.TOP_CATEGORY_ACTION);
    }

    @Override
    protected void initController() {
        mController = new CateGoryController(getContext());
        mController.setListener(this);
    }

    private void initView(View view) {
        mTopCategoryLv = (ListView) view.findViewById(R.id.top_category_lv);
        mTopCategoryAdapter = new TopCategoryAdapter(getActivity());
        mTopCategoryLv.setAdapter(mTopCategoryAdapter);
        mTopCategoryLv.setOnItemClickListener(this);
        mSubcategory = (SubCategoryView) view.findViewById(R.id.subcategory);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mTopCategoryAdapter.tabItem(position);
        TopCategoryBean bean = mTopCategoryAdapter.getItem(position);
        mSubcategory.onShow(bean);
    }
}
