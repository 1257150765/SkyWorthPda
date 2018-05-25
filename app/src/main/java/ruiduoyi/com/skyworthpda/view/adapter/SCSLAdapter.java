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
import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;

/**
 * Created by Chen on 2018/5/8.
 */

public class SCSLAdapter extends RecyclerView.Adapter<SCSLAdapter.SCSLHolder> {
    private OnItemClickListener onItemClickListener;
    private List<SCSLBean> data;

    public SCSLAdapter(List<SCSLBean> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SCSLHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SCSLHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scsl, parent, false));
    }

    @Override
    public void onBindViewHolder(SCSLHolder holder, final int position) {
        holder.tvZw.setText(data.get(position).getZw());
        holder.tvWl.setText(data.get(position).getWl());
        holder.tvTdl.setText(data.get(position).getTdl());
        holder.tvYl.setText(data.get(position).getYl());
        holder.tvSysl.setText(data.get(position).getSyyl());
        holder.tvKdbs.setText(data.get(position).getKdbs());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener){
                    onItemClickListener.onItemClick(data.get(position),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void addItem(List<SCSLBean> data){
        for (SCSLBean bean:data) {
            data.add(0,bean);
            notifyItemInserted(0);
        }

    }
    class SCSLHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_zw_item_scsl)
        TextView tvZw;
        @BindView(R.id.tv_wl_item_scsl)
        TextView tvWl;
        @BindView(R.id.tv_tdl_item_scsl)
        TextView tvTdl;
        @BindView(R.id.tv_yl_item_scsl)
        TextView tvYl;
        @BindView(R.id.tv_sysl_item_scsl)
        TextView tvSysl;
        @BindView(R.id.tv_kdbs_item_scsl)
        TextView tvKdbs;
        View content;
        public SCSLHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
    interface OnItemClickListener{
        void onItemClick(SCSLBean scslBean,int position);
    }
}
