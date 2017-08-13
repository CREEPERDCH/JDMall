package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.CommentCountBean;
import com.creeperdch.jdmall.bean.CommentDetailsbean;
import com.creeperdch.jdmall.bean.GoodCommentBean;
import com.creeperdch.jdmall.bean.ProductInfoBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SAdd2ShopcarParams;
import com.creeperdch.jdmall.consf.CommentType;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDetailsController extends BaseController {
    public ProductDetailsController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.COMMENT_COUNT_ACTION:
                onModelChanged(action, loadCommentCount((Long) values[0]));
                break;
            case IDiyMessage.COMMENT_ACTION:
                onModelChanged(action, loadComment((Long) values[0], (Integer) values[1]));
                break;
            case IDiyMessage.PRODUCT_INFO_ACTION:
                onModelChanged(action, loadProductInfo((Long) values[0]));
                break;
            case IDiyMessage.PRODUCT_GOOD_COMMENT_ACTION:
                onModelChanged(action, loadGoodComments((Long) values[0]));
                break;
            case IDiyMessage.ADD2SHOPCAR_ACTION:
                onModelChanged(action, add2Shocar((SAdd2ShopcarParams) values[0]));
                break;
        }
    }

    private CommentCountBean loadCommentCount(long pid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("productId", pid + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.COMMENT_COUNT_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), CommentCountBean.class);
        }
        return null;
    }

    private List<CommentDetailsbean> loadComment(long pid, long commentType) {
        HashMap<String, String> paramsMap = new HashMap<>();
        //评论的类型有:全部评论(0) 好评(1) 中评(2) 差评(3)  有图(0+hasImgCom=true)
        paramsMap.put("productId", pid + "");
        if (commentType != CommentType.TYPE_HAS_IMG) {
            paramsMap.put("type", commentType + "");
        } else {
            paramsMap.put("type", CommentType.TYPE_ALL + "");
            paramsMap.put("hasImgCom", "true");
        }
        String jsonStr = HttpUtil.doPost(HttpConstant.COMMENT_DETAILS_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), CommentDetailsbean.class);
        }
        return new ArrayList<>();
    }

    private ProductInfoBean loadProductInfo(Long pid) {
        String jsonStr = HttpUtil.doGet(HttpConstant.PRODUCT_INFO_URL + "?id=" + pid);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), ProductInfoBean.class);
        }
        return null;
    }

    private List<GoodCommentBean> loadGoodComments(Long pid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("productId", pid + "");
        paramsMap.put("type", "1");
        String jsonStr = HttpUtil.doPost(HttpConstant.PRODUCT_GOOD_COMMENTS_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), GoodCommentBean.class);
        }
        return new ArrayList<>();
    }

    private Object add2Shocar(SAdd2ShopcarParams paramsBean) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", paramsBean.userId + "");
        paramsMap.put("productId", paramsBean.productId + "");
        paramsMap.put("buyCount", paramsBean.buyCount + "");
        paramsMap.put("pVersion", paramsBean.pVersion);
        String jsonStr = HttpUtil.doPost(HttpConstant.ADD2SHOPCAR_ACTION, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}
