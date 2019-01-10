package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2019-01-01.
 */

public class FhdDetailBean {


    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"ship_djbh":"S101812290","shipm_id":2,"shipping_id":5058,"line_no":1,"vonder_code":"KDG001N0620","ship_plan_quantity":1,"warehouse_no":"FG10","warehouse_name":"成品仓","order_sn":"D101812290","order_line_no":0,"shipd_jlrq":"2018-12-29T15:00:41.153","itm_pmgg":"KFR-72L/F2CA1A-3(酒红色)"},{"ship_djbh":"S101812290","shipm_id":2,"shipping_id":5059,"line_no":1,"vonder_code":"KDG001W0310","ship_plan_quantity":1,"warehouse_no":"FG10","warehouse_name":"成品仓","order_sn":"D101812290","order_line_no":0,"shipd_jlrq":"2018-12-29T15:00:41.153","itm_pmgg":"KFR-72W/F2F1B-3"},{"ship_djbh":"S101812290","shipm_id":2,"shipping_id":5061,"line_no":2,"vonder_code":"KDG001N0520","ship_plan_quantity":1,"warehouse_no":"FG10","warehouse_name":"成品仓","order_sn":"D101812290","order_line_no":0,"shipd_jlrq":"2018-12-29T15:00:41.153","itm_pmgg":"KFR-72L/V1CA1A-1(钛沙金)"},{"ship_djbh":"S101812290","shipm_id":2,"shipping_id":5062,"line_no":2,"vonder_code":"KDG001W0520","ship_plan_quantity":1,"warehouse_no":"FG10","warehouse_name":"成品仓","order_sn":"D101812290","order_line_no":0,"shipd_jlrq":"2018-12-29T15:00:41.153","itm_pmgg":"KFR-72W/V1F1B-1"}]
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
         * ship_djbh : S101812290
         * shipm_id : 2
         * shipping_id : 5058
         * line_no : 1
         * vonder_code : KDG001N0620
         * ship_plan_quantity : 1.0
         * warehouse_no : FG10
         * warehouse_name : 成品仓
         * order_sn : D101812290
         * order_line_no : 0
         * shipd_jlrq : 2018-12-29T15:00:41.153
         * itm_pmgg : KFR-72L/F2CA1A-3(酒红色)
         */

        private String ship_djbh;
        private int shipm_id;
        private int shipping_id;
        private int line_no;
        private String vonder_code;
        private double ship_plan_quantity;
        private String warehouse_no;
        private String warehouse_name;
        private String order_sn;
        private int order_line_no;
        private String shipd_jlrq;
        private String itm_pmgg;
        private String stock_out_quantity = "";//实发数量

        public String getStock_out_quantity() {
            return stock_out_quantity;
        }

        public void setStock_out_quantity(String stock_out_quantity) {
            this.stock_out_quantity = stock_out_quantity;
        }

        public String getShip_djbh() {
            return ship_djbh;
        }

        public void setShip_djbh(String ship_djbh) {
            this.ship_djbh = ship_djbh;
        }

        public int getShipm_id() {
            return shipm_id;
        }

        public void setShipm_id(int shipm_id) {
            this.shipm_id = shipm_id;
        }

        public int getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(int shipping_id) {
            this.shipping_id = shipping_id;
        }

        public int getLine_no() {
            return line_no;
        }

        public void setLine_no(int line_no) {
            this.line_no = line_no;
        }

        public String getVonder_code() {
            return vonder_code;
        }

        public void setVonder_code(String vonder_code) {
            this.vonder_code = vonder_code;
        }

        public double getShip_plan_quantity() {
            return ship_plan_quantity;
        }

        public void setShip_plan_quantity(double ship_plan_quantity) {
            this.ship_plan_quantity = ship_plan_quantity;
        }

        public String getWarehouse_no() {
            return warehouse_no;
        }

        public void setWarehouse_no(String warehouse_no) {
            this.warehouse_no = warehouse_no;
        }

        public String getWarehouse_name() {
            return warehouse_name;
        }

        public void setWarehouse_name(String warehouse_name) {
            this.warehouse_name = warehouse_name;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getOrder_line_no() {
            return order_line_no;
        }

        public void setOrder_line_no(int order_line_no) {
            this.order_line_no = order_line_no;
        }

        public String getShipd_jlrq() {
            return shipd_jlrq;
        }

        public void setShipd_jlrq(String shipd_jlrq) {
            this.shipd_jlrq = shipd_jlrq;
        }

        public String getItm_pmgg() {
            return itm_pmgg;
        }

        public void setItm_pmgg(String itm_pmgg) {
            this.itm_pmgg = itm_pmgg;
        }
    }
}
