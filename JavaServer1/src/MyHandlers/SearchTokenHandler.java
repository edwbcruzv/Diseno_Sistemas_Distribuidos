package MyHandlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import TokensIPN.TokensIPN;

public class SearchTokenHandler implements HttpHandler{
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Metodo en /searchtoken:"+he.getRequestMethod());
        
        Headers headers = he.getRequestHeaders();
        for (Entry<String, List<String>> elem : headers.entrySet()) {
            System.out.println(elem.getKey()+":"+elem.getValue());
        }

        if (!he.getRequestMethod().equalsIgnoreCase("post")) {
            he.close();
            return;
        }

        boolean DebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            DebugMode = true;
        }



        String str_req_body =new String(he.getRequestBody().readAllBytes());
        String[] arg=str_req_body.split(",");


        long startTime,finishTime;
        startTime=System.currentTimeMillis();

        int tokens = TokensIPN.tokens(Integer.parseInt(arg[0]),arg[1]);
        
        finishTime=System.currentTimeMillis();

        if (DebugMode) {
            String debugMessage = String.format("La operación tomó %d milisegundos", (finishTime - startTime));
            he.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        // si no hay ningun problema, se procesa la solicitud con el codigobyte[] requestBytes = exchange.getRequestBody().readAllBytes(); 200
        String mensaje="tokens:"+Integer.toString(tokens)+"\n\n";

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
