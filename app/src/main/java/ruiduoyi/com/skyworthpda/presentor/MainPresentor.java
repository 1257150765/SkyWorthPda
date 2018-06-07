package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.MainContact;
import ruiduoyi.com.skyworthpda.model.bean.PermissionBean;
import ruiduoyi.com.skyworthpda.model.ceche.PreferenUtil;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.Config;

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

    }
    
}
