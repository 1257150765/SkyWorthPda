package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-12-27.
 */

public class KwBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"ckm_code":"FG10","ckm_name":"成品仓"},{"ckm_code":"FG11","ckm_name":"试制成品仓"},{"ckm_code":"FG12","ckm_name":"售后成品中转仓"},{"ckm_code":"FG13","ckm_name":"小批机仓"},{"ckm_code":"FG14","ckm_name":"质量检测仓"},{"ckm_code":"FG15","ckm_name":"大巴空调成品仓"},{"ckm_code":"HM10","ckm_name":"管氦成品仓"},{"ckm_code":"HM11","ckm_name":"两器成品仓"},{"ckm_code":"HM25","ckm_name":"控制器成品仓"},{"ckm_code":"PF10","ckm_name":"散件仓"},{"ckm_code":"PJ12","ckm_name":"国内配件"},{"ckm_code":"PJ13","ckm_name":"出口配件"},{"ckm_code":"PT10","ckm_name":"物料不良仓"}]
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
         * ckm_code : FG10
         * ckm_name : 成品仓
         */

        private String ckm_code;
        private String ckm_name;

        public String getCkm_code() {
            return ckm_code;
        }

        public void setCkm_code(String ckm_code) {
            this.ckm_code = ckm_code;
        }

        public String getCkm_name() {
            return ckm_name;
        }

        public void setCkm_name(String ckm_name) {
            this.ckm_name = ckm_name;
        }
    }
}
