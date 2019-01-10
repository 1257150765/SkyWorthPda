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
 * Created by Chen on 2018/5/8.
 */

public class CKScanAdapter extends RecyclerView.Adapter<CKScanAdapter.CKScanHolder> {


    private OnItemClickListener onItemClickListener;
    private List<CpCodeBean.UcDataBean> data;

    public CKScanAdapter(List<CpCodeBean.UcDataBean> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CKScanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CKScanHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan_cksmactivity, parent, false));
    }

    @Override
    public void onBindViewHolder(CKScanHolder holder, final int position) {
        final CpCodeBean.UcDataBean bean = data.get(position);
        holder.tvNo.setText(""+(position+1));
        holder.tvPh.setText(bean.getBrp_lotno());
        holder.tvPmgg.setText(bean.getBrp_pmgg());
        holder.tvQty.setText(""+bean.getBrp_qty());
        holder.tvTmxh.setText(bean.getBrp_upn());
        holder.tvWldm.setText(bean.getBrp_wldm());
        holder.tvUnit.setText(bean.getBrp_unit());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(bean, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(CpCodeBean.UcDataBean bean) {
        data.add(bean);
        notifyDataSetChanged();
    }

    public void removeItem(CpCodeBean.UcDataBean bean) {
        data.remove(bean);
        notifyDataSetChanged();
    }

    class CKScanHolder extends RecyclerView.ViewHolder {

        View content;
        @BindView(R.id.tv_no_item_scan_cksmactivity)
        TextView tvNo;
        @BindView(R.id.tv_tmxh_item_scan_cksmactivity)
        TextView tvTmxh;
        @BindView(R.id.tv_wldm_item_scan_cksmactivity)
        TextView tvWldm;
        @BindView(R.id.tv_qty_item_scan_cksmactivity)
        TextView tvQty;
        @BindView(R.id.tv_pmgg_item_scan_cksmactivity)
        TextView tvPmgg;
        @BindView(R.id.tv_ph_item_scan_cksmactivity)
        TextView tvPh;
        @BindView(R.id.tv_unit_item_scan_cksmactivity)
        TextView tvUnit;
        public CKScanHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CpCodeBean.UcDataBean bean, int position);
    }
}
