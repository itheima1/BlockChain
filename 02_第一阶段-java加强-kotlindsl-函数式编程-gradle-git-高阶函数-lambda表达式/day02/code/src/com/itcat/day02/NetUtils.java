package com.itcat.day02;

/**
 * ClassName:NetUtils
 * Description:发送网络请求
 */
class NetUtils {
    public static void main(String[] args){
//        sendRequest("http://www.baidu.com","GET");
        sendRequest("http://www.baidu.com");
    }
    //方法重载

    /**
     * 默认通过get方式请求网络
     * @param path
     */
    public static void sendRequest(String path){
        System.out.println("请求路劲="+path+"--请求方式=GET");
    }
    /**
     * 发送网络请求
     * @param path 请求路径
     * @param method 请求方式
     */
    public static void sendRequest(String path,String method){//GET POST  30  25 get  5 post  body  header
        System.out.println("请求路劲="+path+"--请求方式="+method);
    }
    //缺点:重载方法太多  xutils
}
