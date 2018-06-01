package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/5/31.
 */

public class CompanyBean {
    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"SrvID":"S01","SrvName":"OPPO-SMT","SrvConSysStr":"server=localhost;database=SKW8_Rims;uid=Rims;pwd=nopainnogain;Connect Timeout=8","SrvConAppStr":"server=localhost;database=SKW8_Rims_App;uid=Rims;pwd=nopainnogain;Connect Timeout=8"},{"SrvID":"S02","SrvName":"中诺-SMT","SrvConSysStr":"server=localhost;database=ZR_Rims;uid=Rims;pwd=nopainnogain;Connect Timeout=8","SrvConAppStr":"server=localhost;database=ZR_Rims_App;uid=Rims;pwd=nopainnogain;Connect Timeout=8"},{"SrvID":"S99","SrvName":"测试-SMT","SrvConSysStr":"server=localhost;database=ZR_Rims;uid=Rims;pwd=nopainnogain;Connect Timeout=8","SrvConAppStr":"server=localhost;database=ZR_Rims_App;uid=Rims;pwd=nopainnogain;Connect Timeout=8"}]
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
         * SrvID : S01
         * SrvName : OPPO-SMT
         * SrvConSysStr : server=localhost;database=SKW8_Rims;uid=Rims;pwd=nopainnogain;Connect Timeout=8
         * SrvConAppStr : server=localhost;database=SKW8_Rims_App;uid=Rims;pwd=nopainnogain;Connect Timeout=8
         */

        private String SrvID;
        private String SrvName;
        private String SrvConSysStr;
        private String SrvConAppStr;

        public String getSrvID() {
            return SrvID;
        }

        public void setSrvID(String SrvID) {
            this.SrvID = SrvID;
        }

        public String getSrvName() {
            return SrvName;
        }

        public void setSrvName(String SrvName) {
            this.SrvName = SrvName;
        }

        public String getSrvConSysStr() {
            return SrvConSysStr;
        }

        public void setSrvConSysStr(String SrvConSysStr) {
            this.SrvConSysStr = SrvConSysStr;
        }

        public String getSrvConAppStr() {
            return SrvConAppStr;
        }

        public void setSrvConAppStr(String SrvConAppStr) {
            this.SrvConAppStr = SrvConAppStr;
        }
    }
}
