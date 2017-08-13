package com.creeperdch.jdmall.mvp.model;
/*
 * Created by wwwwy on 2017/8/6.
 */

import com.creeperdch.jdmall.bean.ResultBean;

public interface IResetPwdModel {

    public void resetPwd(String name);

    public static interface IPwdResetListener {

        public void onPwdReseted(ResultBean resultBean);
    }
}
