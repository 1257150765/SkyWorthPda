package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CKBean;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdDetailBean;


/**
 * Created by Chen on 2018/5/14.
 */

public interface CKSMContact {

    public interface View extends BaseContact.View{


        void onCheckCpCODESucceed(CpCodeBean.UcDataBean bean);

        void loadFhdDetailSucceed(FhdDetailBean bean);

        void onCancelCkSucceed(String cancelType, CpCodeBean.UcDataBean bean);
        void onCkSucceed();

        void onCkFail(List<CKBean.UcDataBean> ucData);
    }
    public interface Presentor{

        void checkRQCODE(String djbh, String code);

        void ck();

        void loadFhdDetail(String djbh);

        void cancelCk(String djbh, CpCodeBean.UcDataBean bean, String cancelTypeOne);

        void ck(String key_shiplist, String key_scanlist, String djbh);

    }

}
