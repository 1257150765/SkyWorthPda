package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.ZWCXBean;

/**
 * Created by Chen on 2018/6/6.
 */

public interface VersionSeitchContact {
    public interface View extends BaseContact.View{
        void onLoadZWCXSucceed(List<ZWCXBean.UcDataBean> zwStr);
        void onVersionSwitchSucceed();
    }
    public interface Presentor{
        void loadZWCX(String xb);
        void versionSwitch(String xb, String zwm_cxdm);
    }
}
