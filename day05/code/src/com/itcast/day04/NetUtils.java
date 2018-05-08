package com.itcast.day04;

/**
 * ClassName:NetUtils
 * Description:网络工具
 * 怎么在其它地方只存在一个实例?
 * 饿汉式单例模式
 * 懒汉式单例模式
 */
class NetUtils {
    public static String name;
    public int age;

    /*---------------------------- 饿汉式单例模式 ----------------------------*/
//    //定义一个变量保存NetUitls的实例  用的时候返回
//    private static NetUtils mNetUtils = new NetUtils();
//    //第一步:私有构造函数
//    private NetUtils() {
//    }
//
//    //第二步:还需要将当前对象的引用传递给外部使用
//    public static NetUtils getInstance(){
//        return mNetUtils;
//    }

    /*---------------------------- 懒汉式单例模式 ----------------------------*/
    //定义字段复用赋值   用的时候再赋值
    private static NetUtils mNetUtils = null;
    //1.私有构造函数
    private NetUtils(){}
    //2.提供方法返回当前对象的引用
    public static NetUtils getInstance(){
        //判断是否为空  为空再创建 不为空直接返回
        synchronized (NetUtils.class) {
            if (mNetUtils == null) {//1
                mNetUtils = new NetUtils();//2
            }
        }
        return mNetUtils;
    }


    public void sendRequest(){
        //发送网络请求
    }
}
