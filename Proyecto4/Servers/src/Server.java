import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    public ArrayList<String> List_CURPs = new ArrayList<String>();
    
    private HttpServer server;
    private int puerto;
    private int count_femin;
    private int count_male;
    private int count_curps;


    public Server(int puerto) throws IOException{
        this.puerto=puerto;
        this.count_femin=0;
        this.count_male=0;
        this.count_curps=0;

        this.server = HttpServer.create(new InetSocketAddress(this.puerto), 0);
    
        HttpContext statusServerContext = this.server.createContext("/status_server");
        HttpContext pushCURPContext = this.server.createContext("/push_curp");
        HttpContext statusGenerateContext = this.server.createContext("/status_generate");
        HttpContext statusListContext = this.server.createContext("/status_list");
        HttpContext lengthBytesListContext = this.server.createContext("/length_bytes_list");
        HttpContext countMaleContext = this.server.createContext("/count_male");
        HttpContext countFeminContext = this.server.createContext("/count_femin");
        HttpContext countEntityContext = this.server.createContext("/count_entity");
        
        statusServerContext.setHandler(this::statusServerHandler);
        pushCURPContext.setHandler(this::pushCURPHandler);
        statusGenerateContext.setHandler(this::statusGenerateHandler);
        statusListContext.setHandler(this::statusListHandler);
        lengthBytesListContext.setHandler(this::lengthBytesListHandler);
        countMaleContext.setHandler(this::countMale);
        countFeminContext.setHandler(this::countFemin);
        countEntityContext.setHandler(this::countEntity);

        this.server.setExecutor(Executors.newFixedThreadPool(10));
    }

    public void start() {
        this.server.start();
    }

    public void statusServerHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en "+this.puerto+"/status_server:" + he.getRequestMethod());
        // Asegurando que la peticion sea GET
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }

        // variable que contendra el mensaje al cliente
        String responseMessage = "Servidor Corriendo en el puerto "+ this.puerto;

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void pushCURPHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/push_curp:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }
        String str_CURP = new String(he.getRequestBody().readAllBytes());
        String responseMessage =str_CURP+":No cumple con el tamaño del curp";

        if (str_CURP.length()==18) {
            if(str_CURP.charAt(10)=='M'){
                this.count_male++;
            }else{
                this.count_femin++;
            }
            this.count_curps++;
    
            this.List_CURPs.add(str_CURP);
            responseMessage = "Agregado:" + str_CURP;
            
        }


        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void statusGenerateHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/status_generate:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente

        int start_count=this.count_curps;
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int finish_count=this.count_curps;
        
        String responseMessage = String.valueOf(finish_count-start_count);

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void statusListHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/status_list:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = String.valueOf(this.count_curps);

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void lengthBytesListHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/length_bytes_list:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = String.valueOf(this.count_curps*18);

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void countMale(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/count_male:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = String.valueOf(this.count_male);

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }
    
    public void countFemin(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/count_femin:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = String.valueOf(this.count_femin);

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void countEntity(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/count_entity:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }
        String str_entity = new String(he.getRequestBody().readAllBytes());
        String responseMessage = str_entity + ":No es una entidad valida";
        if (str_entity.length()==2) {
            int count_entity=0;
            Iterator<String> itr = this.List_CURPs.iterator();

            while(itr.hasNext()) {
                if(itr.next().substring(11,13).equals(str_entity)){
                    count_entity++;
                }   
            }

            responseMessage = String.valueOf(count_entity);
        }


        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = he.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        he.close();
    }
}
