package 线程的创建;

/**
 * ClassName:TicketThread
 * Description:
 */
class TicketThread extends Thread {
    private int ticket = 100;
    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(getName()+"卖出第"+ticket+"张票");
                ticket--;
            }
        }
    }
}
