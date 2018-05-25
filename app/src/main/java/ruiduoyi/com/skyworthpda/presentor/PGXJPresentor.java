package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ruiduoyi.com.skyworthpda.contact.PGXJContact;
import ruiduoyi.com.skyworthpda.model.bean.PGXJBean;

/**
 * Created by Chen on 2018/5/14.
 */

public class PGXJPresentor implements PGXJContact.Presentor {
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
        List<String> xbData = new ArrayList<>();
        for (int i=1; i<10; i++){
            xbData.add("SMT0"+i);
        }
        view.onLoading(false);
        view.onLoadXbSucceed(xbData);
    }

    @Override
    public void loadData(String xbStr) {
        view.onLoading(true);
        List<PGXJBean> data = new ArrayList<>();
        for (int i=0; i<10; i++){
            PGXJBean bean = new PGXJBean();
            bean.setZw("10-10");
            bean.setWl("R4.01.02060272");
            bean.setTdl("H4.01.040163;R4.01.02011078");
            bean.setJd("*");
            data.add(bean);
        }

        view.onLoading(false);
        view.onLoadDataSucceed(xbStr+"的校验站位",data);
    }
}
