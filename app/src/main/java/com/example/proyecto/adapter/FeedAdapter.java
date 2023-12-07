package com.example.proyecto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;

import java.util.List;

import com.example.proyecto.model.Feed;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<Feed> feeds;

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed,parent,false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        if (feeds != null && position < feeds.size()) {
            Feed feed = feeds.get(position);
            holder.bind(feed);
        }
    }

    @Override
    public int getItemCount() {
        return feeds != null ? feeds.size() : 0;
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView lastValueTextView;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            lastValueTextView = itemView.findViewById(R.id.lastValueTextView);
        }

        public void bind(Feed feed) {
            nameTextView.setText(feed.getName());
            lastValueTextView.setText(feed.getLast_value());
        }
    }
}