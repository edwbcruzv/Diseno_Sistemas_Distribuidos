import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class AddVertexHandler implements HttpHandler{
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        // System.out.println("Metodo en /task:" + he.getRequestMethod());

        Headers headers = he.getRequestHeaders();
        // for (Entry<String, List<String>> elem : headers.entrySet()) {
        //     System.out.println(elem.getKey() + ":" + elem.getValue());
        // }

        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }

        byte[] serializado = he.getRequestBody().readAllBytes();
        PoligonoIrreg object = (PoligonoIrreg) SerializationUtils.deserialize(serializado);
        System.out.println("(Servidor): Poligono Recibido");
        System.out.println(object);
        object.addVertexRandom();
        System.out.println("(Servidor): Poligono antes de enviarse");
        System.out.println(object);
        serializado = SerializationUtils.serialize(object);


        int code_200 = 200;
        // necesitamos llenar los header
        try {
            he.sendResponseHeaders(code_200, serializado.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream os = he.getResponseBody();
        try {
            os.write(serializado);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
