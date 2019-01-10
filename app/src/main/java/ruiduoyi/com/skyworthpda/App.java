package ruiduoyi.com.skyworthpda;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.CatchExceptionUtil;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.EDAScanUtil;
import ruiduoyi.com.skyworthpda.util.LogWraper;
import ruiduoyi.com.skyworthpda.util.NewLandScanUtil;
import ruiduoyi.com.skyworthpda.util.SoundPoolUtil;
import ruiduoyi.com.skyworthpda.util.Util;

/**
 * Created by Chen on 2018/5/24.
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    public static int themeId = R.style.AppTheme1;
    public static EDAScanUtil edaScanUtil;
    public static NewLandScanUtil newLandScanUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.MAC = Util.getMac(this);
        //CrashReport.initCrashReport(getApplicationContext(), "f25ebf3d0c", true);
        CatchExceptionUtil.getInstance().init(this,true,false);
        SoundPoolUtil.init(this);
        PreferenUtil preferenUtil = new PreferenUtil(this);
        int themeId = preferenUtil.getInt(Config.THEME_ID);
        setThemeId(themeId);
        LogWraper.d("App","初始化this.themeId:"+this.themeId);
        //Log.d(TAG, "onCreate: "+Build.MODEL);
        //如果是EDA60K
        if (Build.MODEL.equals("EDA60K")){
            edaScanUtil = new EDAScanUtil(this);
        }else if ("PDT-90P".equals(Build.MODEL)){
            boolean b = preferenUtil.getBoolean(Config.IS_INIT);
            //如果没有初始化
            if (!b){
                Util.init(this);
                preferenUtil.setBoolean(Config.IS_INIT,true);
            }
        }else if ("NLS-M620".equals(Build.MODEL)){//新大陆的pda（亚伦）
            //Log.d(TAG, "onCreate: "+Build.MODEL);
            newLandScanUtil = new NewLandScanUtil(this);
        }else {
            Toast.makeText(this,"程序不支持此类型的终端，会出现无法扫描的情况",Toast.LENGTH_LONG).show();
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
