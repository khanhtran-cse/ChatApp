package com.qkt.app.chatapp.activity.guest;

import android.content.Context;
import android.content.Intent;

import com.qkt.app.chatapp.R;
import com.qkt.app.chatapp.activity.chat.ChatActivity;
import com.qkt.app.chatapp.model.User;

/**
 * Created by qkt on 31/07/2017.
 */

public class GetNamePresenter implements GetNameInterface.Presenter {
    private Context mContext;
    private GetNameInterface.View mView;
    private GetNameModel mModel;
    private String mName;

    public GetNamePresenter(Context context, GetNameInterface.View view){
        mContext = context;
        mView = view;
        mModel = new GetNameModel(this);
    }

    public void login(String name){
        if(name.equals("")){
            mView.onError(mContext.getString(R.string.error_invalid_name));
            return;
        }
        mName = name;
        mModel.signInAnonymously();
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(mContext, ChatActivity.class);
        User user = mModel.getCurrentUser();
        if(user != null) {
            intent.putExtra("user", new String[]{mName, user.getUid()});
            intent.putExtra("roomInfo", new String[]{"Public Room", "publicRoom"});
            mContext.startActivity(intent);
            mView.quit();
        } else{
            mView.onError("");
        }
    }

    @Override
    public void onLoginError(Exception ex) {
        mView.onError(ex.getMessage());
    }
}
