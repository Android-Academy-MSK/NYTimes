package ru.androidacademy.msk.nytimes.data.network.models.dto;

import com.google.gson.annotations.SerializedName;

public final class MultimediaDTO {

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;


    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
