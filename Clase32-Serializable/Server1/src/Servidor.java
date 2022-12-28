import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Servidor {
    public static void main(String[] args) throws Exception {
        
        int puerto1=5001;
        HttpServer server1 = HttpServer.create(new InetSocketAddress(puerto1) , 0);
        server1.createContext("/task", new TaskHandler());
        server1.setExecutor(Executors.newFixedThreadPool(8));
        server1.start();

        System.out.println("Funcionando...");
    }
}
