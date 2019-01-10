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
import ruiduoyi.com.skyworthpda.model.bean.FhdDetailBean;


/**
 * Created by Chen on 2018/5/14.
 */

public class CKZSAdapter extends RecyclerView.Adapter<CKZSAdapter.CKZSHolder> {


    private static final String TAG = CKZSAdapter.class.getSimpleName();
    private List<FhdDetailBean.UcDataBean> data;

    public CKZSAdapter(List<FhdDetailBean.UcDataBean> data) {
        this.data = data;
    }

    @Override
    public CKZSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CKZSHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zs_cksmactivity, parent, false));
    }

    @Override
    public void onBindViewHolder(CKZSHolder holder, int position) {

        FhdDetailBean.UcDataBean bean = data.get(position);
        holder.tvNo.setText("" + (position + 1));
        holder.tvWldm.setText(bean.getVonder_code());
        holder.tvPmgg.setText(bean.getItm_pmgg());
        holder.tvPlanqty.setText("" + bean.getShip_plan_quantity());
        holder.tvQty.setText(""+bean.getStock_out_quantity()==null?"0.0":bean.getStock_out_quantity());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(CpCodeBean.UcDataBean bean) {

        for (FhdDetailBean.UcDataBean b : data) {
            Log.d(TAG, "addItem: out:"+b.getStock_out_quantity());
            Log.d(TAG, "addItem: plan:"+b.getShip_plan_quantity());
            if (b.getVonder_code().equals(bean.getBrp_wldm())&& !b.getStock_out_quantity().equals(""+b.getShip_plan_quantity())) {
                String stock_out_quantity = b.getStock_out_quantity();
                if (stock_out_quantity == null || "".equals(stock_out_quantity)){
                    stock_out_quantity = "0.0";
                }
                float qty = 0.0f;
                try {
                    qty = Float.parseFloat(stock_out_quantity);
                }catch (Exception e){
                    e.printStackTrace();
                }
                b.setStock_out_quantity(""+(qty+bean.getBrp_qty()));
                notifyDataSetChanged();
                return;
            }
        }

    }

    public void removeItem(CpCodeBean.UcDataBean bean) {

        for (FhdDetailBean.UcDataBean b : data) {

            if (b.getVonder_code().equals(bean.getBrp_wldm())&&!"0.0".equals(b.getStock_out_quantity())) {
                String stock_out_quantity = b.getStock_out_quantity();
                if (stock_out_quantity == null || "".equals(stock_out_quantity)){
                    stock_out_quantity = "0.0";
                }
                float qty = 0.0f;
                try {
                    qty = Float.parseFloat(stock_out_quantity);
                }catch (Exception e){
                    e.printStackTrace();
                }
                b.setStock_out_quantity(""+(qty-bean.getBrp_qty()));
                notifyDataSetChanged();
                break;
            }
        }
    }

    public class CKZSHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_planqty_item_ck_cksmactivity)
        TextView tvPlanqty;
        @BindView(R.id.tv_no_item_ck_cksmactivity)
        TextView tvNo;
        @BindView(R.id.tv_wldm_item_ck_cksmactivity)
        TextView tvWldm;
        @BindView(R.id.tv_pmgg_item_ck_cksmactivity)
        TextView tvPmgg;
        @BindView(R.id.tv_qty_item_ck_cksmactivity)
        TextView tvQty;
        View content;

        public CKZSHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
