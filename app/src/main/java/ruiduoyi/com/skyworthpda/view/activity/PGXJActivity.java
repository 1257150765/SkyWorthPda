package ruiduoyi.com.skyworthpda.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.PGXJContact;
import ruiduoyi.com.skyworthpda.model.bean.PGXJBean;
import ruiduoyi.com.skyworthpda.presentor.PGXJPresentor;
import ruiduoyi.com.skyworthpda.view.adapter.PGXJAdapter;

/**
 * Created by Chen on 2018/5/14.
 */

/**
 * 品管巡检
 */
public class PGXJActivity extends BaseScanActivity implements PGXJContact.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_xb_pgxjactivity)
    Spinner spXb;
    @BindView(R.id.et_jyzw_pgxjactivity)
    EditText etJyzw;
    @BindView(R.id.et_zwcx_pgxjactivity)
    EditText etZwcx;
    @BindView(R.id.et_code_pgxjactivity)
    EditText etCode;
    @BindView(R.id.rv_recycler_pgxjactivity)
    RecyclerView rvRecycler;
    private ArrayAdapter<String> xbAdapter;
    private List<String> xbData;
    private PGXJContact.Presentor presentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgxj);
        ButterKnife.bind(this);
        initView();
        presentor = new PGXJPresentor(this,this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("品管巡检");
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
    public void onLoadXbSucceed(List<String> xbData) {
        this.xbData = xbData;
        xbAdapter = new ArrayAdapter<String>(PGXJActivity.this, R.layout.item_spinner, xbData);
        spXb.setAdapter(xbAdapter);
    }

    @Override
    public void onLoadDataSucceed(String jjzwStr, List<PGXJBean> data) {
        etJyzw.setText(jjzwStr);
        PGXJAdapter adapter = new PGXJAdapter(data);
        rvRecycler.setAdapter(adapter);
        rvRecycler.setLayoutManager(new LinearLayoutManager(PGXJActivity.this));
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
