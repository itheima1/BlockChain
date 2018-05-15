package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:Main
 * Description:
 */
class Main {
    public static void main(String[] args){
        //创建线程池
        //创建线程池 指定线程数量
        ExecutorService service =  Executors.newFixedThreadPool(3);//单核cpu 真实开发可以设置成核心数-1
        //执行多线程操作
        //可以通过execute执行 执行对象就是Runnable对象
        MyRunnable runnable = new MyRunnable();
        service.execute(runnable);
        service.execute(runnable);
        //线程池线程执行完成之后比并没有走销毁流程 而是回到线程池中 成为初始状态 等待下次执行
    }
}
