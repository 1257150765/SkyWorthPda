package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.SCSLContact;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.CheckZWBean;
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
    private String startType = "";

    public SCSLPresentor(SCSLContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadXb();
    }

    /**
     * 加载线别
     */
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

    /**
     * 加载上料信息
     * @param xbStr 线别
     * @param key_flag 是否待上料
     */
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

    /**
     * 检查站位
     * @param type 站位||二维码
     * @param code 检查的值
     */
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
                    view.onScanError();
                    view.onShowTipsDailog(value.getUcMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onScanError();
                view.onShowTipsDailog("验证站位出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 检查二维码
     * @param type 站位||二维码
     * @param code 检查的值
     */
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
                    view.onCheckQRCODESucceed(type,bean);
                }else {
                    view.onScanError();
                    view.onShowTipsDailog(value.getUcMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onScanError();
                view.onShowTipsDailog("验证条码出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 首次上料
     * @param xbm_xbdm 线别
     * @param zw 站位
     * @param code 二维码
     * @param wldm 物料代码
     * @param qty 数量
     */
    @Override
    public void scsl(String xbm_xbdm, String zw,String code, String wldm, String qty,String binValue) {
        view.onLoading(true);
        RetrofitManager.scsl(xbm_xbdm,zw,code,wldm,qty,binValue).subscribe(new Observer<SCSLBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SCSLBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    //如果后台返回了提示信息则显示给用户
                    List<SCSLBean.UcDataBean> ucData = value.getUcData();
                    if (ucData != null && ucData.size() > 0 &&  (!"".equals(ucData.get(0).getZwl_desc()))){
                        view.onShowTipsDailog2(ucData.get(0).getZwl_desc());
                        view.onExecuteSucceed2();
                    }else {
                        view.onExecuteSucceed();
                    }

                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                    view.onExecuteFalse();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("上料出错");
                view.onExecuteFalse();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 生产续料
     * @param xbm_xbdm 线别
     * @param oldCoed 旧料盘二维码
     * @param code 新料盘二维码
     * @param wldm 物料代码
     * @param qty 数量
     * @param binValue
     */
    @Override
    public void scxl(String xbm_xbdm, String oldCoed, String code, String wldm, String qty, String binValue) {
        view.onLoading(true);
        RetrofitManager.scxl(xbm_xbdm,oldCoed,code,wldm,qty,binValue).subscribe(new Observer<SCXLBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SCXLBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){

                    //如果后台返回了提示信息则显示给用户
                    List<SCXLBean.UcDataBean> ucData = value.getUcData();
                    if (ucData != null && ucData.size() > 0 &&  (!"".equals(ucData.get(0).getZwl_desc()))){
                        view.onShowTipsDailog2(ucData.get(0).getZwl_desc());
                        view.onExecuteSucceed2();
                    }else {
                        view.onExecuteSucceed();
                    }
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                    view.onExecuteFalse();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("续料出错");
                view.onExecuteFalse();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 上料确认
     * @param xbm_xbdm 线别
     * @param zw 站位
     * @param code 二维码
     * @param wldm 物料代码
     */
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
                    view.onExecuteFalse();
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onExecuteFalse();
                view.onShowTipsDailog("上料确认出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**
     * 执行整体物料下线
     * @param wlxxType 单个下料||整体下料
     * @param xb 线别
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
                    view.onExecuteFalse();
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onExecuteFalse();
                view.onShowTipsDailog("整体下料出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 加载确认下料信息
     * @param xbm_xbdm 线别
     * @param key_flag 是否待上料
     */
    @Override
    public void loadQRData(String xbm_xbdm, String key_flag) {
        view.onLoading(true);
        List<SLXXBean> data = new ArrayList<>();
        RetrofitManager.getQRXX(xbm_xbdm,key_flag).subscribe(new Observer<SLXXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SLXXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadDataSucceed(value.getUcData());
                }else {
                    view.onExecuteFalse();
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                //e.printStackTrace();
                view.onLoading(false);
                view.onExecuteFalse();
                view.onShowTipsDailog("加载出错");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setStartType(String startType) {
        this.startType = startType;
    }
}
