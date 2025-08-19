package Operaciones;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Operaciones {
    public static double covertirCantidad(double dinero,double tasa){
        return dinero*tasa;
    }

    public static double tasas(String base, String destino)throws Exception{

        HttpClient client = HttpClient.newHttpClient();

        String apiKey="78058626b6f5a16b34e84816";
        String url="https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        String req_result = jsonResponse.get("result").getAsString();
        if(!req_result.equals("success")){
            return 0;
        }

        return jsonResponse.getAsJsonObject("conversion_rates").get(destino).getAsDouble();
    }

    public static void conversiones(String moneda, String destino)throws Exception{
        Scanner scanner= new Scanner(System.in);
        double conversiones=tasas(moneda,destino);

        if (conversiones==0) {
            System.out.println("Moneda no disponible.");
            return;
        }

        double cantidad;
        while (true){
            try {
                System.out.println("Ingrese su cantidad a convertir:");
                cantidad =Double.parseDouble(scanner.next());
                if (cantidad < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa una cantidad válida mayor a 0.");
            }
        }

        System.out.println(cantidad + " " + moneda + " = " + covertirCantidad(cantidad,conversiones) + " " + destino);


    }
}
