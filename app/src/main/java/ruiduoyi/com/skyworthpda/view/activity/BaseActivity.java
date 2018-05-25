package ruiduoyi.com.skyworthpda.view.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import ruiduoyi.com.skyworthpda.App;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.BaseContact;
import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;

/**
 * 封装了加载动画，缓存等
 */
public  abstract class BaseActivity extends AppCompatActivity implements BaseContact.View{
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected PreferenUtil preferenUtil;
    protected Animation anim;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(App.themeId);
        preferenUtil = new PreferenUtil(this);
        anim= AnimationUtils.loadAnimation(this, R.anim.btn_apha);
        View view = LayoutInflater.from(this).inflate(R.layout.loading, null, false);
        dialog = new AlertDialog.Builder(this,R.style.transBg)
                .setView(view)
                .setCancelable(false)
                .create();
    }

    protected abstract void initView();

    @Override
    public void onLoading(boolean isLoading) {
        if (null == dialog){
            return;
        }
        if (isLoading){
            if (!dialog.isShowing()){
                dialog.show();
            }
        }else {
            dialog.dismiss();
        }
    }

}
