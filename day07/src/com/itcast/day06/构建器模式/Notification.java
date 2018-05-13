package com.itcast.day06.构建器模式;

/**
 * ClassName:Notification
 * Description:构建器模式思想 就是先通过一个三方类class保存需要的变量 最终拿到所有的变量字段之后 再创建需要的对象Notification
 */
class Notification {
    //选择性
    private String title;
    private String content;
    private String img;
    private long time;

    public Notification(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.img = builder.img;
        this.time = builder.time;
    }

    static class Builder{
         String title;
         String content;
         String img;
         long time;
        //设置title
        public Builder title(String title) {
            this.title= title;
            return this;
        }
        //设置content
        public Builder content(String content) {
            this.content= content;
            return this;
        }
        //img
        public Builder img(String img) {
            this.img= img;
            return this;
        }
        //time
        public Builder time(long time) {
            this.time= time;
            return this;
        }
        public Notification build(){
            return new Notification(this);
        }
    }

    /*---------------------------- 第一种实现方式:多个构造函数 ----------------------------*/
//    public Notification(String title) {
//        this.title = title;
//    }
//
//    public Notification(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//    public Notification(String title, String content, String img) {
//        this.title = title;
//        this.content = content;
//        this.img = img;
//    }
//
//    public Notification(String title, String content, String img, long time) {
//        this.title = title;
//        this.content = content;
//        this.img = img;
//        this.time = time;
//    }
    /*---------------------------- 通过get和set方法 ----------------------------*/

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public long getTime() {
//        return time;
//    }
//
//    public void setTime(long time) {
//        this.time = time;
//    }
}
