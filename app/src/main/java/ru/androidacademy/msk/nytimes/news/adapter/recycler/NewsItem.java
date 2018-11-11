package ru.androidacademy.msk.nytimes.news.adapter.recycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NewsItem implements Serializable {


    public static NewsItem create(@NonNull String title,
                                  @Nullable String imageUrl,
                                  @NonNull String category,
                                  @NonNull Date publishDate,
                                  @NonNull String previewText,
                                  @NonNull String url) {
        return new NewsItem(title, imageUrl, category, publishDate, previewText, url);
    }

    @NonNull
    private final String title;
    @Nullable
    private final String imageUrl;
    @NonNull
    private final String category;
    @NonNull
    private final Date publishDate;
    @NonNull
    private final String previewText;
    @NonNull
    private final String url;

    private NewsItem(@NonNull String title,
                     @Nullable String imageUrl,
                     @NonNull String category,
                     @NonNull Date publishDate,
                     @NonNull String previewText,
                     @NonNull String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.url = fullText;
    }


    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    @NonNull
    public Date getPublishDate() {
        return publishDate;
    }

    @NonNull
    public String getPreviewText() {
        return previewText;
    }

    @NonNull
    public String getUrl() {
        return url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsItem newsItem = (NewsItem) o;
        return Objects.equals(title, newsItem.title) &&
                Objects.equals(imageUrl, newsItem.imageUrl) &&
                Objects.equals(category, newsItem.category) &&
                Objects.equals(publishDate, newsItem.publishDate) &&
                Objects.equals(previewText, newsItem.previewText) &&
                Objects.equals(url, newsItem.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, imageUrl, category, publishDate, previewText, url);
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category='" + category + '\'' +
                ", publishDate=" + publishDate +
                ", previewText='" + previewText + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

