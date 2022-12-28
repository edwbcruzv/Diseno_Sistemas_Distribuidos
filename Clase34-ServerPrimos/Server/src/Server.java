import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Server {
    public ArrayList<String> List_CURPs = new ArrayList<String>();
    
    private HttpServer server;
    private int puerto;

    public Server(int puerto) throws IOException{
        this.puerto=puerto;

        this.server = HttpServer.create(new InetSocketAddress(this.puerto), 0);
    
        HttpContext statusServerContext = this.server.createContext("/status_server");
        HttpContext isPrimeContext = this.server.createContext("/isprime");
        
        statusServerContext.setHandler(this::statusServerHandler);
        isPrimeContext.setHandler(this::isPrimeHandler);
        

        this.server.setExecutor(Executors.newFixedThreadPool(10));
    }

    public static void main(String[] args) throws Exception {

        Server server1 = new Server(5001);
        server1.start();

        System.out.println("Funcionando...");
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

    public void isPrimeHandler(HttpExchange he) throws IOException{
        System.out.println("Metodo en " + this.puerto + "/isprime   " + he.getRequestMethod());

        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }

        // Headers headers = he.getRequestHeaders();
        // for (Entry<String, List<String>> elem : headers.entrySet()) {
        //     System.out.println(elem.getKey() + ":" + elem.getValue());
        // }
        
        // String str_body = new String(he.getRequestBody().readAllBytes());
        URI req_URI = he.getRequestURI();
        // String str_URI = req_URI.toString();
        String str_query=req_URI.getQuery();
        String responseMessage;

        Map<String,Integer> params=new HashMap<String, Integer>();
        try {
            
            String[] arr_keys =str_query.split("&");
            String[] aux;
            for (int i = 0; i < arr_keys.length; i++) {
                aux=arr_keys[i].split("=");
                params.put(aux[0], Integer.parseInt(aux[1]));
            }
    
            Iterator it=params.keySet().iterator();
            while (it.hasNext()) {
                String key=(String) it.next();
                System.out.println(key+":"+params.get(key));
            }
            
            
        } catch (Exception e) {
            responseMessage="Error al extraer los parametros";
            sendResponse(responseMessage.getBytes(), he);
            return;
        }

        responseMessage = "No se mando ningun numero";

        if (params.containsKey("numero")) {
            responseMessage = "El numero "+params.get("numero")+isPrime(params.get("numero"));
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

    private String isPrime(int numero){
        // El 0, 1 y 4 no son primos
        if (numero == 0 || numero == 1 || numero == 4) {
            return " no es primo.";
        } 
        for (int x = 2; x < numero / 2; x++) {
            // Si es divisible por cualquiera de estos números, no
            // es primo
            if (numero % x == 0)
                return " no es primo.";
        }
        // Si no se pudo dividir por ninguno de los de arriba, sí es primo
        return " es primo.";
    }
}
