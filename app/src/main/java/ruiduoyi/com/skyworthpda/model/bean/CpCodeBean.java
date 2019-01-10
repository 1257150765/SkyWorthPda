package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-12-27.
 */

public class CpCodeBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"brp_upn":"K1B1682E0918003430","brp_wldm":"KEB001W2160_A1894","brp_qrcode":"K1B1682E0918003430","brp_qty":1,"brp_pmgg":"SMVH22A-5A4C1NA(O)","brp_lotno":"","brp_unit":"PCS"}]
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
         * brp_upn : K1B1682E0918003430
         * brp_wldm : KEB001W2160_A1894
         * brp_qrcode : K1B1682E0918003430
         * brp_qty : 1.0
         * brp_pmgg : SMVH22A-5A4C1NA(O)
         * brp_lotno :
         * brp_unit : PCS
         */

        private String brp_upn;
        private String brp_wldm;
        private String brp_qrcode;
        private double brp_qty;
        private String brp_pmgg;
        private String brp_lotno;
        private String brp_unit;

        public String getBrp_upn() {
            return brp_upn;
        }

        public void setBrp_upn(String brp_upn) {
            this.brp_upn = brp_upn;
        }

        public String getBrp_wldm() {
            return brp_wldm;
        }

        public void setBrp_wldm(String brp_wldm) {
            this.brp_wldm = brp_wldm;
        }

        public String getBrp_qrcode() {
            return brp_qrcode;
        }

        public void setBrp_qrcode(String brp_qrcode) {
            this.brp_qrcode = brp_qrcode;
        }

        public double getBrp_qty() {
            return brp_qty;
        }

        public void setBrp_qty(double brp_qty) {
            this.brp_qty = brp_qty;
        }

        public String getBrp_pmgg() {
            return brp_pmgg;
        }

        public void setBrp_pmgg(String brp_pmgg) {
            this.brp_pmgg = brp_pmgg;
        }

        public String getBrp_lotno() {
            return brp_lotno;
        }

        public void setBrp_lotno(String brp_lotno) {
            this.brp_lotno = brp_lotno;
        }

        public String getBrp_unit() {
            return brp_unit;
        }

        public void setBrp_unit(String brp_unit) {
            this.brp_unit = brp_unit;
        }
    }
}
