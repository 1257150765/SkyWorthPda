package ruiduoyi.com.skyworthpda.contact;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2018/5/8.
 */

public interface MainContact {
    public interface View extends BaseContact.View{

        void onLoadPermissionSecceed(List<String> groupTitles, List<List<String>> allChildTitles, List<List<Integer>> allChildImgs);
    }

    public interface Presentor{
        void loadPermission();
    }
}
