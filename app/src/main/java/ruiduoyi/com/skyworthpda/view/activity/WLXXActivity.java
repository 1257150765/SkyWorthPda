package ruiduoyi.com.skyworthpda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import ruiduoyi.com.skyworthpda.contact.WLXXContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.presentor.WLXXPresentor;
import ruiduoyi.com.skyworthpda.util.Config;

/**
 * Created by Chen on 2018/6/6.
 */

public class WLXXActivity extends BaseScanActivity implements WLXXContact.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_text_wlxx)
    TextView tvText;
    @BindView(R.id.ed_edit_wlxx)
    EditText edEdit;
    @BindView(R.id.sp_spinner_wlxx)
    Spinner spSpinner;
    @BindView(R.id.btn_save_wlxx)
    Button btnSave;
    @BindView(R.id.btn_calcel_wlxx)
    Button btnCalcel;
    private WLXXContact.Presentor presentor;
    private String xb;
    private ArrayAdapter<String> adapter;
    private CheckQRCODEBean.UcDataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DialogActivity);
        setContentView(R.layout.activity_wlxx);
        ButterKnife.bind(this);
        initView();
        presentor = new WLXXPresentor(this, this);
    }

    @Override
    protected void onReceiveCode(String code) {
        presentor.checkQRCODE(code);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("物料下线");
        Intent intent = getIntent();
        xb = intent.getStringExtra(Config.EXTRA_DATE_XB);
    }

    /**
     * 加载下料站位成功
     * @param data
     */
    @Override
    public void onLoadXXZWSucceed(List<?> data) {
        /*this.data = data;
        List<String> xbDataStr = new ArrayList<>();
        for (XbBean.UcDataBean bean : xbData){
            xbDataStr.add(bean.getXbm_xbdm());
        }
        adapter = new ArrayAdapter<String>(WLXXActivity.this, R.layout.item_spinner, xbDataStr);
        spSpinner.setAdapter(adapter);*/
    }

    /**
     * 检查二维码成功
     * @param bean
     */
    @Override
    public void onCheckQRCODESucceed(CheckQRCODEBean.UcDataBean bean) {
        this.bean = bean;
        edEdit.setText(bean.getV_oricode());
        presentor.loadXXZW(xb,bean.getV_oricode());
    }

    @OnClick({R.id.btn_save_wlxx, R.id.btn_calcel_wlxx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_wlxx:
                if (null == bean){
                    showSnakeBar("请扫描二维码");
                    return;
                }
                presentor.wlxx(Config.WLXX_TYPE_DGXL,xb,bean.getV_oricode(),bean.getV_wldm());
                break;
            case R.id.btn_calcel_wlxx:
                finish();
                break;
        }
    }
}
