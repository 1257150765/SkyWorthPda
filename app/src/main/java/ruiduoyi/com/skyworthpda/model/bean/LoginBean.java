package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/5/31.
 */

public class LoginBean {

    /**
     * utStatus : true
     * ucMsg : 登入成功！
     * ucData : [{"usr_yhdm":"ADMIN","usr_yhmc":"管理员","usr_yhmm":"40080140940078078F40740140940078D70124B70122970120770118570116370114170112F60110D60108B60106960104760102560100360078F","usr_gsdm":"ZR","usr_gsmc":"创维液晶-中诺SMT","usr_bmmc":"IT 部","usr_drsj":"2018-05-31 21:07:31","usr_tokenid":"ADMIN-3578AC83054A4B2DB8F06CC7519F5213"}]
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
         * usr_yhdm : ADMIN
         * usr_yhmc : 管理员
         * usr_yhmm : 40080140940078078F40740140940078D70124B70122970120770118570116370114170112F60110D60108B60106960104760102560100360078F
         * usr_gsdm : ZR
         * usr_gsmc : 创维液晶-中诺SMT
         * usr_bmmc : IT 部
         * usr_drsj : 2018-05-31 21:07:31
         * usr_tokenid : ADMIN-3578AC83054A4B2DB8F06CC7519F5213
         */

        private String usr_yhdm;
        private String usr_yhmc;
        private String usr_yhmm;
        private String usr_gsdm;
        private String usr_gsmc;
        private String usr_bmmc;
        private String usr_drsj;
        private String usr_tokenid;

        public String getUsr_yhdm() {
            return usr_yhdm;
        }

        public void setUsr_yhdm(String usr_yhdm) {
            this.usr_yhdm = usr_yhdm;
        }

        public String getUsr_yhmc() {
            return usr_yhmc;
        }

        public void setUsr_yhmc(String usr_yhmc) {
            this.usr_yhmc = usr_yhmc;
        }

        public String getUsr_yhmm() {
            return usr_yhmm;
        }

        public void setUsr_yhmm(String usr_yhmm) {
            this.usr_yhmm = usr_yhmm;
        }

        public String getUsr_gsdm() {
            return usr_gsdm;
        }

        public void setUsr_gsdm(String usr_gsdm) {
            this.usr_gsdm = usr_gsdm;
        }

        public String getUsr_gsmc() {
            return usr_gsmc;
        }

        public void setUsr_gsmc(String usr_gsmc) {
            this.usr_gsmc = usr_gsmc;
        }

        public String getUsr_bmmc() {
            return usr_bmmc;
        }

        public void setUsr_bmmc(String usr_bmmc) {
            this.usr_bmmc = usr_bmmc;
        }

        public String getUsr_drsj() {
            return usr_drsj;
        }

        public void setUsr_drsj(String usr_drsj) {
            this.usr_drsj = usr_drsj;
        }

        public String getUsr_tokenid() {
            return usr_tokenid;
        }

        public void setUsr_tokenid(String usr_tokenid) {
            this.usr_tokenid = usr_tokenid;
        }
    }
}
