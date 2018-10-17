package ru.androidacademy.msk.nytimes.news;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.androidacademy.msk.nytimes.R;
import ru.androidacademy.msk.nytimes.data.DataUtils;
import ru.androidacademy.msk.nytimes.data.NewsItem;
import ru.androidacademy.msk.nytimes.details.NewsDetailsActivity;
import ru.androidacademy.msk.nytimes.utils.Utils;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class NewsListActivity extends AppCompatActivity {

    @Nullable private ProgressBar progress;
    @Nullable private RecyclerView recycler;
    @Nullable private NewsAdapter adapter;
    @Nullable private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        progress = findViewById(R.id.progress);
        recycler = findViewById(R.id.recycler);

        adapter = new NewsAdapter(this, newsItem -> NewsDetailsActivity.start(this, newsItem));
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new NewsItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_micro)));

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = getResources().getInteger(R.integer.landscape_news_columns_count);
            recycler.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else {
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void updateItems(List<NewsItem> news) {
        if (adapter != null) adapter.replaceItems(news);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showProgress(true);
        disposable = Observable.fromCallable(DataUtils::generateNews)
                               .subscribeOn(Schedulers.io())
                               .observeOn(AndroidSchedulers.mainThread())
                               .subscribe(this::updateItems,
                                          th -> {

                                          });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Utils.disposeSafe(disposable);
        showProgress(false);
    }

    private void showProgress(boolean shouldShow) {
        if (progress != null) {
            Utils.setVisibile(progress, shouldShow);
        }
    }


}
