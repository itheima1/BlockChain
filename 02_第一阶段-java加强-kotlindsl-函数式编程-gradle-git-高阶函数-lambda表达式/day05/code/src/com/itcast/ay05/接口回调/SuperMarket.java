package com.itcast.ay05.接口回调;

/**
 * ClassName:SuperMarket
 * Description:超市
 * 问题 :在buySoy方法中开启新线程  线程中的结果不能直接返回
 */
class SuperMarket {
    //买酱油
    //只要有取酱油的能力 都可以取
    //第二步:接收具有能力的接口对象
    public void buySoy(Mother.FeedBack feedBack) {
        //刚好没有货了  需要到仓库中找 等5分钟
        //妈妈直接回去  不等了  找到之后再给我
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //获取酱油
                Soy soy = new Soy("海天");
                //第三步:通过接口对象将数据传回去
                //让儿子将酱油带回去
                if(feedBack!=null) {
                    feedBack.feed(soy);
                }

            }
        }).start();


    }
}
