package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.BDContact;
import ruiduoyi.com.skyworthpda.model.bean.GzBean;
import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;

/**
 * Created by Chen on 2018/6/14.
 */

public class BDPresentor implements BDContact.Presentor {
    private Context context;
    private BDContact.View view;

    public BDPresentor(Context context, BDContact.View view) {
        this.context = context;
        this.view = view;
        loadZZXB();
    }



    @Override
    public void loadZZXB() {
        view.onLoading(true);
        RetrofitManager.getZZXB().subscribe(new Observer<XbBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(XbBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadZZXBSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("加载线别出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 扫描到条码后调用接口 后台根据绑定类型做逻辑 (取消扫描，code传CANCEL)
     * @param xbm_xbdm 线别
     * @param key_gzdm 绑定类型
     * @param key_qrcode 条码
     */

    @Override
    public void bd(String xbm_xbdm, String key_gzdm, final String key_qrcode) {
        view.onLoading(true);
        RetrofitManager.getMesDetail(xbm_xbdm,key_gzdm,key_qrcode).subscribe(new Observer<MesBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MesBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    //当没有返回数据时，判断为mes和压缩机都绑定完成（一次操作需绑定两个条码）
                    if (value.getUcData() == null || value.getUcData().size() ==0) {
                        view.onBDSucceed(key_qrcode);
                    }else {
                        //当有返回数据时，判断为mes绑定完成（一次操作需绑定两个条码）
                        view.onBDSucceed(value.getUcData().get(0));
                    }
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("绑定出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
