package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/14.
 */

public class MesBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"brp_djbh":"790000009222","brp_wldm":"KEA001N1330_C2347","brp_upn":"79000000922200001","brp_pmgg":"SMFC12B-3A1A1NB(I)","plm_jhsl":43,"bdm_cnt":0,"brp_qty":1}]
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
         * brp_djbh : 790000009222
         * brp_wldm : KEA001N1330_C2347
         * brp_upn : 79000000922200001
         * brp_pmgg : SMFC12B-3A1A1NB(I)
         * plm_jhsl : 43
         * bdm_cnt : 0
         * brp_qty : 1.0
         */

        private String brp_djbh;
        private String brp_wldm;
        private String brp_upn;
        private String brp_pmgg;
        private int plm_jhsl;
        private int bdm_cnt;
        private double brp_qty;

        public String getBrp_djbh() {
            return brp_djbh;
        }

        public void setBrp_djbh(String brp_djbh) {
            this.brp_djbh = brp_djbh;
        }

        public String getBrp_wldm() {
            return brp_wldm;
        }

        public void setBrp_wldm(String brp_wldm) {
            this.brp_wldm = brp_wldm;
        }

        public String getBrp_upn() {
            return brp_upn;
        }

        public void setBrp_upn(String brp_upn) {
            this.brp_upn = brp_upn;
        }

        public String getBrp_pmgg() {
            return brp_pmgg;
        }

        public void setBrp_pmgg(String brp_pmgg) {
            this.brp_pmgg = brp_pmgg;
        }

        public int getPlm_jhsl() {
            return plm_jhsl;
        }

        public void setPlm_jhsl(int plm_jhsl) {
            this.plm_jhsl = plm_jhsl;
        }

        public int getBdm_cnt() {
            return bdm_cnt;
        }

        public void setBdm_cnt(int bdm_cnt) {
            this.bdm_cnt = bdm_cnt;
        }

        public double getBrp_qty() {
            return brp_qty;
        }

        public void setBrp_qty(double brp_qty) {
            this.brp_qty = brp_qty;
        }
    }
}
