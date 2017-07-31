package com.qkt.app.chatapp.activity.guest;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.qkt.app.chatapp.model.User;

/**
 * Created by qkt on 31/07/2017.
 */

public class GetNameModel {
    private FirebaseAuth mAuth;
    private GetNameInterface.Presenter mPresenter;

    public GetNameModel(GetNameInterface.Presenter presenter){
        mPresenter = presenter;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInAnonymously(){
        mAuth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mPresenter.onLoginSuccess();
                        } else {
                            mPresenter.onLoginError(task.getException());
                        }
                    }
                });
    }

    public User getCurrentUser(){
        User user = null;
        FirebaseUser u = mAuth.getCurrentUser();
        if(u != null){
            user = new User("",u.getUid());
        }
        return user;
    }

    public void setGuestName(String name){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){

        }
    }
}
