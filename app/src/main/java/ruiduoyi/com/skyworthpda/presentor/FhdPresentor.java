package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.FhdContact;
import ruiduoyi.com.skyworthpda.model.bean.CKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdBean;
import ruiduoyi.com.skyworthpda.model.net.RetrofitManager;


/**
 * Created by Chen on 2018/5/14.
 */

public class FhdPresentor implements FhdContact.Presentor {
    private static final String TAG = FhdPresentor.class.getSimpleName();
    private FhdContact.View view;
    private Context context;

    public FhdPresentor(FhdContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadRecord();
    }




    @Override
    public void loadFhd(String djbh) {
        view.onLoading(true);
        RetrofitManager.getFhd(djbh).subscribe(new Observer<FhdBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FhdBean value) {
                view.onLoading(false);
                if (value.isUtStatus()){
                    view.onLoadFhdSucceed(value.getUcData());
                }else {
                    view.onShowTipsDailog(value.getUcMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onLoading(false);
                view.onShowTipsDailog("获取发货单出错!");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadRecord() {
        view.onLoading(true);
        RetrofitManager.getCkRecord().subscribe(new Observer<CKRecordBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CKRecordBean value) {
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
}
