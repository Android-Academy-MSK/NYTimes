package ru.androidacademy.msk.nytimes.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.androidacademy.msk.nytimes.R;
import ru.androidacademy.msk.nytimes.about.AboutActivity;
import ru.androidacademy.msk.nytimes.data.network.RestApi;
import ru.androidacademy.msk.nytimes.data.network.models.NewsCategory;
import ru.androidacademy.msk.nytimes.data.network.models.dto.NewsDTO;
import ru.androidacademy.msk.nytimes.data.network.models.dto.TopStoriesResponse;
import ru.androidacademy.msk.nytimes.details.NewsDetailsActivity;
import ru.androidacademy.msk.nytimes.news.adapter.recycler.NewsAdapter;
import ru.androidacademy.msk.nytimes.news.adapter.recycler.NewsItem;
import ru.androidacademy.msk.nytimes.news.adapter.recycler.NewsItemDecoration;
import ru.androidacademy.msk.nytimes.news.adapter.spinner.CategoriesSpinnerAdapter;
import ru.androidacademy.msk.nytimes.utils.Utils;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public final class NewsListActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_news_list;
    public static final String TAG = NewsListActivity.class.getSimpleName();

    @Nullable
    private ProgressBar progress;
    @Nullable
    private RecyclerView recycler;
    @Nullable
    private Spinner spinnerCategories;

    @Nullable
    private View error;
    @Nullable
    private Button errorAction;
    @Nullable
    private Toolbar toolbar;

    private NewsAdapter newsAdapter;
    private CategoriesSpinnerAdapter categoriesAdapter;

    @Nullable
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        setupUi();
        setupUx();
    }


    private void setupUi() {
        findViews();
        setupToolbar();
        setupSpinner();
        setupRecyclerViewAdapter();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupSpinner() {
        final NewsCategory[] categories = NewsCategory.values();
        categoriesAdapter = CategoriesSpinnerAdapter.createDefault(this, categories);
        spinnerCategories.setAdapter(categoriesAdapter);
    }

    private void setupRecyclerViewAdapter() {
        newsAdapter = new NewsAdapter(this);
        recycler.setAdapter(newsAdapter);
        recycler.addItemDecoration(new NewsItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_micro)));
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = getResources().getInteger(R.integer.landscape_news_columns_count);
            recycler.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else {
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }
    }


    private void setupUx() {
        errorAction.setOnClickListener(view -> loadItems(categoriesAdapter.getSelectedCategory().serverValue()));
        newsAdapter.setOnClickNewsListener(newsItem -> NewsDetailsActivity.start(this, newsItem));
        categoriesAdapter.setOnCategorySelectedListener(category -> loadItems(category.serverValue()), spinnerCategories);
    }

    private void loadItems(@NonNull String category) {
        showProgress(true);
        final Disposable disposable = RestApi.getInstance()
                .topStories()
                .get(category)
                .map(response -> TopStoriesMapper.map(response.getNews()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setupNews, this::handleError);
        compositeDisposable.add(disposable);
    }

    private void setupNews(List<NewsItem> newsItems) {
        showProgress(false);
        updateItems(newsItems);
    }

    private void handleError(Throwable th) {
        if (Utils.isDebug()) {
            Log.e(TAG, th.getMessage(), th);
        }
        Utils.setVisible(error, true);
        Utils.setVisible(progress, false);
        Utils.setVisible(recycler, false);
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadItems(categoriesAdapter.getSelectedCategory().serverValue());
    }

    @Override
    protected void onStop() {
        super.onStop();
        showProgress(false);
        compositeDisposable.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_activity:
                startActivity(new Intent(this, AboutActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsAdapter = null;
        recycler = null;
        progress = null;
    }

    private void updateItems(@Nullable List<NewsItem> news) {
        if (newsAdapter != null) newsAdapter.replaceItems(news);

        Utils.setVisible(recycler, true);
        Utils.setVisible(progress, false);
        Utils.setVisible(error, false);
    }


    private void showProgress(boolean shouldShow) {
        Utils.setVisible(progress, shouldShow);
        Utils.setVisible(recycler, !shouldShow);
        Utils.setVisible(error, !shouldShow);
    }

    private void findViews() {
        progress = findViewById(R.id.progress);
        recycler = findViewById(R.id.recycler);
        error = findViewById(R.id.error_layout);
        errorAction = findViewById(R.id.action_button);
        toolbar = findViewById(R.id.toolbar);
        spinnerCategories = findViewById(R.id.spinner_categories);
    }
}
