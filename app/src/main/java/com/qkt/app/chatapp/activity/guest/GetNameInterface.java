package com.qkt.app.chatapp.activity.guest;

/**
 * Created by qkt on 31/07/2017.
 */

public interface GetNameInterface {
    interface Presenter{
        void onLoginSuccess();
        void onLoginError(Exception ex);
    }

    interface View{
        void onError(String mgs);
        void quit();
    }
}
