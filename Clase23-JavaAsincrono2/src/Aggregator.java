import java.util.List;
import java.util.Map;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
        CompletableFuture<HttpResponse<String>>[] futures = new CompletableFuture[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            String workerAddress = workersAddresses.get(0);
            String task = tasks.get(i);

            byte[] requestPayload = task.getBytes();
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
            System.out.println("future[" + i + "] (" + workerAddress + ") realizara tarea["+(i+1)+"]:" + task);
            
        }
        
        
        List<String> results = new ArrayList();
        for (int i = 0; i < tasks.size(); i++) {

            HttpResponse<String> res = futures[i].join();

            results.add(res.headers().toString()+"\nBody-> " + res.body());
        }

        return results;
    }
}
