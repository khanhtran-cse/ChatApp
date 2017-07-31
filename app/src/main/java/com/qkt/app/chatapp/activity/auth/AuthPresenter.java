package com.qkt.app.chatapp.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.qkt.app.chatapp.R;
import com.qkt.app.chatapp.activity.guest.GetNameActivity;
import com.qkt.app.chatapp.activity.home.HomeActivity;
import com.qkt.app.chatapp.activity.home.HomeInterface;

/**
 * Created by qkt on 28/07/2017.
 */

public class AuthPresenter implements AuthInterface.Presenter{
    private AuthInterface.View mView;
    private AuthModel mModel;
    private Context mContext;

    public AuthPresenter(Context context, AuthInterface.View view){
        mView = view;
        mModel = new AuthModel(this);
        mContext = context;
    }

    @Override
    public void onRegisterSuccess() {
        mView.hideProgress();
        mView.onRegisterSuccess();
    }

    @Override
    public void onRegisterError(Exception ex) {
        mView.hideProgress();
        mView.onError(ex.getMessage());
    }

    @Override
    public void onLoginSuccess() {
        mView.hideProgress();
        Toast.makeText(mContext,"Login success.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(mContext, HomeActivity.class);
        mContext.startActivity(intent);
        mView.quit();
    }

    @Override
    public void onLoginError(Exception ex) {
        mView.hideProgress();
        mView.onError(ex.getMessage());

    }


    public void login(String email, String password){
        if(email == null || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mView.onError(mContext.getString(R.string.error_email_incorrect));
            return;
        }

        if(password == null || password.length() < 6){
            mView.onError(mContext.getString(R.string.error_password_incorrect));
            return;
        }

        mView.showProgress();
        mModel.login(email,password);
    }

    public void register(String email, String password){
        if(email == null || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mView.onError(mContext.getString(R.string.error_email_incorrect));
            return;
        }

        if(password == null || password.length() < 6){
            mView.onError(mContext.getString(R.string.error_password_incorrect));
            return;
        }

        mView.showProgress();
        mModel.register(email,password);
    }

    public void loginAnonymously(){
        Intent intent = new Intent(mContext, GetNameActivity.class);
        mContext.startActivity(intent);
    }
}
