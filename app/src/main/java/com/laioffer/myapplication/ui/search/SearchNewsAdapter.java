package com.laioffer.myapplication.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.myapplication.R;
import com.laioffer.myapplication.databinding.SearchNewsItemBinding;
import com.laioffer.myapplication.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{
    // 1. Supporting data: dataset
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        //去提醒adapter， 有新的view了， 需要refresh
        notifyDataSetChanged();
    }

    // 2. Adapter overrides:
    @NonNull
    @Override
    //创建view： view是循环使用的
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    //当每一个view要显示内容的时候， 所以当你滑屏幕的时候就会call一次
    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        //填充position：
        //先拿到新闻内容
        Article article = articles.get(position);
        //拿到了holder， 就可以拿到view的相关变量了，因为下面我们已经bind过，并将bind的fileds给了holder
        //的constructor
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



    // 3. SearchNewsViewHolder: holder 就是为了减少call binding的次数， 只在创建view的时候call
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;

        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}



