package com.company.tools;

/**
 * Created by user on 11/8/16.
 */
public class Tool {
    private String name;
    private String title;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Tool(){
        name = "";
        title = "";
        url = "";
    }
}
