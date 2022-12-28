import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TaskHandler implements HttpHandler{
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Metodo en /task:" + he.getRequestMethod());

        Headers headers = he.getRequestHeaders();
        for (Entry<String, List<String>> elem : headers.entrySet()) {
            System.out.println(elem.getKey() + ":" + elem.getValue());
        }

        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }

        byte[] serializado = he.getRequestBody().readAllBytes();
        System.out.println(serializado);
        Demo objeto = (Demo) SerializationUtils.deserialize(serializado);
        System.out.println("a = " + objeto.a);
        System.out.println("b = " + objeto.b);



        String mensaje = "Tarea Hecha\n";

        int code_200 = 200;

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
