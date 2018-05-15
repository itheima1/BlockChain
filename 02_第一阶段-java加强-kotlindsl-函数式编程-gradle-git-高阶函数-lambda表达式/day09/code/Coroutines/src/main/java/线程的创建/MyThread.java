package 线程的创建;

/**
 * ClassName:MyThread
 * Description:
 */
class MyThread extends Thread {
    @Override
    public void run() {
        //每隔500毫秒 打印一个数字  总共打印10个数字
        for (int i = 0; i < 10; i++) {
            System.out.println(getName()+"-打印了"+i);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
