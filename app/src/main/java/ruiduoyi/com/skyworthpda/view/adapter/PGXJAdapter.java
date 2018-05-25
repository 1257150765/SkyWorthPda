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
import ruiduoyi.com.skyworthpda.model.bean.PGXJBean;

/**
 * Created by Chen on 2018/5/14.
 */

public class PGXJAdapter extends RecyclerView.Adapter<PGXJAdapter.PGXJHolder> {

    private List<PGXJBean> data;

    public PGXJAdapter(List<PGXJBean> data) {
        this.data = data;
    }

    @Override
    public PGXJHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PGXJHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pgxj, parent, false));
    }

    @Override
    public void onBindViewHolder(PGXJHolder holder, int position) {
        PGXJBean pgxjBean = data.get(position);
        holder.tvZw.setText(pgxjBean.getZw());
        holder.tvWl.setText(pgxjBean.getWl());
        holder.tvTdl.setText(pgxjBean.getTdl());
        holder.tvJd.setText(pgxjBean.getJd());
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
        public PGXJHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
