package ruiduoyi.com.skyworthpda.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.MainContact;
import ruiduoyi.com.skyworthpda.presentor.MainPresentor;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.view.adapter.MainExpandableMenuAdapter;
import ruiduoyi.com.skyworthpda.widget.MyExpandableListView;


public class MainActivity extends BaseActivity implements MainContact.View{

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
    private MainContact.Presentor presentor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(new Explode().setDuration(400));
        getWindow().setExitTransition(new Explode().setDuration(400));
        ButterKnife.bind(this);
        initView();
        presentor = new MainPresentor(this,this);
    }

    @Override
    protected void initView(){
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.home);
        actionBar.setTitle(" ");
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_2));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_3));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.banner_4));
        //views.add(BGABannerUtil.getItemImageView(this, R.drawable.banner_1));
        bgabBanner.setData(views);
        userName.setText("欢迎你！"+preferenUtil.getString(Config.CACHE_DATA_USERNAME));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.LEFT);
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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.ll_exit_layout_mainactivity:
                finish();
                break;
        }
    }



    @Override
    public void onLoadPermissionSecceed(List<String> groupTitles, final List<List<String>> allChildTitles, List<List<Integer>> allChildImgs) {
        MainExpandableMenuAdapter adapter=new MainExpandableMenuAdapter(MainActivity.this,R.layout.item_main_title,
                R.layout.item_main_group_title,groupTitles,allChildTitles,allChildImgs);
        elvExpandedMenu.setAdapter(adapter);
        elvExpandedMenu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (allChildTitles.get(groupPosition).get(childPosition)){
                    //首次上料
                    case Config.PERMISSION_FCL_SCSL_NAME:
                        gotoSCSL(Config.PERMISSION_FCL_SCSL_NAME);
                        break;
                    //生产续料
                    case Config.PERMISSION_FCL_SCXL_NAME:
                        gotoSCSL(Config.PERMISSION_FCL_SCXL_NAME);
                        break;
                    //上料确认
                    case Config.PERMISSION_FCL_SLQR_NAME:
                        gotoSCSL(Config.PERMISSION_FCL_SLQR_NAME);
                        break;
                    //站位调整
                    case Config.PERMISSION_FCL_ZWTZ_NAME:
                        gotoZWTZ();
                        break;
                    //品管巡检
                    case Config.PERMISSION_PZGL_PGXJ_NAME:
                        gotoPGXJ();
                        break;
                }
                return true;
            }
        });
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
        intent.putExtra(SCSLActivity.START_TYPE,startType);
        //startActivity(intent);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
}
