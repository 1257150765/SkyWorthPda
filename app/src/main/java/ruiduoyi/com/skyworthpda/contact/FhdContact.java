package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdBean;


/**
 * Created by Chen on 2018/5/14.
 */

public interface FhdContact {

    public interface View extends BaseContact.View{
        void onLoadFhdSucceed(List<FhdBean.UcDataBean> ucData);
        void onLoadRecordSucceed(CKRecordBean.UcDataBean ucData);
    }
    public interface Presentor{
        void loadFhd(String djbh);
        void loadRecord();
    }

}
