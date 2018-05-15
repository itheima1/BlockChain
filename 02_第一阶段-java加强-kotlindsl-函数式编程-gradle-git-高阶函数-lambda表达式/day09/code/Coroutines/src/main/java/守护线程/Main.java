package 守护线程;

/**
 * ClassName:Main
 * Description:
 */
class Main {
    public static void main(String[] args){
        /*---------------------------- 普通的线程 ----------------------------*/
        //用户线程  主线程执行结束 用户线程可以继续执行
        //主线程执行结束之后 垃圾回收线程也应该终止掉 垃圾回收线程就是属于守护线程
        //执行没有顺序 并行执行
        System.out.println("主线程开始执行");


        MyThread thread = new MyThread();
        thread.setName("线程1");
        //设置为守护线程  必须在启动前调用
        //主线程执行结束之后 线程就停止了
        thread.setDaemon(true);

        thread.start();


        System.out.println("主线程结束执行");
    }
}
