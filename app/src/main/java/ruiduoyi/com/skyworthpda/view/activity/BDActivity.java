package ruiduoyi.com.skyworthpda.view.activity;

/**
 * Created by Chen on 2018/6/13.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.BDContact;
import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.presentor.BDPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

/**
 * 压缩机绑定，控制器/电器盒绑定,(简称绑定)
 */
public class BDActivity extends BaseStatuScanActivity implements View.OnFocusChangeListener, BDContact.View {

    private static final String CANCEL = "CANCEL";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_tv7_bdActivity)
    TextView tvTv7;
    @BindView(R.id.tv_tv8_bdActivity)
    TextView tvTv8;

    @BindView(R.id.et_edit7_bdActivity)
    EditText etEdit7;
    @BindView(R.id.et_edit8_bdActivity)
    EditText etEdit8;
    @BindView(R.id.sp_xbSp_bdActivity)
    Spinner spXbSp;
    @BindView(R.id.tv_tips_bdActivity)
    TextView tvTips;
    @BindView(R.id.tv_gdh_bdActivity)
    TextView tvGdh;
    @BindView(R.id.tv_cpxh_bdActivity)
    TextView tvCpxh;
    @BindView(R.id.tv_pmgg_bdActivity)
    TextView tvPmgg;
    @BindView(R.id.tv_ddsl_bdActivity)
    TextView tvDdsl;
    @BindView(R.id.tv_trsl_bdActivity)
    TextView tvTrsl;
    private EditText focusEditText;
    private String startTypeName;
    private String startTypeCode;
    private String tips = "";
    private BDPresentor presentor;
    private ArrayAdapter<String> xbAdapter;
    private List<XbBean.UcDataBean> xbData;
    private XbBean.UcDataBean bean;
    private String mesCode = "";
    private String bdType = "";
    private static final String TYPE_ZZYSJ = "ZZYSJ";
    private static final String TYPE_ZZKZQ = "ZZGXJC";
    private static final String TYPE_ZZDZJ = "ZZDZJ";
    //private boolean isCancel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bdactivity);
        ButterKnife.bind(this);
        initView();
        presentor = new BDPresentor(this, this);
    }

    @Override
    protected void initView() {
        startTypeName = getIntent().getStringExtra(Config.ACTIVITY_START_TYPE_NAME);
        startTypeCode = getIntent().getStringExtra(Config.ACTIVITY_START_TYPE_CODE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("总装车间-" + startTypeName);
        spXbSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //因为不要默认值，所有每个下拉框都加了一个空的选项，
                // 用户一定选择后才能操作（防止用户错选），所以第一个框是空的，不加载数据
                //其他地方的下拉框也以此类推
                if (null == xbData || position == 0) {
                    bean = null;
                    clearData();
                    return;
                }
                clearData();
                bean = xbData.get(position - 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bean = xbData.get(0);
            }
        });
        switch (startTypeCode) {
            case Config.PERMISSION_SMTZZ_YSJBD_CODE:
                tvTv7.setText("MES条码:");
                bdType = TYPE_ZZYSJ;
                tvTv8.setText("压缩机条码:");
                tips = "*提示: 请先扫描MES条码, 然后扫描压缩机条码.";
                tvTips.setText(tips);
                break;
            case Config.PERMISSION_SMTZZ_KZQDQHBD_CODE:
                bdType = TYPE_ZZKZQ;
                tvTv7.setText("MES条码:");
                tvTv8.setText("控制器条码:");
                tips = "*提示: 请先扫描MES条码, 然后扫描控制器条码.";
                tvTips.setText(tips);

                break;
            case Config.PERMISSION_SMTZZ_DZJC_CODE:
                bdType = TYPE_ZZDZJ;
                tvTv7.setText("创维条码:");
                tvTv8.setText("NG条码:");
                tips = "*提示: 请先扫描创维条码, 然后扫描品质条码.";
                tvTips.setText(tips);
                break;
        }
        etEdit7.setOnFocusChangeListener(this);
        etEdit8.setOnFocusChangeListener(this);
    }

    private void clearData() {
        etEdit7.setText("");
        etEdit8.setText("");
        etEdit7.requestFocus();
    }


    @Override
    protected void onReceiveCode(String code) {
        if (null == bean) {
            showSnakeBar("请先选择线别");
            return;
        }
        presentor.bd(bean.getXbm_xbdm(),bdType,code);
        /*
        if (focusEditText.getId() == etEdit7.getId()) {
            presentor.bd(bean.getXbm_xbdm(), bdType, edit7Text);
        } else if (focusEditText.getId() == etEdit8.getId()) {
            presentor.bd(bean.getXbm_xbdm(),bdType,edit8Text);
        }*/
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
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (v instanceof EditText) {
                focusEditText = (EditText) v;
            }

        }
    }

    /**
     * 成功加载总装线别
     * @param xbData
     */
    @Override
    public void onLoadZZXBSucceed(List<XbBean.UcDataBean> xbData) {
        this.xbData = xbData;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        for (XbBean.UcDataBean bean : xbData) {
            xbDataStr.add(bean.getXbm_xbdm());
        }
        xbAdapter = new ArrayAdapter<String>(BDActivity.this, R.layout.item_spinner, xbDataStr);
        spXbSp.setAdapter(xbAdapter);
    }



    @Override
    public void onBDFalse() {

    }

    /**
     * 2个都绑定绑定成功
     * @param code
     */
    @Override
    public void onBDSucceed(String code) {
        //已经取消了扫描记录（绑定成功或者取消绑定都会回调这个方法）
        /*isCancel = true;
        //有可能是取消之前的扫描记录
        if (CANCEL.equals(code)){
            etEdit8.setText("");
            onExecuteSucceed();
            return;
        }*/
        etEdit8.setText(code);
        if ("OK".equals(code.toUpperCase())){
            SoundPoolUtil.playOK();
        }else if ("NG".equals(code.toUpperCase())){
            SoundPoolUtil.playBlp();
        }
        onExecuteSucceed();
        clearData();
    }

    /**
     * 成功绑定一个
     * @param ucData
     */
    @Override
    public void onBDSucceed(final MesBean.UcDataBean ucData) {
        //isCancel = false;
        if (!tvGdh.getText().toString().equals(ucData.getBrp_djbh())){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("扫描的MES条码与上次扫描的工单号不一致, 请确认工单是否正确？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            spXbSp.setEnabled(false);
                            setData(ucData);
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presentor.bd(bean.getXbm_xbdm(),bdType,CANCEL);

                        }
                    })
                    .create();
            dialog.show();
        }else {
            setData(ucData);
        }

    }
    private void setData(MesBean.UcDataBean ucData){
        tvGdh.setText(ucData.getBrp_djbh());
        tvCpxh.setText(ucData.getBrp_wldm());
        tvPmgg.setText(ucData.getBrp_pmgg());
        tvDdsl.setText(""+ucData.getPlm_jhsl());
        tvTrsl.setText(""+ucData.getBdm_cnt());
        etEdit7.setText(ucData.getBrp_upn());
        etEdit8.requestFocus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消了此功能，扫描后默认为ok
        //如果线别不为空，并且没有取消扫描记录
        /*if (bean != null && !isCancel){
            presentor.bd(bean.getXbm_xbdm(),bdType,CANCEL);
        }*/
    }
}
