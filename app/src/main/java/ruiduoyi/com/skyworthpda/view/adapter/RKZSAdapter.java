package ruiduoyi.com.skyworthpda.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;


/**
 * Created by Chen on 2018/5/14.
 */

public class RKZSAdapter extends RecyclerView.Adapter<RKZSAdapter.RKZSHolder> {



    private List<CpCodeBean.UcDataBean> data;

    public RKZSAdapter(List<CpCodeBean.UcDataBean> data) {
        this.data = data;
    }

    @Override
    public RKZSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RKZSHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zs_rksmactivity, parent, false));
    }

    @Override
    public void onBindViewHolder(RKZSHolder holder, int position) {

        CpCodeBean.UcDataBean bean = data.get(position);
        holder.tvNo.setText(""+(position+1));
        holder.tvWldm.setText(bean.getBrp_wldm());
        holder.tvPmgg.setText(bean.getBrp_pmgg());
        holder.tvQty.setText(""+bean.getBrp_qty());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(CpCodeBean.UcDataBean bean) {
        for (CpCodeBean.UcDataBean b : data) {
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
        notifyDataSetChanged();
    }

    public void removeItem(CpCodeBean.UcDataBean bean) {
        CpCodeBean.UcDataBean temp= null;
        for (CpCodeBean.UcDataBean b : data) {
            if (b.getBrp_wldm().equals(bean.getBrp_wldm())) {
                double qty = b.getBrp_qty();
                b.setBrp_qty(qty - bean.getBrp_qty());
                if (b.getBrp_qty()<=0){
                    temp = b;
                }
                break;
            }
        }
        if (temp!=null){
            data.remove(temp);
        }
        notifyDataSetChanged();
    }

    public class RKZSHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_no_item_rk_rksmactivity)
        TextView tvNo;
        @BindView(R.id.tv_wldm_item_rk_rksmactivity)
        TextView tvWldm;
        @BindView(R.id.tv_pmgg_item_rk_rksmactivity)
        TextView tvPmgg;
        @BindView(R.id.tv_qty_item_rk_rksmactivity)
        TextView tvQty;
        View content;

        public RKZSHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
