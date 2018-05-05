package com.itcast.day03;

import com.itcast.day03.hh.Dog;
import com.itcast.day03.hh.ShepHerdDog;

/**
 * ClassName:Test
 * Description:
 */
class Test {
    public static void main(String[] args){
        Dog shepHerdDog = new ShepHerdDog();

//    shepHerdDog.herdShep()
//    ruralDog.watchDoor()

        //想调用herdShep方法  1.判断是否是ShepHerdDog 2.转换成ShepHerdDog类型
        if(shepHerdDog instanceof ShepHerdDog){
            //2.转换成ShepHerdDog类型
//            val newDog = shepHerdDog as ShepHerdDog //as强转

//            newDog.herdShep()
//            shepHerdDog.herdShep();
        }
    }
}
