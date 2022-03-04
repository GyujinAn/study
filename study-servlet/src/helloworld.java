import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class helloworld {



    public static void main(String[] args) throws InterruptedException {


        HttpServletResponse httpServletResponse;

        HttpServletRequest httpServletRequest;


        ThreadTable t = new ThreadTable();
        t.method2();

        t.method2();


        t.method2();

    }

}

class ThreadTable extends HttpServlet {

    synchronized public void method1() throws InterruptedException {
        System.out.println("start method1");

        wait();
        System.out.println("end method1");
    }

    synchronized public void method2(){
        System.out.println("start method2");


        notify();

        for (int i =0;i<1000000 ; i++ ) {

        }
        System.out.println("after notify");


    }


}

