package ru.androidacademy.msk.nytimes.data.network.endpoints;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.androidacademy.msk.nytimes.data.network.models.dto.TopStoriesResponse;

public interface TopStoriesEndpoint {

    @GET("topstories/v2/{section}.json")
    Single<TopStoriesResponse> get(@Path("section") @NonNull String section);
}
