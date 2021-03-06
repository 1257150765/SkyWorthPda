package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.SLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;

/**
 * Created by Chen on 2018/5/8.
 */

public interface SCSLContact {
    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<XbBean.UcDataBean> xbData);
        void onLoadDataSucceed(List<SLXXBean.UcDataBean> data);
        void onCheckZWSucceed(String type,String code);
        void onCheckQRCODESucceed(String type, CheckQRCODEBean.UcDataBean bean);

        void onWLXXSucceed();
        void onShowTipsDailog2(String msg);
    }
    public interface Presentor{
        void loadXb();
        void loadData(String xbStr,String key_flag);
        void checkZW(String type, String code);
        void checkQRCODE(String type, String code);
        void scsl(String xbm_xbdm, String zw,String code, String wldm, String qty,String binValue);
        void scxl(String xbm_xbdm, String oldCoed, String code, String wldm, String qty, String binValue);
        void slqr(String xbm_xbdm, String zw, String code, String wldm);

        void wlxx(String type, String xbm_xbdm);

        void loadQRData(String xbm_xbdm, String key_flag);

        void setStartType(String startType);
    }
}
