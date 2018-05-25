package ruiduoyi.com.skyworthpda;

import android.app.Application;

import ruiduoyi.com.skyworthpda.util.LogWraper;

/**
 * Created by Chen on 2018/5/24.
 */

public class App extends Application {
    public static int themeId = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        int themeId = 2;
        if (themeId == 1){
            this.themeId = R.style.AppTheme1;
        }else if(themeId ==2){
            this.themeId = R.style.AppTheme2;
        }
        LogWraper.d("App","初始化this.themeId:"+this.themeId);
    }
}
