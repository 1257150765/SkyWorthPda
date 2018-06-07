package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.ZWTZContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckZWBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.model.bean.ZWTZBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

/**
 * Created by Chen on 2018/5/14.
 */

public class ZWTZPresentor implements ZWTZContact.Presentor {
    private static final String TAG = ZWTZPresentor.class.getSimpleName();
    private ZWTZContact.View view;
    private Context context;

    public ZWTZPresentor(ZWTZContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadXb();
    }

    @Override
    public void loadXb() {
        view.onLoading(true);
        RetrofitManager.getXb().subscribe(new Observer<XbBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(XbBean value) {
                view.onLoading(false);
                LogWraper.d(TAG,""+value);
                if (value.isUtStatus()){
                    view.onLoadXbSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                //e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("加载线别出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void checkZw(final String zw) {
        view.onLoading(true);
        RetrofitManager.checkZW(Config.CHECK_TYPE_ZWM,zw).subscribe(new Observer<CheckZWBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckZWBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCheckZwSucceed(zw);
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("站位验证失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void zwtz(String xbm_xbdm, String oldZw, String newZw) {
        view.onLoading(true);
        RetrofitManager.zwtz(xbm_xbdm,oldZw,newZw).subscribe(new Observer<ZWTZBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZWTZBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){

                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("调整站位出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
