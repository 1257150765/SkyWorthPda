package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/6.
 */

public class SCXLBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"zwl_desc":""}]
     */

    private boolean utStatus;
    private String ucMsg;
    private List<UcDataBean> ucData;

    public boolean isUtStatus() {
        return utStatus;
    }

    public void setUtStatus(boolean utStatus) {
        this.utStatus = utStatus;
    }

    public String getUcMsg() {
        return ucMsg;
    }

    public void setUcMsg(String ucMsg) {
        this.ucMsg = ucMsg;
    }

    public List<UcDataBean> getUcData() {
        return ucData;
    }

    public void setUcData(List<UcDataBean> ucData) {
        this.ucData = ucData;
    }

    public static class UcDataBean {
        /**
         * zwl_desc :
         */

        private String zwl_desc;

        public String getZwl_desc() {
            return zwl_desc;
        }

        public void setZwl_desc(String zwl_desc) {
            this.zwl_desc = zwl_desc;
        }
    }
}
