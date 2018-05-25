package ruiduoyi.com.skyworthpda.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.SCSLContact;
import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;
import ruiduoyi.com.skyworthpda.presentor.SCSLPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.view.adapter.SCSLAdapter;

/**
 * Created by Chen on 2018/5/8.
 */

public class SCSLActivity extends BaseScanActivity implements SCSLContact.View {
    @BindView(R.id.et_code_scslactivity)
    EditText etCode;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_scslactivity)
    Spinner spXb;
    @BindView(R.id.cb_jdsl_scslactivity)
    CheckBox cbJdsl;
    @BindView(R.id.et_zwcx_scslactivity)
    EditText etZwcx;
    @BindView(R.id.et_slzw_scslactivity)
    EditText etSlzw;
    @BindView(R.id.btn_bbqh_scslactivity)
    Button btnBbqh;
    @BindView(R.id.btn_dgxl_scslactivity)
    Button btnDgxl;
    @BindView(R.id.btn_qbxl_scslactivity)
    Button btnQbxl;
    @BindView(R.id.rv_recycler_scslactivity)
    RecyclerView rvRecycler;
    @BindView(R.id.ll_btncontainer_scslactivity)
    LinearLayout llBtncontainer;
    @BindView(R.id.hsv_recycler_container_scslactivity)
    HorizontalScrollView hsvRecyclerContainer;
    @BindView(R.id.tv_tv3_scslactivity)
    TextView tvTv3;
    @BindView(R.id.tv_tv4_scslactivity)
    TextView tvTv4;
    private ArrayAdapter<String> xbAdapter;
    private List<String> xbData;
    private SCSLContact.Presentor presentor;
    private String startType = "";
    public static final String START_TYPE = "startType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scsl_activity_layout);
        ButterKnife.bind(this);
        startType = getIntent().getStringExtra(START_TYPE);
        initView();
        presentor = new SCSLPresentor(this, this);
        //getWindow().setEnterTransition(new Explode().setDuration(600));
        //getWindow().setExitTransition(new Explode().setDuration(600));
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(startType);
        switch (startType) {
            //首次上料
            case Config.PERMISSION_FCL_SCSL_NAME:
                llBtncontainer.setVisibility(View.VISIBLE);
                break;
            //生产续料
            case Config.PERMISSION_FCL_SCXL_NAME:
                tvTv3.setText("旧料盘");
                tvTv4.setText("新料盘");
                break;
            //上料确认
            case Config.PERMISSION_FCL_SLQR_NAME:
                break;
        }

        spXb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null == xbData) {
                    return;
                }
                presentor.loadData(xbData.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
    protected void onReceiveCode(String code) {
        //
    }

    @OnClick({R.id.btn_bbqh_scslactivity, R.id.btn_dgxl_scslactivity, R.id.btn_qbxl_scslactivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //版本切换
            case R.id.btn_bbqh_scslactivity:
                break;
            //单个下料
            case R.id.btn_dgxl_scslactivity:
                break;
            //全部下料
            case R.id.btn_qbxl_scslactivity:
                break;
        }
    }

    @Override
    public void onLoadXbSucceed(List<String> xbData) {
        this.xbData = xbData;
        xbAdapter = new ArrayAdapter<String>(SCSLActivity.this, R.layout.item_spinner, xbData);
        spXb.setAdapter(xbAdapter);
    }

    @Override
    public void onLoadDataSucceed(List<SCSLBean> data) {
        SCSLAdapter adapter = new SCSLAdapter(data);
        //DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
       // itemAnimator.setAddDuration(500L);
        //itemAnimator.setRemoveDuration(500L);
        //rvRecycler.setItemAnimator(itemAnimator);
       /* SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        rvRecycler.setItemAnimator(animator);*/
        rvRecycler.setAdapter(adapter);

        rvRecycler.setLayoutManager(new LinearLayoutManager(SCSLActivity.this));
        //adapter.notifyItemRangeInserted(0,data.size());
    }
}
