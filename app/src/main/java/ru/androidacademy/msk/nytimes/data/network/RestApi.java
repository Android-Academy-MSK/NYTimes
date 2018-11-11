package ru.androidacademy.msk.nytimes.data.network;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.androidacademy.msk.nytimes.data.network.endpoints.TopStoriesEndpoint;
import ru.androidacademy.msk.nytimes.data.network.interceptors.ApiKeyInterceptor;

public final class RestApi {

    private static RestApi restApi;
    private static final String TOP_STORIES_BASE_URL = "https://api.nytimes.com/svc/";

    public static synchronized RestApi getInstance() {
        if (restApi == null) {
            restApi = new RestApi();
        }
        return restApi;
    }

    private final TopStoriesEndpoint topStoriesEndpoint;

    private RestApi() {
        final OkHttpClient client = buildClient();
        final Retrofit retrofit = buildRetrofit(client);

        topStoriesEndpoint = retrofit.create(TopStoriesEndpoint.class);
    }

    private Retrofit buildRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(TOP_STORIES_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient buildClient() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }

    @NonNull
    public TopStoriesEndpoint topStories() {
        return topStoriesEndpoint;
    }
}
