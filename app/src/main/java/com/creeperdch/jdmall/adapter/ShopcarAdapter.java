package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.ShopCarBean;
import com.creeperdch.jdmall.listener.IShopcarDeleteListener;
import com.creeperdch.jdmall.utils.ImageUtil;

import java.util.ArrayList;

public class ShopcarAdapter extends JDBaseAdapter<ShopCarBean> {

    private IShopcarDeleteListener mListener;

    public void setListener(IShopcarDeleteListener listener) {
        this.mListener = listener;
    }

    public ShopcarAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shopcars_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShopCarBean bean = mDatas.get(position);
        ImageUtil.loadImage(mContext, bean.getPimageUrl(), holder.productIv);
        holder.nameTv.setText(bean.getPname());
        holder.priceTv.setText("$ " + bean.getPprice());
        holder.buyCountTv.setText("x" + bean.getBuyCount());
        holder.cbx.setChecked(bean.isChecked);

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onShopcarDeleted(bean.getId());
                }
            }
        });
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : null;
    }

    /**
     * 用户点击某个item
     */
    public void tabItem(int position) {
        ShopCarBean bean = mDatas.get(position);
        bean.isChecked = !bean.isChecked;
        mDatas.set(position, bean);
        notifyDataSetChanged();
    }

    /**
     * 判断是否每个item都显示
     */
    public boolean isAllChecked() {
        boolean flag = true;
        for (ShopCarBean bean : mDatas) {
            if (!bean.isChecked) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 设置所有的item是否全选
     *
     * @param checked 这个参数是用来告诉用户 全选按钮的选中状态的
     */
    public void checkAll(boolean checked) {
        for (ShopCarBean bean : mDatas) {
            bean.isChecked = checked;
        }
        notifyDataSetChanged();
    }

    /**
     * 获取选中商品的总价
     */
    public double getCheckedTotalPrice() {
        double result = 0;
        for (ShopCarBean bean : mDatas) {
            if (bean.isChecked) {
                result += bean.getPprice() + bean.getBuyCount();
            }
        }
        return result;
    }

    /**
     * 获取选中商品的个数
     */
    public int getCheckedTotalCount() {
        int result = 0;
        for (ShopCarBean bean : mDatas) {
            if (bean.isChecked) {
                result++;
            }
        }
        return result;
    }

    /**
     * 删除某个Item
     */
    public void delItem(long delShopcarId) {
        ShopCarBean currentDelBean = null;
        for (ShopCarBean bean : mDatas) {
            if (bean.getId() == delShopcarId) {
                currentDelBean = bean;
            }
        }
        if (currentDelBean != null) {
            mDatas.remove(currentDelBean);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取选中商品列表
     */
    public ArrayList<ShopCarBean> getCheckedListData() {
        ArrayList<ShopCarBean> result = new ArrayList<>();
        for (ShopCarBean bean : mDatas) {
            if (bean.isChecked) {
                result.add(bean);
            }
        }
        return result;
    }

    public static class ViewHolder {
        View rootView;
        CheckBox cbx;
        ImageView productIv;
        TextView nameTv;
        TextView versionTv;
        TextView priceTv;
        TextView buyCountTv;
        TextView deleteProduct;

        ViewHolder(View rootView) {
            this.rootView = rootView;
            this.cbx = (CheckBox) rootView.findViewById(R.id.cbx);
            this.productIv = (ImageView) rootView.findViewById(R.id.product_iv);
            this.nameTv = (TextView) rootView.findViewById(R.id.name_tv);
            this.versionTv = (TextView) rootView.findViewById(R.id.version_tv);
            this.priceTv = (TextView) rootView.findViewById(R.id.price_tv);
            this.buyCountTv = (TextView) rootView.findViewById(R.id.buyCount_tv);
            this.deleteProduct = (TextView) rootView.findViewById(R.id.delete_product);
        }
    }
}
