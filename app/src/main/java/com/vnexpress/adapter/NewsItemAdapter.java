package com.vnexpress.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vnexpress.API.News;
import com.vnexpress.R;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder>{
    Context context;
    List<News> newsList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(News news);
    }

    public NewsItemAdapter(Context context,OnItemClickListener listener){
        this.context=context;
        this.listener=listener;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        if(news==null)
            return;
        holder.titleNews.setText(news.getTitle());
     if(news.getImage()!=""){
        Picasso.get().load(news.getImage()).into(holder.imgNews);}
     holder.cardNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 listener.onItemClick(news);
                }
          });

    }
    private void openDetailNewsFragment(News news){

    }

    @Override
    public int getItemCount() {
        if(newsList!=null)
            return newsList.size();
        return 0;
    }
public void setData(List<News> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView imgNews;
        CardView cardNews;
        TextView titleNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNews = itemView.findViewById(R.id.card_news);
            imgNews = itemView.findViewById(R.id.img_news);
            titleNews = itemView.findViewById(R.id.title_news);
        }
    }
}
