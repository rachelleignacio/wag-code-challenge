package com.rachelleignacio.wagcodechallenge.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rachelleignacio.wagcodechallenge.R;
import com.rachelleignacio.wagcodechallenge.domain.User;

import java.util.List;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView gravatar;
        public TextView username;
        public TextView goldBadgeCount;
        public TextView silverBadgeCount;
        public TextView bronzeBadgeCount;

        public ViewHolder(View itemView) {
            super(itemView);
            gravatar = itemView.findViewById(R.id.user_gravatar);
            username = itemView.findViewById(R.id.user_name);
            goldBadgeCount = itemView.findViewById(R.id.user_gold_count);
            silverBadgeCount = itemView.findViewById(R.id.user_silver_count);
            bronzeBadgeCount = itemView.findViewById(R.id.user_bronze_count);
        }
    }

    public UserAdapter(List<User> users) {
        userList = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);
        Context context = holder.itemView.getContext();

        holder.username.setText(user.username);
        holder.goldBadgeCount.setText(String.format(context.getString(R.string.gold_badges_text),
                user.badges.goldBadgeCount));
        holder.silverBadgeCount.setText(String.format(context.getString(R.string.silver_badges_text),
                user.badges.silverBadgeCount));
        holder.bronzeBadgeCount.setText(String.format(context.getString(R.string.bronze_badges_text),
                user.badges.bronzeBadgeCount));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
