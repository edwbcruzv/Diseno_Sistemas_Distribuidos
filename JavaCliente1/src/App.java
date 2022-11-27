/*
 * Enviar y recuperar respuestas a un servidor.
 * Leer documentacion para ver las maneras de crear un cliente
 * 
 import java.net.http.HttpClient;
 */

/*
 * Configurador de encabezados y cuerpo de la solicitud
 * 
 * 
 import java.net.http.HttpRequest;
 */

/*
 * Accede a l codigo del estado de la respuesta, los header
 * y el HttpRequest que corresponde a esa respuesta
 * 
 import java.net.http.HttpResponse;
 */

 import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.net.URI;
public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Configuracion por defecto:
         *      solicitud:GET
         *      http/2
         *      redireccion:nula
         *      proxy: por determinado
         *      ssl:por determinado
         */
        // HttpClient cliente=HttpClient.newHttpClient();

        /*
         * personalizado
         */
        HttpClient cliente = HttpClient.newBuilder()
                .version(Version.HTTP_1_1) // version del protocolo
                .followRedirects(Redirect.NORMAL) // politica de redirecciondes
                .connectTimeout(Duration.ofSeconds(10)) // duracon de espera de conexion
                .build();

        // HttpRequest req=HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/users")).GET().build();
        HttpRequest req=HttpRequest.newBuilder(URI.create("http://localhost:5000/status")).GET().build();

        // HttpRequest req=HttpRequest.newBuilder()
        //             .uri(URI.create("localhost:3000/searchtoken"))
        //             .header("XDebug","true")
        //             .POST(BodyPublishers.ofString("123456,IPN"))
        //             .build();

        
        
        // enviando peticion y esperando la respuesta
        HttpResponse<String> res=cliente.send(req,HttpResponse.BodyHandlers.ofString());

        String headers=res.headers().toString();
        System.out.println(headers);
        System.out.println(res.body());
    }
}
