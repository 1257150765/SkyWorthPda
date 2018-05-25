package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ruiduoyi.com.skyworthpda.contact.ZWTZContact;

/**
 * Created by Chen on 2018/5/14.
 */

public class ZWTZPresentor implements ZWTZContact.Presentor {
    private ZWTZContact.View view;
    private Context context;

    public ZWTZPresentor(ZWTZContact.View view, Context context) {
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
    public void loadZW(String xb) {
        view.onLoadZwSucceed(xb+"的站位程序");
    }
}
