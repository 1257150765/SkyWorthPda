package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chen on 2018/04/25.
 */

public class DownloadUtils {
    private Context mContext;

    public DownloadUtils(Context mContext) {
        this.mContext = mContext;
    }

    public io.reactivex.Observable<Integer> downloadAPK(final String url_str, final String filePath, final String fileName){
        return io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                URL url= null;
                File file=new File(filePath);
                if (!file.exists()){
                    file.mkdir();
                }
                url = new URL(url_str);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000);
                urlConnection.connect();
                double fileSize=urlConnection.getContentLength()/1024;
                Log.e("getLastModified()",urlConnection.getLastModified()+"");
                InputStream in=urlConnection.getInputStream();
                OutputStream out=new FileOutputStream(filePath+"/"+fileName,false);
                byte[] buff=new byte[1024];
                int downloadSize=0;
                int size;
                while ((size = in.read(buff)) != -1) {
                    downloadSize++;
                    if (downloadSize%100==0){
                        int progress=(int) (downloadSize/fileSize*100);
                        if (progress<100)
                            e.onNext(progress);
                        Log.e("download",downloadSize/fileSize+"");
                    }
                    out.write(buff, 0, size);
                }
                e.onComplete();
                installAPK(filePath+"/"+fileName);

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //下载到本地后执行安装
    private void installAPK(String filePath) {
        //获取下载文件的Uri
        File file = new File(filePath);
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data;
            // 判断版本大于等于7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
                //清单文件中配置的authorities
                data = FileProvider.getUriForFile(mContext, "com.ruiduoyi.FileProvider", file);
                // 给目标应用一个临时授权
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//重点！！
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//重点！！！
                data = Uri.fromFile(file);
            }
            intent.setDataAndType(data, "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        } else {
            Log.e("ruin", file.getName()+"文件不存在!" );
        }

    }
}
