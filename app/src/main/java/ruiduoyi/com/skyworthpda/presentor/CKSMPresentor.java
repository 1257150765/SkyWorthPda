package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.CKSMContact;
import ruiduoyi.com.skyworthpda.model.bean.CKBean;
import ruiduoyi.com.skyworthpda.model.bean.CancelCKBean;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdDetailBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;


/**
 * Created by Chen on 2018/5/14.
 */

public class CKSMPresentor implements CKSMContact.Presentor {
    private static final String TAG = CKSMPresentor.class.getSimpleName();
    private CKSMContact.View view;
    private Context context;

    public CKSMPresentor(CKSMContact.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void checkRQCODE(String djbh,String code) {
        view.onLoading(true);
        RetrofitManager.checkCPCODE2(djbh,code).subscribe(new Observer<CpCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CpCodeBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCheckCpCODESucceed(value.getUcData().get(0));
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }
            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("验证条码出错!");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void ck() {

    }

    @Override
    public void loadFhdDetail(final String djbh) {
        view.onLoading(true);
        RetrofitManager.getFhdDetail(djbh).subscribe(new Observer<FhdDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FhdDetailBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.loadFhdDetailSucceed(value);
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("查询发货单"+djbh+"出错!");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void cancelCk(String djbh, final CpCodeBean.UcDataBean bean, final String cancelType) {
        view.onLoading(true);
        RetrofitManager.cancelCk(djbh,bean==null?"*":bean.getBrp_qrcode()).subscribe(new Observer<CancelCKBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CancelCKBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCancelCkSucceed(cancelType,bean);
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("取消失败，请重试!");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void ck(String key_shiplist, String key_scanlist, String djbh) {
        view.onLoading(true);
        RetrofitManager.ck(key_shiplist,key_scanlist,djbh).subscribe(new Observer<CKBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CKBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCkSucceed();
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                    view.onCkFail(value.getUcData());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("发货失败，请重试!");

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
