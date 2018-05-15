package com.itcast.ay05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * ClassName:TestCollection
 * Description:
 */
class TestCollection {
    public static void main(String[] args){
        /*---------------------------- List ----------------------------*/
        ArrayList<String> list = new ArrayList<String>();
//        list.add("林青霞");
//        list.add("高圆圆");
//        list.add("高圆圆");
//        for (String name:list) {
//            System.out.println(name);
//        }
//        Vector
        /*---------------------------- Set ----------------------------*/
        HashSet<String> set = new HashSet<String>();
        set.add("林青霞");
        set.add("高圆圆");
        set.add("高圆圆");
        for (String name:set) {
            System.out.println(name);
        }
        /*---------------------------- map ----------------------------*/
        HashMap<String,String> map = new HashMap<>();
        map.put("美女","高圆圆");
        map.put("帅哥","徐顺利");
        map.put("美女","领情下");
        Hashtable hashtable;
    }
}
