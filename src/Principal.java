import com.google.gson.JsonSyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcionElegida = 0;

        ConsultaConversion consulta = new ConsultaConversion();
        Calculos calculos = new Calculos(consulta, lectura);
        GeneradorDeArchivos generador = new GeneradorDeArchivos();
        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n***************************************************
                *** Sea bienvenido al Conversor de Monedas ***
                
                1) Dólar ==>> Peso argentino
                2) Peso argentino ==>> Dólar
                3) Dólar ==>> Real brasileño
                4) Real brasileño ==>> Dólar
                5) Dólar ==>> Peso colombiano
                6) Peso colombiano ==>> Dólar
                7) Otra opción de conversión
                8) Salir
                ***************************************************
                """;

        while (opcionElegida != 8) {
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            String input = lectura.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("Ingrese un número válido entre 1 y 8.");
                continue;
            }

            try {
                opcionElegida = Integer.parseInt(input);
                LocalDateTime timestamp = LocalDateTime.now();
                String formattedDate = timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                String respuesta;

                switch (opcionElegida) {
                    case 1, 2, 3, 4, 5, 6 -> {
                        String[][] opciones = {
                                {"USD", "ARS"}, {"ARS", "USD"}, {"USD", "BRL"},
                                {"BRL", "USD"}, {"USD", "COP"}, {"COP", "USD"}
                        };
                        String[] seleccion = opciones[opcionElegida - 1];
                        calculos.almacenarVariables(seleccion[0], seleccion[1]);
                        respuesta = formattedDate + " - " + calculos.obtenerMensajeRespuesta();
                        respuestas.add(respuesta);
                    }
                    case 7 -> {
                        calculos.almacenarValoresPersonalizados();
                        respuesta = formattedDate + " - " + calculos.obtenerMensajeRespuesta();
                        respuestas.add(respuesta);
                    }
                    case 8 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }

            } catch (JsonSyntaxException | NullPointerException e) {
                System.out.println("Error: Verifique los códigos de moneda ingresados.");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
            }
        }
        generador.guardarJson(respuestas);
        System.out.println("Programa finalizado.");
    }
}


