import java.util.Scanner;

public class Calculos {

    private String monedaBase;
    private String monedaObjeto;
    private double cantidad;

    private final Scanner lectura;
    private final ConsultaConversion conversion;

    public Calculos(ConsultaConversion conversion, Scanner lectura) {
        this.conversion = conversion;
        this.lectura = lectura;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaObjeto() {
        return monedaObjeto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void almacenarVariables(String monedaBase, String monedaObjeto) {
        this.monedaBase = monedaBase.toUpperCase();
        this.monedaObjeto = monedaObjeto.toUpperCase();

        System.out.println("Ingresa el valor que deseas convertir");
        boolean entradaValida = false;
        do {
            try {
                this.cantidad = Double.parseDouble(lectura.nextLine());
                if (this.cantidad < 0) {
                    System.out.println("El valor debe ser positivo. Intenta nuevamente.");
                } else {
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error. Ingrese un valor numérico válido.");
            }
        } while (!entradaValida);
    }

    public void almacenarValoresPersonalizados() {
        String menuOtrasOpciones = "... (listado completo de monedas aquí) ...";

        System.out.println(menuOtrasOpciones);
        System.out.println("Ingresa la moneda base con 3 letras");
        this.monedaBase = lectura.next().toUpperCase();
        System.out.println("Ingresa la moneda objeto con 3 letras");
        this.monedaObjeto = lectura.next().toUpperCase();

        boolean entradaValida = false;
        do {
            System.out.println("Ingrese el valor que deseas convertir");
            if (lectura.hasNextDouble()) {
                this.cantidad = lectura.nextDouble();
                if (this.cantidad >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("El valor debe ser positivo.");
                }
            } else {
                System.out.println("Error. Ingrese un valor numérico.");
                lectura.next();
            }
        } while (!entradaValida);
    }

    public String obtenerMensajeRespuesta() {
        String resultadoConversion = conversion.buscarConversion(getMonedaBase(), getMonedaObjeto(), getCantidad());
        String mensaje = getMonedaBase() + " " + getCantidad() + " equivale a: " + getMonedaObjeto() + " " + resultadoConversion;
        System.out.println(mensaje);
        return mensaje;
    }
}
