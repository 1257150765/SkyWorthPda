package ruiduoyi.com.skyworthpda.contact;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2018/5/7.
 */

public interface LoginContact {
     public interface View extends BaseContact.View{
        void onLoginSecceed(String userName,String pwd);
        void onLoginFalse(String userNameErrorInfo,String pwdErrorInfo);
    }

    public interface Presentor {
        void login(String userName, String pwd);

    }
}
