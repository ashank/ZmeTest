package com.zme.zlibrary.thirdparty.share;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: ShareContent
 * @Description: 分享内容的Model
 * @author: Zhiyahan
 * @data: 2018/10/17 19:11
 */
public class ShareContent implements Serializable {


    private String content;
    private String title;
    private String url;
    private String image;
    private String appName;
    /**
     * 图片地址，适用分享到空间
     */
    private List<String> images;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ShareContent{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", appName='" + appName + '\'' +
                ", images=" + images +
                '}';
    }
}
