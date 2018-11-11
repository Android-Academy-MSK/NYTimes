package ru.androidacademy.msk.nytimes.data.network.interceptors;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class ApiKeyInterceptor implements Interceptor {


    private static final String TOP_STORIES_API_KEY = "518d82bed02c453e9d81e615e1862f18";
    private static final String API_KEY_HEADER_NAME = "api-key";


    public static ApiKeyInterceptor create() {
        return new ApiKeyInterceptor();
    }


    private ApiKeyInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request requestWithoutApiKey = chain.request();

        final HttpUrl url = requestWithoutApiKey.url()
                .newBuilder()
                .build();

        final Request requestWithAttachedApiKey = requestWithoutApiKey.newBuilder()
                .url(url)
                .addHeader(API_KEY_HEADER_NAME, TOP_STORIES_API_KEY)
                .build();

        return chain.proceed(requestWithAttachedApiKey);
    }
}
