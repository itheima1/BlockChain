package com.itcast.day04;

/**
 * ClassName:SingleInstance
 * Description:
 */
class SingleInstance {
    public static void main(String[] args){
        //在内存中有两个实例
//        NetUtils test = new NetUtils();//调用了无参构造函数
//        NetUtils test1 = new NetUtils();
//        test.sendRequest();
//        test1.sendRequest();
        //需要的是在内存中只有一个实例 单例实现
        NetUtils netUtils1 = NetUtils.getInstance();
        NetUtils netUtils2 = NetUtils.getInstance();
        NetUtils.getInstance().name = "";

    }
}
