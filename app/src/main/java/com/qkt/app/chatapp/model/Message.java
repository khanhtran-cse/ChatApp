package com.qkt.app.chatapp.model;

/**
 * Created by qkt on 29/07/2017.
 */

public class Message {
    private String username;
    private String text;
    private String time;
    private String uid;

    public Message(){

    }

    public Message(String userId,String username, String text, String time){
        this.username = username;
        this.text = text;
        this.time = time;
        this.uid = userId;
    }

    public void setUid(String userId){
        this.uid = userId;
    }

    public String getUid(){
        return this.uid;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setText(String text){
        this.text =text;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getUsername(){
        return this.username;
    }

    public String getText(){
        return this.text;
    }

    public String getTime(){
        return this.time;
    }
}
