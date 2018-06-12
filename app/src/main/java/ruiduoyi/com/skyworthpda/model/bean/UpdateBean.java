package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/8.
 */

public class UpdateBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"v_UpFlag":"","v_SrvVer":"","v_UpAddr":""}]
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
         * v_UpFlag :
         * v_SrvVer :
         * v_UpAddr :
         */

        private String v_UpFlag;
        private String v_SrvVer;
        private String v_UpAddr;

        public String getV_UpFlag() {
            return v_UpFlag;
        }

        public void setV_UpFlag(String v_UpFlag) {
            this.v_UpFlag = v_UpFlag;
        }

        public String getV_SrvVer() {
            return v_SrvVer;
        }

        public void setV_SrvVer(String v_SrvVer) {
            this.v_SrvVer = v_SrvVer;
        }

        public String getV_UpAddr() {
            return v_UpAddr;
        }

        public void setV_UpAddr(String v_UpAddr) {
            this.v_UpAddr = v_UpAddr;
        }
    }
}
