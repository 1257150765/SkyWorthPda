package ruiduoyi.com.skyworthpda.view.activity;

import android.content.Intent;
import android.os.Bundle;
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
import ruiduoyi.com.skyworthpda.contact.WLXXContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.XLZWBean;
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
    private List<XLZWBean.UcDataBean> data;
    private XLZWBean.UcDataBean zwBean;

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
        clearData();
        
        presentor.checkQRCODE(code);
    }

    private void clearData() {
        edEdit.setText("");
        if (adapter != null && data !=null){
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("物料下线");
        Intent intent = getIntent();
        xb = intent.getStringExtra(Config.EXTRA_DATE_XB);
        spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null == data || position == 0){
                    zwBean = null;
                    return;
                }
                zwBean = data.get(position - 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onExecuteSucceed() {
        super.onExecuteSucceed();
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 加载下料站位成功
     * @param data
     */
    @Override
    public void onLoadXXZWSucceed(List<XLZWBean.UcDataBean> data) {
        this.data = data;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        for (XLZWBean.UcDataBean bean : data){
            xbDataStr.add(bean.getSll_zwdm()+" 剩余数量:"+bean.getSll_osqty());
        }
        adapter = new ArrayAdapter<String>(WLXXActivity.this, R.layout.item_spinner, xbDataStr);
        spSpinner.setAdapter(adapter);
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
                if (zwBean == null){
                    showSnakeBar("请选择站位");
                    return;
                }
                presentor.wlxx(Config.WLXX_TYPE_DGXL,xb,bean.getV_oricode(),bean.getV_wldm(),zwBean.getSll_zwdm());
                break;
            case R.id.btn_calcel_wlxx:
                finish();
                break;
        }
    }
}
