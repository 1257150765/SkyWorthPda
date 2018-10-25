package ruiduoyi.com.skyworthpda.view.activity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
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
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

/**
 * 封装了加载动画，缓存等
 */
public  abstract class BaseActivity extends AppCompatActivity implements BaseContact.View{
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected PreferenUtil preferenUtil;
    protected Animation anim;
    private AlertDialog loadingDialog;
    protected AlertDialog tipsDialog;
    private Toast tipsToast;
    private ImageView ivSucceed;
    private ImageView ivFail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.themeId);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        if (tipsDialog != null && tipsDialog.isShowing()){
            tipsDialog.dismiss();
        }
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
        showSnakeBar(getWindow().getDecorView(),tips);
    }
    public void showSnakeBar(View view,String tips){
        Snackbar.make(view,tips,Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 执行成功，有语音，有弹出框
     * */
    @Override
    public void onExecuteSucceed() {
        SoundPoolUtil.playOK();
        tipsToast.setView(ivSucceed);
        tipsToast.show();
        //showSucceedStatu();
    }
    /**
     * 执行成功,只有语音，没有弹出框（绿色标识）
     */
    @Override
    public void onExecuteSucceed2() {
        SoundPoolUtil.playOK();
        //showSucceedStatu();
    }


    /**
     * 扫描二维码时验证出错，会提示
     */
    @Override
    public void onScanError() {
        SoundPoolUtil.playScanError();
    }

    /**
     * 执行失败
     */
    @Override
    public void onExecuteFalse() {
        SoundPoolUtil.playNG();
        //tipsToast.setView(ivFail);
        //tipsToast.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
