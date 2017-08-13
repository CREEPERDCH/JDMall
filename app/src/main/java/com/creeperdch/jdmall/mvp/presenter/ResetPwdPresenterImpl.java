package com.creeperdch.jdmall.mvp.presenter;
/*
 * Created by wwwwy on 2017/8/6.
 */

import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.mvp.model.IResetPwdModel;
import com.creeperdch.jdmall.mvp.model.ResetPwdModellmp;
import com.creeperdch.jdmall.mvp.view.IResetView;

public class ResetPwdPresenterImpl implements IResetPwdPresenter, IResetPwdModel, IResetPwdModel.IPwdResetListener {

    private IResetPwdModel iModel;
    private IResetView mResetView;

    public ResetPwdPresenterImpl(IResetView resetView) {
        this.iModel = new ResetPwdModellmp(this);
        this.mResetView = resetView;
    }

    @Override
    public void resetPwd(String account) {
        mResetView.showProgressDialog();
        iModel.resetPwd(account);
    }

    @Override
    public void onPwdReseted(ResultBean resultBean) {
        mResetView.hideProgressDialog();
        mResetView.dealResetPwdResult(resultBean);
    }
}
