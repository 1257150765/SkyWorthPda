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
import ruiduoyi.com.skyworthpda.contact.YZCSContact;
import ruiduoyi.com.skyworthpda.model.bean.GzBean;
import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.presentor.BDPresentor;
import ruiduoyi.com.skyworthpda.presentor.YZCSPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

/**
 * 运转测试
 */
public class YZCSActivity extends BaseStatuScanActivity implements YZCSContact.View {

    private static final String CANCEL = "CANCEL";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_tv7_yzcsActivity)
    TextView tvTv7;
    @BindView(R.id.et_edit7_yzcsActivity)
    EditText etEdit7;
    @BindView(R.id.sp_xbSp_yzcsActivity)
    Spinner spXbSp;
    @BindView(R.id.tv_gdh_yzcsActivity)
    TextView tvGdh;
    @BindView(R.id.tv_cpxh_yzcsActivity)
    TextView tvCpxh;
    @BindView(R.id.tv_pmgg_yzcsActivity)
    TextView tvPmgg;
    @BindView(R.id.tv_ddsl_yzcsActivity)
    TextView tvDdsl;
    @BindView(R.id.tv_trsl_yzcsActivity)
    TextView tvTrsl;
    @BindView(R.id.sp_gzSp_yzcsActivity)
    Spinner spGz;
    @BindView(R.id.et_aDianLiu_yzcsActivity)
    EditText etADianLiu;
    @BindView(R.id.et_vDianYa_yzcsActivity)
    EditText etVDianYa;
    private EditText focusEditText;
    private String startTypeName;
    private String startTypeCode;
    private String tips = "";
    private YZCSPresentor presentor;
    private ArrayAdapter<String> xbAdapter;
    private List<XbBean.UcDataBean> xbData;
    private XbBean.UcDataBean bean;
    private String bdType = "";

    private ArrayAdapter<String> gzAdapter;
    private List<GzBean.UcDataBean> gzData;
    private GzBean.UcDataBean gzBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzcs);
        ButterKnife.bind(this);
        initView();
        presentor = new YZCSPresentor(this, this);
    }

    @Override
    protected void initView() {
        startTypeName = getIntent().getStringExtra(Config.ACTIVITY_START_TYPE_NAME);
        startTypeCode = getIntent().getStringExtra(Config.ACTIVITY_START_TYPE_CODE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("总装车间-" + startTypeName);
        //线别
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
        //工站
        spGz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //因为不要默认值，所有每个下拉框都加了一个空的选项，
                // 用户一定选择后才能操作（防止用户错选），所以第一个框是空的，不加载数据
                //其他地方的下拉框也以此类推
                if (null == gzData || position == 0) {
                    gzBean = null;
                    return;
                }
                gzBean = gzData.get(position - 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bean = xbData.get(0);
            }
        });
    }

    private void clearData() {
        etADianLiu.setText("");
        etVDianYa.setText("");
        etEdit7.setText("");
        etADianLiu.requestFocus();
    }


    @Override
    protected void onReceiveCode(String code) {
        String dl = etADianLiu.getText().toString();
        String dy = etVDianYa.getText().toString();
        if (null == bean) {
            showSnakeBar("请先选择线别");
            return;
        }else if (null == gzBean){
            showSnakeBar("请先选择工站");
            return;
        } else if ("".equals(dl)){
            showSnakeBar("请先输入电流值");
            return;
        }else if ("".equals(dy)){
            showSnakeBar("请先输入电压值");
            return;
        }
        bdType = gzBean.getOpr_gzdm();
        presentor.bd(bean.getXbm_xbdm(), bdType,code, dl, dy);

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



    /**
     * 成功加载总装线别
     *
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
        xbAdapter = new ArrayAdapter<String>(YZCSActivity.this, R.layout.item_spinner, xbDataStr);
        spXbSp.setAdapter(xbAdapter);
    }
    @Override
    public void onLoadZZGZSucceed(List<GzBean.UcDataBean> gzData) {
        this.gzData = gzData;
        List<String> gzDataStr = new ArrayList<>();
        gzDataStr.add("");
        for (GzBean.UcDataBean bean : gzData) {
            gzDataStr.add(bean.getOpr_gzmc());
        }
        gzAdapter = new ArrayAdapter<String>(YZCSActivity.this, R.layout.item_spinner, gzDataStr);
        spGz.setAdapter(gzAdapter);

    }


    @Override
    public void onBDFalse() {

    }

    /**
     * 2个都绑定绑定成功
     *
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
        if ("OK".equals(code.toUpperCase())) {
            SoundPoolUtil.playOK();
        } else if ("NG".equals(code.toUpperCase())) {
            SoundPoolUtil.playBlp();
        }
        onExecuteSucceed();
        clearData();
    }



    /**
     * 成功绑定一个
     *
     * @param ucData
     */
    @Override
    public void onBDSucceed(final MesBean.UcDataBean ucData) {
        //isCancel = false;
        if (!tvGdh.getText().toString().equals(ucData.getBrp_djbh())) {
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
                            presentor.bd(bean.getXbm_xbdm(), bdType, CANCEL,"","");

                        }
                    })
                    .create();
            dialog.show();
        } else {
            setData(ucData);
        }
        clearData();

    }

    private void setData(MesBean.UcDataBean ucData) {
        tvGdh.setText(ucData.getBrp_djbh());
        tvCpxh.setText(ucData.getBrp_wldm());
        tvPmgg.setText(ucData.getBrp_pmgg());
        tvDdsl.setText("" + ucData.getPlm_jhsl());
        tvTrsl.setText("" + ucData.getBdm_cnt());
        etEdit7.setText(ucData.getBrp_upn());
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
