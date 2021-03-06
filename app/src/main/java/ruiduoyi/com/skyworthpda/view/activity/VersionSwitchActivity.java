package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.VersionSeitchContact;
import ruiduoyi.com.skyworthpda.model.bean.ZWCXBean;
import ruiduoyi.com.skyworthpda.presentor.VersionSwitchPresentor;
import ruiduoyi.com.skyworthpda.util.Config;


/**
 *  Created by Chen on 2018/5/8.
 * 版本切换
 */
public class VersionSwitchActivity extends BaseActivity implements VersionSeitchContact.View {
    @BindView(R.id.tv_text_versionSwitch)
    TextView tvText;
    @BindView(R.id.ed_edit_versionSwitch)
    EditText edEdit;
    @BindView(R.id.sp_spinner_versionSwitch)
    Spinner spSpinner;
    @BindView(R.id.btn_save_versionSwitch)
    Button btnSave;
    @BindView(R.id.btn_calcel_versionSwitch)
    Button btnCalcel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String xb;
    private VersionSwitchPresentor presentor;
    private String zwcx;
    private List<ZWCXBean.UcDataBean> zwcxData;
    private ArrayAdapter<String> zwcxAdapter;
    private ZWCXBean.UcDataBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DialogActivity);
        setContentView(R.layout.activity_version_switch);
        ButterKnife.bind(this);
        presentor = new VersionSwitchPresentor(this, this);
        initView();
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("版本切换");
        Intent intent = getIntent();
        xb = intent.getStringExtra(Config.EXTRA_DATE_XB);
        zwcx = intent.getStringExtra(Config.EXTRA_DATE_ZWCX);
        tvText.setText(xb + "当前生产的站位程序");
        edEdit.setText(zwcx);
        presentor.loadZWCX(xb);
        spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null == zwcxData || position == 0) {
                    bean = null;
                    return;
                }
                bean = zwcxData.get(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onLoadZWCXSucceed(List<ZWCXBean.UcDataBean> zwcxData) {
        this.zwcxData = zwcxData;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        for (ZWCXBean.UcDataBean bean : zwcxData) {
            xbDataStr.add(bean.getZwm_cxdm());
        }
        zwcxAdapter = new ArrayAdapter<String>(VersionSwitchActivity.this, R.layout.item_spinner, xbDataStr);
        spSpinner.setAdapter(zwcxAdapter);
    }

    @Override
    public void onVersionSwitchSucceed() {
        //onExecuteSucceed();
        setResult(RESULT_OK);
        finish();
    }

    @OnClick({R.id.btn_save_versionSwitch, R.id.btn_calcel_versionSwitch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_versionSwitch:
                if (bean == null) {
                    showSnakeBar("请选择需切换的站位程序");
                    return;
                }
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("切换站位程序后，线体的物料将会整体下料，确定切换？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presentor.versionSwitch(xb, bean.getZwm_cxdm());

                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .create();
                dialog.show();

                break;
            case R.id.btn_calcel_versionSwitch:
                finish();
                break;
        }
    }
}
