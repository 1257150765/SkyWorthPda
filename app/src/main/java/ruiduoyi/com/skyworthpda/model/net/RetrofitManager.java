package ruiduoyi.com.skyworthpda.model.net;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ruiduoyi.com.skyworthpda.model.bean.CKBean;
import ruiduoyi.com.skyworthpda.model.bean.CKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.CancelCKBean;
import ruiduoyi.com.skyworthpda.model.bean.CancelRKBean;
import ruiduoyi.com.skyworthpda.model.bean.CheckQRCODEBean;
import ruiduoyi.com.skyworthpda.model.bean.CheckZWBean;
import ruiduoyi.com.skyworthpda.model.bean.CpCodeBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdBean;
import ruiduoyi.com.skyworthpda.model.bean.FhdDetailBean;
import ruiduoyi.com.skyworthpda.model.bean.GzBean;
import ruiduoyi.com.skyworthpda.model.bean.HaveRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.KwBean;
import ruiduoyi.com.skyworthpda.model.bean.LoginBean;
import ruiduoyi.com.skyworthpda.model.bean.CompanyBean;
import ruiduoyi.com.skyworthpda.model.bean.MesBean;
import ruiduoyi.com.skyworthpda.model.bean.PGXJBean;
import ruiduoyi.com.skyworthpda.model.bean.PGXJRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.PermissionBean;
import ruiduoyi.com.skyworthpda.model.bean.RKBean;
import ruiduoyi.com.skyworthpda.model.bean.RKRecordBean;
import ruiduoyi.com.skyworthpda.model.bean.SCSLBean;
import ruiduoyi.com.skyworthpda.model.bean.SCXLBean;
import ruiduoyi.com.skyworthpda.model.bean.SLQRBean;
import ruiduoyi.com.skyworthpda.model.bean.SLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.UpdateBean;
import ruiduoyi.com.skyworthpda.model.bean.UploadLogBean;
import ruiduoyi.com.skyworthpda.model.bean.VersionSwitchBean;
import ruiduoyi.com.skyworthpda.model.bean.WLXXBean;
import ruiduoyi.com.skyworthpda.model.bean.XLZWBean;
import ruiduoyi.com.skyworthpda.model.bean.XbBean;
import ruiduoyi.com.skyworthpda.model.bean.ZWCXBean;
import ruiduoyi.com.skyworthpda.model.bean.ZWTZBean;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

import static ruiduoyi.com.skyworthpda.util.Config.BASE_URL;


/**
 * Created by Chen on 2018/4/24.
 */

public class RetrofitManager {
    public static  String MAC = "";

    static {
        init();
    }

    private static String token = "";
    private static String companyName = "";

    public static void setToken(String token) {
        RetrofitManager.token = token;
    }

    public static void setCompanyName(String companyName) {
        RetrofitManager.companyName = companyName;
    }

    static Retrofit retrofit;
    public static void init(){

        Interceptor logInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder builder = request.url().newBuilder()
                        .scheme(request.url().scheme())
                        .host(request.url().host());
                if (!"".equals(token)){
                    builder.addQueryParameter("key_tokenid",token);
                }
                if (!"".equals(companyName)){
                    builder.addQueryParameter("key_srvid",companyName);
                }
                builder.addQueryParameter("key_Mac",MAC);
                Request request1 = request.newBuilder()
                        .url(builder.build())
                        .method(request.method(),request.body())
                        .build();
                //在这里获取到request后就可以做任何事情了
                LogWraper.d("Net",request1.toString());
                Response response = chain.proceed(request1);
                MediaType mediaType = response.body().contentType();
                String content= response.body().string();
                LogWraper.d("Net",content);
                return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Observable<LoginBean> login(String companyName, String key_usrid, String key_pwd, String key_isAuto){
        return retrofit.create(Api.class).login(Config.TYPE_INTERFACE_LOGIN,companyName,key_usrid,key_pwd,key_isAuto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public static Observable<CompanyBean> getCompanyList(){
        return retrofit.create(Api.class).getCompanyList(Config.TYPE_INTERFACE_GETCOMPANYLIST,"all")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<PermissionBean> getPermission(){
        return retrofit.create(Api.class).getPermission(Config.TYPE_INTERFACE_PERMISSION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<XbBean> getXb(){
        return retrofit.create(Api.class).getXb(Config.TYPE_INTERFACE_XB,"XBM")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<XbBean> getZZXB(){
        return retrofit.create(Api.class).getXb(Config.TYPE_INTERFACE_XB,"ZZXB")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<GzBean> getGz(){
        return retrofit.create(Api.class).getGz(Config.TYPE_INTERFACE_XB,"YZCS")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<SLXXBean> getSLXX(String key_xbdm,String key_flag){
        return retrofit.create(Api.class).getSLXX(Config.TYPE_INTERFACE_GETSLXX,key_xbdm,key_flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<SLXXBean> getQRXX(String key_xbdm,String key_flag){
        return retrofit.create(Api.class).getSLXX(Config.TYPE_INTERFACE_GETQRXX,key_xbdm,key_flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<CheckZWBean> checkZW(String key_type, String key_chkval){
        return retrofit.create(Api.class).checkZW(Config.TYPE_INTERFACE_CHECK,key_type,key_chkval)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //检查二维码
    public static Observable<CheckQRCODEBean> checkQRCODE(String key_type, String key_chkval){
        return retrofit.create(Api.class).checkQRCODE(Config.TYPE_INTERFACE_CHECK,key_type,key_chkval)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 执行首次上料
     * @param xbm_xbdm 线别
     * @param zw 站位
     * @param code 二维码
     * @param wldm 物料代码
     * @param qty 数量
     * @return
     */
    public static Observable<SCSLBean> scsl(String xbm_xbdm, String zw, String code,String wldm, String qty,String binValue){
        return retrofit.create(Api.class).scsl(Config.TYPE_INTERFACE_SCSL,xbm_xbdm,zw,code,wldm,qty,binValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    /**
     * 执行生产续料
     * @param xbm_xbdm 线别
     * @param oldCoed 旧料盘
     * @param code 新料盘
     * @param wldm 物料代码
     * @param qty 数量
     * @param binValue
     * @return
     */
    public static Observable<SCXLBean> scxl(String xbm_xbdm, String oldCoed, String code, String wldm, String qty, String binValue){
        return retrofit.create(Api.class).scxl(Config.TYPE_INTERFACE_SCXL,xbm_xbdm,oldCoed,code,wldm,qty,binValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 执行上料确认
     * @param xbm_xbdm 线别
     * @param zw 站位
     * @param code 二维码
     * @param wldm 物料代码
     * @return
     */
    public static Observable<SLQRBean> slqr(String xbm_xbdm, String zw, String code, String wldm){
        return retrofit.create(Api.class).slqr(Config.TYPE_INTERFACE_SCQR,xbm_xbdm,zw,code,wldm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 执行站位调整
     * @param xbm_xbdm 线别
     * @param oldZw 旧站位
     * @param newZw 新站位
     * @return
     */
    public static Observable<ZWTZBean> zwtz(String xbm_xbdm, String oldZw, String newZw){
        return retrofit.create(Api.class).zwtz(Config.TYPE_INTERFACE_ZWTZ,xbm_xbdm,oldZw,newZw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 获取站位程序列表
     * @param xb
     * @return
     */
    public static Observable<ZWCXBean> getZwcx(String xb){
        return retrofit.create(Api.class).getZwcx(Config.TYPE_INTERFACE_XB,"PZW",xb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 执行版本切换
     * @param xb
     * @param zwcx
     * @return
     */
    public static Observable<VersionSwitchBean> versionSwitch(String xb,String zwcx){
        return retrofit.create(Api.class).versionSwitch(Config.TYPE_INTERFACE_VERSIONSWITCH,xb,zwcx)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获得下料站位
     * @param xb 线别
     * @param qrcode 二维码
     * @return
     */
    public static Observable<XLZWBean> getXLZW(String xb,String qrcode){
        return retrofit.create(Api.class).getXLZW(Config.TYPE_INTERFACE_GETXLZW,xb,qrcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 获得是否有巡检记录
     * @param xb 线别
     * @return
     */
    public static Observable<HaveRecordBean> getHaveRecord(String xb){
        return retrofit.create(Api.class).getHaveRecord(Config.TYPE_INTERFACE_GETHAVERECORE,xb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获得巡检记录
     * @param xb 线别
     * @return
     */
    public static Observable<PGXJRecordBean> getRecord(String xb){
        return retrofit.create(Api.class).getRecord(Config.TYPE_INTERFACE_GETECORE,xb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 物料下线
     * @param wlxxType 全体、单个
     * @param xb 线别
     * @param v_oricode 二维码
     * @param wldm 物料代码
     * @param key_zwdm 下料站位
     * @return
     */
    public static Observable<WLXXBean> wlxx(String wlxxType, String xb, String v_oricode, String wldm,String key_zwdm){
        return retrofit.create(Api.class).wlxx(Config.TYPE_INTERFACE_WLXX,wlxxType,xb,v_oricode,wldm,key_zwdm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<PGXJBean> pgxj(String type, String xb, String v_oricode, String wldm ){
        return retrofit.create(Api.class).pgxj(Config.TYPE_INTERFACE_PGXJ,type,xb,v_oricode,wldm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UpdateBean> checkUpdate(){
        return retrofit.create(Api.class).checkUpdate(Config.TYPE_INTERFACE_UPDATE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<UpdateBean> checkUpdate2(String companyName){
        return retrofit.create(Api.class).checkUpdate2(Config.TYPE_INTERFACE_UPDATE,companyName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<MesBean> getMesDetail(String key_xbdm, String key_gzdm, String key_qrcode){
        return retrofit.create(Api.class).getMesDetail(Config.TYPE_INTERFACE_GETMES,key_xbdm,key_gzdm,key_qrcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<MesBean> getMesDetail2(String key_xbdm, String key_gzdm, String key_qrcode,String key_cur,String key_vol){
        return retrofit.create(Api.class).getMesDetail2(Config.TYPE_INTERFACE_GETMES,key_xbdm,key_gzdm,key_qrcode,key_cur,key_vol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UploadLogBean> uploadLog(String key_Mac,String key_ErrMsg){
        return retrofit.create(Api.class).uploadLog(Config.TYPE_INTERFACE_UPLOADLOG,key_Mac,key_ErrMsg);

    }

    public static void logout() {
        setToken("");
        setCompanyName("");
    }

    public static Observable<KwBean> getKw(){
        return retrofit.create(Api.class).getKw(Config.TYPE_INTERFACE_GETKW).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    //入库时验证成品条码
    public static Observable<CpCodeBean> checkCPCODE(String code) {
        return retrofit.create(Api.class).checkCPCODE(Config.TYPE_INTERFACE_CHECK_CPCODE,code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //入库
    public static Observable<RKBean> rk(String kwCode,String jsonData,String djbh) {
        return retrofit.create(Api.class).rk(Config.TYPE_INTERFACE_RK,kwCode,jsonData,djbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<CancelRKBean> cancelRk(String djbh, String tmJson) {
        return retrofit.create(Api.class).cancelRk(Config.TYPE_INTERFACE_CANCELRK,djbh,tmJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<RKRecordBean> getRkRecord() {
        return retrofit.create(Api.class).getRkRecord(Config.TYPE_INTERFACE_GETRKRECORD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public static Observable<FhdBean> getFhd(String djbh) {
        //0获取发货单，1获取发货单明细
        return retrofit.create(Api.class).getFhd(Config.TYPE_INTERFACE_GETFHD,djbh,"0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<FhdDetailBean> getFhdDetail(String djbh) {
        //0获取发货单，1获取发货单明细
        return retrofit.create(Api.class).getFhdDetail(Config.TYPE_INTERFACE_GET_FHDDETAIL,djbh,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //出库时验证成品条码
    public static Observable<CpCodeBean> checkCPCODE2(String djbh, String code) {
        return retrofit.create(Api.class).checkCPCODE2(Config.TYPE_INTERFACE_CHECK_CPCODE2,djbh,code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CancelCKBean> cancelCk(String djbh, String tmxh) {
        return retrofit.create(Api.class).cancelCk(Config.TYPE_INTERFACE_CANCELCK,djbh,tmxh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CKBean> ck(String key_shiplist, String key_scanlist, String djbh) {
        return retrofit.create(Api.class).ck(Config.TYPE_INTERFACE_CK,key_shiplist,key_scanlist,djbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public static Observable<CKRecordBean> getCkRecord() {
        return retrofit.create(Api.class).getCkRecord(Config.TYPE_INTERFACE_GETCKRECORD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    interface Api{
        @GET("SmtPDADataDeal")
        Observable<LoginBean> login(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid, @Query("key_usrid") String key_usrid, @Query("key_pwd") String key_pwd, @Query("key_isAuto")String key_isAuto);
        @GET("SmtPDADataDeal")
        Observable<CompanyBean> getCompanyList(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid);

        @GET("SmtPDADataDeal")
        Observable<UpdateBean> checkUpdate(@Query("key_prgid") String key_prgid);
        @GET("SmtPDADataDeal")
        Observable<UpdateBean> checkUpdate2(@Query("key_prgid") String key_prgid,@Query("key_srvid") String key_srvid);

        @GET("SmtPDADataDeal")
        Observable<PermissionBean> getPermission(@Query("key_prgid") String key_prgid);
        @GET("SmtPDADataDeal")
        Observable<XbBean> getXb(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type);
        @GET("SmtPDADataDeal")
        Observable<GzBean> getGz(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type);
        @GET("SmtPDADataDeal")
        Observable<SLXXBean> getSLXX(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_flag") String key_flag);
        @GET("SmtPDADataDeal")
        Observable<CheckZWBean> checkZW(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type, @Query("key_chkval") String key_chkval);
        @GET("SmtPDADataDeal")
        Observable<CheckQRCODEBean> checkQRCODE(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type, @Query("key_chkval") String key_chkval);
        @GET("SmtPDADataDeal")
        Observable<SCSLBean> scsl(@Query("key_prgid") String key_prgid,@Query("key_xbdm") String key_xbdm,@Query("key_zwdm") String key_zwdm,@Query("key_qrcode") String key_qrcode,@Query("key_wldm") String key_wldm,@Query("key_qty") String key_qty,@Query("key_usrVal") String key_usrVal);

        @GET("SmtPDADataDeal")
        Observable<ZWCXBean> getZwcx(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type, @Query("key_xbdm") String key_xbdm);

        @GET("SmtPDADataDeal")
        Observable<VersionSwitchBean> versionSwitch(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_cxdm") String key_cxdm);

        @GET("SmtPDADataDeal")
        Observable<XLZWBean> getXLZW(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_qrcode") String key_qrcode);

        @GET("SmtPDADataDeal")
        Observable<SCXLBean> scxl(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_oldcode") String key_oldcode, @Query("key_qrcode") String key_qrcode, @Query("key_wldm") String key_wldm, @Query("key_qty") String key_qty,@Query("key_usrVal") String key_usrVal);

        @GET("SmtPDADataDeal")
        Observable<SLQRBean> slqr(@Query("key_prgid") String key_prgid,@Query("key_xbdm") String key_xbdm,@Query("key_zwdm") String key_zwdm,@Query("key_qrcode") String key_qrcode,@Query("key_wldm") String key_wldm);

        @GET("SmtPDADataDeal")
        Observable<ZWTZBean> zwtz(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_oldzw") String key_oldzw, @Query("key_newzw") String key_newzw);


        @GET("SmtPDADataDeal")
        Observable<HaveRecordBean> getHaveRecord(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm);

        @GET("SmtPDADataDeal")
        Observable<PGXJRecordBean> getRecord(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm);

        @GET("SmtPDADataDeal")
        Observable<WLXXBean> wlxx(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type,  @Query("key_xbdm") String key_xbdm,@Query("key_qrcode") String key_qrcode, @Query("key_wldm") String wldm,@Query("key_zwdm") String key_zwdm);

        @GET("SmtPDADataDeal")
        Observable<PGXJBean> pgxj(@Query("key_prgid") String key_prgid, @Query("key_type") String key_type,  @Query("key_xbdm") String key_xbdm, @Query("key_qrcode") String key_qrcode, @Query("key_wldm") String wldm);

        @GET("SmtPDADataDeal")
        Observable<MesBean> getMesDetail(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_gzdm") String gzdm, @Query("key_qrcode") String key_qrcode);

        @GET("SmtPDADataDeal")
        Observable<MesBean> getMesDetail2(@Query("key_prgid") String key_prgid, @Query("key_xbdm") String key_xbdm, @Query("key_gzdm") String gzdm, @Query("key_qrcode") String key_qrcode,@Query("key_cur") String key_cur,@Query("key_vol") String key_vol);

        @GET("SmtPDADataDeal")
        Observable<UploadLogBean> uploadLog(@Query("key_prgid") String key_prgid,@Query("key_Mac") String key_Mac,@Query("key_ErrMsg") String key_ErrMsg);

        @GET("SmtPDADataDeal")
        Observable<KwBean> getKw(@Query("key_prgid") String key_prgid);

        @GET("SmtPDADataDeal")
        Observable<CpCodeBean> checkCPCODE(@Query("key_prgid") String key_prgid, @Query("key_tmxh") String code);

        @GET("SmtPDADataDeal")
        Observable<CpCodeBean> checkCPCODE2(@Query("key_prgid") String key_prgid,@Query("key_ShipNo") String key_ShipNo, @Query("key_tmxh") String code);

        @GET("SmtPDADataDeal")
        Observable<RKBean> rk(@Query("key_prgid") String key_prgid, @Query("key_stkid")String kwCode, @Query("key_scanlist")String jsonData, @Query("key_djbh")String djbh);

        @GET("SmtPDADataDeal")
        Observable<CancelRKBean> cancelRk(@Query("key_prgid") String key_prgid, @Query("key_djbh") String djbh, @Query("key_tmxh")String tmJson);

        @GET("SmtPDADataDeal")
        Observable<RKRecordBean> getRkRecord(@Query("key_prgid") String key_prgid);


        @GET("SmtPDADataDeal")
        Observable<FhdBean> getFhd(@Query("key_prgid") String key_prgid, @Query("key_Djbh") String key_Djbh, @Query("key_flag") String key_flag);

        @GET("SmtPDADataDeal")
        Observable<FhdDetailBean> getFhdDetail(@Query("key_prgid") String key_prgid, @Query("key_Djbh") String key_Djbh, @Query("key_flag") String key_flag);


        @GET("SmtPDADataDeal")
        Observable<CancelCKBean> cancelCk(@Query("key_prgid") String key_prgid, @Query("key_djbh") String djbh, @Query("key_tmxh")String tmxh);

        @POST("SmtPDADataDeal")
        Observable<CKBean> ck(@Query("key_prgid") String key_prgid, @Query("key_shiplist") String key_shiplist, @Query("key_scanlist") String key_scanlist, @Query("key_djbh")String djbh);


        @GET("SmtPDADataDeal")
        Observable<CKRecordBean> getCkRecord(@Query("key_prgid") String key_prgid);

    }
}
