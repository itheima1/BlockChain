package com.itcast.day04.bean类;

import java.util.Objects;

/**
 * ClassName:News
 * Description: 有用的只有四行  其他都是一样的
 */
class News {
    //标题
    private String title;
    //简介
    private String desc;
    //图片地址
    private String imgPath;
    //内容
    private String content;
    //构造方法

    public News(String title, String desc, String imgPath, String content) {
        this.title = title;
        this.desc = desc;
        this.imgPath = imgPath;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(desc, news.desc) &&
                Objects.equals(imgPath, news.imgPath) &&
                Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, desc, imgPath, content);
    }
}
