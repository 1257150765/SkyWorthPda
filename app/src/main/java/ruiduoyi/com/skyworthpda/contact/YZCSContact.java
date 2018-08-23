package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.GzBean;
import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;

/**
 * Created by Chen on 2018-08-22.
 */

public interface YZCSContact {
    public interface View extends BaseContact.View{
        void onLoadZZXBSucceed(List<XbBean.UcDataBean> ucData);
        void onBDSucceed(String key_qrcode, MesBean.UcDataBean ucDataBean);
        void onLoadZZGZSucceed(List<GzBean.UcDataBean> ucData);
    }
    public interface Presentor{
        void loadZZXB();
        void bd(String key_xbdm,String key_gzdm, String key_qrcode,String key_cur,String key_vol);
    }
}
