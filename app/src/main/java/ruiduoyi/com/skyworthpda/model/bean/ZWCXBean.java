package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/6.
 */

public class ZWCXBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"zwm_cxdm":"H2.31.OSCAR001-MB-A1-AB-SMT01"},{"zwm_cxdm":"H2.31.OSCAR011-MB-A1-AB-SMT01"},{"zwm_cxdm":"Z9.06.MMF600032K01-MB-A1-AB-SMT01"},{"zwm_cxdm":"Z9.06.MMF600032K01-MB-A2-AB-SMT01"}]
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
         * zwm_cxdm : H2.31.OSCAR001-MB-A1-AB-SMT01
         */

        private String zwm_cxdm;

        public String getZwm_cxdm() {
            return zwm_cxdm;
        }

        public void setZwm_cxdm(String zwm_cxdm) {
            this.zwm_cxdm = zwm_cxdm;
        }
    }
}
