package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.SCSLContact;
import ruiduoyi.com.skyworthpda.model.bean.SLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.presentor.SCSLPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;
import ruiduoyi.com.skyworthpda.view.adapter.SCSLAdapter;

/**
 * Created by Chen on 2018/5/8.
 */

/**
 * 首次上料，生产续料 上料确认
 */
public class SCSLActivity extends BaseScanActivity implements SCSLContact.View, View.OnFocusChangeListener {

    private static final String TAG = SCSLActivity.class.getSimpleName();
    private static final int REQUEST_CODE_VERSIONSWITCH = 1001;
    private static final int REQUEST_CODE_WLXX = 1002;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_scslactivity)
    Spinner spXb;
    @BindView(R.id.cb_jdsl_scslactivity)
    CheckBox cbJdsl;
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
    @BindView(R.id.et_edit1_scslactivity)
    EditText etEdit1;//第一个输入框
    @BindView(R.id.et_edit2_scslactivity)
    EditText etEdit2;//第二个输入框
    @BindView(R.id.et_edit3_scslactivity)
    EditText etEdit3;//第三个输入框
    private ArrayAdapter<String> xbAdapter;
    private List<XbBean.UcDataBean> xbData;
    private SCSLPresentor presentor;
    private String startType = "";
    public static final String START_TYPE = "startType";
    private String key_flag = "0";
    private XbBean.UcDataBean bean;
    private EditText focusEditText;
    //验证类型(站位|二维码)
    private String edit2ScanType = "";
    private String edit3ScanType = "";
    private AlertDialog dialog;
    private SCSLAdapter adapter;
    private List<SLXXBean.UcDataBean> slData;
    private String curXb = "";//记录当前的线别
    private String isUse = "";
    protected AlertDialog tipsDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scsl_activity_layout);
        ButterKnife.bind(this);
        startType = getIntent().getStringExtra(START_TYPE);
        initView();
        presentor = new SCSLPresentor(this, this);
        presentor.setStartType(startType);
        //getWindow().setEnterTransition(new Explode().setDuration(600));
        //getWindow().setExitTransition(new Explode().setDuration(600));
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(startType);

        etEdit1.setOnFocusChangeListener(this);
        etEdit2.setOnFocusChangeListener(this);
        etEdit3.setOnFocusChangeListener(this);
        etEdit2.requestFocus();
        //是否待上料
        cbJdsl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (null == bean){
                    return;
                }
                if (isChecked){
                    key_flag = "1";
                }else {
                    key_flag = "0";
                }
                loadData();
            }
        });
        //根据启动类型，显示不同的标题，并且修改扫描类型
        switch (startType) {
            //首次上料，第二个框是站位，第三个框是二维码
            case Config.PERMISSION_FCL_SCSL_NAME:
                llBtncontainer.setVisibility(View.VISIBLE);
                edit2ScanType = Config.CHECK_TYPE_ZWM;
                edit3ScanType = Config.CHECK_TYPE_QRCODE;
                break;
            //生产续料，第二个框和第三个框都是二维码
            case Config.PERMISSION_FCL_SCXL_NAME:
                tvTv3.setText("旧料盘:");
                tvTv4.setText("新料盘:");
                edit2ScanType = Config.CHECK_TYPE_QRCODE;
                edit3ScanType = Config.CHECK_TYPE_QRCODE;
                break;
            //上料确认，第二个框是站位，第三个框是二维码
            case Config.PERMISSION_FCL_SLQR_NAME:
                cbJdsl.setText("仅待确认");
                edit2ScanType = Config.CHECK_TYPE_ZWM;
                edit3ScanType = Config.CHECK_TYPE_QRCODE;
                break;
        }
        //选择线别
        spXb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //因为不要默认值，所有每个下拉框都加了一个空的选项，
                // 用户一定选择后才能操作（防止用户错选），所以第一个框是空的，不加载数据
                //其他地方的下拉框也以此类推
                if (null == xbData || position == 0) {
                    bean = null;
                    curXb = "";
                    clearData();
                    return;
                }
                clearData();
                bean = xbData.get(position - 1);
                curXb = bean.getXbm_xbdm();
                setZwcx(bean.getXbm_zwcxdm());
                loadData();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bean = xbData.get(0);
            }
        });
    }

    /**
     * 加载列表数据
     */
    private void loadData() {
        //获取确认列表
        if (startType.equals(Config.PERMISSION_FCL_SLQR_NAME)){
            presentor.loadQRData(bean.getXbm_xbdm(),key_flag);
        }else {
            //获取上料信息
            presentor.loadData(bean.getXbm_xbdm(), key_flag);
        }
    }

    /**
     * 清除数据，操作之后需要吧原来的数据清除，以免影响用户判断
     */
    private void clearData() {
        etEdit1.setText("");
        etEdit2.setText("");
        etEdit3.setText("");
        if (adapter != null && slData != null){
            slData.clear();
            adapter.notifyDataSetChanged();
        }
    }


    //执行成功，吧所有输入框的内容都清除掉
    @Override
    public void onExecuteSucceed() {
        super.onExecuteSucceed();
        loadData();
        etEdit2.requestFocus();
        etEdit2.setText("");
        etEdit3.setText("");
        isUse = "";
    }

    @Override
    public void onExecuteSucceed2() {
        super.onExecuteSucceed2();
        loadData();
        etEdit2.requestFocus();
        etEdit2.setText("");
        etEdit3.setText("");
        isUse = "";
    }

    //执行失败，可以认定只是最后一次扫描有问题，吧第三输入框的内容清除掉就可以了
    @Override
    public void onExecuteFalse() {
        super.onExecuteFalse();
        etEdit3.requestFocus();
        //etEdit2.setText("");
        etEdit3.setText("");
    }

    //扫描回调,
    @Override
    protected void onReceiveCode(String code) {
        if (null == bean){
            showSnakeBar("请选择线别");
            return;
        }
        String type = "";
        //如果是首次上料和生产续料，是的话就根据扫描内容有没有*号判断是站位还是物料
        //如果是生产续料，判断旧料盘是否正确 不正确就一直扫旧料盘，正确就默认继续扫新料盘
        if (Config.PERMISSION_FCL_SCXL_NAME.equals(startType)) {
            /*if ("N".equals(isUse) || "".equals(isUse)){
                etEdit2.requestFocus();
            }else if ("Y".equals(isUse) && !etEdit2.getText().toString().equals("")){
                etEdit3.requestFocus();
            }*/
        }else {
            if (code.contains("*")) {
                //type = edit2ScanType;
                etEdit3.requestFocus();
                if ("".equals(etEdit2.getText().toString())){
                    return;
                }
            } else{
                //type = edit3ScanType;
                etEdit2.requestFocus();
            }
            //LogWraper.d(TAG,"--"+type+","+code);
        }

        if (focusEditText.getId() == etEdit2.getId()){
            type = edit2ScanType;
        }else if (focusEditText.getId() == etEdit3.getId()){
            type = edit3ScanType;
        }
        if ("".equals(type) || "".equals(code)) {
            return;
        } else if (Config.CHECK_TYPE_ZWM.equals(type)) {
            presentor.checkZW(type, code);
        } else if (Config.CHECK_TYPE_QRCODE.equals(type)) {
            presentor.checkQRCODE(type, code);
        }
    }

    /**
     * 版本切换 单个下料 整体下料
     * @param view
     */
    @OnClick({R.id.btn_bbqh_scslactivity, R.id.btn_dgxl_scslactivity, R.id.btn_qbxl_scslactivity})
    public void onViewClicked(View view) {
        if (null == bean){
            showSnakeBar("请选择线别");
            return;
        }
        switch (view.getId()) {
            //版本切换
            case R.id.btn_bbqh_scslactivity:
                Intent intent = new Intent(this,VersionSwitchActivity.class);
                intent.putExtra(Config.EXTRA_DATE_XB,bean.getXbm_xbdm());
                intent.putExtra(Config.EXTRA_DATE_ZWCX,bean.getXbm_zwcxdm());
                startActivityForResult(intent,REQUEST_CODE_VERSIONSWITCH);
                break;
            //单个下料
            case R.id.btn_dgxl_scslactivity:
                Intent intent2 = new Intent(this,WLXXActivity.class);
                intent2.putExtra(Config.EXTRA_DATE_XB,bean.getXbm_xbdm());
                startActivityForResult(intent2,REQUEST_CODE_WLXX);
                break;
            //全部下料
            case R.id.btn_qbxl_scslactivity:
                if (dialog == null){
                    dialog = new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    presentor.wlxx(Config.WLXX_TYPE_QTXL,bean.getXbm_xbdm());
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                }
                dialog.setMessage("对"+bean.getXbm_xbdm()+"线整体下料?");
                dialog.show();
                break;
        }
    }


    /**
     * 检查二维码成功
     * @param type QRCODE
     * @param code 二维码
     * @param wldm 物料代码
     * @param qty 数量
     */
    @Override
    public void onCheckQRCODESucceed(String type, String code,String wldm,String qty,String isUse) {
        //记录旧料盘是否正确
        focusEditText.setText(code);
        if (Config.PERMISSION_FCL_SCXL_NAME.equals(startType) && "2".equals(isUse)){
            this.isUse = "2";//2代表最后一次上料，1代表上过料，但是,不是最后一次上料，0代表没上过
        }else if (Config.PERMISSION_FCL_SCXL_NAME.equals(startType) && ("1".equals(isUse) || "0".equals(isUse))){
            this.isUse = isUse;
            //当验证的是旧料盘才会显示提示
            if (focusEditText.getId() == etEdit2.getId()){
                super.onScanError();
                onShowTipsDailog("旧料盘错误");
                etEdit2.setText("");
                etEdit2.requestFocus();
                return;
            }
        }

        //如果是第一或二个输入框，表示还没输入完,吧焦点移到第三个输入框
        if (focusEditText.getId() == etEdit2.getId() || focusEditText.getId() == etEdit1.getId()){
            etEdit3.requestFocus();
            return;
        }
        //输入完则执行过程
        String edit2Str = etEdit2.getText().toString().trim();
        if (null == bean){
            onShowTipsDailog("请选择线别");
            return;
        }
        //非0代表该物料已经上过了
        /*if (Config.PERMISSION_FCL_SCSL_NAME.equals(startType) || Config.PERMISSION_FCL_SCXL_NAME.equals(startType)) {
            if (!"0".equals(isUse)) {
                onShowTipsDailog("该物料已使用，扫描异常!");
                etEdit3.setText("");
                super.onScanError();
                return;
            }
        }else if (Config.PERMISSION_FCL_SLQR_NAME.equals(startType) && "2".equals(isUse)){
            if (!"0".equals(isUse)) {
                onShowTipsDailog("该物料");
                etEdit3.setText("");
                super.onScanError();
                return;
            }
        }*/
        switch (startType) {
            //首次上料
            case Config.PERMISSION_FCL_SCSL_NAME:
                if ("".equals(edit2Str)){
                    onShowTipsDailog("请扫描站位");
                    return;
                }
                if ("".equals(etEdit3.getText().toString().trim())){
                    onShowTipsDailog("请扫描条码");
                    return;
                }
                presentor.scsl(bean.getXbm_xbdm(),edit2Str,code,wldm,qty);
                break;
            //生产续料
            case Config.PERMISSION_FCL_SCXL_NAME:
                if ("".equals(edit2Str)){
                    onShowTipsDailog("请扫描旧料盘");
                    return;
                }
                if ("".equals(etEdit3.getText().toString().trim())){
                    onShowTipsDailog("请扫描新料盘");
                    return;
                }
                presentor.scxl(bean.getXbm_xbdm(),edit2Str,code,wldm,qty);
                break;
            //上料确认
            case Config.PERMISSION_FCL_SLQR_NAME:
                if ("".equals(edit2Str)){
                    onShowTipsDailog("请扫描站位");
                    return;
                }
                if ("".equals(etEdit3.getText().toString().trim())){
                    onShowTipsDailog("请扫描条码");
                    return;
                }
                presentor.slqr(bean.getXbm_xbdm(),edit2Str,code,wldm);
                break;
        }
    }

    //执行物料下线成功，
    @Override
    public void onWLXXSucceed() {
        super.onExecuteSucceed();
        presentor.loadXb();
    }

    @Override
    public void onShowTipsDailog2(String msg) {
        if (tipsDialog2 == null) {
            tipsDialog2 = new AlertDialog.Builder(this)
                    //.setView(R.layout.loading)
                    .setCancelable(false)
                    .setTitle("温馨提示")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
        }
        tipsDialog2.setMessage(msg);
        tipsDialog2.show();
    }


    /**
     * 检查站位成功
     * @param type
     * @param code
     */
    @Override
    public void onCheckZWSucceed(String type, String code) {
        etEdit2.setText(code);
        etEdit3.requestFocus();
        //扫描站位后，滑动到对应的站位
        for (int i = 0; i < slData.size(); i++) {
            //LogWraper.d(TAG,"--"+slData.get(i).getZwl_zwdm());
            if (code.equals(slData.get(i).getZwl_zwdm())){
                rvRecycler.scrollToPosition(i);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) rvRecycler.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(i, 0);
            }
        }
    }

    /**
     * 监听三个输入框的焦点变化
     * @param v
     * @param hasFocus
     */

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            if (v instanceof EditText){
                focusEditText = (EditText) v;
            }
            if (v.getId() == etEdit3.getId()){
                if (etEdit2.getText().toString().equals("")){
                    // TODO: 2018-07-19
                    showSnakeBar("请先扫描站位");
                    etEdit2.requestFocus();
                    return;
                }
            }
        }
    }

    /**
     *版本切换 单个下料 执行成功后回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_VERSIONSWITCH:
                    //版本切换后，需要重新加载线别的站位程序
                    presentor.loadXb();
                    break;
                case REQUEST_CODE_WLXX:
                    //物料下线，需要重新加载上料信息
                    loadData();
                    break;
            }
        }
    }

    /**
     * 获取线别列表成功
     * @param xbData
     */
    @Override
    public void onLoadXbSucceed(List<XbBean.UcDataBean> xbData) {
        this.xbData = xbData;
        List<String> xbDataStr = new ArrayList<>();
        xbDataStr.add("");
        int index = 0;
        for (int i=0; i<xbData.size(); i++){
            XbBean.UcDataBean bean = xbData.get(i);
            xbDataStr.add(bean.getXbm_xbdm());
            if (curXb.equals(bean.getXbm_xbdm())){
                index = i + 1;
            }
        }
        xbAdapter = new ArrayAdapter<String>(SCSLActivity.this, R.layout.item_spinner, xbDataStr);
        spXb.setAdapter(xbAdapter);
        //Log.d(TAG, "onLoadXbSucceed: curXb:"+curXb);
        //Log.d(TAG, "onLoadXbSucceed: index:"+index);
        spXb.setSelection(index);
    }


    /**
     * 获取已上料信息成功
     * @param data
     */
    @Override
    public void onLoadDataSucceed(List<SLXXBean.UcDataBean> data) {
        this.slData = data;
        adapter = new SCSLAdapter(data);
        rvRecycler.setAdapter(adapter);
        rvRecycler.setLayoutManager(new LinearLayoutManager(SCSLActivity.this));
        //adapter.notifyItemRangeInserted(0,data.size());
    }
    //设置站位程序
    private void setZwcx(String xbm_zwcxdm) {
        etEdit1.setText(xbm_zwcxdm);
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
}
