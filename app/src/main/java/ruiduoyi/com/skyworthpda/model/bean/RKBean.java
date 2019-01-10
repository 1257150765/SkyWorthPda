package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018-12-29.
 */

public class RKBean {
    /**
     * utStatus : false
     * ucMsg : StkInPostCRM服务器返回错误:[InWS|20181229914] 产品编码[KEB001W2160_A1894]匹配不到对应的产品
     * ucData : []
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
         * cDjbh : RP1812280001
         */

        private String cDjbh;

        public String getCDjbh() {
            return cDjbh;
        }

        public void setCDjbh(String cDjbh) {
            this.cDjbh = cDjbh;
        }
    }
}
