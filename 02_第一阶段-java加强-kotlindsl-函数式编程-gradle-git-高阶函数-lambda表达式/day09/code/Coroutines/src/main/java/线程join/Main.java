package 线程join;

/**
 * ClassName:Main
 * Description:
 */
class Main {
    public static void main(String[] args){
        /*---------------------------- 普通的线程 ----------------------------*/
        //执行没有顺序 并行执行
        System.out.println("主线程开始执行");

        MyThread thread = new MyThread();
        thread.setName("线程1");
        thread.start();
        //join  必须要等到当前线程执行结束 才能结束  相当于把线程的并行执行 变成了串行执行
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束执行");
    }
}
