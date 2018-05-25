package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ruiduoyi.com.skyworthpda.contact.SCSLContact;
import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;

/**
 * Created by Chen on 2018/5/8.
 */

public class SCSLPresentor implements SCSLContact.Presentor {
    private SCSLContact.View view;
    private Context context;

    public SCSLPresentor(SCSLContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadXb();
    }

    @Override
    public void loadXb() {
        List<String> xbData = new ArrayList<>();
        for (int i=1; i<10; i++){
            xbData.add("SMT0"+i);
        }
        view.onLoadXbSucceed(xbData);
    }

    @Override
    public void loadData(String xbStr) {
        List<SCSLBean> data = new ArrayList<>();

        for (int i=0; i<10; i++){
            SCSLBean bean = new SCSLBean();
            bean.setZw("10-10");
            bean.setWl("R4.01.02060272");
            bean.setTdl("H4.01.040163;R4.01.02011078");
            bean.setYl("17");
            bean.setSyyl("");
            bean.setKdbs("");
            data.add(bean);
        }
        view.onLoadDataSucceed(data);
    }
}
