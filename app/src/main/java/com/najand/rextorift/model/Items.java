package com.najand.rextorift.model;

import java.io.Serializable;

public class Items implements Serializable {
    private String title;
    private String url;
    private String explanation;

    public Items(String title, String picUrl, String content) {
        this.title = title;
        this.url = picUrl;
        this.explanation = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHdUrl() {
        return url;
    }

    public void setHdUrl(String picUrl) {
        this.url = picUrl;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String content) {
        this.explanation = content;
    }
}
