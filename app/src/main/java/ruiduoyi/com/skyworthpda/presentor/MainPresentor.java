package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ruiduoyi.com.skyworthpda.R;
import ruiduoyi.com.skyworthpda.contact.MainContact;
import ruiduoyi.com.skyworthpda.util.Config;

/**
 * Created by Chen on 2018/5/8.
 */

public class MainPresentor implements MainContact.Presentor {
    private MainContact.View view;
    private Context context;
    private String[] permissionStrArr;
    public MainPresentor(MainContact.View view, Context context) {
        this.view = view;
        this.context = context;
        loadPermission();
    }

    @Override
    public void loadPermission() {
        List<String> groupTitles=new ArrayList<>();
        final List<List<String>> allChildTitles=new ArrayList<>();
        List<List<Integer>> allChildImgs=new ArrayList<>();
        groupTitles.add(Config.PERMISSION_FCL);
        groupTitles.add(Config.PERMISSION_PZGL);
        String permissionStr = "FCL_001,FCL_002,FCL_003,FCL_004,PZGL_001";
        permissionStrArr = permissionStr.split(",");
        //防错料
        List<String> childFclTitle = new ArrayList<>();
        List<Integer> childFclImg = new ArrayList<>();
        if (isPermission(Config.PERMISSION_FCL_SCSL_CODE)){
            childFclTitle.add(Config.PERMISSION_FCL_SCSL_NAME);
            childFclImg.add(R.mipmap.scsl);
        }
        if (isPermission(Config.PERMISSION_FCL_SCXL_CODE)){
            childFclTitle.add(Config.PERMISSION_FCL_SCXL_NAME);
            childFclImg.add(R.mipmap.scxl);
        }
        if (isPermission(Config.PERMISSION_FCL_SLQR_CODE)){
            childFclTitle.add(Config.PERMISSION_FCL_SLQR_NAME);
            childFclImg.add(R.mipmap.scqr);
        }
        if (isPermission(Config.PERMISSION_FCL_ZWTZ_CODE)){
            childFclTitle.add(Config.PERMISSION_FCL_ZWTZ_NAME);
            childFclImg.add(R.mipmap.zwtz);
        }
        allChildTitles.add(childFclTitle);
        allChildImgs.add(childFclImg);

        //品质管理
        List<String> childPzglTitle = new ArrayList<>();
        List<Integer> childPzglImg = new ArrayList<>();
        if (isPermission(Config.PERMISSION_PZGL_PGXJ_CODE)){
            childPzglTitle.add(Config.PERMISSION_PZGL_PGXJ_NAME);
            childPzglImg.add(R.mipmap.pgxj);
        }
        allChildTitles.add(childPzglTitle);
        allChildImgs.add(childPzglImg);
        view.onLoadPermissionSecceed(groupTitles,allChildTitles,allChildImgs);
    }
    public boolean isPermission(String functionCode){
        boolean isExist=false;
        for (int i=0;i<permissionStrArr.length;i++){
            if (permissionStrArr[i].equals(functionCode)){
                isExist=true;
            }
        }
        return isExist;
    }
}
