package ruiduoyi.com.skyworthpda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.FhdContact;
import ruiduoyi.com.skyworthpda.model.bean.CKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdBean;
import ruiduoyi.com.skyworthpda.presentor.FhdPresentor;
import ruiduoyi.com.skyworthpda.view.adapter.CKZSAdapter;
import ruiduoyi.com.skyworthpda.view.adapter.FhdAdapter;


/**
 * Created by Chen on 2018-12-27.
 */


public class FhdActivity extends BaseScanActivity implements FhdContact.View, FhdAdapter.OnItemClickListener {
    private static final String TAG = FhdActivity.class.getSimpleName();
    private static final int REQUEST_CODE_RECORD_DATA = 10001;
    private static final int REQUEST_CODE_NORMAL_DATA = 10002;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.btn_search_fhdActivity)
    Button btnSearch;
    @BindView(R.id.et_fhd_fhdActivity)
    EditText etFhd;


    private FhdPresentor presentor;

    private CKZSAdapter ckAdapter;
    private ArrayList<FhdBean.UcDataBean> fhdData;
    private FhdAdapter fhdAdapter;


    @Override
    protected void onReceiveCode(String code) {

    }
    //瑞多益科技

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhd);
        ButterKnife.bind(this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        initView();
        presentor = new FhdPresentor(this, this);
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
        actionBar.setTitle("出库扫描");
        fhdData = new ArrayList<FhdBean.UcDataBean>();
        //scanData = new ArrayList<CpCodeBean.UcDataBean>();
        fhdAdapter = new FhdAdapter(fhdData);
        //scanAdapter = new CKScanAdapter(scanData);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(fhdAdapter);

        //scanedList.setLayoutManager(new LinearLayoutManager(this));
        // scanedList.setAdapter(scanAdapter);
        fhdAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onLoadFhdSucceed(List<FhdBean.UcDataBean> ucData) {
        fhdData.clear();
        fhdData.addAll(ucData);
        fhdAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadRecordSucceed(CKRecordBean.UcDataBean ucData) {
        if (ucData != null && ucData.getTable1().size()>0){
            Intent intent = new Intent(FhdActivity.this,CKSMActivity.class);
            intent.putExtra(CKSMActivity.RECORD_DATA,ucData);
            //startActivity(intent);
            startActivityForResult(intent,REQUEST_CODE_RECORD_DATA);
            finish();
        }else {
            presentor.loadFhd("");
        }
    }

    @OnClick(R.id.btn_search_fhdActivity)
    public void onViewClicked() {
        presentor.loadFhd(etFhd.getText().toString());
    }

    @Override
    public void onItemClick(FhdBean.UcDataBean bean, int position) {
        Intent intent = new Intent(FhdActivity.this,CKSMActivity.class);
        intent.putExtra(CKSMActivity.FHDH,bean.getShip_djbh());
        //startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE_NORMAL_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_NORMAL_DATA:
                    //如果是正常出货，成功时，需要刷新一下数据
                    presentor.loadFhd("");
                    break;

            }
        }
        //如果是有上一次的记录，则无论成功或失败都需要请求一下还有无记录存在服务器
        if (requestCode == REQUEST_CODE_RECORD_DATA){
            presentor.loadRecord();
        }

    }
}
