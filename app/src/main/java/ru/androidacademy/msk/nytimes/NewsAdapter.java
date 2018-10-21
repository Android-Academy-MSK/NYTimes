package ru.androidacademy.msk.nytimes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import ru.androidacademy.msk.nytimes.data.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<NewsItem> items = new ArrayList<>();

    private final LayoutInflater inflater;
    private final RequestManager imageLoader;
    @Nullable
    private final OnItemClickListener clickListener;

    public NewsAdapter(Context context, @Nullable OnItemClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;

        final RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.image_placeholder)
                .fallback(R.drawable.image_placeholder)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView categoryView;
        private final TextView titleView;
        private final TextView previewView;
        private final TextView dateView;

        ViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(items.get(position));
                }
            });
            categoryView = itemView.findViewById(R.id.item_category);
            imageView = itemView.findViewById(R.id.item_image);
            titleView = itemView.findViewById(R.id.item_title);
            previewView = itemView.findViewById(R.id.item_preview);
            dateView = itemView.findViewById(R.id.item_date);
        }

        void bind(NewsItem newsItem) {
            imageLoader.load(newsItem.getImageUrl()).into(imageView);
            categoryView.setText(newsItem.getCategory().getName());
            titleView.setText(newsItem.getTitle());
            previewView.setText(newsItem.getPreviewText());
            dateView.setText(Utils.formatDateTime(itemView.getContext(), newsItem.getPublishDate()));
        }
    }

}
