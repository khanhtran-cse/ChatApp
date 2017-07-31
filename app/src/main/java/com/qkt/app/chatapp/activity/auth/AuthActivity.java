package com.qkt.app.chatapp.activity.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qkt.app.chatapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends AppCompatActivity
    implements AuthInterface.View{
    private static final String TAG = "AuthActivity";
    @BindView(R.id.actv_email)
    AutoCompleteTextView email;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.tv_status)
    TextView status;
    @BindView(R.id.pb_progress)
    ProgressBar progress;
    private AuthPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mPresenter = new AuthPresenter(this,this);
        ButterKnife.bind(this);
    }

    @Override
    public void showProgress() {
        status.setText(getText(R.string.auth_status_checking));
        status.setTextColor(getResources().getColor(android.R.color.black));
        status.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        status.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(String msg) {
        status.setText(msg);
        status.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        status.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRegisterSuccess() {
        status.setText(getText(R.string.auth_register_success));
        status.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        status.setVisibility(View.VISIBLE);
    }

    public void quit(){
        this.finish();
    }

    public void login(View view){
        String e = email.getText().toString();
        String p = password.getText().toString();
        mPresenter.login(e,p);

    }

    public void register(View view){
        String e = email.getText().toString();
        String p = password.getText().toString();
        mPresenter.register(e,p);
    }

    public void signInAnonymously(View view){
        mPresenter.loginAnonymously();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"AuthActivity destroy.");
        super.onDestroy();
    }
}
