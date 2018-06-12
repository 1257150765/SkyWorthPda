package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.ZWTZContact;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.presentor.ZWTZPresentor;

public class ZWTZActivity extends BaseScanActivity implements ZWTZContact.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_zwtzActivity)
    Spinner spXb;
    @BindView(R.id.et_zwcx_zwtzActivity)
    EditText etZwcx;
    @BindView(R.id.tv_tv3_zwtzActivity)
    TextView tvTv3;
    @BindView(R.id.et_oldZw_zwtzActivity)
    EditText etOldZw;
    @BindView(R.id.tv_tv4_zwtzActivity)
    TextView tvTv4;
    @BindView(R.id.et_newZw_zwtzActivity)
    EditText etNewZw;
    @BindView(R.id.btn_tz_zwtzActivity)
    Button btnTz;
    @BindView(R.id.btn_exit_zwtzActivity)
    Button btnExit;
    @BindView(R.id.ll_btncontainer_zwtzActivity)
    LinearLayout llBtncontainer;
    private ZWTZContact.Presentor presentor;
    private List<XbBean.UcDataBean> xbData;
    private ArrayAdapter<String> xbAdapter;
    private XbBean.UcDataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zwtz);
        ButterKnife.bind(this);
        presentor = new ZWTZPresentor(this, this);
        initView();
    }

    @Override
    public void onExecuteSucceed() {
        super.onExecuteSucceed();
        etOldZw.setText("");
        etNewZw.setText("");
    }

    @Override
    public void onExecuteFalse() {
        super.onExecuteFalse();
        etOldZw.setText("");
        etNewZw.setText("");
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("站位调整");
        spXb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null == xbData || position == 0) {
                    etZwcx.setText("");
                    bean = null;
                    return;
                }
                bean = xbData.get(position - 1);
                etZwcx.setText(bean.getXbm_zwcxdm());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_tz_zwtzActivity, R.id.btn_exit_zwtzActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tz_zwtzActivity:
                if (null == bean) {
                    showSnakeBar("请选择线别");
                    return;
                }
                String oldZw = etOldZw.getText().toString().trim();
                String newZw = etNewZw.getText().toString().trim();
                if ("".equals(oldZw)) {
                    showSnakeBar("请扫描旧站位");
                    return;
                }
                if ("".equals(oldZw)) {
                    showSnakeBar("请扫描新站位");
                    return;
                }

                presentor.zwtz(bean.getXbm_xbdm(), oldZw, newZw);
                break;
            case R.id.btn_exit_zwtzActivity:
                finish();
                break;
        }
    }

    @Override
    public void onLoadXbSucceed(List<XbBean.UcDataBean> xbData) {
        this.xbData = xbData;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        for (XbBean.UcDataBean bean : xbData) {
            xbDataStr.add(bean.getXbm_xbdm());
        }
        xbAdapter = new ArrayAdapter<String>(ZWTZActivity.this, R.layout.item_spinner, xbDataStr);
        spXb.setAdapter(xbAdapter);
    }

    @Override
    public void onCheckZwSucceed(String zw) {
        if (etOldZw.hasFocus()) {
            etOldZw.setText(zw);
            etNewZw.requestFocus();
        } else if (etNewZw.hasFocus()) {
            etNewZw.setText(zw);
        }
    }


    @Override
    protected void onReceiveCode(String code) {
        presentor.checkZw(code);
    }
}
