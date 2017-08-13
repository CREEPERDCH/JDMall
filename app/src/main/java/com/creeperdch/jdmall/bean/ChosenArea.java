package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import com.creeperdch.jdmall.controller.AddressController;

public class ChosenArea {
    public AddressController.Area tabProvince;
    public AddressController.Area tabCity;
    public AddressController.Area tabArea;

    public ChosenArea(AddressController.Area tabProvince, AddressController.Area tabCity, AddressController.Area tabArea) {
        this.tabProvince = tabProvince;
        this.tabCity = tabCity;
        this.tabArea = tabArea;
    }
}
