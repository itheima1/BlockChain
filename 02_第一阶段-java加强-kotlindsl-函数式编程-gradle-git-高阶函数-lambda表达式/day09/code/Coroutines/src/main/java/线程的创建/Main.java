package 线程的创建;

/**
 * ClassName:Main
 * Description:
 */
class Main {
    public static void main(String[] args){
//        System.out.println("主线程开始执行");
        /*---------------------------- 直接通过Thread方式创建 ----------------------------*/
//        MyThread thread = new MyThread();
//        thread.setName("线程1");
//        thread.start();
        /*---------------------------- 通过Runnable方式进行创建 ----------------------------*/
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.setName("线程2");
        thread.start();

//        System.out.println("主线程结束执行");

        /*---------------------------- 直接通过Thread卖票 ----------------------------*/
        //如果线程间要共享数据的话就需要通过Runnable接口定义Thread
        //窗口1
//        TicketThread thread1 = new TicketThread();
//        //窗口2
//        TicketThread thread2 = new TicketThread();
//        //窗口3
//        TicketThread thread3 = new TicketThread();
//
//        thread1.setName("窗口1");
//        thread2.setName("窗口2");
//        thread3.setName("窗口3");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        /*---------------------------- 通过Runnable方式卖票 ----------------------------*/
        //通过Runnable可以实现线程间数据的共享 共享完数据之后有些问题 重复打印了数据
//        TicketRunnable runnable = new TicketRunnable();
//        Thread thread1 = new Thread(runnable);
//        Thread thread2 = new Thread(runnable);
//        Thread thread3 = new Thread(runnable);
//
//        thread1.setName("窗口1");
//        thread2.setName("窗口2");
//        thread3.setName("窗口3");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

    }
}
