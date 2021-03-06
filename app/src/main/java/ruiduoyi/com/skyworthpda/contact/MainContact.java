package ruiduoyi.com.skyworthpda.contact;

import java.util.List;

import ruiduoyi.com.skyworthpda.model.bean.PermissionBean;

/**
 * Created by Chen on 2018/5/8.
 */

public interface MainContact {
    public interface View extends BaseContact.View{
        void onCheckUpdateSucceed(boolean hasNewVer, String url, boolean isAuto);
        void onLoadPermissionSecceed(List<PermissionBean.UcDataBean> titles, List<List<PermissionBean.UcDataBean>> childs);
        void onUpdate(int value);
        void onUpdateSucceed();
    }

    public interface Presentor{
        void loadPermission();
        void checkUpdate(boolean b);
        void update(String url);
    }
}
