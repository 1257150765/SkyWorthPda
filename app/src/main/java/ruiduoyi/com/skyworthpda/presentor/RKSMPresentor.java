package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.RKSMContact;
import ruiduoyi.com.skyworthpda.model.bean.CancelRKBean;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.KwBean;
import ruiduoyi.com.skyworthpda.model.bean.RKBean;
import ruiduoyi.com.skyworthpda.model.bean.RKRecordBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.LogWraper;


/**
 * Created by Chen on 2018/5/14.
 */

public class RKSMPresentor implements RKSMContact.Presentor {
    private static final String TAG = RKSMPresentor.class.getSimpleName();
    private RKSMContact.View view;
    private Context context;

    public RKSMPresentor(RKSMContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadKw();

    }


    @Override
    public void loadKw() {
        view.onLoading(true);
        RetrofitManager.getKw().subscribe(new Observer<KwBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(KwBean value) {
                view.onLoading(false);
                LogWraper.d(TAG,""+value);
                if (value.isUtStatus()){
                    view.onLoadKwSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                //e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("加载库位出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /*@Override
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
                    if (value.getUcData() == null || value.getUcData().size() == 0) {
                        view.onLoadHaveRecordSucceed(false,"");
                    }else {
                        view.onLoadHaveRecordSucceed(true, value.getUcData().get(0).getXjm_zwcxdm());
                    }

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
    */

    @Override
    public void loadRecord() {
        view.onLoading(true);
        RetrofitManager.getRkRecord().subscribe(new Observer<RKRecordBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RKRecordBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadRecordSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("加载入库记录出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }



    @Override
    public void checkRQCODE(String code) {
        view.onLoading(true);
        RetrofitManager.checkCPCODE(code).subscribe(new Observer<CpCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CpCodeBean value) {

                view.onLoading(false);
                if (value.isUtStatus() &&value.getUcData().size()>0){
                    view.onCheckCpCODESucceed(value.getUcData().get(0));
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void rk(String kwCode, String jsonData, String djbh) {
        view.onLoading(true);
        RetrofitManager.rk(kwCode,jsonData,djbh).subscribe(new Observer<RKBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RKBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onRkSucceed();
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                    view.onRkFail(value.getUcData());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void cancelRk(String djbh, final CpCodeBean.UcDataBean bean, final String cancelType) {
        view.onLoading(true);
        RetrofitManager.cancelRk(djbh,bean==null?"*":bean.getBrp_qrcode()).subscribe(new Observer<CancelRKBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CancelRKBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCancelRkSucceed(cancelType,bean);
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
