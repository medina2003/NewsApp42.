package com.example.newsapp42.OnClick;

import com.example.newsapp42.models.Article;

public interface OnItemClickListener {
    void onItemClick(int position);

    void onItemLongClick (Article position);

    void simpleClick ();

    void onItemLongClick (String uri);

}
