import java.util.List;
import java.util.Map;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private DataBase database;

    public Aggregator() {
        this.database = new DataBase();
    }

    public List<String> statusDBs(List<String> server_list) {

        CompletableFuture<HttpResponse<String>>[] futures = new CompletableFuture[server_list.size()];
        System.out.println("Numero de Servidores BDs:" + server_list.size());
        List<String> results = new ArrayList();
        // inicializando los futures
        for (int i = 0; i < futures.length; i++) {
            futures[i] = this.database.sendRequestGET(server_list.get(i)+"/status_server");
            System.out.println("Inicializando future[" + i + "] (" + server_list.get(i) + ") ");

            HttpResponse<String> res = futures[i].join();
            // al estar terminado el fucture se lee el resultado de la tarea que se le
            // asigno y se carga otra(res.headers().toString() + )
            results.add(server_list.get(i)+"/status_server ---->Body:"+ res.body());
        }

        return results;
    }
}
