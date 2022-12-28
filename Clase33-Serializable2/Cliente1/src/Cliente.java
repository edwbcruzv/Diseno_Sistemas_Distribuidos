import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;

public class Cliente {
    public static void main(String[] args) throws Exception {
        
        
        HttpClient cliente = HttpClient.newBuilder()
                .version(Version.HTTP_1_1) // version del protocolo
                .followRedirects(Redirect.NORMAL) // politica de redirecciondes
                .connectTimeout(Duration.ofSeconds(10)) // duracon de espera de conexion
                .build();
        
        PoligonoIrreg object= new PoligonoIrreg(3);
    
        
            System.out.println("(Cliente): Poligono antes de enviarse");
            System.out.println(object);   

            byte[] serializado =SerializationUtils.serialize(object);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5001/add_vertex"))
                    .POST(BodyPublishers.ofByteArray(serializado))
                    .build();
            // enviando peticion y esperando la respuesta
            HttpResponse<byte[]> res = cliente.send(req, HttpResponse.BodyHandlers.ofByteArray());
            // System.out.println("Body-> " + res.body());
            object=null;
            object=(PoligonoIrreg)SerializationUtils.deserialize(res.body());
            System.out.println("(Cliente): Poligono Recibido");
            System.out.println(object);
            object.addVertexRandom();

            bandera--;
    }

}
