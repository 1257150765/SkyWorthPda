package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/6.
 */

public class HaveRecordBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"xjm_xbdm":"SMT03","xjm_zwcxdm":"H2.31.OSCAR001-MB-A1-AB-SMT03","xjm_ztbz":1,"xjm_jlry":"QC03","xjm_jlrq":"2018-05-31T11:01:50.107","xjm_xjry":"QC03","xjm_xjrq":"2018-05-31T11:09:13.6","xjm_id":643}]
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
         * xjm_xbdm : SMT03
         * xjm_zwcxdm : H2.31.OSCAR001-MB-A1-AB-SMT03
         * xjm_ztbz : 1
         * xjm_jlry : QC03
         * xjm_jlrq : 2018-05-31T11:01:50.107
         * xjm_xjry : QC03
         * xjm_xjrq : 2018-05-31T11:09:13.6
         * xjm_id : 643
         */

        private String xjm_xbdm;
        private String xjm_zwcxdm;
        private int xjm_ztbz;
        private String xjm_jlry;
        private String xjm_jlrq;
        private String xjm_xjry;
        private String xjm_xjrq;
        private int xjm_id;

        public String getXjm_xbdm() {
            return xjm_xbdm;
        }

        public void setXjm_xbdm(String xjm_xbdm) {
            this.xjm_xbdm = xjm_xbdm;
        }

        public String getXjm_zwcxdm() {
            return xjm_zwcxdm;
        }

        public void setXjm_zwcxdm(String xjm_zwcxdm) {
            this.xjm_zwcxdm = xjm_zwcxdm;
        }

        public int getXjm_ztbz() {
            return xjm_ztbz;
        }

        public void setXjm_ztbz(int xjm_ztbz) {
            this.xjm_ztbz = xjm_ztbz;
        }

        public String getXjm_jlry() {
            return xjm_jlry;
        }

        public void setXjm_jlry(String xjm_jlry) {
            this.xjm_jlry = xjm_jlry;
        }

        public String getXjm_jlrq() {
            return xjm_jlrq;
        }

        public void setXjm_jlrq(String xjm_jlrq) {
            this.xjm_jlrq = xjm_jlrq;
        }

        public String getXjm_xjry() {
            return xjm_xjry;
        }

        public void setXjm_xjry(String xjm_xjry) {
            this.xjm_xjry = xjm_xjry;
        }

        public String getXjm_xjrq() {
            return xjm_xjrq;
        }

        public void setXjm_xjrq(String xjm_xjrq) {
            this.xjm_xjrq = xjm_xjrq;
        }

        public int getXjm_id() {
            return xjm_id;
        }

        public void setXjm_id(int xjm_id) {
            this.xjm_id = xjm_id;
        }
    }
}
