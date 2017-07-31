package com.qkt.app.chatapp.activity.chat;

import com.google.firebase.database.DataSnapshot;
import com.qkt.app.chatapp.model.Message;

import java.util.List;

/**
 * Created by qkt on 29/07/2017.
 */

public interface ChatInterface {
    public interface View{
        void updateMessageList(List<Message> messages);
        void setInfo(String userId, String roomName);
        void setAdapter();
        void clearText();
    }

    public interface Presenter{
        void updateMessageList(DataSnapshot dataSnapshot);

    }
}
