package ruiduoyi.com.skyworthpda.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import ruiduoyi.com.skyworthpda.App;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.EDAScanUtil;
import ruiduoyi.com.skyworthpda.util.LogWraper;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;

import static ruiduoyi.com.skyworthpda.App.edaScanUtil;

/**
 * 封装了扫描功能，（）
 * Created by Chen on 2018/5/7.
 */
public abstract class BaseScanActivity extends BaseActivity implements EDAScanUtil.EDAScanListener {
    private static final String CODE_RECEIVER_ACTION = Config.CUSTOM_NAME;
    private static final String TAG = BaseScanActivity.class.getSimpleName();
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (CODE_RECEIVER_ACTION.equals(intent.getAction())){
                if (tipsDialog.isShowing()){
                    tipsDialog.dismiss();
                }
                String barcode = intent.getStringExtra("scannerdata");
                LogWraper.d(TAG,barcode);
                onReceiveCode(barcode);
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (edaScanUtil == null){
            IntentFilter filter = new IntentFilter(CODE_RECEIVER_ACTION);
            registerReceiver(receiver, filter);
        }else {
            LogWraper.d(TAG,"监听器");
            edaScanUtil.start(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (edaScanUtil == null){
            unregisterReceiver(receiver);
        }else {
            edaScanUtil.stop();
        }
    }
    protected abstract void onReceiveCode(String code);

    @Override
    public void onExecuteSucceed() {
        super.onExecuteSucceed();
    }

    @Override
    public void onScanError() {
        super.onScanError();
    }

    @Override
    public void onScanSucceed(String code) {
        LogWraper.d(TAG,"EDA60K:"+code);
        onReceiveCode(code);
    }
}
