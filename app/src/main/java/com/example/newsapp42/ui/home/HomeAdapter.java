package com.example.newsapp42.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp42.OnClick.OnItemClickListener;
import com.example.newsapp42.databinding.ItemNewsBinding;
import com.example.newsapp42.models.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Article> list;
    private OnItemClickListener onItemClickListener;


    public HomeAdapter(OnItemClickListener click) {
        list = new ArrayList<>();
        onItemClickListener=click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()));
       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(list.get(position));
        holder.itemView.setOnLongClickListener(view -> {
            onItemClickListener.onItemLongClick(list.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(List<Article> articles){
        Comparator<Article> articleComparator = Comparator.comparing(new Function<Article, Long>() {
            @Override
            public Long apply(Article article) {
                return article.getData();
            }
        });
        list = articles;
        list.sort(articleComparator);
        Collections.reverse(list);
        notifyDataSetChanged();
    }

//    public void addItem(Article article) {
//        list.add(0, article);
//        notifyItemInserted(0);
//    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemNewsBinding itemNewsBinding;

        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            itemNewsBinding=itemView;
        }

        public void bind(Article article) {
//            21:26, 21 Апр 2022
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss, dd MMM yyyy",
                    Locale.ROOT);
            String str = String.valueOf(simpleDateFormat.format(article.getData()));
            itemNewsBinding.tvTitle.setText(String.format("%s                     %s", article.getText(), str));

        }
    }
}
