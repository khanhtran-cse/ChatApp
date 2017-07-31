package com.qkt.app.chatapp.activity.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qkt.app.chatapp.R;
import com.qkt.app.chatapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeInterface.View {
    private HomePresenter mPresenter;
    private List<String> mUsers;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPresenter = new HomePresenter(this,this);

        mUsers = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mUsers);
        ListView listView = (ListView)findViewById(R.id.lv_friends);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPresenter.chatWith(i);
            }
        });
    }

    @Override
    public void updateFriendList(List<User> users) {
        mUsers.clear();
        for(int i = 0; i< users.size();i++) {
            mUsers.add(users.get(i).getEmail());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.setUserOffline();
        super.onDestroy();
    }
}
