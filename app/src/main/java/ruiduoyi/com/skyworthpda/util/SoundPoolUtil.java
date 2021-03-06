package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import ruiduoyi.com.skyworthpda.R;

/**
 * Created by Chen on 2018/6/7.
 */

public class SoundPoolUtil {
    private static final int MAX_SOUND_SIZE = 2;
    private static int scanError;
    static SoundPool soundPool;
    private static int ok;
    private static int ng;
    private static int blp;

    public static void init(Context context){
        if (soundPool == null){
            soundPool = new SoundPool(MAX_SOUND_SIZE, AudioManager.STREAM_MUSIC,100);
        }
        scanError = soundPool.load(context, R.raw.scanerror, 1);
        ok = soundPool.load(context, R.raw.ok, 1);
        ng = soundPool.load(context, R.raw.ng, 1);
        //不良品提交成功
        blp = soundPool.load(context, R.raw.buliangpin_ok, 1);
    }

    public static void playOK(){
        soundPool.play(ok,1.0f,1.0f,1,0,1f);
    }
    public static void playScanError(){
        soundPool.play(scanError,1.0f,1.0f,1,0,1f);
    }
    public static void playNG(){
        soundPool.play(ng,1.0f,1.0f,1,0,1f);
    }
    public static void playBlp(){
        soundPool.play(blp,1.0f,1.0f,1,0,1f);
    }

}
