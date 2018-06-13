package ruiduoyi.com.skyworthpda.view.activity;

/**
 * Created by Chen on 2018/6/13.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.util.Config;

/**
 * 压缩机绑定，控制器/电器盒绑定,(简称绑定)
 */
public class BDActivity extends BaseScanActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String startType;

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
        switch (startType){

        }
    }

    @Override
    protected void onReceiveCode(String code) {

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
