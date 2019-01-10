package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.RKSMContact;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.KwBean;
import ruiduoyi.com.skyworthpda.model.bean.RKBean;
import ruiduoyi.com.skyworthpda.model.bean.RKRecordBean;
import ruiduoyi.com.skyworthpda.presentor.RKSMPresentor;
import ruiduoyi.com.skyworthpda.view.adapter.RKScanAdapter;
import ruiduoyi.com.skyworthpda.view.adapter.RKZSAdapter;


/**
 * Created by Chen on 2018-12-27.
 */

public class RKSMActivity extends BaseScanActivity implements RKSMContact.View, RKScanAdapter.OnItemClickListener {
    private static final String TAG = RKSMActivity.class.getSimpleName();
    private static final String CANCEL_TYPE_ONE = "one";//取消一个
    private static final String CANCEL_TYPE_ALL = "all";//取消所有
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_kw_rksmactivity)
    Spinner spKw;
    @BindView(R.id.rk_list)
    RecyclerView rkList;//扫描汇总的数据
    @BindView(R.id.scaned_list)
    RecyclerView scanedList;//扫描到的数据
    @BindView(R.id.btn_cancelRk_rksmActivity)
    Button btnCancelRk;
    @BindView(R.id.btn_tj_rksmActivity)
    Button btnTj;

    private RKSMPresentor presentor;
    private List<KwBean.UcDataBean> kwData;
    private ArrayAdapter<String> kwAdapter;
    private ArrayList<CpCodeBean.UcDataBean> rkData;
    private ArrayList<CpCodeBean.UcDataBean> scanData;
    private RKZSAdapter rkAdapter;
    private RKScanAdapter scanAdapter;
    private String kwCode = "";
    private String jsonData = "";
    private String djbh = "";

    @Override
    protected void onReceiveCode(String code) {
        if (!"".equals(djbh)) {
            onShowTipsDailog("请提交后再扫描!");
            return;
        }
        presentor.checkRQCODE(code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rksm);
        ButterKnife.bind(this);
        initView();
        presentor = new RKSMPresentor(this, this);
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
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("入库扫描");
        rkData = new ArrayList<CpCodeBean.UcDataBean>();
        scanData = new ArrayList<CpCodeBean.UcDataBean>();
        rkAdapter = new RKZSAdapter(rkData);
        scanAdapter = new RKScanAdapter(scanData);
        scanAdapter.setOnItemClickListener(this);
        rkList.setLayoutManager(new LinearLayoutManager(this));
        rkList.setAdapter(rkAdapter);

        scanedList.setLayoutManager(new LinearLayoutManager(this));
        scanedList.setAdapter(scanAdapter);

        spKw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || kwData == null || kwData.size() == 0) {
                    kwCode = "";
                    return;
                }
                kwCode = kwData.get(position - 1).getCkm_code();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onLoadKwSucceed(List<KwBean.UcDataBean> kwData) {
        this.kwData = kwData;
        List<String> kwDataStr = new ArrayList<>();
        kwDataStr.add("");

       /* KwBean.UcDataBean test = new KwBean.UcDataBean();
        test.setCkm_code("123456789");
        test.setCkm_name("错误的仓位");
        kwData.add(test);*/
        for (KwBean.UcDataBean bean : kwData) {
            kwDataStr.add(bean.getCkm_name());
        }


        kwAdapter = new ArrayAdapter<String>(RKSMActivity.this, R.layout.item_spinner, kwDataStr);
        spKw.setAdapter(kwAdapter);
        presentor.loadRecord();
    }



    @Override
    public void onCheckCpCODESucceed(CpCodeBean.UcDataBean bean) {

        scanAdapter.addItem(bean);
        rkAdapter.addItem(bean);
    }

    @Override
    public void onRkSucceed() {
        //preferenUtil.setString("djbh","");
        djbh = "";
        spKw.setEnabled(true);
        scanData.clear();
        rkData.clear();
        scanAdapter.notifyDataSetChanged();
        rkAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRkFail(List<RKBean.UcDataBean> bean) {
        if (bean != null && bean.size() > 0) {
            djbh = bean.get(0).getCDjbh();
            //preferenUtil.setString("djbh",djbh);
            spKw.setEnabled(false);
        }
    }

    @Override
    public void onCancelRkSucceed(String cancelType, CpCodeBean.UcDataBean bean) {
        if (RKSMActivity.CANCEL_TYPE_ONE.equals(cancelType)){
            rkAdapter.removeItem(bean);
            scanAdapter.removeItem(bean);
        }else if (RKSMActivity.CANCEL_TYPE_ALL.equals(cancelType)) {
            djbh = "";
            spKw.setEnabled(true);
            scanData.clear();
            rkData.clear();
            scanAdapter.notifyDataSetChanged();
            rkAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadRecordSucceed(RKRecordBean.UcDataBean ucData) {
        List<RKRecordBean.UcDataBean.Table1Bean> scan = ucData.getTable1();
        if (scan !=null && scan.size() >0){
            for (RKRecordBean.UcDataBean.Table1Bean b:scan){
                CpCodeBean.UcDataBean cpBean = new CpCodeBean.UcDataBean();
                cpBean.setBrp_wldm(b.getBrp_wldm());
                cpBean.setBrp_upn(b.getBrp_upn());
                cpBean.setBrp_unit(b.getBrp_unit());
                cpBean.setBrp_qrcode(b.getBrp_qrcode());
                cpBean.setBrp_pmgg(b.getBrp_pmgg());
                cpBean.setBrp_qty(b.getBrp_qty());
                djbh = b.getScan_djbh();
                kwCode = b.getScan_ckdm();
                scanAdapter.addItem(cpBean);
                rkAdapter.addItem(cpBean);
            }
            if (kwData != null){
                for (int i=0; i<kwData.size(); i++){
                    KwBean.UcDataBean b = kwData.get(i);
                    if (b.getCkm_code().equals(kwCode)){
                        spKw.setSelection(i+1);
                        spKw.setEnabled(false);
                    }
                }
            }

        }
    }

    @OnClick({R.id.btn_cancelRk_rksmActivity, R.id.btn_tj_rksmActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelRk_rksmActivity: {
                String tips = djbh.equals("")?"是否取消?":"该单据可能已提交到CRM，是否取消?";
                AlertDialog delDialog = new AlertDialog.Builder(RKSMActivity.this).setTitle("提示")
                        .setMessage(tips)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.cancelRk(djbh, null, RKSMActivity.CANCEL_TYPE_ALL);
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
            case R.id.btn_tj_rksmActivity:
                if ("".equals(kwCode)) {
                    onShowTipsDailog("请选择库位");
                    return;
                }

                if (scanData.size() == 0) {
                    onShowTipsDailog("请扫描条码后再提交");
                    return;
                }

                Data data = new Data();
                List<Data.Temp> d = new ArrayList<>();
                for (CpCodeBean.UcDataBean b : scanData) {
                    Data.Temp t = new Data.Temp();
                    t.setTmxh(b.getBrp_upn());
                    d.add(t);
                }

                data.setData(d);
                Gson gson = new Gson();
                jsonData = gson.toJson(data);

                Log.d(TAG, "onViewClicked: jsonData" + jsonData);
                AlertDialog delDialog = new AlertDialog.Builder(RKSMActivity.this).setTitle("提示")
                        .setMessage("是否提交?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.rk(kwCode, jsonData, djbh);
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

    @Override
    public void onItemClick(final CpCodeBean.UcDataBean bean, int position) {
        AlertDialog delDialog = new AlertDialog.Builder(RKSMActivity.this).setTitle("提示")
                .setMessage("是否删除条码:"+bean.getBrp_qrcode()+"?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!"".equals(djbh)){
                            onShowTipsDailog("该数据可能已提交到CRM,不允许此操作!");
                            return;
                        }
                        presentor.cancelRk("",bean,RKSMActivity.CANCEL_TYPE_ONE);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        delDialog.show();
    }


    static class Data {
        private List<Temp> data;

        public List<Temp> getData() {
            return data;
        }

        public void setData(List<Temp> data) {
            this.data = data;
        }

        static class Temp {
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
