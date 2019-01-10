package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.CKSMContact;
import ruiduoyi.com.skyworthpda.model.bean.CKBean;
import ruiduoyi.com.skyworthpda.model.bean.CKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdDetailBean;
import ruiduoyi.com.skyworthpda.presentor.CKSMPresentor;
import ruiduoyi.com.skyworthpda.view.adapter.CKScanAdapter;
import ruiduoyi.com.skyworthpda.view.adapter.CKZSAdapter;


/**
 * Created by Chen on 2018-12-27.
 */


public class CKSMActivity extends BaseScanActivity implements CKSMContact.View, CKScanAdapter.OnItemClickListener {
    private static final String TAG = CKSMActivity.class.getSimpleName();
    public static final String FHDH = "fhd";
    public static final String RECORD_DATA = "recordBean";
    private static final String CANCEL_TYPE_ONE = "one";//取消一个
    private static final String CANCEL_TYPE_ALL = "all";//取消所有
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ck_list)
    RecyclerView ckList;
    @BindView(R.id.scaned_list)
    RecyclerView scanedList;
    @BindView(R.id.tv_fhd_cksmActivity)
    TextView tvFhd;

    @BindView(R.id.btn_tj_cksmActivity)
    Button btnTj;
    @BindView(R.id.btn_cancel_cksmActivity)
    Button btnCancelCksmActivity;

    private CKSMPresentor presentor;
    private ArrayList<FhdDetailBean.UcDataBean> ckData;
    private CKZSAdapter ckAdapter;
    private String fhd = "";
    private ArrayList<CpCodeBean.UcDataBean> scanData;
    private CKScanAdapter scanAdapter;
    private String djbh = "";
    private String key_scanlist;
    private String key_shiplist;
    private CKRecordBean.UcDataBean recordData;


    @Override
    protected void onReceiveCode(String code) {
        if (!"".equals(djbh)) {
            onShowTipsDailog("请提交后再扫描!");
            return;
        }
        presentor.checkRQCODE(fhd, code);
    }
    //瑞多益科技

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cksm);
        ButterKnife.bind(this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        initView();
        presentor = new CKSMPresentor(this, this);
        presentor.loadFhdDetail(fhd);
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
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        fhd = intent.getStringExtra(FHDH);

        recordData = (CKRecordBean.UcDataBean) intent.getSerializableExtra(RECORD_DATA);
        /*if ("".equals(fhd)) {
            *//*Toast.makeText(CKSMActivity.this, "发货单号为空，请重试!", Toast.LENGTH_LONG).show();
            finish();*//*
        }*/

        if (recordData !=null){
            fhd = recordData.getTable1().get(0).getShipout_sn();
            djbh = recordData.getTable1().get(0).getScan_djbh();

        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("出库扫描");
        ckData = new ArrayList<FhdDetailBean.UcDataBean>();
        scanData = new ArrayList<CpCodeBean.UcDataBean>();
        ckAdapter = new CKZSAdapter(ckData);
        scanAdapter = new CKScanAdapter(scanData);
        scanAdapter.setOnItemClickListener(this);
        ckList.setLayoutManager(new LinearLayoutManager(this));
        ckList.setAdapter(ckAdapter);

        scanedList.setLayoutManager(new LinearLayoutManager(this));
        scanedList.setAdapter(scanAdapter);
        tvFhd.setText(fhd);
    }


    @Override
    public void onCheckCpCODESucceed(CpCodeBean.UcDataBean bean) {
        scanData.add(bean);
        scanAdapter.notifyDataSetChanged();
        ckAdapter.addItem(bean);
    }

    @Override
    public void loadFhdDetailSucceed(FhdDetailBean bean) {
        ckData.addAll(bean.getUcData());
        ckAdapter.notifyDataSetChanged();
        if (recordData != null){
            List<CKRecordBean.UcDataBean.Table1Bean> table1 = recordData.getTable1();
            for (CKRecordBean.UcDataBean.Table1Bean table1Bean : table1){
                CpCodeBean.UcDataBean cpBean = new CpCodeBean.UcDataBean();
                cpBean.setBrp_qty(table1Bean.getBrp_qty());
                cpBean.setBrp_pmgg(table1Bean.getBrp_pmgg());
                cpBean.setBrp_qrcode(table1Bean.getBrp_qrcode());
                cpBean.setBrp_unit(table1Bean.getBrp_unit());
                cpBean.setBrp_upn(table1Bean.getBrp_upn());
                cpBean.setBrp_wldm(table1Bean.getBrp_wldm());
                cpBean.setBrp_lotno(table1Bean.getBrp_lotno());
                ckAdapter.addItem(cpBean);
                scanData.add(cpBean);
            }
            scanAdapter.notifyDataSetChanged();
            recordData = null;
        }
    }

    @Override
    public void onCancelCkSucceed(String cancelType, CpCodeBean.UcDataBean bean) {

        if (CKSMActivity.CANCEL_TYPE_ONE.equals(cancelType)){
            ckAdapter.removeItem(bean);
            scanAdapter.removeItem(bean);
        }else if (CKSMActivity.CANCEL_TYPE_ALL.equals(cancelType)) {
            djbh = "";
            for (FhdDetailBean.UcDataBean b:ckData){
                b.setStock_out_quantity("");
            }
            scanData.clear();
            ckAdapter.notifyDataSetChanged();
            scanAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCkSucceed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onCkFail(List<CKBean.UcDataBean> ucData) {
        if (ucData != null && ucData.size()>0){
            djbh = ucData.get(0).getCDjbh();
        }
    }


    @OnClick({R.id.btn_tj_cksmActivity, R.id.btn_cancel_cksmActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tj_cksmActivity: {
                if (scanData.size() == 0) {
                    onShowTipsDailog("请扫描条码后再提交");
                    return;
                }

                Data2 data2 = new Data2();
                List<Data2.Temp> d = new ArrayList<>();
                for (CpCodeBean.UcDataBean b : scanData) {
                    Data2.Temp t = new Data2.Temp();
                    t.setTmxh(b.getBrp_upn());
                    d.add(t);
                }
                data2.setData(d);
                Gson gson = new Gson();
                key_scanlist = gson.toJson(data2);
                //------------
                List<FhdDetailBean.UcDataBean> t = new ArrayList<>();
                Data1 data1 = new Data1();
                for (FhdDetailBean.UcDataBean b:ckData){
                    if (b.getStock_out_quantity()==null || "".equals(b.getStock_out_quantity())){
                        t.add(b);
                    }
                }
                for (FhdDetailBean.UcDataBean b:t){
                    ckData.remove(b);
                }
                data1.setData(ckData);
                key_shiplist = gson.toJson(data1);

                Log.d(TAG, "onViewClicked: key_scanlist" + key_scanlist);
                Log.d(TAG, "onViewClicked: key_shiplist" + key_shiplist);
                AlertDialog delDialog = new AlertDialog.Builder(CKSMActivity.this).setTitle("提示")
                        .setMessage("是否提交?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.ck(key_shiplist, key_scanlist, djbh);
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                delDialog.show();
                break;
            }

            case R.id.btn_cancel_cksmActivity: {
                String tips = djbh.equals("") ? "是否取消?" : "该单据可能已提交到CRM，是否取消?";
                AlertDialog delDialog = new AlertDialog.Builder(CKSMActivity.this).setTitle("提示")
                        .setMessage(tips)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.cancelCk(djbh, null, CKSMActivity.CANCEL_TYPE_ALL);
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                delDialog.show();
                break;
            }
        }
    }

    @Override
    public void onItemClick(final CpCodeBean.UcDataBean bean, int position) {
        AlertDialog delDialog = new AlertDialog.Builder(CKSMActivity.this).setTitle("提示")
                .setMessage("是否删除条码:"+bean.getBrp_qrcode()+"?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!"".equals(djbh)){
                            onShowTipsDailog("该数据可能已提交到CRM,不允许此操作!");
                            return;
                        }
                        presentor.cancelCk("",bean,CKSMActivity.CANCEL_TYPE_ONE);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        delDialog.show();
    }

    private class Data1 {
        private List<FhdDetailBean.UcDataBean> data;

        public List<FhdDetailBean.UcDataBean> getData() {
            return data;
        }

        public void setData(List<FhdDetailBean.UcDataBean> data) {
            this.data = data;
        }
    }

    static class Data2 {
        private List<Temp> data;

        public List<Temp> getData() {
            return data;
        }

        public void setData(List<Temp> data) {
            this.data = data;
        }

        static class Temp{
            private String tmxh;

            public String getTmxh() {
                return tmxh;
            }

            public void setTmxh(String tmxh) {
                this.tmxh = tmxh;
            }
        }
    }
}
