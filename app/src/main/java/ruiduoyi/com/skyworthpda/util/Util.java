package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Chen on 2018/6/5.
 */

public class Util {

    public static  int getFColor(String colorStr){
        int color = Color.BLACK;
        switch (colorStr){
            case "Y":
                color = Color.YELLOW;
                break;
            case "R":
                color = Color.RED;
                break;
            case "V":
                color = Color.parseColor("#A757A8");
                break;
        }
        return color;
    }

    public static  int getBColor(String colorStr){
        int color = Color.WHITE;
        switch (colorStr){
            case "Y":
                color = Color.YELLOW;
                break;
            case "R":
                color = Color.RED;
                break;
            case "V":
                color = Color.parseColor("#A757A8");
                break;
        }
        return color;
    }

    public static void init(Context context) {
        Intent intent = new Intent(Config.BROADCAST_SETTING);
        // 修改扫描工具内应用设置中的开发者项下的广播名称
        intent.putExtra(Config.BROADCAST_KEY, Config.CUSTOM_NAME);
        // 修改扫描工具内应用设置下的条码发送方式为 "广播"
        intent.putExtra(Config.SEND_KEY, "BROADCAST");
        // 修改扫描工具内应用设置下的结束符为 "NONE"
        intent.putExtra(Config.END_KEY, "NONE");
        context.sendBroadcast(intent);
    }
    public static void startToAppDetail(Context context){
        //跳到详情，让用户授予权限
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(localIntent);
    }
    public static String getMac(Context context) {
        String mac = "";
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        mac = info.getMacAddress();
        return mac;
    }
}
