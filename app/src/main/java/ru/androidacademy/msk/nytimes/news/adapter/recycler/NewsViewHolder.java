package ru.androidacademy.msk.nytimes.news.adapter.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import ru.androidacademy.msk.nytimes.R;
import ru.androidacademy.msk.nytimes.utils.Utils;

public final class NewsViewHolder extends RecyclerView.ViewHolder {

    private static final int LAYOUT = R.layout.item_news;

    private final RequestManager imageLoader;
    private final View itemView;


    public static NewsViewHolder create(@NonNull LayoutInflater inflater,
                                        @NonNull ViewGroup parent,
                                        @NonNull RequestManager imageLoader) {
        final View view = inflater.inflate(LAYOUT, parent, false);
        return new NewsViewHolder(view, imageLoader);
    }

    private ImageView imageView;
    private TextView categoryView;
    private TextView titleView;
    private TextView previewView;
    private TextView dateView;


    NewsViewHolder(@NonNull View itemView,
                   @NonNull RequestManager imageLoader) {
        super(itemView);
        this.itemView = itemView;
        this.imageLoader = imageLoader;
        findViews(itemView);

    }

    void bind(@NonNull NewsItem newsItem,
              @NonNull NewsAdapter.OnItemClickListener newsClickListener) {
        setupUi(newsItem);
        setupUx(newsItem, newsClickListener);
    }

    private void setupUi(NewsItem newsItem) {
        imageLoader.load(newsItem.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        categoryView.setText(newsItem.getCategory());
        titleView.setText(newsItem.getTitle());
        previewView.setText(newsItem.getPreviewText());
        dateView.setText(Utils.formatDateTime(itemView.getContext(), newsItem.getPublishDate()));
    }

    private void setupUx(@NonNull NewsItem newsItem,
                         @Nullable NewsAdapter.OnItemClickListener newsClickListener) {

        itemView.setOnClickListener(view -> {
            int position = getAdapterPosition();
            if (newsClickListener != null && position != RecyclerView.NO_POSITION) {
                newsClickListener.onItemClick(newsItem);
            }
        });
    }

    private void findViews(@NonNull View itemView) {
        categoryView = itemView.findViewById(R.id.item_category);
        imageView = itemView.findViewById(R.id.item_image);
        titleView = itemView.findViewById(R.id.item_title);
        previewView = itemView.findViewById(R.id.item_preview);
        dateView = itemView.findViewById(R.id.item_date);
    }
}


