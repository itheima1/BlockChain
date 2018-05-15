package com.itcast.ay05.接口回调;

/**
 * ClassName:Mother
 * Description:妈妈
 */
class Mother {
    public static void main(String[] args) {
        //有超市
        SuperMarket superMarket = new SuperMarket();

        //买酱油  买的时候没有任何延迟
        Son son = new Son();
//        son.feed(Soy("海天"));
        superMarket.buySoy(son);

//        System.out.println("买到了"+soy.name+"牌的酱油");
        //做菜

        System.out.println("做甜点");
    }
    //其实我们就是用feed这个方法函数 但是对于java来说 函数必须存在对象中
    //所以我们虽然只需要这个函数 ,但是必须要通过对象包裹来调用
    //kotlin函数可以单独存在 就可以直接通过函数来进行回调
    //第一步:定义能力
    //取物品的能力
    interface FeedBack {
        //取酱油行为
        void feed(Soy soy);
    }

    //儿子  取酱油
    static class Son implements FeedBack {
        //第四步:在具有能力的对象方法下进行接收
        @Override
        public void feed(Soy soy) {
            //取回了酱油
            System.out.println("获取到酱油了" + soy.name);
            System.out.println("妈妈开始做菜");
        }
    }
}
