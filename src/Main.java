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

            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            String codigoMoneda = jsonResponse.get("base_code").getAsString();
            JsonObject conversiones = jsonResponse.getAsJsonObject("conversion_rates");

            double dolarPeso = conversiones.get("MXN").getAsDouble();

            System.out.println("Codigo de moneda:" + codigoMoneda);
            System.out.println("1"+ codigoMoneda + " = "+ dolarPeso+" MXN");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
