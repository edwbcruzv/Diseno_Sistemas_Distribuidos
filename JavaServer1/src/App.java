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

import MyHandlers.SearchTokenHandler;
import MyHandlers.StatusHandler;

import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        
        int puerto1=5001;

        HttpServer server1 = HttpServer.create(new InetSocketAddress(puerto1) , 0);

        // curl -v localhost:3000/status
        server1.createContext("/status", new StatusHandler());

        // curl -v -H 'X-Debug:true' -d '15460551,NVDIS' localhost:3000/searchtoken
        server1.createContext("/searchtoken", new SearchTokenHandler());
        server1.start();

        // int puerto2 = 5002;

        // HttpServer server2 = HttpServer.create(new InetSocketAddress(puerto2), 0);

        // // curl -v localhost:3000/status
        // server2.createContext("/status", new StatusHandler());

        // // curl -v -H 'X-Debug:true' -d '15460551,NVDIS' localhost:3000/searchtoken
        // server2.createContext("/searchtoken", new SearchTokenHandler());
        // server2.start();
        System.out.println("Funcionando...");
    }
}
