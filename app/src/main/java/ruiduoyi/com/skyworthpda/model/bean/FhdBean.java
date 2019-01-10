package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2019-01-01.
 */

public class FhdBean {


    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"ship_djbh":"S101812290","shipping_day":"2018-12-29","delivery":"","shipping_method":"17.5整车(17.5整车享受当张订单金额1%的补贴)","store_no":"1002939","consignee":"王洪山","phone":"13336201319","address":"菏泽市中华西路高新区办公大厅院内A3A","driver_info_name":"李先生","driver_info_mobile":"1888888888","car_number":"粤A123456","shipm_id":2},{"ship_djbh":"S101812291","shipping_day":"2018-12-29","delivery":"","shipping_method":"17.5整车(17.5整车享受当张订单金额1%的补贴)","store_no":"1002939","consignee":"王洪山","phone":"13336201319","address":"菏泽市中华西路高新区办公大厅院内A3A","driver_info_name":"李先生","driver_info_mobile":"1888888888","car_number":"粤A123456","shipm_id":2163}]
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
         * shipping_day : 2018-12-29
         * delivery :
         * shipping_method : 17.5整车(17.5整车享受当张订单金额1%的补贴)
         * store_no : 1002939
         * consignee : 王洪山
         * phone : 13336201319
         * address : 菏泽市中华西路高新区办公大厅院内A3A
         * driver_info_name : 李先生
         * driver_info_mobile : 1888888888
         * car_number : 粤A123456
         * shipm_id : 2
         */

        private String ship_djbh;
        private String shipping_day;
        private String delivery;
        private String shipping_method;
        private String store_no;
        private String consignee;
        private String phone;
        private String address;
        private String driver_info_name;
        private String driver_info_mobile;
        private String car_number;
        private int shipm_id;

        public String getShip_djbh() {
            return ship_djbh;
        }

        public void setShip_djbh(String ship_djbh) {
            this.ship_djbh = ship_djbh;
        }

        public String getShipping_day() {
            return shipping_day;
        }

        public void setShipping_day(String shipping_day) {
            this.shipping_day = shipping_day;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getShipping_method() {
            return shipping_method;
        }

        public void setShipping_method(String shipping_method) {
            this.shipping_method = shipping_method;
        }

        public String getStore_no() {
            return store_no;
        }

        public void setStore_no(String store_no) {
            this.store_no = store_no;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDriver_info_name() {
            return driver_info_name;
        }

        public void setDriver_info_name(String driver_info_name) {
            this.driver_info_name = driver_info_name;
        }

        public String getDriver_info_mobile() {
            return driver_info_mobile;
        }

        public void setDriver_info_mobile(String driver_info_mobile) {
            this.driver_info_mobile = driver_info_mobile;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public int getShipm_id() {
            return shipm_id;
        }

        public void setShipm_id(int shipm_id) {
            this.shipm_id = shipm_id;
        }
    }
}
