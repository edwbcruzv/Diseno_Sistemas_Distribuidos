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
        Demo object = new Demo(2022, "Prueba serializacion y deserializacion");
        byte[] serializado =SerializationUtils.serialize(object);
        System.out.println(serializado);

        
        
        HttpClient cliente = HttpClient.newBuilder()
                .version(Version.HTTP_1_1) // version del protocolo
                .followRedirects(Redirect.NORMAL) // politica de redirecciondes
                .connectTimeout(Duration.ofSeconds(10)) // duracon de espera de conexion
                .build();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5001/task"))
                .POST(BodyPublishers.ofByteArray(serializado))
                .build();

        // enviando peticion y esperando la respuesta
        HttpResponse<String> res = cliente.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("Body-> " + res.body());
    }

}
