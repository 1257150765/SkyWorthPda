package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.PGXJContact;
import ruiduoyi.com.skyworthpda.model.bean.PGXJRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.presentor.PGXJPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.view.adapter.PGXJAdapter;

/**
 * Created by Chen on 2018/5/14.
 */

/**
 * 品管巡检
 */
public class PGXJActivity extends BaseScanActivity implements PGXJContact.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_pgxjactivity)
    Spinner spXb;
    @BindView(R.id.et_jyzw_pgxjactivity)
    EditText etJyzw;
    @BindView(R.id.et_zwcx_pgxjactivity)
    EditText etZwcx;
    @BindView(R.id.et_code_pgxjactivity)
    EditText etCode;
    @BindView(R.id.rv_recycler_pgxjactivity)
    RecyclerView rvRecycler;
    private ArrayAdapter<String> xbAdapter;
    private List<XbBean.UcDataBean> xbData;
    private PGXJContact.Presentor presentor;
    private XbBean.UcDataBean bean;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgxj);
        ButterKnife.bind(this);
        initView();
        presentor = new PGXJPresentor(this,this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("品管巡检");
        spXb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null == xbData || position == 0) {
                    bean = null;
                    return;
                }
                bean = xbData.get(position-1);
                //presentor.loadData(xbData.get(position));
                presentor.loadHaveRecord(bean.getXbm_xbdm());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





    @Override
    protected void onReceiveCode(String code) {
        presentor.checkRQCODE(code);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadXbSucceed(List<XbBean.UcDataBean> xbData) {
        this.xbData = xbData;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        for (XbBean.UcDataBean bean : xbData){
            xbDataStr.add(bean.getXbm_xbdm());
        }
        xbAdapter = new ArrayAdapter<String>(PGXJActivity.this, R.layout.item_spinner, xbDataStr);
        spXb.setAdapter(xbAdapter);
    }

    @Override
    public void onLoadHaveRecordSucceed(boolean b, String msg) {
        if (b){
            etZwcx.setText(msg);
            if (null == bean){
                showSnakeBar("请选择线别");
                return;
            }
            presentor.loadRecord(bean.getXbm_xbdm());
        }else {
            if (dialog == null){
                dialog = new AlertDialog.Builder(this)
                        .setMessage(msg)
                        .setTitle("提示")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.pgxj(Config.PGXJ_TYPE_ADD,bean.getXbm_xbdm(),"","");
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
            }
            dialog.show();

        }
    }

    @Override
    public void onLoadRecord(List<PGXJRecordBean.UcDataBean> data) {
        PGXJAdapter adapter = new PGXJAdapter(data);
        rvRecycler.setAdapter(adapter);
        rvRecycler.setLayoutManager(new LinearLayoutManager(PGXJActivity.this));
    }

    @Override
    public void onCheckQRCODESucceed(String type, String xb, String qrcode, String wldm) {
        presentor.pgxj(Config.PGXJ_TYPE_ADD,xb,qrcode,wldm);
    }
}
