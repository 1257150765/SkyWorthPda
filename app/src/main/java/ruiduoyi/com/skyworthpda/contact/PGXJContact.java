package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.PGXJBean;

/**
 * Created by Chen on 2018/5/14.
 */

public interface PGXJContact {

    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<String> xbData);
        void onLoadDataSucceed(String jjzwStr,List<PGXJBean> data);
    }
    public interface Presentor{
        void loadXb();
        void loadData(String xbStr);
    }

}
