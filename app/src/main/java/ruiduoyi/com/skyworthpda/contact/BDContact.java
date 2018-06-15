package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;

/**
 * Created by Chen on 2018/6/14.
 */

public interface BDContact {
    public interface View extends BaseContact.View{
        void onLoadZZXBSucceed(List<XbBean.UcDataBean> ucData);
        void onBDSucceed(MesBean.UcDataBean ucDataBean);
        void onBDFalse();

        void onBDSucceed(String key_qrcode);
    }
    public interface Presentor{
        void loadZZXB();
        void bd(String key_xbdm,String key_gzdm, String key_qrcode);
    }
}
