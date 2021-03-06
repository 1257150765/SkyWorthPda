package ruiduoyi.com.skyworthpda.model.bean;

/**
 * Created by Chen on 2018/6/6.
 */

import java.util.List;

/**
 * 下料站位
 */
public class XLZWBean {


    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"sll_zwdm":"10-10","sll_osqty":3000}]
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
         * sll_zwdm : 10-10
         * sll_osqty : 3000
         */

        private String sll_zwdm;
        private int sll_osqty;

        public String getSll_zwdm() {
            return sll_zwdm;
        }

        public void setSll_zwdm(String sll_zwdm) {
            this.sll_zwdm = sll_zwdm;
        }

        public int getSll_osqty() {
            return sll_osqty;
        }

        public void setSll_osqty(int sll_osqty) {
            this.sll_osqty = sll_osqty;
        }
    }
}
