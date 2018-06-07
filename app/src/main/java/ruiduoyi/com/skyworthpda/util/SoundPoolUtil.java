package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Chen on 2018/6/7.
 */

public class SoundPoolUtil {
    private static final int MAX_SOUND_SIZE = 2;
    static SoundPool soundPool;
    public static void init(Context context){
        if (soundPool == null){
            soundPool = new SoundPool(MAX_SOUND_SIZE, AudioManager.STREAM_MUSIC,100);

        }
    }

}
