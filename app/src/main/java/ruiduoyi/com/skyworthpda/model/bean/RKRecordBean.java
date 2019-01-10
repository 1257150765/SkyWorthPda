package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-12-29.
 */

public class RKRecordBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"brp_wldm":"KEB001W2160_A1894","brp_pmgg":"SMVH22A-5A4C1NA(O)","brp_qty":2}],"Table1":[{"brp_xh":1,"brp_upn":"K1B1682E0918003430","brp_wldm":"KEB001W2160_A1894","brp_qrcode":"K1B1682E0918003430","brp_qty":1,"brp_pmgg":"SMVH22A-5A4C1NA(O)","brp_lotno":"","brp_unit":"PCS","scan_djbh":"RP1812290026","scan_ckdm":null},{"brp_xh":2,"brp_upn":"K1B1682E0918003429","brp_wldm":"KEB001W2160_A1894","brp_qrcode":"K1B1682E0918003429","brp_qty":1,"brp_pmgg":"SMVH22A-5A4C1NA(O)","brp_lotno":"","brp_unit":"PCS","scan_djbh":"","scan_ckdm":null}]}
     */

    private boolean utStatus;
    private String ucMsg;
    private UcDataBean ucData;

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

    public UcDataBean getUcData() {
        return ucData;
    }

    public void setUcData(UcDataBean ucData) {
        this.ucData = ucData;
    }

    public static class UcDataBean {
        private List<TableBean> Table;
        private List<Table1Bean> Table1;

        public List<TableBean> getTable() {
            return Table;
        }

        public void setTable(List<TableBean> Table) {
            this.Table = Table;
        }

        public List<Table1Bean> getTable1() {
            return Table1;
        }

        public void setTable1(List<Table1Bean> Table1) {
            this.Table1 = Table1;
        }

        public static class TableBean {
            /**
             * brp_wldm : KEB001W2160_A1894
             * brp_pmgg : SMVH22A-5A4C1NA(O)
             * brp_qty : 2.0
             */

            private String brp_wldm;
            private String brp_pmgg;
            private double brp_qty;

            public String getBrp_wldm() {
                return brp_wldm;
            }

            public void setBrp_wldm(String brp_wldm) {
                this.brp_wldm = brp_wldm;
            }

            public String getBrp_pmgg() {
                return brp_pmgg;
            }

            public void setBrp_pmgg(String brp_pmgg) {
                this.brp_pmgg = brp_pmgg;
            }

            public double getBrp_qty() {
                return brp_qty;
            }

            public void setBrp_qty(double brp_qty) {
                this.brp_qty = brp_qty;
            }
        }

        public static class Table1Bean {
            /**
             * brp_xh : 1
             * brp_upn : K1B1682E0918003430
             * brp_wldm : KEB001W2160_A1894
             * brp_qrcode : K1B1682E0918003430
             * brp_qty : 1.0
             * brp_pmgg : SMVH22A-5A4C1NA(O)
             * brp_lotno :
             * brp_unit : PCS
             * scan_djbh : RP1812290026
             * scan_ckdm : null
             */

            private int brp_xh;
            private String brp_upn;
            private String brp_wldm;
            private String brp_qrcode;
            private double brp_qty;
            private String brp_pmgg;
            private String brp_lotno;
            private String brp_unit;
            private String scan_djbh;
            private String scan_ckdm;

            public int getBrp_xh() {
                return brp_xh;
            }

            public void setBrp_xh(int brp_xh) {
                this.brp_xh = brp_xh;
            }

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

            public String getScan_djbh() {
                return scan_djbh;
            }

            public void setScan_djbh(String scan_djbh) {
                this.scan_djbh = scan_djbh;
            }

            public String getScan_ckdm() {
                return scan_ckdm;
            }

            public void setScan_ckdm(String scan_ckdm) {
                this.scan_ckdm = scan_ckdm;
            }
        }
    }
}
