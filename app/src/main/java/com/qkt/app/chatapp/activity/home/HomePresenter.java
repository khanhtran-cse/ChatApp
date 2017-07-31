package com.qkt.app.chatapp.activity.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.qkt.app.chatapp.activity.chat.ChatActivity;
import com.qkt.app.chatapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkt on 29/07/2017.
 */

public class HomePresenter implements HomeInterface.Presenter {
    private static final String TAG = "HomePresenter";
    private HomeInterface.View mView;
    private HomeModel mModel;
    private Context mContext;
    private List<User> mUsers;

    public HomePresenter(Context context, HomeInterface.View view){
        mView = view;
        mContext = context;
        mModel = new HomeModel(this);
        if(!mModel.isLogin()){
            Toast.makeText(mContext,"The user don't login.",Toast.LENGTH_LONG).show();
        }
        mModel.addUserToOnlineList();
        mUsers = new ArrayList<>();
    }

    @Override
    public void onOnlineListChanged(Iterable<DataSnapshot> onlineUsers) {
        mUsers.clear();
        for(DataSnapshot item : onlineUsers){
            Log.i(TAG,item.getKey() + " -- " + item.getValue().toString());
            User user = new User(item.getValue().toString(),item.getKey());
            if(user.getUid() != mModel.getCurrentUser().getUid()) {
                mUsers.add(user);
            }
        }
        mView.updateFriendList(mUsers);
    }

    public void setUserOffline(){
        mModel.removeUserFromOnlineList();
    }

    public void chatWith(int position){
        Intent intent = new Intent(mContext, ChatActivity.class);

        User cur = mModel.getCurrentUser();
        if(cur != null){
            String roomId;
            if(cur.getUid().compareTo(mUsers.get(position).getUid()) < 0){
                roomId = cur.getUid() + mUsers.get(position).getUid();
            } else{
                roomId = mUsers.get(position).getUid() + cur.getUid();
            }
            intent.putExtra("roomInfo",new String[]{mUsers.get(position).getEmail(),roomId});
            intent.putExtra("user",new String[]{cur.getEmail(),cur.getUid()});
            mContext.startActivity(intent);
        } else{
            Toast.makeText(mContext,"Please login again.",Toast.LENGTH_LONG).show();
        }
    }
}
