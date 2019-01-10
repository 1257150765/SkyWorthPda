package ruiduoyi.com.skyworthpda.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdBean;


/**
 * Created by Chen on 2018/5/14.
 */

public class FhdAdapter extends RecyclerView.Adapter<FhdAdapter.FhdHolder> {


    private static final String TAG = FhdAdapter.class.getSimpleName();
    private List<FhdBean.UcDataBean> data;
    private OnItemClickListener listener;

    public FhdAdapter(List<FhdBean.UcDataBean> data) {
        this.data = data;
    }

    @Override
    public FhdHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FhdHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fhdactivity, parent, false));
    }

    @Override
    public void onBindViewHolder(final FhdHolder holder, final int position) {

        final FhdBean.UcDataBean bean = data.get(position);
        holder.tvShipDjbh.setText(bean.getShip_djbh());
        holder.tvShipDay.setText(bean.getShipping_day());
        holder.tvStoreNo.setText(bean.getStore_no());
        holder.tvConsignee.setText(bean.getConsignee());
        holder.tvAddress.setText(bean.getAddress());
        holder.tvDelevery.setText(bean.getDelivery());
        holder.tvDriverInfoCarnum.setText(bean.getCar_number());
        holder.tvDriverInfoMobile.setText(bean.getDriver_info_mobile());
        holder.tvDriverInfoName.setText(bean.getDriver_info_name());
        holder.tvPhone.setText(bean.getPhone());
        holder.tvShippingMethod.setText(bean.getShipping_method());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: listener"+listener);
                if (listener != null){
                    listener.onItemClick(bean,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(CpCodeBean.UcDataBean bean) {
        /*for (CpCodeBean.UcDataBean b : data) {
            if (b.getBrp_wldm().equals(bean.getBrp_wldm())) {
                double qty = b.getBrp_qty();
                b.setBrp_qty(qty + bean.getBrp_qty());
                notifyDataSetChanged();
                return;
            }
        }
        CpCodeBean.UcDataBean cloneBean = new CpCodeBean.UcDataBean();
        cloneBean.setBrp_qty(bean.getBrp_qty());
        cloneBean.setBrp_lotno(bean.getBrp_lotno());
        cloneBean.setBrp_pmgg(bean.getBrp_pmgg());
        cloneBean.setBrp_qrcode(bean.getBrp_qrcode());
        cloneBean.setBrp_unit(bean.getBrp_unit());
        cloneBean.setBrp_upn(bean.getBrp_upn());
        cloneBean.setBrp_wldm(bean.getBrp_wldm());
        data.add(cloneBean);
        notifyDataSetChanged();*/
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(FhdBean.UcDataBean bean, int position);
    }
    public class FhdHolder extends RecyclerView.ViewHolder {

        View content;
        @BindView(R.id.tv_ship_djbh_fhdActivity)
        TextView tvShipDjbh;
        @BindView(R.id.tv_ship_day_fhdActivity)
        TextView tvShipDay;
        @BindView(R.id.tv_store_no_fhdActivity)
        TextView tvStoreNo;
        @BindView(R.id.tv_consignee_fhdActivity)
        TextView tvConsignee;
        @BindView(R.id.tv_phone_fhdActivity)
        TextView tvPhone;
        @BindView(R.id.tv_address_fhdActivity)
        TextView tvAddress;
        @BindView(R.id.tv_shipping_method_fhdActivity)
        TextView tvShippingMethod;
        @BindView(R.id.tv_delevery_fhdActivity)
        TextView tvDelevery;
        @BindView(R.id.tv_driver_info_name_fhdActivity)
        TextView tvDriverInfoName;
        @BindView(R.id.tv_driver_info_mobile_fhdActivity)
        TextView tvDriverInfoMobile;
        @BindView(R.id.tv_driver_info_carnum_fhdActivity)
        TextView tvDriverInfoCarnum;
        public FhdHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
