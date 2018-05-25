package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

/**
 * Created by Chen on 2018/5/14.
 */

public interface ZWTZContact {
    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<String> xbData);
        void onLoadZwSucceed(String zwStr);
    }
    public interface Presentor{
        void loadXb();
        void loadZW(String xb);
    }
}
