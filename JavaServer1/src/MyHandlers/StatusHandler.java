package MyHandlers;



import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class StatusHandler implements HttpHandler{
    // metodo que maneja la peticion
    @Override
    public void handle(HttpExchange he){
        // Asegurando que el metodo sea POST
        if (!he.getRequestMethod().equalsIgnoreCase("get")) {
            he.close();
            return;
        }

        // si no hay ningun problema, se procesa la solicitud con el codigo 200
        String mensaje="Servidor corriendo, /status";

        int code_200=200;

        // necesitamos llenar los header
        try {
            he.sendResponseHeaders(code_200, mensaje.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream os = he.getResponseBody();
        try {
            os.write(mensaje.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
