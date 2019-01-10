package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2019-01-02.
 */

public class CancelCKBean {
    /**
            * utStatus : false
            * ucMsg : StkInCancel已生成入库单号的数据,取消时需要同时提交入库单号！
            * ucData : []
            */

    private boolean utStatus;
    private String ucMsg;
    private List<?> ucData;

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

    public List<?> getUcData() {
        return ucData;
    }

    public void setUcData(List<?> ucData) {
        this.ucData = ucData;
    }
}
