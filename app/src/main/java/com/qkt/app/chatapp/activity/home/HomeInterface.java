package com.qkt.app.chatapp.activity.home;

import com.google.firebase.database.DataSnapshot;
import com.qkt.app.chatapp.model.User;

import java.util.Iterator;
import java.util.List;

/**
 * Created by qkt on 29/07/2017.
 */

public interface HomeInterface {
    public interface View{
        public void updateFriendList(List<User> users);
    }

    public interface Presenter{
        public void onOnlineListChanged(Iterable<DataSnapshot> onlineUsers);
    }
}
