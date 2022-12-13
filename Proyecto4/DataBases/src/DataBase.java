import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class DataBase {
    private HttpClient database;

    public DataBase() {
        this.database = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public CompletableFuture<HttpResponse<String>> sendRequestPOST(String url, byte[] requestPayload) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .build();

        return database.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                // .thenApply(HttpResponse::body);
    
    }

    public CompletableFuture<HttpResponse<String>> sendRequestGET(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        return database.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        // .thenApply(HttpResponse::body);

    }
}