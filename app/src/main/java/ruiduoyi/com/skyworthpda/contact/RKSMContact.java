package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.KwBean;
import ruiduoyi.com.skyworthpda.model.bean.RKBean;
import ruiduoyi.com.skyworthpda.model.bean.RKRecordBean;


/**
 * Created by Chen on 2018/5/14.
 */

public interface RKSMContact {

    public interface View extends BaseContact.View{
        void onLoadKwSucceed(List<KwBean.UcDataBean> xbData);

        void onCheckCpCODESucceed(CpCodeBean.UcDataBean bean);
        void onRkSucceed();
        void onRkFail(List<RKBean.UcDataBean> bean);
        void onCancelRkSucceed(String cancelType, CpCodeBean.UcDataBean bean);

        void onLoadRecordSucceed(RKRecordBean.UcDataBean ucData);
    }
    public interface Presentor{
        void loadKw();
        void loadRecord();
        void checkRQCODE(String code);

        void rk(String kwCode, String jsonData, String djbh);
        void cancelRk(String djbh, CpCodeBean.UcDataBean bean, String cancelType);

    }

}
