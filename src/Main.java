import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner= new Scanner(System.in);

            System.out.println("Ingrese su moneda base:");
            String moneda=scanner.nextLine().toUpperCase();

            System.out.println("Ingrese su moneda a convertir:");
            String convertir=scanner.nextLine().toUpperCase();

            JsonObject conversiones=tasas(moneda);

            if (!conversiones.has(convertir)) {
                System.out.println("Moneda no disponible.");
                return;
            }
            double tasaConversion=conversiones.get(convertir).getAsDouble();

            System.out.println("Ingrese su cantidad a convertir");
            double cantidad=scanner.nextDouble();


            System.out.println(cantidad + " " + moneda + " = " + covertirCantidad(cantidad,tasaConversion) + " " + convertir);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double covertirCantidad(double dinero,double tasa){
        return dinero*tasa;
    }

    public static JsonObject tasas(String base)throws Exception{
        HttpClient client = HttpClient.newHttpClient();

        String apiKey="78058626b6f5a16b34e84816";
        String url="https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

        return jsonResponse.getAsJsonObject("conversion_rates");
    }
}
