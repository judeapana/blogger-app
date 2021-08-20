package com.application.blogger.logic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.blogger.R;
import com.application.blogger.ServiceSavePost;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private final int resource;
    private final Context context;

    public PostAdapter(@NonNull Context context, int resource, List<Post> posts) {
        super(context, resource, posts);
        this.resource = resource;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout linearLayout;
        Post post = getItem(position);
        if (convertView == null) {
            linearLayout = new LinearLayout(getContext());
            String inf = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater) getContext().getSystemService(inf);
            layoutInflater.inflate(resource, linearLayout, true);
        } else {
            linearLayout = (LinearLayout) convertView;
        }
        TextView post_title = linearLayout.findViewById(R.id.post_title);
        TextView post_date = linearLayout.findViewById(R.id.post_date);
        TextView post_msg = linearLayout.findViewById(R.id.post_msg);
        post_title.setText(post.getTitle());
        post_date.setText(post.getDate());
        post_msg.setText(post.getMessage());
        return linearLayout;
    }
}
