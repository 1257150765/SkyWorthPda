package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.PGXJRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;

/**
 * Created by Chen on 2018/5/14.
 */

public interface PGXJContact {

    public interface View extends BaseContact.View{
        void onLoadXbSucceed(List<XbBean.UcDataBean> xbData);
        void onLoadHaveRecordSucceed(boolean b, String ucMsg);
        void onLoadRecord(List<PGXJRecordBean.UcDataBean> data);

        void onCheckQRCODESucceed(String type, String v_oricode, String v_wldm, String qty);
    }
    public interface Presentor{
        void loadXb();
        void loadHaveRecord(String key_xbdm);
        void loadRecord(String key_xbdm);
        void pgxj(String key_type,String key_xbdm,String key_qrcode,String key_wldm);
        void checkRQCODE(String code);
    }

}
