package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/11.
 */

public class PGXJBean {
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
