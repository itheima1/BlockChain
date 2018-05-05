package com.itcast.day03;

/**
 * ClassName:News
 * Description:
 */
class News {
    private String title;
    private String content;//只能获取content  不能修改contnet

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        //敏感
        if(title.equals("十八大")){
            System.out.println("这个有敏感信息  不能设置");
        }
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
