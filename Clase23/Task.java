

// Java program to illustrate
// ThreadPool
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


// Task class to be executed (Step 1)
class Task implements Runnable {
    private String name;

    public Task(String s) {
        name = s;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {
        try {
            System.out.println("Inicia:"+name);
            long startTime, finishTime;
            startTime = System.currentTimeMillis();
            HttpClient cliente = HttpClient.newBuilder()
                    .version(Version.HTTP_1_1) // version del protocolo
                    .followRedirects(Redirect.NORMAL) // politica de redirecciondes
                    .connectTimeout(Duration.ofSeconds(10)) // duracon de espera de conexion
                    .build();

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://192.168.92.147:5001/searchtoken"))
                    .header("X-Debug", "true")
                    .POST(BodyPublishers.ofString("1757600,IPN"))
                    .build();

            // enviando peticion y esperando la respuesta
            HttpResponse<String> res = cliente.send(req, HttpResponse.BodyHandlers.ofString());

            String headers = res.headers().toString();
            System.out.println(headers);
            System.out.println(res.body());
            finishTime = System.currentTimeMillis();
            System.out.println(name + ", Tiempo de ejecucion:"+ Long.toString(finishTime - startTime)+" milisegundos");
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

