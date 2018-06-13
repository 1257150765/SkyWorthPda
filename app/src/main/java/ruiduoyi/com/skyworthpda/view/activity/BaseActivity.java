package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ruiduoyi.com.skyworthpda.App;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.BaseContact;
import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

/**
 * 封装了加载动画，缓存等
 */
public  abstract class BaseActivity extends AppCompatActivity implements BaseContact.View{
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected PreferenUtil preferenUtil;
    protected Animation anim;
    private AlertDialog loadingDialog;
    private AlertDialog tipsDialog;
    private Toast tipsToast;
    private ImageView ivSucceed;
    private ImageView ivFail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.themeId);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        preferenUtil = new PreferenUtil(getApplicationContext());
        anim= AnimationUtils.loadAnimation(this, R.anim.btn_apha);
        View view = LayoutInflater.from(this).inflate(R.layout.loading, null, false);
        loadingDialog = new AlertDialog.Builder(this,R.style.transBg)
                .setView(view)
                .setCancelable(false)
                .create();

        tipsDialog = new AlertDialog.Builder(this)
                //.setView(R.layout.loading)
                .setCancelable(true)
                .setTitle("温馨提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        tipsToast = new Toast(this);
        tipsToast.setDuration(Toast.LENGTH_SHORT);
        tipsToast.setGravity(Gravity.CENTER,0,0);
        ivSucceed = new ImageView(this);
        ivSucceed.setImageResource(R.mipmap.ok);
        ivFail = new ImageView(this);
        ivFail.setImageResource(R.mipmap.error);
    }

    protected abstract void initView();

    @Override
    public void onLoading(boolean isLoading) {
        if (null == loadingDialog){
            return;
        }
        if (isLoading){
            if (!loadingDialog.isShowing()){
                loadingDialog.show();
                WindowManager.LayoutParams params = loadingDialog.getWindow().getAttributes();
                params.height = 250;
                params.width = 250;
                loadingDialog.getWindow().setAttributes(params);
            }

        }else {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onShowTipsDailog(String tips) {
        if (null == tipsDialog){
            return;
        }
        tipsDialog.setMessage(tips);
        tipsDialog.show();
    }

    public void showSnakeBar(String tips){
        Snackbar.make(getWindow().getDecorView(),tips,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onExecuteSucceed() {
        SoundPoolUtil.playOK();
        tipsToast.setView(ivSucceed);
        tipsToast.show();
    }

    @Override
    public void onScanError() {

    }

    @Override
    public void onExecuteFalse() {
        /*tipsToast.setView(ivFail);
        tipsToast.show();*/
    }
}
