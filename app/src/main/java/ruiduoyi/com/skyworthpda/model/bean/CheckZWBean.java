package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/4.
 */

public class CheckZWBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"Column1":"16-3"}]
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
         * Column1 : 16-3
         */

        private String Column1;

        public String getColumn1() {
            return Column1;
        }

        public void setColumn1(String Column1) {
            this.Column1 = Column1;
        }
    }
}
