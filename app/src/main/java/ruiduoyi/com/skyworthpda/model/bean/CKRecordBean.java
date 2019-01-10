package ruiduoyi.com.skyworthpda.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen on 2019-01-02.
 */

public class CKRecordBean implements Serializable{

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : {"Table":[{"brp_wldm":"KDG001N0620","brp_pmgg":"KFR-72L/F2CA1A-3(酒红色)","brp_qty":1},{"brp_wldm":"KDG001N0520","brp_pmgg":"KFR-72L/V1CA1A-1(钛沙金)","brp_qty":1}],"Table1":[{"brp_xh":1,"brp_upn":"TM0000002","brp_wldm":"KDG001N0620","brp_qrcode":"TM0000001","brp_qty":1,"brp_pmgg":"KFR-72L/F2CA1A-3(酒红色)","brp_lotno":"","brp_unit":"ST","scan_djbh":"","shipout_sn":"S101812290"},{"brp_xh":2,"brp_upn":"TM0000001","brp_wldm":"KDG001N0520","brp_qrcode":"TM0000001","brp_qty":1,"brp_pmgg":"KFR-72L/V1CA1A-1(钛沙金)","brp_lotno":"","brp_unit":"ST","scan_djbh":"","shipout_sn":"S101812290"}]}
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

    public static class UcDataBean implements Serializable{
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

        public static class TableBean implements Serializable{
            /**
             * brp_wldm : KDG001N0620
             * brp_pmgg : KFR-72L/F2CA1A-3(酒红色)
             * brp_qty : 1
             */

            private String brp_wldm;
            private String brp_pmgg;
            private int brp_qty;

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

            public int getBrp_qty() {
                return brp_qty;
            }

            public void setBrp_qty(int brp_qty) {
                this.brp_qty = brp_qty;
            }
        }

        public static class Table1Bean implements Serializable {
            /**
             * brp_xh : 1
             * brp_upn : TM0000002
             * brp_wldm : KDG001N0620
             * brp_qrcode : TM0000001
             * brp_qty : 1
             * brp_pmgg : KFR-72L/F2CA1A-3(酒红色)
             * brp_lotno :
             * brp_unit : ST
             * scan_djbh :
             * shipout_sn : S101812290
             */

            private int brp_xh;
            private String brp_upn;
            private String brp_wldm;
            private String brp_qrcode;
            private int brp_qty;
            private String brp_pmgg;
            private String brp_lotno;
            private String brp_unit;
            private String scan_djbh;
            private String shipout_sn;

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

            public int getBrp_qty() {
                return brp_qty;
            }

            public void setBrp_qty(int brp_qty) {
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

            public String getShipout_sn() {
                return shipout_sn;
            }

            public void setShipout_sn(String shipout_sn) {
                this.shipout_sn = shipout_sn;
            }
        }
    }
}
