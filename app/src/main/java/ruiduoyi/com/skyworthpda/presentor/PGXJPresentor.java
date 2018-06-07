package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.PGXJContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.HaveRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.PGXJRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

/**
 * Created by Chen on 2018/5/14.
 */

public class PGXJPresentor implements PGXJContact.Presentor {
    private static final String TAG = PGXJPresentor.class.getSimpleName();
    private PGXJContact.View view;
    private Context context;

    public PGXJPresentor(PGXJContact.View view, Context context) {
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
    public void loadHaveRecord(String key_xbdm) {
        view.onLoading(true);
        RetrofitManager.getHaveRecord(key_xbdm).subscribe(new Observer<HaveRecordBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HaveRecordBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadHaveRecordSucceed(true,value.getUcData().get(0).getXjm_zwcxdm());
                }else {
                    view.onLoadHaveRecordSucceed(false,value.getUcMsg());

                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("加载巡检记录出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadRecord(String key_xbdm) {
        view.onLoading(true);
        RetrofitManager.getRecord(key_xbdm).subscribe(new Observer<PGXJRecordBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PGXJRecordBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadRecord(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("加载巡检记录出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pgxj(String key_type, String key_xbdm, String key_qrcode, String key_wldm) {
        //RetrofitManager.pgxj
    }

    @Override
    public void checkRQCODE(String code) {
        view.onLoading(true);
        RetrofitManager.checkQRCODE(Config.CHECK_TYPE_QRCODE,code).subscribe(new Observer<CheckQRCODEBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckQRCODEBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    CheckQRCODEBean.UcDataBean bean = value.getUcData().get(0);
                    view.onCheckQRCODESucceed(Config.CHECK_TYPE_QRCODE,bean.getV_oricode(),bean.getV_wldm(),""+bean.getV_qty());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("验证条码出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
