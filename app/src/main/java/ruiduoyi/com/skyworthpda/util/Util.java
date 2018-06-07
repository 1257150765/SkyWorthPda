package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import ruiduoyi.com.skyworthpda.App;

/**
 * Created by Chen on 2018/6/5.
 */

public class Util {

    public int getColor(String colorStr){
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
}