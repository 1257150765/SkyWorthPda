package ruiduoyi.com.skyworthpda.view.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ruiduoyi.com.skyworthpda.App;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.LoginContact;
import ruiduoyi.com.skyworthpda.model.bean.CompanyBean;
import ruiduoyi.com.skyworthpda.model.bean.LoginBean;
import ruiduoyi.com.skyworthpda.presentor.LoginPresenter;
import ruiduoyi.com.skyworthpda.util.Config;

public class LoginActivity extends BaseActivity implements LoginContact.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_img)
    CircleImageView userImg;
    @BindView(R.id.et_user_id_loginactivity)
    TextInputEditText etUserId;
    @BindView(R.id.til_user_id_layout_loginactivity)
    TextInputLayout tilUserIdLayout;
    @BindView(R.id.et_user_pwd_loginactivity)
    TextInputEditText etUserPwd;
    @BindView(R.id.tiluser_pwd_layout_loginactivity)
    TextInputLayout tiluserPwdLayout;
    @BindView(R.id.cv_login_loginactivity)
    CardView cvLogin;
    @BindView(R.id.sp_companyname_loginactivity)
    Spinner spCompanyname;
    private LoginPresenter presenter;
    private List<CompanyBean.UcDataBean> companyNameList;
    private CompanyBean.UcDataBean companyBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        presenter = new LoginPresenter(this, this);
    }


    @Override
    protected void initView() {
        etUserId.setText(preferenUtil.getString(Config.CACHE_DATA_USERCODE));
        spCompanyname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //LogWraper.d(TAG,"position:"+position);
                if (companyNameList == null){
                    return;
                }
                companyBean = companyNameList.get(position);
                App.setThemeId(position);
                preferenUtil.setInt(Config.THEME_ID,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                App.setThemeId(0);
                preferenUtil.setInt(Config.THEME_ID,0);
            }
        });
    }

    @OnClick(R.id.cv_login_loginactivity)
    public void onViewClicked() {
        cvLogin.startAnimation(anim);
        String userName = etUserId.getText().toString();
        String pwd = etUserPwd.getText().toString();
        if (null == companyBean){
            Snackbar.make(getWindow().getDecorView(),"系统名称不能为空",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(userName)) {
            tilUserIdLayout.setError("账号不能为空");
            tilUserIdLayout.setErrorEnabled(true);
            return;
        }
        if ("".equals(pwd)) {
            tiluserPwdLayout.setError("密码不能为空");
            tiluserPwdLayout.setErrorEnabled(true);
            return;
        }
        tilUserIdLayout.setErrorEnabled(false);
        tiluserPwdLayout.setErrorEnabled(false);
        //presenter.login(companyBean.getSrvID(),userName, pwd);
        presenter.checkUpdate(companyBean.getSrvID());
    }



    @Override
    public void onLoadConpanyNameSucceed(List<CompanyBean.UcDataBean> companyNameList) {
        this.companyNameList = companyNameList;
        String code = preferenUtil.getString(Config.CACHE_DATA_COMPANYCODE);
        List<String> data = new ArrayList<>(companyNameList.size());
        for (CompanyBean.UcDataBean bean:companyNameList){
            data.add(bean.getSrvName());
        }
        spCompanyname.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, data));
        for (int i = 0; i < companyNameList.size(); i++) {
            if (code.equals(companyNameList.get(i).getSrvID())){
                spCompanyname.setSelection(i);
            }
        }
    }

    @Override
    public void onLoginSecceed(String companyCode, LoginBean.UcDataBean ucDataBean) {
        preferenUtil.setString(Config.CACHE_DATA_USERNAME, ucDataBean.getUsr_yhmc());
        preferenUtil.setString(Config.CACHE_DATA_USERCODE, ucDataBean.getUsr_yhdm());
        preferenUtil.setString(Config.CACHE_DATA_COMPANYCODE, companyCode);
        preferenUtil.setString(Config.CACHE_DATA_COMPANYNAME, ucDataBean.getUsr_gsmc());
        preferenUtil.setString(Config.CACHE_DATA_USERTOKEN, ucDataBean.getUsr_tokenid());
        preferenUtil.setString(Config.CACHE_DATA_BM, ucDataBean.getUsr_bmmc());
        startActivity(new Intent(this, MainActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        LoginActivity.this.finish();
    }

    @Override
    public void onCheckUpdateSucceed(boolean hasUpdate, final String url) {
        final String userName = etUserId.getText().toString();
        final String pwd = etUserPwd.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        if (hasUpdate) {
            builder.setCancelable(false)
                    .setMessage("发现新版本，点击确定开始更新")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.update(url);
                        }
                    });
        } else {
            builder.setCancelable(true)
                    .setMessage("未发现新版本，是否更新")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.update(url);
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.login(companyBean.getSrvID(),userName, pwd);
                        }
                    });
        }
        builder.create().show();
    }
}
