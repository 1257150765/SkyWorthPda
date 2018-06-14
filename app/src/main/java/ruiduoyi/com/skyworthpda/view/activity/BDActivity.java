package ruiduoyi.com.skyworthpda.view.activity;

/**
 * Created by Chen on 2018/6/13.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.util.Config;

/**
 * 压缩机绑定，控制器/电器盒绑定,(简称绑定)
 */
public class BDActivity extends BaseStatuScanActivity implements View.OnFocusChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_tv7_bdActivity)
    TextView tvTv7;
    @BindView(R.id.tv_tv8_bdActivity)
    TextView tvTv8;
    @BindView(R.id.et_edit1_bdActivity)
    EditText etEdit1;
    @BindView(R.id.et_edit7_bdActivity)
    EditText etEdit7;
    @BindView(R.id.et_edit8_bdActivity)
    EditText etEdit8;
    private String startType;
    private EditText focusEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bdactivity);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {

        startType = getIntent().getStringExtra(Config.ACTIVITY_START_TYPE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(startType);

        switch (startType) {
            case Config.PERMISSION_SMTZZ_YSJBD_NAME:
                tvTv7.setText("MES条码:");
                tvTv8.setText("压缩机条码:");
                break;
            case Config.PERMISSION_SMTZZ_KZQDQHBD_NAME:
                tvTv7.setText("MES条码:");
                tvTv8.setText("控制器条码:");
                break;
            case Config.PERMISSION_SMTZZ_DZJC_NAME:
                tvTv7.setText("创维条码:");
                tvTv8.setText("OK/NG条码:");
                break;
        }
        etEdit1.setOnFocusChangeListener(this);
        etEdit7.setOnFocusChangeListener(this);
        etEdit8.setOnFocusChangeListener(this);
    }

    @Override
    public void onExecuteSucceed() {

    }


    @Override
    protected void onReceiveCode(String code) {
        focusEditText.setText(code);
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
        if (hasFocus){
            if (v instanceof EditText){
                focusEditText = (EditText) v;
            }
            if(focusEditText.getId() != etEdit1.getId()){
                if ("".equals(etEdit1.getText().toString())){
                    etEdit1.requestFocus();
                    showSnakeBar("请先扫描线别");
                    return;
                }
            }
        }
    }
}
