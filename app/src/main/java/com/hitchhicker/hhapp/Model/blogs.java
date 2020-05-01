package com.hitchhicker.hhapp.Model;

public class blogs
{
    private String post_title,url,thumbnail;

    public blogs(){

    }

    public blogs(String post_title, String url, String thumbnail) {
        this.post_title = post_title;
        this.url = url;
        this.thumbnail = thumbnail;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
