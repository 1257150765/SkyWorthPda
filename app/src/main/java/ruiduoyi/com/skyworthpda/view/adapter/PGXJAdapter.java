package ruiduoyi.com.skyworthpda.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.model.bean.PGXJRecordBean;

/**
 * Created by Chen on 2018/5/14.
 */

public class PGXJAdapter extends RecyclerView.Adapter<PGXJAdapter.PGXJHolder> {

    private List<PGXJRecordBean.UcDataBean> data;

    public PGXJAdapter(List<PGXJRecordBean.UcDataBean> data) {
        this.data = data;
    }

    @Override
    public PGXJHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PGXJHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pgxj, parent, false));
    }

    @Override
    public void onBindViewHolder(PGXJHolder holder, int position) {

        PGXJRecordBean.UcDataBean pgxjBean = data.get(position);

        holder.tvZw.setText(pgxjBean.getXjd_zwdm());
        holder.tvWl.setText(pgxjBean.getXjd_wldm());
        holder.tvTdl.setText(pgxjBean.getXjd_dywl());
        holder.tvJd.setText(pgxjBean.getXjd_chkms());
        if ("".equals(pgxjBean.getXjd_chkms())){
            holder.content.setBackgroundColor(Color.WHITE);
        }else {
            holder.content.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PGXJHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_zw_item_pgxj)
        TextView tvZw;
        @BindView(R.id.tv_wl_item_pgxj)
        TextView tvWl;
        @BindView(R.id.tv_tdl_item_pgxj)
        TextView tvTdl;
        @BindView(R.id.tv_jd_item_pgxj)
        TextView tvJd;
        View content;
        public PGXJHolder(View itemView) {
            super(itemView);
            content = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
