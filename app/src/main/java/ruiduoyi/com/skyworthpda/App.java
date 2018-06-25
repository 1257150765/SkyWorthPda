package ruiduoyi.com.skyworthpda;

import android.app.Application;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import com.honeywell.aidc.AidcManager;
import com.tencent.bugly.crashreport.CrashReport;

import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.util.CatchExceptionUtil;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.EDAScanUtil;
import ruiduoyi.com.skyworthpda.util.LogWraper;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;
import ruiduoyi.com.skyworthpda.util.Util;

/**
 * Created by Chen on 2018/5/24.
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    public static int themeId = R.style.AppTheme1;
    public static EDAScanUtil edaScanUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        //CrashReport.initCrashReport(getApplicationContext(), "f25ebf3d0c", true);
        CatchExceptionUtil.getInstance().init(this,true,false);
        SoundPoolUtil.init(this);
        PreferenUtil preferenUtil = new PreferenUtil(this);
        boolean b = preferenUtil.getBoolean(Config.IS_INIT);
        //如果没有初始化
        if (!b){
            Util.init(this);
            preferenUtil.setBoolean(Config.IS_INIT,true);
        }
        int themeId = preferenUtil.getInt(Config.THEME_ID);
        setThemeId(themeId);
        LogWraper.d("App","初始化this.themeId:"+this.themeId);
        Log.d(TAG, "onCreate: "+Build.MODEL);
        //如果是EDA60K
        if (Build.MODEL.equals("EDA60K")){

            edaScanUtil = new EDAScanUtil(this);
        }
    }
    public static void setThemeId(int i){
        if (i%2 == 0){
            themeId = R.style.AppTheme2;
        }else if(i ==1){
            themeId = R.style.AppTheme1;
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (edaScanUtil != null){
            edaScanUtil.onDestroy();
        }
    }
}
