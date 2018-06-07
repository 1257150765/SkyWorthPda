package ruiduoyi.com.skyworthpda.presentor;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.LoginContact;
import ruiduoyi.com.skyworthpda.model.bean.CompanyBean;
import ruiduoyi.com.skyworthpda.model.bean.LoginBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.DownloadService;

/**
 * Created by DengJf on 2018/1/12.
 */

public class LoginPresenter implements LoginContact.Presentor {
    private LoginContact.View view;
    private Context context;
    private BroadcastReceiver receiver;

    public LoginPresenter(LoginContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadCompanyName();
    }

    @Override
    public void login(final String companyName, final String userName, final String pwd) {
        view.onLoading(true);
        final String userNameErrorInfo = "";
        final String pwdErrorInfo = "";
        RetrofitManager.login(companyName,userName,pwd).subscribe(new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean value) {
                view.onLoading(false);
                if (value.isUtStatus()) {
                    view.onLoginSecceed(companyName,value.getUcData().get(0));
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("登录出错");
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void loadCompanyName() {
        view.onLoading(true);
        RetrofitManager.getCompanyList().subscribe(new Observer<CompanyBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CompanyBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadConpanyNameSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("获取公司名称失败");
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void checkUpdate(String companyCode) {

    }

    @Override
    public void update(String url) {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long data = intent.getLongExtra(DownloadService.EXTENDED_DATA_STATUS,0L);
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/RdyPDA.apk")),
                        "application/vnd.android.package-archive");
                context.startActivity(intent);
                ((Activity)(context)).finish();
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, new IntentFilter(DownloadService.BROADCAST_ACTION));
        //FirstActivity.this.registerReceiver(receiver,new IntentFilter(DownloadService.BROADCAST_ACTION));
        //presenter.downloadInstallApk(url);
        Intent serviceIntent = new Intent(context,DownloadService.class);
        //将下载地址url放入intent中
        serviceIntent.setData(Uri.parse(url));
        context.startService(serviceIntent);
    }
    /**
     * 是否有新版本
     * @param newVersion
     * @return
     */
    private boolean haveNewVersion(String newVersion) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String oldVersion = pi.versionName;
        boolean isHaveNewVersion = false;
        //LogWraper.d(TAG, "haveNewVersion: newVersion"+newVersion);
        //LogWraper.d(TAG, "haveNewVersion: oldVersion"+oldVersion);
        String[] oldVersionArr = oldVersion.split("\\.");
        String[] newVersionArr = newVersion.split("\\.");
        try {
            //Log.d(TAG, "haveNewVersion: length"+oldVersionArr.length);
            for (int i=0;i<oldVersionArr.length; i++){
                if((Integer.parseInt(newVersionArr[i])) > (Integer.parseInt(oldVersionArr[i]))){
                    isHaveNewVersion = true;
                }else if ((Integer.parseInt(newVersionArr[i])) == (Integer.parseInt(oldVersionArr[i]))){
                    continue;
                }else{
                    break;
                }
            }
        }catch (Exception e){
            return false;
        }
        return isHaveNewVersion;
    }
}
