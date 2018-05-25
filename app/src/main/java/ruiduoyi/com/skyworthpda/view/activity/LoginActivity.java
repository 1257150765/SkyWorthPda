package ruiduoyi.com.skyworthpda.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ruiduoyi.com.skyworthpda.App;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.LoginContact;
import ruiduoyi.com.skyworthpda.presentor.LoginPresenter;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

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
    @BindView(R.id.cb_remember_loginactivity)
    CheckBox cbRemember;
    @BindView(R.id.cv_login_loginactivity)
    CardView cvLogin;
    private LoginPresenter presenter;
    private boolean isChecked = true;
    private int firstColor = Config.COLOR_FIRST_GREEN;

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

        if (preferenUtil.getBoolean(Config.CACHE_DATA_REMEMBER)){
            etUserId.setText(preferenUtil.getString(Config.CACHE_DATA_USERNAME));
            etUserPwd.setText(preferenUtil.getString(Config.CACHE_DATA_PWD));
        }
        cbRemember.setChecked(true);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginActivity.this.isChecked = isChecked;
            }
        });

    }

    @OnClick(R.id.cv_login_loginactivity)
    public void onViewClicked() {
        cvLogin.startAnimation(anim);
        String userName = etUserId.getText().toString();
        String pwd = etUserPwd.getText().toString();
        if ("".equals(userName)){
            tilUserIdLayout.setError("账号不能为空");
            tilUserIdLayout.setErrorEnabled(true);
            return;
        }
        if ("".equals(pwd)){
            tiluserPwdLayout.setError("密码不能为空");
            tiluserPwdLayout.setErrorEnabled(true);
            return;
        }
        tilUserIdLayout.setErrorEnabled(false);
        tiluserPwdLayout.setErrorEnabled(false);
        presenter.login(userName,pwd);
    }

    @Override
    public void onLoginSecceed(String userName,String pwd) {
        preferenUtil.setBoolean(Config.CACHE_DATA_REMEMBER,isChecked);
        if (isChecked){
            preferenUtil.setString(Config.CACHE_DATA_USERNAME,userName);
            preferenUtil.setString(Config.CACHE_DATA_PWD,pwd);
        }else {
            preferenUtil.setString(Config.CACHE_DATA_USERNAME,"");
            preferenUtil.setString(Config.CACHE_DATA_PWD,"");
        }
        startActivity(new Intent(this, MainActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        //startActivity(new Intent(LoginActivity.this,MainActivity.class));
        LoginActivity.this.finish();
    }

    @Override
    public void onLoginFalse(String userNameErrorInfo, String pwdErrorInfo) {
        if (!"".equals(userNameErrorInfo)){
            tilUserIdLayout.setError(userNameErrorInfo);
            tilUserIdLayout.setErrorEnabled(true);
        }
        if (!"".equals(pwdErrorInfo)){
            tiluserPwdLayout.setError(pwdErrorInfo);
            tiluserPwdLayout.setErrorEnabled(true);
        }
    }
}
