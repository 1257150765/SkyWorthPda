package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.ZWCXBean;

/**
 * Created by Chen on 2018/6/6.
 */

/**
 * 物料下线
 */
public interface WLXXContact {
    public interface View extends BaseContact.View{
        void onLoadXXZWSucceed(List<ZWCXBean.UcDataBean> zwStr);
        void onCheckQRCODESucceed(CheckQRCODEBean.UcDataBean bean);
    }
    public interface Presentor{
        void loadXXZW(String xb, String qrcode);
        void checkQRCODE(String key_chkval);
    }
}
