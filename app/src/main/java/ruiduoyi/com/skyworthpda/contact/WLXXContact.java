package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;

/**
 * Created by Chen on 2018/6/6.
 */

/**
 * 物料下线
 */
public interface WLXXContact {
    public interface View extends BaseContact.View{
        void onLoadXXZWSucceed(List<?> zwStr);
        void onCheckQRCODESucceed(CheckQRCODEBean.UcDataBean bean);
    }
    public interface Presentor{
        void loadXXZW(String xb, String qrcode);
        void checkQRCODE(String key_chkval);
        void wlxx(String wlxxType, String xb, String v_oricode, String v_wldm);
    }
}
