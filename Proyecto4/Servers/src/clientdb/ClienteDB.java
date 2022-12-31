package clientdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ClienteDB {
    private HttpClient client_db;

    public ClienteDB() {
        this.client_db = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public CompletableFuture<HttpResponse<String>> sendAsyncRequestPOST(String url, byte[] requestPayload) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .build();

        return client_db.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                // .thenApply(HttpResponse::body);
    
    }

    public CompletableFuture<HttpResponse<String>> sendAsyncRequestGET(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        return client_db.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        // .thenApply(HttpResponse::body);

    }

    public HttpResponse<String> sendRequestPOST(String url, byte[] requestPayload) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .build();

        return client_db.send(request, HttpResponse.BodyHandlers.ofString());
        // .thenApply(HttpResponse::body);

    }

    public HttpResponse<String> sendRequestGET(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        return client_db.send(request, HttpResponse.BodyHandlers.ofString());
        // .thenApply(HttpResponse::body);

    }
}