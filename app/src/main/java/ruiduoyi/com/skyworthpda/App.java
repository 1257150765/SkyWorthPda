package ruiduoyi.com.skyworthpda;

import android.app.Application;

import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

/**
 * Created by Chen on 2018/5/24.
 */

public class App extends Application {
    public static int themeId = R.style.AppTheme1;
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenUtil preferenUtil = new PreferenUtil(this);
        int themeId = preferenUtil.getInt(Config.THEME_ID);
        setThemeId(themeId);
        LogWraper.d("App","初始化this.themeId:"+this.themeId);
    }
    public static void setThemeId(int i){
        if (i == 0){
            themeId = R.style.AppTheme1;
        }else if(i ==1){
            themeId = R.style.AppTheme2;
        }else if(i ==2){
            themeId = R.style.AppTheme3;
        }else if(i ==3){
            themeId = R.style.AppTheme4;
        }
    }
}
