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
    private static int ng;
    static SoundPool soundPool;
    private static int ok;

    public static void init(Context context){
        if (soundPool == null){
            soundPool = new SoundPool(MAX_SOUND_SIZE, AudioManager.STREAM_MUSIC,100);
        }
        ng = soundPool.load(context, R.raw.ng, 1);
        ok = soundPool.load(context, R.raw.ok, 1);
    }

    public static void playOK(){
        soundPool.play(ok,0.8f,0.8f,1,0,1f);
    }
    public static void playNG(){
        soundPool.play(ng,0.8f,0.8f,1,0,1f);
    }

}
