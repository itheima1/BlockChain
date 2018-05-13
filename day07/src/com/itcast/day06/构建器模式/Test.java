package com.itcast.day06.构建器模式;

/**
 * ClassName:Test
 * Description:
 */
class Test {
    public static void main(String[] args) {
//        Notification notification = new Notification();
//        notification.setTitle("标题");
//        notification.setContent("内容");
//        notification.setImg("图标");
//        notification.setTime(System.currentTimeMillis());

        Notification notification = new Notification.Builder()
                .title("标题")
                .content("内容")
                .img("图标")
                .time(System.currentTimeMillis())
                .build();
    }
}
