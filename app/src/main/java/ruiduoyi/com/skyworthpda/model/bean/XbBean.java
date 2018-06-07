package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/4.
 */

public class XbBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"xbm_xbdm":"SMT01","xbm_zwcxdm":"Z9.06.MMF600032K01-MB-A2-AB-SMT01"},{"xbm_xbdm":"SMT02","xbm_zwcxdm":"R2.31.M3901001-MB-A1-AB-SMT02"},{"xbm_xbdm":"SMT03","xbm_zwcxdm":"H2.31.OSCAR001-MB-A1-AB-SMT03"},{"xbm_xbdm":"SMT04","xbm_zwcxdm":""},{"xbm_xbdm":"SMT05","xbm_zwcxdm":""},{"xbm_xbdm":"SMT06","xbm_zwcxdm":null},{"xbm_xbdm":"SMT07","xbm_zwcxdm":null},{"xbm_xbdm":"SMT08","xbm_zwcxdm":null}]
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
         * xbm_xbdm : SMT01
         * xbm_zwcxdm : Z9.06.MMF600032K01-MB-A2-AB-SMT01
         */

        private String xbm_xbdm;
        private String xbm_zwcxdm;

        public String getXbm_xbdm() {
            return xbm_xbdm;
        }

        public void setXbm_xbdm(String xbm_xbdm) {
            this.xbm_xbdm = xbm_xbdm;
        }

        public String getXbm_zwcxdm() {
            return xbm_zwcxdm;
        }

        public void setXbm_zwcxdm(String xbm_zwcxdm) {
            this.xbm_zwcxdm = xbm_zwcxdm;
        }
    }
}
