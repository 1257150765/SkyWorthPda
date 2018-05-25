package ruiduoyi.com.skyworthpda.presentor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ruiduoyi.com.skyworthpda.contact.LoginContact;
import ruiduoyi.com.skyworthpda.view.activity.LoginActivity;

/**
 * Created by DengJf on 2018/1/12.
 */

public class LoginPresenter implements LoginContact.Presentor {
    private LoginContact.View view;
    private Context context;

    public LoginPresenter(LoginContact.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(final String userName, final String pwd) {
        view.onLoading(true);
        String userNameErrorInfo = "";
        String pwdErrorInfo = "";
        if ("123456".equals(userName)){

        }else {
            userNameErrorInfo = "用户不存在";
            view.onLoginFalse(userNameErrorInfo,pwdErrorInfo);
            return;
        }
        if ("123456".equals(pwd)){

        }else {
            pwdErrorInfo = "密码不正确";
            view.onLoginFalse(userNameErrorInfo,pwdErrorInfo);
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Handler(context.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.onLoading(false);
                        view.onLoginSecceed(userName,pwd);
                    }
                });

            }
        }).start();

    }
}
