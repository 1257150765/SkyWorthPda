package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;
import android.os.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.MainContact;
import ruiduoyi.com.skyworthpda.model.bean.PermissionBean;
import ruiduoyi.com.skyworthpda.model.bean.UpdateBean;
import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.DownloadService;
import ruiduoyi.com.skyworthpda.util.DownloadUtils;

/**
 * Created by Chen on 2018/5/8.
 */

public class MainPresentor implements MainContact.Presentor {
    private MainContact.View view;
    private Context context;
    private PreferenUtil preferenUtil;
    public MainPresentor(MainContact.View view, Context context) {
        this.view = view;
        this.context = context;
        preferenUtil = new PreferenUtil(context);
        loadPermission();
    }

    @Override
    public void loadPermission() {
        RetrofitManager.getPermission()
                .subscribe(new Observer<PermissionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PermissionBean value) {
                        if (value.isUtStatus()){
                            List<PermissionBean.UcDataBean> ucDatas = value.getUcData();
                            List<PermissionBean.UcDataBean> titles = new ArrayList<>();
                            List<List<PermissionBean.UcDataBean>> childs = new ArrayList<>();
                            for (PermissionBean.UcDataBean bean : ucDatas) {
                                //当没有收录并且是第一层时
                                if (!titles.contains(bean) && bean.getG_px() == 1){
                                    titles.add(bean);
                                }
                                if (bean.getG_px() == 2){
                                    for (int i=0; i<titles.size();i++){
                                        PermissionBean.UcDataBean bean2 = titles.get(i);
                                        if (bean2.getG_mkdm() == bean.getG_mkdm()){
                                            if (childs.size() <= i){
                                                List<PermissionBean.UcDataBean> list = new ArrayList<>();
                                                list.add(bean);
                                                childs.add(i,list);
                                            }else {
                                                childs.get(i).add(bean);
                                            }
                                        }
                                    }
                                }
                            }
                            view.onLoadPermissionSecceed(titles,childs);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //view.onShowTipsDailog("加载成功");
    }

    @Override
    public void checkUpdate() {
        view.onLoading(true);
        RetrofitManager.checkUpdate().subscribe(new Observer<UpdateBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UpdateBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    UpdateBean.UcDataBean bean = value.getUcData().get(0);
                    if (DownloadService.haveNewVersion(context,bean.getV_SrvVer())){
                        view.onCheckUpdateSucceed(true,bean.getV_UpAddr());
                    }else {
                        view.onCheckUpdateSucceed(false,bean.getV_UpAddr());
                    }
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("更新出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void update(String url) {
        DownloadUtils downloadUtils = new DownloadUtils(context);
        downloadUtils.downloadAPK(url, Environment.getExternalStorageDirectory().getPath(),"App.apk")
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        view.onUpdate(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.onShowTipsDailog("更新失败");
                    }

                    @Override
                    public void onComplete() {
                        view.onUpdateSucceed();
                    }
                });
    }

}
