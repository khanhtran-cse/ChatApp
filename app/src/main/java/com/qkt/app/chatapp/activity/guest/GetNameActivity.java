package com.qkt.app.chatapp.activity.guest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qkt.app.chatapp.R;

public class GetNameActivity extends AppCompatActivity implements GetNameInterface.View {
    private EditText etName;
    private TextView tvError;
    private GetNamePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);
        etName = (EditText)findViewById(R.id.et_name);
        tvError = (TextView)findViewById(R.id.tv_error);
        mPresenter = new GetNamePresenter(this,this);
    }

    @Override
    public void onError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(View.VISIBLE);
    }

    public void getName(View view){
        tvError.setVisibility(View.INVISIBLE);
        mPresenter.login(etName.getText().toString());
    }

    @Override
    public void quit() {
        finish();
    }

    public void cancel(View view){
        finish();
    }
}
