package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.VersionSeitchContact;
import ruiduoyi.com.skyworthpda.model.bean.VersionSwitchBean;
import ruiduoyi.com.skyworthpda.model.bean.ZWCXBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;

/**
 * Created by Chen on 2018/6/6.
 */

public class VersionSwitchPresentor implements VersionSeitchContact.Presentor {
    private Context context;
    private VersionSeitchContact.View view;

    public VersionSwitchPresentor(Context context, VersionSeitchContact.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 加载当前线别的站位程序
     * @param xb
     */
    @Override
    public void loadZWCX(String xb) {
        view.onLoading(true);
        RetrofitManager.getZwcx(xb).subscribe(new Observer<ZWCXBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZWCXBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadZWCXSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 执行版本切换
     * @param xb 线别
     * @param zwm_cxdm 站位程序
     */
    @Override
    public void versionSwitch(String xb, String zwm_cxdm) {
        view.onLoading(true);
        RetrofitManager.versionSwitch(xb,zwm_cxdm).subscribe(new Observer<VersionSwitchBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(VersionSwitchBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onVersionSwitchSucceed();
                    view.onExecuteSucceed();
                }else {
                    view.onExecuteFalse();
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
