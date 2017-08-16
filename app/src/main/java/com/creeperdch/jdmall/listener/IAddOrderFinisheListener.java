package com.creeperdch.jdmall.listener;
/*
 * Created by CREEPER_D on 2017/8/15.
 */

import com.creeperdch.jdmall.bean.RAddOrder;

public interface IAddOrderFinisheListener {

    void onOrderCanel(long oid);

    void onPayWhenReceiveConfirmed(long oid);

    void onPayOnlineConfirmed(RAddOrder mBean);
}
