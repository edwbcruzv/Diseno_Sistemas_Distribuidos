/*
 * Proyecto 3
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */

/*
 * Esta clase implementa un servidor HTTP simple,
 * Un HttpServer esta vinculado a una direccion IP y un puerto,
 * y escucha conexiones TCP de los clientes en esta direccion
 import com.sun.net.httpserver.HttpServer;
 */

/*
 * esta interface procesan socicitudes, para ello deben
 * de estar asociados a una ruta URL que representa la ubicacion 
 * del servicio en este servidor
 import com.sun.net.httpserver.HttpHandler;
 */

/*
 * Se encarga de hacer el mapeo de las URLs y los handlers
 * Si la url no existe regresa el codigo de error 404 
 import com.sun.net.httpserver.HttpContext;
 */


/*
 * Esta clase encapsula una solicitud HTTP recibida y una 
 * respueta que se genera en el intercambio.
 * Proporciona metodos para examinar la solicitud del cloente y
 * para generar y enviar respuesta. 
 * 
 * Leer documentacion que serviran para leer y dar respuesta a peticiones
 *  
 import com.sun.net.httpserver.HttpExchange;
 */


/*
 * Es un Map< String,List<String> >, el cual representa los encabezados
 * de la solicitud y respuestas HTTP
 * 
 * Leer Documentacion para obtener los headers
 * 
 import com.sun.net.httpserver.Headers;
 */

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {
        
        int puerto1=5001;
        HttpServer server1 = HttpServer.create(new InetSocketAddress(puerto1) , 0);
        server1.createContext("/status", new StatusHandler());
        server1.createContext("/searchtoken", new SearchTokenHandler());
        server1.setExecutor(Executors.newFixedThreadPool(8));
        server1.start();

        int puerto2 = 5002;
        HttpServer server2 = HttpServer.create(new InetSocketAddress(puerto2), 0);
        server2.createContext("/status", new StatusHandler());
        server2.createContext("/searchtoken", new SearchTokenHandler());
        server2.setExecutor(Executors.newFixedThreadPool(8));
        server2.start();

        int puerto3 = 5003;
        HttpServer server3 = HttpServer.create(new InetSocketAddress(puerto3), 0);
        server3.createContext("/status", new StatusHandler());
        server3.createContext("/searchtoken", new SearchTokenHandler());
        server3.setExecutor(Executors.newFixedThreadPool(8));
        server3.start();

        System.out.println("Funcionando...");
    }
}
