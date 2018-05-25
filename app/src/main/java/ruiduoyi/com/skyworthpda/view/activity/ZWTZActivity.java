package ruiduoyi.com.skyworthpda.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.ZWTZContact;
import ruiduoyi.com.skyworthpda.presentor.ZWTZPresentor;

public class ZWTZActivity extends BaseScanActivity implements ZWTZContact.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_zwtzactivity)
    Spinner spXb;
    @BindView(R.id.et_zwcx_zwtzactivity)
    EditText etZwcx;
    @BindView(R.id.tv_tv3_zwtzactivity)
    TextView tvTv3;
    @BindView(R.id.et_slzw_zwtzactivity)
    EditText etSlzw;
    @BindView(R.id.tv_tv4_zwtzactivity)
    TextView tvTv4;
    @BindView(R.id.et_code_zwtzactivity)
    EditText etCode;
    @BindView(R.id.btn_tz_zwtzactivity)
    Button btnTz;
    @BindView(R.id.btn_exit_zwtzactivity)
    Button btnExit;
    private ZWTZContact.Presentor presentor;
    private List<String> xbData;
    private ArrayAdapter<String> xbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zwtz);
        ButterKnife.bind(this);
        presentor = new ZWTZPresentor(this,this);
        initView();
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
                if (null == xbData) {
                    return;
                }
                presentor.loadZW(xbData.get(position));
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

    @OnClick({R.id.btn_tz_zwtzactivity, R.id.btn_exit_zwtzactivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tz_zwtzactivity:
                break;
            case R.id.btn_exit_zwtzactivity:
                finish();
                break;
        }
    }

    @Override
    public void onLoadXbSucceed(List<String> xbData) {
        this.xbData = xbData;
        xbAdapter = new ArrayAdapter<String>(ZWTZActivity.this, android.R.layout.simple_spinner_item, xbData);
        spXb.setAdapter(xbAdapter);
    }

    @Override
    public void onLoadZwSucceed(String zwStr) {
        etZwcx.setText(zwStr);
    }

    @Override
    protected void onReceiveCode(String code) {

    }
}