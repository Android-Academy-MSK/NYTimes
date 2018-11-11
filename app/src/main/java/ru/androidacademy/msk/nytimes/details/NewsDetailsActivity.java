package ru.androidacademy.msk.nytimes.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.bumptech.glide.util.Preconditions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ru.androidacademy.msk.nytimes.R;
import ru.androidacademy.msk.nytimes.news.adapter.recycler.NewsItem;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_news_details;
    private static final String EXTRA_NEWS_ITEM = "extra:newsItem";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        final NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);
        final ActionBar ab = getSupportActionBar();
        final ActionBar actionBar = Preconditions.checkNotNull(ab);
        final WebView webView = findViewById(R.id.web_view);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(newsItem.getCategory());
        webView.loadUrl(newsItem.getUrl());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void start(@NonNull Context context, @NonNull NewsItem newsItem) {
        context.startActivity(new Intent(context, NewsDetailsActivity.class).putExtra(EXTRA_NEWS_ITEM, newsItem));
    }

}
