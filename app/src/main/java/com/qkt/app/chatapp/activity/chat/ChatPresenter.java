package com.qkt.app.chatapp.activity.chat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.qkt.app.chatapp.model.Message;
import com.qkt.app.chatapp.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by qkt on 29/07/2017.
 */

public class ChatPresenter implements ChatInterface.Presenter {
    private static final String TAG = "ChatPresenter";
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private ChatInterface.View mView;
    private ChatModel mModel;
    private FirebaseAuth mAuth;
    private Context mContext;
    private String mRoomId;
    private String mRoomName;
    private User mUser;
    private List<Message> mMessages;

    public ChatPresenter(Context context, ChatInterface.View view, Intent intent){
        mView = view;
        mModel = new ChatModel(this);
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
        mMessages = new ArrayList<>();
        initialize(intent);
        mModel.subscribeRoom(mRoomId);
        mView.setInfo(mUser.getUid(), mRoomName);
        mView.setAdapter();
    }

    public void initialize(Intent intent){
        String[] r = intent.getStringArrayExtra("roomInfo");
        String[] m = intent.getStringArrayExtra("user");
        if(r != null || m != null){
            mUser = new User(m[0],m[1]);
            mRoomId = r[1];
            mRoomName = r[0];
        } else{
            Toast.makeText(mContext,"Failed",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateMessageList(DataSnapshot dataSnapshot ) {
        mMessages.clear();
        for(DataSnapshot item : dataSnapshot.getChildren()){
            Log.i(TAG,item.getValue().toString());
            Message m = item.getValue(Message.class);
            mMessages.add(m);
        }
        mView.updateMessageList(mMessages);
    }

    public void sendMessage(String text){
        if(text == null){
            return;
        }
        Message message = new Message();
        message.setUsername(mUser.getEmail());
        message.setText(text);
        Date d = new Date();
        String time = sdf.format(d);
        message.setTime(time);
        message.setUid(mUser.getUid());

        mModel.sendMessage(mRoomId,message);
        mView.clearText();
    }
}
