package ruiduoyi.com.skyworthpda.model.bean;

import java.util.List;

/**
 * Created by Chen on 2018/6/1.
 */

public class PermissionBean {

    /**
     * utStatus : true
     * ucMsg : 操作成功！
     * ucData : [{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"","g_cxmc":"","g_xh":0,"g_px":1},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA101D1","g_cxmc":"材料入库","g_xh":101,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA102D1","g_cxmc":"良品退库","g_xh":102,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA103D1","g_cxmc":"锡膏入库","g_xh":103,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA201D1","g_cxmc":"生产领料","g_xh":201,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA203D1","g_cxmc":"生产借料","g_xh":203,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA301D1","g_cxmc":"储位设置","g_xh":301,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA302D1","g_cxmc":"库存盘点","g_xh":302,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA303D1","g_cxmc":"库存查询","g_xh":303,"g_px":2},{"g_mkdm":1,"g_mkmc":"SMT仓库","g_cxdm":"PDA304D1","g_cxmc":"批次登记","g_xh":304,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"","g_cxmc":"","g_xh":0,"g_px":1},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA305D1","g_cxmc":"先进先出","g_xh":305,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA401D1","g_cxmc":"PCB绑定","g_xh":401,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA403D1","g_cxmc":"物料转移","g_xh":403,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA405D1","g_cxmc":"领料确认","g_xh":405,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA501D1","g_cxmc":"锡膏存储","g_xh":501,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA502D1","g_cxmc":"锡膏回温","g_xh":502,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA503D1","g_cxmc":"锡膏上线","g_xh":503,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA504D1","g_cxmc":"上料信息","g_xh":504,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA505D1","g_cxmc":"胶水存储","g_xh":505,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA506D1","g_cxmc":"胶水回温","g_xh":506,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA507D1","g_cxmc":"胶水上线","g_xh":507,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA508D1","g_cxmc":"MSD拆包","g_xh":508,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA509D1","g_cxmc":"MSD封包","g_xh":510,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA510D1","g_cxmc":"MSD烘烤","g_xh":509,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA601D1","g_cxmc":"首次上料","g_xh":601,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA602D1","g_cxmc":"生产续料","g_xh":602,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA603D1","g_cxmc":"上料确认","g_xh":603,"g_px":2},{"g_mkdm":2,"g_mkmc":"SMT产线","g_cxdm":"PDA604D1","g_cxmc":"站位调整","g_xh":604,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"","g_cxmc":"","g_xh":0,"g_px":1},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA701D1","g_cxmc":"PCB拆包","g_xh":701,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA702D1","g_cxmc":"上料续料","g_xh":702,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA703D1","g_cxmc":"上料调整","g_xh":703,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA704D1","g_cxmc":"品管巡检","g_xh":704,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA705D1","g_cxmc":"飞达巡检","g_xh":705,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA706D1","g_cxmc":"钢网巡检","g_xh":706,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA707D1","g_cxmc":"设备信息","g_xh":707,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA708D1","g_cxmc":"条码信息","g_xh":708,"g_px":2},{"g_mkdm":3,"g_mkmc":"SMT品质","g_cxdm":"PDA709D1","g_cxmc":"刮刀巡检","g_xh":709,"g_px":2}]
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
         * g_mkdm : 1
         * g_mkmc : SMT仓库
         * g_cxdm :
         * g_cxmc :
         * g_xh : 0
         * g_px : 1
         */

        private int g_mkdm;
        private String g_mkmc;
        private String g_cxdm;
        private String g_cxmc;
        private int g_xh;
        private int g_px;

        public int getG_mkdm() {
            return g_mkdm;
        }

        public void setG_mkdm(int g_mkdm) {
            this.g_mkdm = g_mkdm;
        }

        public String getG_mkmc() {
            return g_mkmc;
        }

        public void setG_mkmc(String g_mkmc) {
            this.g_mkmc = g_mkmc;
        }

        public String getG_cxdm() {
            return g_cxdm;
        }

        public void setG_cxdm(String g_cxdm) {
            this.g_cxdm = g_cxdm;
        }

        public String getG_cxmc() {
            return g_cxmc;
        }

        public void setG_cxmc(String g_cxmc) {
            this.g_cxmc = g_cxmc;
        }

        public int getG_xh() {
            return g_xh;
        }

        public void setG_xh(int g_xh) {
            this.g_xh = g_xh;
        }

        public int getG_px() {
            return g_px;
        }

        public void setG_px(int g_px) {
            this.g_px = g_px;
        }
    }
}
