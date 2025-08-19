import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String apiKey="78058626b6f5a16b34e84816";
            String url="https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/USD";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Encabezados: " + response.headers());
            System.out.println("Cuerpo: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
