package ru.androidacademy.msk.nytimes.data.network.models.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class NewsDTO {

    @SerializedName("subsection")
    private String subsection;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String abstractDescription;

    @SerializedName("published_date")
    private Date publishDate;

    @SerializedName("multimedia")
    private List<MultimediaDTO> multimedia;

    @SerializedName("url")
    private String url;


    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractDescription() {
        return abstractDescription;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }

    public String getUrl() {
        return url;
    }
}
