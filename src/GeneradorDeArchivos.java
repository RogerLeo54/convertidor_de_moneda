import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class GeneradorDeArchivos {

    public void guardarJson(List<String> lista) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter("historial_consultas.json")) {
            fileWriter.write(gson.toJson(lista));
            System.out.println("Historial de consultas guardado en 'historial_consultas.json'");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de consultas: " + e.getMessage());
        }
    }
}