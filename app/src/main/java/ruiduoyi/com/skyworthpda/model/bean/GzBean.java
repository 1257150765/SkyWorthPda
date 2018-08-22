package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/4.
 */

/**
 * 线别列表
 */

public class GzBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"opr_gzdm":"ZZYZCS2","opr_gzmc":"总装运转测试2"},{"opr_gzdm":"ZZYZCS3","opr_gzmc":"总装运转测试3"},{"opr_gzdm":"ZZYZCS4","opr_gzmc":"总装运转测试4"},{"opr_gzdm":"ZZYZCS5","opr_gzmc":"总装运转测试5"}]
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
         * opr_gzdm : ZZYZCS2
         * opr_gzmc : 总装运转测试2
         */

        private String opr_gzdm;
        private String opr_gzmc;

        public String getOpr_gzdm() {
            return opr_gzdm;
        }

        public void setOpr_gzdm(String opr_gzdm) {
            this.opr_gzdm = opr_gzdm;
        }

        public String getOpr_gzmc() {
            return opr_gzmc;
        }

        public void setOpr_gzmc(String opr_gzmc) {
            this.opr_gzmc = opr_gzmc;
        }
    }
}
