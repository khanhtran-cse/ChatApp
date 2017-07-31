package com.qkt.app.chatapp.activity.home;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qkt.app.chatapp.model.User;

/**
 * Created by qkt on 29/07/2017.
 */

public class HomeModel {
    private static final String TAG = "HomeModel";
    private FirebaseUser mUser;
    private HomeInterface.Presenter mPresenter;
//    private DatabaseReference onlRef;

    public HomeModel(HomeInterface.Presenter presenter){
        mPresenter = presenter;
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        subscribeOnlineList();
    }

    public boolean isLogin(){
        if(mUser == null){
            return false;
        }
        return true;
    }

    public void addUserToOnlineList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("online").child(mUser.getUid()).setValue(mUser.getEmail());
        Log.i(TAG,"Added user to online list");
    }

    public void  removeUserFromOnlineList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("online").child(mUser.getUid()).removeValue();
        Log.i(TAG,"Removed user to online list");
    }

    public void subscribeOnlineList(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference onlRef ;
        onlRef = database.getReference("online");
        onlRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPresenter.onOnlineListChanged(dataSnapshot.getChildren());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public User getCurrentUser(){
        if(mUser != null){
            User user = new User(mUser.getEmail(),mUser.getUid());
            return user;
        }
        return null;
    }

    public void subscribeUserInfo(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference onlRef ;
        onlRef = database.getReference("chats").child(mUser.getUid());
        onlRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPresenter.onOnlineListChanged(dataSnapshot.getChildren());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
