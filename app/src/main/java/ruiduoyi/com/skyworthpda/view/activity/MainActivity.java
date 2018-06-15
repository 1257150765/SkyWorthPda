package ruiduoyi.com.skyworthpda.view.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.MainContact;
import ruiduoyi.com.skyworthpda.model.bean.PermissionBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.presentor.MainPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.DownloadService;
import ruiduoyi.com.skyworthpda.view.adapter.MainExpandableMenuAdapter;
import ruiduoyi.com.skyworthpda.widget.MyExpandableListView;


public class MainActivity extends BaseActivity implements MainContact.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.banner_mainactivity)
    BGABanner bgabBanner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.elv_expanded_menu_mainactivity)
    MyExpandableListView elvExpandedMenu;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.left_content)
    LinearLayout leftContent;

    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.ll_switch_layout_mainactivity)
    LinearLayout llSwitchLayout;
    @BindView(R.id.ll_exit_layout_mainactivity)
    LinearLayout llExitLayout;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.bm)
    TextView bm;
    private MainContact.Presentor presentor;
    private ProgressDialog downloadProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.MainActivityTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        //getWindow().setEnterTransition(new Explode().setDuration(600));
        //getWindow().setExitTransition(new Explode().setDuration(600));
        ButterKnife.bind(this);
        initView();
        presentor = new MainPresentor(this, this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.home);
        actionBar.setTitle(" ");
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_2));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_3));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_4));
        //views.add(BGABannerUtil.getItemImageView(this, R.drawable.banner_1));
        bgabBanner.setData(views);
        userName.setText("欢迎你！" + preferenUtil.getString(Config.CACHE_DATA_USERNAME));
        companyName.setText("公司:" + preferenUtil.getString(Config.CACHE_DATA_COMPANYNAME));
        bm.setText("部门:" + preferenUtil.getString(Config.CACHE_DATA_BM));

        downloadProgressDialog = new ProgressDialog(this);
        downloadProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        downloadProgressDialog.setCancelable(false);
        downloadProgressDialog.setCanceledOnTouchOutside(false);
        downloadProgressDialog.setTitle("下载中");
        downloadProgressDialog.setMax(100);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.checkUptate:
                presentor.checkUpdate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @OnClick({R.id.ll_switch_layout_mainactivity, R.id.ll_exit_layout_mainactivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_switch_layout_mainactivity:
                RetrofitManager.logout();
                RetrofitManager.setCompanyName("");
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.ll_exit_layout_mainactivity:
                finish();
                break;
        }
    }


    @Override
    public void onCheckUpdateSucceed(boolean hasNewVer, final String url) {
        try {
            URL url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onShowTipsDailog("URL不合法，更新失败");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        if (hasNewVer) {
            builder.setCancelable(false)
                    .setMessage("发现新版本，点击确定开始更新")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presentor.update(url);
                        }
                    });
        } else {
            builder.setCancelable(true)
                    .setMessage("未发现新版本，是否更新")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presentor.update(url);
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
        builder.create().show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        RetrofitManager.logout();

    }

    @Override
    public void onLoadPermissionSecceed(List<PermissionBean.UcDataBean> titles, final List<List<PermissionBean.UcDataBean>> childs) {
        List<String> groupTitles = new ArrayList<>();
        for (PermissionBean.UcDataBean bean1:titles) {
            groupTitles.add(bean1.getG_mkmc());
        }
        //子标题
        List<List<String>> allChildTitles = new ArrayList<>();
        //子标题的图片
        List<List<Integer>> allChildImgs = new ArrayList<>();
        for (List<PermissionBean.UcDataBean> bean2:childs) {
            List<String> dataStr = new ArrayList<>();
            List<Integer> dataImg = new ArrayList<>();
            for (PermissionBean.UcDataBean bean3 : bean2){
                dataStr.add(bean3.getG_cxmc());
                switch (bean3.getG_cxdm()){
                    case Config.PERMISSION_FCL_SCSL_CODE:
                        dataImg.add(R.mipmap.scsl);
                        break;
                    //生产续料
                    case Config.PERMISSION_FCL_SCXL_CODE:
                        dataImg.add(R.mipmap.scxl);
                        break;
                    //上料确认
                    case Config.PERMISSION_FCL_SLQR_CODE:
                        dataImg.add(R.mipmap.scqr);
                        break;
                    //站位调整
                    case Config.PERMISSION_FCL_ZWTZ_CODE:
                        dataImg.add(R.mipmap.zwtz);
                        break;
                    //品管巡检
                    case Config.PERMISSION_PZGL_PGXJ_CODE:
                        dataImg.add(R.mipmap.pgxj);
                        break;
                    default:
                        dataImg.add(R.mipmap.unknown);
                        break;
                }
            }
            allChildTitles.add(dataStr);
            allChildImgs.add(dataImg);
        }

        MainExpandableMenuAdapter adapter = new MainExpandableMenuAdapter(MainActivity.this, R.layout.item_main_title,
                R.layout.item_main_group_title, groupTitles, allChildTitles, allChildImgs);
        elvExpandedMenu.setAdapter(adapter);
        elvExpandedMenu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PermissionBean.UcDataBean bean = childs.get(groupPosition).get(childPosition);
                switch (bean.getG_cxdm()) {
                    //首次上料
                    case Config.PERMISSION_FCL_SCSL_CODE:
                        gotoSCSL(Config.PERMISSION_FCL_SCSL_NAME);
                        break;
                    //生产续料
                    case Config.PERMISSION_FCL_SCXL_CODE:
                        gotoSCSL(Config.PERMISSION_FCL_SCXL_NAME);
                        break;
                    //上料确认
                    case Config.PERMISSION_FCL_SLQR_CODE:
                        gotoSCSL(Config.PERMISSION_FCL_SLQR_NAME);
                        break;
                    //站位调整
                    case Config.PERMISSION_FCL_ZWTZ_CODE:
                        gotoZWTZ();
                        break;
                    //品管巡检
                    case Config.PERMISSION_PZGL_PGXJ_CODE:
                        gotoPGXJ();
                        break;
                    //压缩机绑定
                    case Config.PERMISSION_SMTZZ_YSJBD_CODE:
                        gotoBD(bean.getG_cxmc(),bean.getG_cxdm());
                        break;
                    //控制器/电器盒绑定
                    case Config.PERMISSION_SMTZZ_KZQDQHBD_CODE:
                        gotoBD(bean.getG_cxmc(),bean.getG_cxdm());
                        break;
                    //电子检查
                    case Config.PERMISSION_SMTZZ_DZJC_CODE:
                        gotoBD(bean.getG_cxmc(),bean.getG_cxdm());
                        break;
                    default:
                        showSnakeBar("敬请期待");
                        break;
                }
                return true;
            }
        });
    }

    private void gotoBD(String startTypeName,String startTypeCode) {
        Intent intent =  new Intent(MainActivity.this,BDActivity.class);
        intent.putExtra(Config.ACTIVITY_START_TYPE_NAME,startTypeName);
        intent.putExtra(Config.ACTIVITY_START_TYPE_CODE,startTypeCode);
        startActivity(intent);
    }

    @Override
    public void onUpdate(int value) {
        //下载进度条
        downloadProgressDialog.setProgress(value);
        downloadProgressDialog.show();
    }

    @Override
    public void onUpdateSucceed() {
        if (downloadProgressDialog != null && downloadProgressDialog.isShowing()){
            downloadProgressDialog.dismiss();
        }
    }


    private void gotoPGXJ() {
        Intent intent = new Intent(MainActivity.this, PGXJActivity.class);
        startActivity(intent);
    }

    //站位调整
    private void gotoZWTZ() {
        Intent intent = new Intent(MainActivity.this, ZWTZActivity.class);
        startActivity(intent);
    }

    private void gotoSCSL(String startType) {
        Intent intent = new Intent(MainActivity.this, SCSLActivity.class);
        intent.putExtra(SCSLActivity.START_TYPE, startType);
        //startActivity(intent);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
