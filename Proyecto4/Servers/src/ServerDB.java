
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import clientdb.Aggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDB {
    private HttpServer server;
    private int puerto;
    private Aggregator aggregator;
    private int segs;

    public ServerDB(int puerto) throws IOException{
        this.puerto=puerto;
        

        this.server = HttpServer.create(new InetSocketAddress(this.puerto), 0);
    
        HttpContext statusServerDBContext = this.server.createContext("/status_server_db");
        HttpContext statusGenerateDBContext = this.server.createContext("/status_generate_db");
        HttpContext statusListDBContext = this.server.createContext("/status_list_db");
        HttpContext lengthBytesListDBContext = this.server.createContext("/length_bytes_list_db");
        HttpContext countMaleFeminDBContext = this.server.createContext("/count_male_femin_db");
        HttpContext countEntityDBContext = this.server.createContext("/count_entity_db");//POST
        
        statusServerDBContext.setHandler(this::statusServerDBHandler);
        statusGenerateDBContext.setHandler(this::statusGenerateDBHandler);
        statusListDBContext.setHandler(this::statusListDBHandler);
        lengthBytesListDBContext.setHandler(this::lengthBytesListDBHandler);
        countMaleFeminDBContext.setHandler(this::countMaleFeminDBHandler);
        countEntityDBContext.setHandler(this::countEntityDBHandler);

        this.server.setExecutor(Executors.newFixedThreadPool(4));
        
    }

    public void start() {
        this.server.start();
    }

    public void runDB(List<String> server_list){
        this.aggregator=new Aggregator(server_list);
    }

    public void generateDB(int segs){
        this.segs=segs;
        ExecutorService exec1 = Executors.newSingleThreadExecutor();
        exec1.submit(() -> {
            this.aggregator.startGenerateCURPs(segs);
        });
        
    }


    public void statusServerDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/status_server_DB:" + he.getRequestMethod());
        // Asegurando que la peticion sea GET
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }

        // variable que contendra el mensaje al cliente
        String responseMessage = "Servidor Corriendo en el puerto " + this.puerto + "\n";

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void statusGenerateDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/status_generate_db:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        
        // variable que contendra el mensaje al cliente
        String responseMessage = "Servidor generando "+ String.valueOf(60/this.segs)+" CURPs por minuto.\n";

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void statusListDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/status_list_db:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        


        // variable que contendra el mensaje al cliente
        String responseMessage = this.aggregator.statusListDB();

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void lengthBytesListDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/length_bytes_list_db:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = this.aggregator.lengthBytesListDB();

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void countMaleFeminDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/count_male_femin_db:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }
        // variable que contendra el mensaje al cliente
        String responseMessage = this.aggregator.countMaleFeminDB();

        // respondiendole al cliente
        sendResponse(responseMessage.getBytes(), he);
    }

    public void countEntityDBHandler(HttpExchange he) throws IOException {
        System.out.println("Metodo en " + this.puerto + "/count_entity_db:" + he.getRequestMethod());
        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }
        String str_entity = new String(he.getRequestBody().readAllBytes());
        String responseMessage = str_entity + ":No es una entidad valida\n";
        if (str_entity.length() == 2) {
            
            responseMessage = this.aggregator.countEntityDB(str_entity);
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
