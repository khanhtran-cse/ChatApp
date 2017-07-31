package com.qkt.app.chatapp.activity.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qkt.app.chatapp.R;
import com.qkt.app.chatapp.adapter.MessageAdapter;
import com.qkt.app.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity
    implements ChatInterface.View{
    private ChatPresenter mPresenter;
    private EditText edMessage;
    private List<Message> mMessages;
    private MessageAdapter mAdapter;
    private String mUserId;
    private String mRoomName;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        edMessage = (EditText)findViewById(R.id.et_message);
        mMessages = new ArrayList<>();
        mPresenter = new ChatPresenter(this,this,intent);
    }

    @Override
    public void setInfo(String userId, String roomName){
        mUserId = userId;
        mRoomName = roomName;
        TextView conversation = (TextView)findViewById(R.id.tv_conversation_name);
        conversation.setText(mRoomName);
    }

    @Override
    public void setAdapter(){
        mAdapter = new MessageAdapter(this,mMessages, mUserId);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_messages);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void clearText() {
        edMessage.setText("");
    }

    public void sendMessage(View view){
        String mes = edMessage.getText().toString();
        mPresenter.sendMessage(mes);

    }

    public void updateMessageList(List<Message> messages){
        for(int i = mMessages.size(); i< messages.size();i++){
            mMessages.add(messages.get(i));
            mAdapter.notifyItemInserted(i);
            layoutManager.scrollToPosition(i);
        }

    }
}
