package com.qkt.app.chatapp.activity.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by qkt on 28/07/2017.
 */

public class AuthModel {
    private FirebaseAuth mAuth;
    private AuthInterface.Presenter mPresenter;

    public AuthModel(AuthInterface.Presenter presenter){
        mPresenter = presenter;
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Add user profile
                            mAuth.signInWithEmailAndPassword(email,password);
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user != null){
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference users = database.getReference("users");
                                users.child(user.getUid()).child("email").setValue(email);
                                mPresenter.onRegisterSuccess();
                            } else{
                                mPresenter.onLoginError(new Exception());
                            }
                        }
                        else{
                            mPresenter.onRegisterError(task.getException());
                        }
                    }
                });
    }

    public void createUserProfile(String email, String password){

    }

    public void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mPresenter.onLoginSuccess();
                        }
                        else{
                            mPresenter.onLoginError(task.getException());
                        }
                    }
                });
    }
}
