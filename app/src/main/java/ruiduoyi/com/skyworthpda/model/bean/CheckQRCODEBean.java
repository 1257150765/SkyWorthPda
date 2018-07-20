package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/5.
 */

public class CheckQRCODEBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"v_msg":"ok","v_upn":"CW-20.808102-180514-000530","v_wldm":"Z9.06.EER1005C1C20","v_dc":"1820","v_lotno":"20180514","v_csdm":"20.808102","v_qty":1300,"v_oricode":"CW-20.808102-180514-000530&Z9.06.EER1005C1C20&1820&20180514&20.808102&1300"}]
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
         * v_msg : ok
         * v_upn : CW-20.808102-180514-000530
         * v_wldm : Z9.06.EER1005C1C20
         * v_dc : 1820
         * v_lotno : 20180514
         * v_csdm : 20.808102
         * v_qty : 1300
         * v_oricode : CW-20.808102-180514-000530&Z9.06.EER1005C1C20&1820&20180514&20.808102&1300
         */

        private String v_msg;
        private String v_upn;
        private String v_wldm;
        private String v_dc;
        private String v_lotno;
        private String v_csdm;
        private int v_qty;
        private String v_oricode;
        private String v_isInUse;

        public String getV_isInUse() {
            return v_isInUse;
        }

        public void setV_isInUse(String v_isInUse) {
            this.v_isInUse = v_isInUse;
        }

        public String getV_msg() {
            return v_msg;
        }

        public void setV_msg(String v_msg) {
            this.v_msg = v_msg;
        }

        public String getV_upn() {
            return v_upn;
        }

        public void setV_upn(String v_upn) {
            this.v_upn = v_upn;
        }

        public String getV_wldm() {
            return v_wldm;
        }

        public void setV_wldm(String v_wldm) {
            this.v_wldm = v_wldm;
        }

        public String getV_dc() {
            return v_dc;
        }

        public void setV_dc(String v_dc) {
            this.v_dc = v_dc;
        }

        public String getV_lotno() {
            return v_lotno;
        }

        public void setV_lotno(String v_lotno) {
            this.v_lotno = v_lotno;
        }

        public String getV_csdm() {
            return v_csdm;
        }

        public void setV_csdm(String v_csdm) {
            this.v_csdm = v_csdm;
        }

        public int getV_qty() {
            return v_qty;
        }

        public void setV_qty(int v_qty) {
            this.v_qty = v_qty;
        }

        public String getV_oricode() {
            return v_oricode;
        }

        public void setV_oricode(String v_oricode) {
            this.v_oricode = v_oricode;
        }
    }
}
