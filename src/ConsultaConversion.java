import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaConversion {

    private static final String API_KEY = "f7b016b99c877addaa988969";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public String buscarConversion(String monedaBase, String monedaObjeto, double cantidad) {
        try {
            URI direccion = URI.create(BASE_URL + API_KEY + "/pair/" + monedaBase + "/" + monedaObjeto);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
            double conversionResult = conversionRate * cantidad;

            return String.valueOf(conversionResult);

        } catch (NumberFormatException | IOException | InterruptedException e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

