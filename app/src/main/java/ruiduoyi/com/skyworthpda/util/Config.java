package ruiduoyi.com.skyworthpda.util;

/**
 * Created by Chen on 2018/5/7.
 */

public class Config {
    public static final String BASE_URL = "http://cwps.ruiduoyi.com:8080/RdyService.asmx/";
    //public static final String BASE_URL = "http://172.24.176.7:9999/RdyService.asmx/";
    //public static final String BASE_URL = "http://192.168.43.173:8889/RdyService.asmx/";
    public static final String CACHE_DATA_USERNAME = "userName";
    public static final String CACHE_DATA_PWD = "pwd";

    public static final String CACHE_DATA_REMEMBER = "isRemember";
    public static final String PERMISSION_FCL = "防错料管理";
    public static final String PERMISSION_FCL_SCSL_CODE = "PDA901D1";
    public static final String PERMISSION_FCL_SCSL_NAME = "首次上料";
    public static final String PERMISSION_FCL_SCXL_CODE = "PDA902D1";
    public static final String PERMISSION_FCL_SCXL_NAME = "生产续料";
    public static final String PERMISSION_FCL_SLQR_CODE = "PDA903D1";
    public static final String PERMISSION_FCL_SLQR_NAME = "上料确认";
    public static final String PERMISSION_FCL_ZWTZ_CODE = "PDA904D1";
    public static final String PERMISSION_FCL_ZWTZ_NAME = "站位调整";
    public static final String PERMISSION_PZGL = "品质管理";
    public static final String PERMISSION_PZGL_PGXJ_CODE = "PDA905D1";
    public static final String PERMISSION_PZGL_PGXJ_NAME = "品管巡检";

    public static final String PERMISSION_SMTZZ_YSJBD_CODE = "PDA906D1";
    public static final String PERMISSION_SMTZZ_YSJBD_NAME = "压缩机绑定";
    public static final String PERMISSION_SMTZZ_KZQDQHBD_CODE = "PDA907D1";
    public static final String PERMISSION_SMTZZ_KZQDQHBD_NAME = "控制器/电器盒绑定";
    public static final String PERMISSION_SMTZZ_DZJC_CODE = "PDA908D1";
    public static final String PERMISSION_SMTZZ_DZJC_NAME = "电子检查";
    public static final String PERMISSION_SMTZZ_YZCS_CODE = "PDA909D1";
    public static final String PERMISSION_SMTZZ_YZCS_NAME = "运转测试";
    public static final String PERMISSION_CP_RKSM = "PDA701D1";
    public static final String PERMISSION_CP_CKSM = "PDA702D1";


    public static final String THEME_ID = "1";
    public static final String CACHE_DATA_COMPANYNAME = "companyName";
    public static final String CACHE_DATA_USERTOKEN = "token";
    public static final String CACHE_DATA_BM = "bm";
    public static final String CACHE_DATA_USERCODE = "userCode";
    public static final String CACHE_DATA_COMPANYCODE= "companyCode";

    public static final String TYPE_INTERFACE_UPDATE = "99999";
    public static final String TYPE_INTERFACE_DATE = "99998";
    public static final String TYPE_INTERFACE_LOGIN = "99997";
    public static final String TYPE_INTERFACE_PERMISSION = "99996";
    public static final String TYPE_INTERFACE_XB= "99995";
    public static final String TYPE_INTERFACE_CHECK = "99994";
    public static final String TYPE_INTERFACE_GZ = "99995";
    public static final String TYPE_INTERFACE_UPLOADLOG = "80001";


    public static final String TYPE_INTERFACE_GETCOMPANYLIST = "10000";
    public static final String TYPE_INTERFACE_GETSLXX = "10001";
    public static final String TYPE_INTERFACE_GETQRXX = "10002";
    public static final String TYPE_INTERFACE_GETXLZW = "10003";
    public static final String TYPE_INTERFACE_GETHAVERECORE = "10004";
    public static final String TYPE_INTERFACE_GETECORE = "10005";

    public static final String TYPE_INTERFACE_SCSL = "20001";
    public static final String TYPE_INTERFACE_VERSIONSWITCH = "20002";
    public static final String TYPE_INTERFACE_WLXX = "20003";
    public static final String TYPE_INTERFACE_SCXL = "20004";
    public static final String TYPE_INTERFACE_SCQR = "20005";
    public static final String TYPE_INTERFACE_ZWTZ = "20006";
    public static final String TYPE_INTERFACE_PGXJ = "20007";
    public static final String CHECK_TYPE_ZWM = "ZWM";
    public static final String CHECK_TYPE_QRCODE = "QRCODE";
    public static final String CHECK_TYPE_QXM = "QXM";
    public static final String IS_INIT = "init";
    // 扫描工具内应用设置的广播名称action;
    public final static String BROADCAST_SETTING = "com.android.scanner.service_settings";
    // 扫描工具内应用设置下的条码发送方式的广播名称key；
    public final static String SEND_KEY = "barcode_send_mode";
    // 扫描工具内应用设置下的条码结束符广播名称key；
    public final static String END_KEY = "endchar";
    // 自定义扫描工具内开发者项内广播名称value；
    public final static String CUSTOM_NAME = "com.ruiduoyi.scanner.broadcast";
    // 扫描工具内开发者项，广播名称的 key；
    public final static String BROADCAST_KEY = "action_barcode_broadcast";

    public static final String EXTRA_DATE_XB = "xb";
    public static final String EXTRA_DATE_ZWCX = "zwcx";
    public static final String EXTRA_STARTTYPE_VERSIONSWITCH = "startTpye";



    public static final String PGXJ_TYPE_ADD = "ADD";
    public static final String PGXJ_TYPE_SCAN = "SCAN";
    public static final String WLXX_TYPE_QTXL = "A";
    public static final String WLXX_TYPE_DGXL = "B";


    public static final String ACTIVITY_START_TYPE_NAME = "startTypeName";
    public static final String ACTIVITY_START_TYPE_CODE = "startTypeCode";
    public static final long STATU_DIALOG_SHOW_TIME = 3000L;
    public static final String TYPE_INTERFACE_GETMES = "30001";


    public static final String TYPE_INTERFACE_GETKW = "ReadStockList";
    public static final String TYPE_INTERFACE_GETRKRECORD = "StkInResume";//获取有无入库的记录
    public static final String TYPE_INTERFACE_CHECK_CPCODE = "StockInCheck";

    public static final String TYPE_INTERFACE_RK = "StockInPost";//入库

    public static final String TYPE_INTERFACE_CANCELRK = "StkInCancel";//取消入库


    public static final String TYPE_INTERFACE_GETFHD = "LoadShippingNote";//获取发货单
    public static final String TYPE_INTERFACE_GET_FHDDETAIL = "LoadShippingNote";//获取发货单明细


    public static final String TYPE_INTERFACE_CHECK_CPCODE2 = "StockOutCheck";//出货条码验证
    public static final String TYPE_INTERFACE_CANCELCK = "StkOutCancel";
    public static final String TYPE_INTERFACE_CK = "StockOutPost";
    public static final String TYPE_INTERFACE_GETCKRECORD = "StkOutResume";

}
