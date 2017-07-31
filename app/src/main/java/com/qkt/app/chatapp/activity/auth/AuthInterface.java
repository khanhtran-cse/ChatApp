package com.qkt.app.chatapp.activity.auth;

/**
 * Created by qkt on 28/07/2017.
 */

public interface AuthInterface {
    public interface View{
        void onError(String msg);
        void showProgress();
        void hideProgress();
        void onRegisterSuccess();
        void quit();
    }

    public interface Presenter{
        void onRegisterSuccess();
        void onRegisterError(Exception ex);
        void onLoginSuccess();
        void onLoginError(Exception ex);
    }
}
