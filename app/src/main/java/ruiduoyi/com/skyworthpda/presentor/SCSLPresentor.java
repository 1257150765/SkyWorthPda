package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.SCSLContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.CheckZWBean;
import ruiduoyi.com.skyworthpda.model.bean.SCQRBean;
import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;
import ruiduoyi.com.skyworthpda.model.bean.SCXLBean;
import ruiduoyi.com.skyworthpda.model.bean.SLQRBean;
import ruiduoyi.com.skyworthpda.model.bean.SLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.WLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;
import ruiduoyi.com.skyworthpda.util.LogWraper;

/**
 * Created by Chen on 2018/5/8.
 */

public class SCSLPresentor implements SCSLContact.Presentor {
    private static final String TAG = SCSLPresentor.class.getSimpleName();
    private SCSLContact.View view;
    private Context context;

    public SCSLPresentor(SCSLContact.View view, Context context) {
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
    public void loadData(String xbStr,String key_flag) {
        view.onLoading(true);
        List<SLXXBean> data = new ArrayList<>();
        RetrofitManager.getSLXX(xbStr,key_flag).subscribe(new Observer<SLXXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SLXXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadDataSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("加载出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void checkZW(final String type, String code) {
        view.onLoading(true);
        RetrofitManager.checkZW(type,code).subscribe(new Observer<CheckZWBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckZWBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onCheckZWSucceed(type,value.getUcData().get(0).getColumn1());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("验证站位出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void checkQRCODE(final String type, String code) {
        view.onLoading(true);
        RetrofitManager.checkQRCODE(type,code).subscribe(new Observer<CheckQRCODEBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckQRCODEBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    CheckQRCODEBean.UcDataBean bean = value.getUcData().get(0);
                    view.onCheckQRCODESucceed(type,bean.getV_oricode(),bean.getV_wldm(),""+bean.getV_qty());
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

    @Override
    public void scsl(String xbm_xbdm, String zw,String code, String wldm, String qty) {
        view.onLoading(true);
        RetrofitManager.scsl(xbm_xbdm,zw,code,wldm,qty).subscribe(new Observer<SCSLBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SCSLBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onExecuteSucceed();
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("上料失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void scxl(String xbm_xbdm, String oldCoed, String code, String wldm, String qty) {
        view.onLoading(true);
        RetrofitManager.scxl(xbm_xbdm,oldCoed,code,wldm,qty).subscribe(new Observer<SCXLBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SCXLBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onExecuteSucceed();
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("续料失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void slqr(String xbm_xbdm, String zw, String code, String wldm) {
        view.onLoading(true);
        RetrofitManager.slqr(xbm_xbdm,zw,code,wldm).subscribe(new Observer<SLQRBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SLQRBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onExecuteSucceed();
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("上料确认失败");
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
     */
    @Override
    public void wlxx(String wlxxType, String xb) {
        view.onLoading(true);
        RetrofitManager.wlxx(wlxxType,xb,"","","").subscribe(new Observer<WLXXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WLXXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onWLXXSucceed();
                    //view.onExecuteSucceed();
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

    @Override
    public void loadQRData(String xbm_xbdm, String key_flag) {
        view.onLoading(true);
        List<SLXXBean> data = new ArrayList<>();
        /*RetrofitManager.getQRXX(xbm_xbdm,key_flag).subscribe(new Observer<SLXXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SLXXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadDataSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoading(false);
                view.onShowTipsDailog("加载出错");
            }

            @Override
            public void onComplete() {

            }
        });*/
    }
}
