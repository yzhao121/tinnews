package com.ita.tinnews.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ita.tinnews.R;
import com.ita.tinnews.databinding.SwipeNewsCardBinding;
import com.ita.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder> {
    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    // 3. Adapter overrides:
    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_news_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.title);
        holder.descriptionTextView.setText(article.description);
        // generally we don not use resize this way
        Picasso.get().load(article.urlToImage).resize(400, 500).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // 2. CardSwipeViewHolder:
    public static class CardSwipeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);

            SwipeNewsCardBinding binding = SwipeNewsCardBinding.bind(itemView);
            imageView = binding.swipeCardImageView;
            titleTextView = binding.swipeCardTitle;
            descriptionTextView = binding.swipeCardDescription;
        }
    }
}
