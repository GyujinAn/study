import java.util.Iterator;
import java.util.Map;

public class ThreadEx{
    public static void main(String[] args) {
//
//        //상속 된 쓰레드의 생성
//        ThreadEX_1 threadEX_1 = new ThreadEX_1();
//
//        //Runnable이 구현 된 클래스의 쓰레드 생성
//        Thread threadEX_2 = new Thread(new ThreadEX_2());
//
//        threadEX_1.start();
//
//        threadEX_2.start();


        Map map = Thread.getAllStackTraces();

        Iterator iterator = map.keySet().iterator();

        int x = 0;

        while(iterator.hasNext()){
            Object next = iterator.next();
            Thread thread = (Thread) next;

            System.out.println("["+ x + "] name : " + thread.getName()
                            + ", group : " + thread.getThreadGroup().getName()
                            + ", daemon : " + thread.isDaemon());

            StackTraceElement[] stackTraceElements = (StackTraceElement[]) map.get(next);

            for(StackTraceElement stackTraceElement : stackTraceElements){
                System.out.println("    "+stackTraceElement);
            }
        }
    }
}

//Thread 클래스를 상속하여 구현
class ThreadEX_1 extends Thread{

    public void run() {
        //비즈니스 로직 구현
        getName();
    }

}

//Runnable 인터페이스를 구현
class ThreadEX_2 implements Runnable{

    @Override
    public void run() {
        //비즈니스 로직 구현
        Thread thread = Thread.currentThread();
        thread.getName();
    }

}

