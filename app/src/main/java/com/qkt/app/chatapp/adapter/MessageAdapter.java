package com.qkt.app.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qkt.app.chatapp.R;
import com.qkt.app.chatapp.model.Message;

import java.util.List;

/**
 * Created by qkt on 31/07/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mMessages;
    private String mMeId;
    private LayoutInflater inflater;
    private Context mContext;

    public MessageAdapter(Context context, List<Message> messages, String meId){
        mMessages = messages;
        mMeId = meId;
        mContext = context;
        inflater =  LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 0){
            view = inflater.inflate(R.layout.item_message_me,parent,false);
        } else{
            view = inflater.inflate(R.layout.item_message_partner,parent,false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mMessages.get(position).getUsername());
        holder.time.setText(mMessages.get(position).getTime());
        holder.text.setText(mMessages.get(position).getText());
    }

    @Override
    public int getItemViewType(int position) {
        if(mMessages.get(position).getUid().equals(mMeId)){
            return 0;
        } else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
        TextView text;

        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.tv_name);
            time = view.findViewById(R.id.tv_time);
            text = view.findViewById(R.id.tv_text);
        }
    }
}
