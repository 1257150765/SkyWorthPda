package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.model.bean.UploadLogBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;

/**
 * Created by Chen on 2018/6/15.
 */

public class CatchExceptionUtil implements UncaughtExceptionHandler{
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/rdy/log/";
    private static final String FILE_NAME = "crash";

    //log文件的后缀名
    private static final String FILE_NAME_SUFFIX = ".txt";

    private static CatchExceptionUtil sInstance = new CatchExceptionUtil();

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;
    private boolean isSvaeToSDCard;
    private boolean isUploadToServer;

    //构造方法私有，防止外部构造多个实例
    private CatchExceptionUtil() {
    }

    public static CatchExceptionUtil getInstance() {
        return sInstance;
    }

    public void init(Context context,boolean isSvaeToSDCard, boolean isUploadToServer){
        this.isSvaeToSDCard = isSvaeToSDCard;
        this.isUploadToServer = isUploadToServer;
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
        String phoneInfo = getPhoneInfo();
        //导出异常信息到SD卡中
        if (isSvaeToSDCard) {
            saveExceptionToSDCard(phoneInfo,e);
        }
        //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
        if (isUploadToServer) {
            uploadExceptionToServer(phoneInfo,e);
        }
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if(isUploadToServer) {
            //如果要上传到服务器，则上传成功后再结束进程
            new Handler(mContext.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.exit(0);
                }
            },10000);
        }else {
            if (mDefaultCrashHandler != null) {
                mDefaultCrashHandler.uncaughtException(t, e);
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }
    private String getPhoneInfo(){
        StringBuilder sb = new StringBuilder();
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        sb.append("App Version:" + pi.versionName + "_" + pi.versionCode);
        //Android版本号
        sb.append("OS Version:" + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);
        return sb.toString();
    }

    private void saveExceptionToSDCard(String phoneInfo, Throwable e) {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                LogWraper.d(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        //以当前时间创建log文件
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //导出发生异常的时间
            pw.println(time);
            pw.println(phoneInfo);
            //导出手机信息
            //dumpPhoneInfo(pw);
            pw.println();
            //导出异常的调用栈信息
            e.printStackTrace(pw);
            pw.close();
        } catch (Exception e2) {
            LogWraper.e(TAG, "dump crash info failed");
        }
    }

    private void uploadExceptionToServer(String phoneInfo, Throwable e) {
        String log = e.getMessage()
                .replace('{',' ')
                .replace('}',' ')+"--"+phoneInfo;

        //log.replaceAll("\\{","");
        //log.replaceAll("}","");
        LogWraper.d(TAG,log);
        RetrofitManager.uploadLog(getMac(),log).subscribe(new Observer<UploadLogBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UploadLogBean value) {
                //android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }

            @Override
            public void onError(Throwable e) {
                //android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private String getMac() {
        String mac = "";
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        mac = info.getMacAddress();
        return mac;
    }


    public void deleteLogFile(){
        File file = new File(PATH);
        if (file.isDirectory() && file.exists()){
            File[] files = file.listFiles();
            for (File f : files){
                f.delete();
            }
        }
        LogWraper.d(TAG,"删除日志");
    }
}
