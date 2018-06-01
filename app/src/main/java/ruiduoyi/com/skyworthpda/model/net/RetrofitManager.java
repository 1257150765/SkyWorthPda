package ruiduoyi.com.skyworthpda.model.net;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
import retrofit2.http.Query;
import ruiduoyi.com.skyworthpda.model.bean.LoginBean;
import ruiduoyi.com.skyworthpda.model.bean.CompanyBean;
import ruiduoyi.com.skyworthpda.util.Config;
import ruiduoyi.com.skyworthpda.util.LogWraper;

import static ruiduoyi.com.skyworthpda.util.Config.BASE_URL;


/**
 * Created by Chen on 2018/4/24.
 */

public class RetrofitManager {
    static {
        init();
    }
    static Retrofit retrofit;
    public static void init(){
        Interceptor logInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //在这里获取到request后就可以做任何事情了
                LogWraper.d("Net",request.toString());
                Response response = chain.proceed(request);
                MediaType mediaType = response.body().contentType();
                String content= response.body().string();
                LogWraper.d("response",content);
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

    public static Observable<LoginBean> login(String companyName,String key_usrid,String key_pwd){
        return retrofit.create(Api.class).login(Config.TYPE_INTERFACE_LOGIN,companyName,key_usrid,key_pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public static Observable<CompanyBean> getCompanyList(){
        return retrofit.create(Api.class).getCompanyList(Config.TYPE_INTERFACE_GETCOMPANYLIST,"all")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    interface Api{
        @GET("SmtPDADataDeal")
        Observable<LoginBean> login(@Query("key_prgid") String key_prgid,@Query("key_srvid") String key_srvid,@Query("key_usrid") String key_usrid,@Query("key_pwd") String key_pwd);
        @GET("SmtPDADataDeal")
        Observable<CompanyBean> getCompanyList(@Query("key_prgid") String key_prgid, @Query("key_srvid") String key_srvid);
    }
}
