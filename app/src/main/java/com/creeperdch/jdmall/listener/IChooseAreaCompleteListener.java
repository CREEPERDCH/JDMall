package com.creeperdch.jdmall.listener;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import com.creeperdch.jdmall.controller.AddressController;

public interface IChooseAreaCompleteListener {
    void onAreaChosen(AddressController.Area tabProvince, AddressController.Area tabCity, AddressController.Area tabArea);
}
