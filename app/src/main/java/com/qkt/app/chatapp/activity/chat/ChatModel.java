package com.qkt.app.chatapp.activity.chat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qkt.app.chatapp.model.Message;

/**
 * Created by qkt on 29/07/2017.
 */

public class ChatModel {
    FirebaseDatabase mDatabase;
    private ChatInterface.Presenter mPresenter;

    public ChatModel(ChatInterface.Presenter presenter){
        this.mPresenter = presenter;
        mDatabase = FirebaseDatabase.getInstance();
    }

    public void sendMessage(String roomId, Message msg){
        DatabaseReference mRef = mDatabase.getReference("rooms").child(roomId);
        mRef.push().setValue(msg);
    }

    public void subscribeRoom(String roomId){
        DatabaseReference reference = mDatabase.getReference("rooms").child(roomId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPresenter.updateMessageList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
