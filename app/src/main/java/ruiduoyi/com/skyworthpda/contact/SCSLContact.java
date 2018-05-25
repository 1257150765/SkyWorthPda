package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;

/**
 * Created by Chen on 2018/5/8.
 */

public interface SCSLContact {
    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<String> xbData);
        void onLoadDataSucceed(List<SCSLBean> data);
    }
    public interface Presentor{
        void loadXb();
        void loadData(String xbStr);
    }
}
