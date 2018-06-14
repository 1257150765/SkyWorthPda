package ruiduoyi.com.skyworthpda.view.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

/**
 * Created by Chen on 2018/6/14.
 */

public abstract class BaseStatuScanActivity extends BaseScanActivity {
    private AlertDialog statuDialog;
    private ImageView ivSucceed;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statuDialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .create();
        handler = new Handler();
        ivSucceed = new ImageView(this);
        ivSucceed.setImageResource(R.mipmap.ok);
    }
    private void showSucceedStatu() {
        statuDialog.setView(ivSucceed);
        statuDialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (statuDialog.isShowing()) {
                    statuDialog.dismiss();
                }
            }
        }, Config.STATU_DIALOG_SHOW_TIME);
    }


    @Override
    public void onExecuteSucceed() {
        SoundPoolUtil.playOK();
        showSucceedStatu();
    }

    @Override
    public void onLoading(boolean isLoading) {
        if (isLoading){
            if (statuDialog.isShowing()){
                statuDialog.dismiss();
            }
        }
        super.onLoading(isLoading);

    }

    @Override
    protected void onDestroy() {
        if (statuDialog.isShowing()){
            statuDialog.dismiss();
        }
        super.onDestroy();

    }
}
