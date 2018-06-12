package ruiduoyi.com.skyworthpda.contact;

/**
 * Created by Chen on 2018/5/15.
 */

public interface BaseContact {

    public interface View{
        void onLoading(boolean isLoading);
        void onShowTipsDailog(String tips);
        void onExecuteSucceed();
        void onScanError();

        void onExecuteFalse();
    }
    public interface Presentor{

    }

}
