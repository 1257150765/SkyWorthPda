package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.CompanyBean;
import ruiduoyi.com.skyworthpda.model.bean.LoginBean;

/**
 * Created by Chen on 2018/5/7.
 */

public interface LoginContact {
     public interface View extends BaseContact.View{
        void onLoadConpanyNameSucceed(List<CompanyBean.UcDataBean> companyNameList);
         void onLoginSecceed(String companyName, LoginBean.UcDataBean ucDataBean);
         void onCheckUpdateSucceed(boolean hasUpdate,String url);

         void onUpdate(Integer value);

         void onUpdateComplete();
     }

    public interface Presentor {
        void login(String companyName, String userName, String pwd, String key_isAuto);
        void loadCompanyName();
        void checkUpdate(String companyCode);
        void update(String url);
    }
}
