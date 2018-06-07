package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.XbBean;

/**
 * Created by Chen on 2018/5/14.
 */

public interface ZWTZContact {
    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<XbBean.UcDataBean> xbData);
        void onCheckZwSucceed(String zw);
    }
    public interface Presentor{
        void loadXb();
        void checkZw(String code);
        void zwtz(String xbm_xbdm, String oldZw, String newZw);
    }
}
