package com.creeperdch.jdmall.mvp.view;
/*
 * Created by wwwwy on 2017/8/6.
 */

import com.creeperdch.jdmall.bean.ResultBean;

public interface IResetView {

    public void showProgressDialog();

    public void hideProgressDialog();

    public void dealResetPwdResult(ResultBean resultBean);
}
