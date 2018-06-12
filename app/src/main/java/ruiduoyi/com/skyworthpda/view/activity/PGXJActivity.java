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
    private List<PGXJRecordBean.UcDataBean> recordData = new ArrayList<>();
    private PGXJAdapter adapter;
    private String zw = "";


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
                    clearData();
                    return;
                }
                clearData();
                bean = xbData.get(position-1);
                etZwcx.setText(bean.getXbm_zwcxdm());
                //presentor.loadData(xbData.get(position));
                presentor.loadHaveRecord(bean.getXbm_xbdm());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onExecuteSucceed() {
        super.onExecuteSucceed();
        etCode.setText("");
        etCode.requestFocus();

    }



    @Override
    public void onExecuteFalse() {
        super.onExecuteFalse();
        etCode.requestFocus();
        etCode.setText("");
    }

    private void clearData() {
        /*删除之前的数据*/
        etJyzw.setText("");
        etZwcx.setText("");

        if (adapter != null && recordData != null) {
            adapter.notifyDataSetChanged();
            recordData.clear();
        }
    }


    @Override
    protected void onReceiveCode(String code) {
        if (bean == null || null == recordData || recordData.size() == 0){
            showSnakeBar("无巡检记录");
            return;
        }
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
            etCode.requestFocus();
            presentor.loadRecord(bean.getXbm_xbdm());
        }else {
            if (dialog == null){
                dialog = new AlertDialog.Builder(this)
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
            String tips = "系统检测到【线别:" + bean.getXbm_xbdm() + "】巡检记录已完成，是需要新增新的巡检记录？";
            dialog.setMessage(tips);
            dialog.show();
        }
    }

    @Override
    public void onLoadRecord(List<PGXJRecordBean.UcDataBean> data) {
        this.recordData = data;
        adapter = new PGXJAdapter(data);
        rvRecycler.setAdapter(adapter);
        rvRecycler.setLayoutManager(new LinearLayoutManager(PGXJActivity.this));
        loadZw();
    }

    /**
     * 找到未巡检的站位
     */
    private void loadZw() {
        if (recordData == null){
            return;
        }
        for (int i=0; i<recordData.size(); i++) {
            PGXJRecordBean.UcDataBean bean = recordData.get(i);
            if (bean.getXjd_chkms().equals("")){
                etJyzw.setText(bean.getXjd_zwdm());
                zw = bean.getXjd_zwdm();
                rvRecycler.scrollToPosition(i);
                return;
            }
        }
    }

    @Override
    public void onCheckQRCODESucceed(String type,String qrcode, String wldm) {
        presentor.pgxj(Config.PGXJ_TYPE_SCAN,bean.getXbm_xbdm(),qrcode,wldm);
    }

    @Override
    public void onPGXJSucceed(String key_type, String data) {

        if (bean == null){
            return;
        }
        presentor.loadRecord(bean.getXbm_xbdm());
    }
}
