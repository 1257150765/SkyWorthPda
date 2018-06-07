package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.WLXXContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.WLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.XLZWBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;

/**
 * Created by Chen on 2018/6/6.
 */

public class WLXXPresentor implements WLXXContact.Presentor {
    private Context context;
    private WLXXContact.View view;

    public WLXXPresentor(Context context, WLXXContact.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 加载下料站位
     * @param xb
     * @param qrcode
     */
    @Override
    public void loadXXZW(String xb, String qrcode) {
        view.onLoading(true);
        RetrofitManager.getXLZW(xb,qrcode).subscribe(new Observer<XLZWBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(XLZWBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadXXZWSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("加载下料站位出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 检查二维码
     * @param key_chkval
     */
    @Override
    public void checkQRCODE(String key_chkval) {
        view.onLoading(true);
        RetrofitManager.checkQRCODE("QRCODE",key_chkval).subscribe(new Observer<CheckQRCODEBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckQRCODEBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCheckQRCODESucceed(value.getUcData().get(0));
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("二维码验证出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 执行物料下线
     * @param wlxxType
     * @param xb
     * @param v_oricode
     * @param xlzw
     */
    @Override
    public void wlxx(String wlxxType, String xb, String v_oricode, String xlzw) {
        view.onLoading(true);
        RetrofitManager.wlxx(wlxxType,xb,v_oricode,xlzw).subscribe(new Observer<WLXXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WLXXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){

                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("下料出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
