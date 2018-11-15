package com.example.homework1.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {

    Context context;
    List<NewsItem> listData;

    public NewsRecyclerViewAdapter(Context context, List<NewsItem> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsItemViewHolder holder, final int position) {

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "" + listData.get(position).getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });


        holder.title.setText("Title : " + listData.get(position).getTitle());
        holder.newsDescription.setText("Description : " + listData.get(position).getDescription());
        holder.date.setText("Date  : " + listData.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView title;
        TextView newsDescription;
        TextView date;

        public NewsItemViewHolder(View view) {
            super(view);
            relativeLayout = view.findViewById(R.id.relative);
            title = view.findViewById(R.id.title);
            newsDescription = view.findViewById(R.id.newsdesc);
            date = view.findViewById(R.id.date);
        }
    }

}
