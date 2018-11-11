package ru.androidacademy.msk.nytimes.news.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import ru.androidacademy.msk.nytimes.R;

public final class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final List<NewsItem> items = new ArrayList<>();

    private final LayoutInflater inflater;
    private final RequestManager imageLoader;
    @Nullable
    private OnItemClickListener newsListener;

    public NewsAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        final RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.image_placeholder)
                .fallback(R.drawable.image_placeholder)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return NewsViewHolder.create(inflater, parent, imageLoader);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,
                                 int position) {

        final NewsItem newsItem = items.get(position);
        holder.bind(newsItem, newsListener);
    }

    public void setOnClickNewsListener(@NonNull OnItemClickListener newsListener) {
        this.newsListener = newsListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void replaceItems(@NonNull List<NewsItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull NewsItem newsItem);
    }

}
