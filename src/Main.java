
import java.util.Scanner;

import com.google.gson.JsonObject;

import static Operaciones.Operaciones.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int op=0;
        Scanner scanner= new Scanner(System.in);

        String[] monedasFiltradas = {"USD","ARS", "BRL", "COP"};
        do{
            System.out.println("""
                    Sea bienvenido/a al Conversor de Monedas
                    
                    1)Dolar -> Peso argentino
                    2)Peso Argentino -> Dolar
                    3) Dolar -> Real Brasileño
                    4) real brasileño -> Dolar
                    5) Dolar -> Peso colombiano
                    6) Peso colombiano -> Dolar
                    0) Salir
                    Elije una opcion valida:
                    
                    """);
            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
                continue;
            }

            switch (op) {
                case 1 -> conversiones(monedasFiltradas[0], monedasFiltradas[1]);
                case 2 -> conversiones(monedasFiltradas[1], monedasFiltradas[0]);
                case 3 -> conversiones(monedasFiltradas[0], monedasFiltradas[2]);
                case 4 -> conversiones(monedasFiltradas[2], monedasFiltradas[0]);
                case 5 -> conversiones(monedasFiltradas[0], monedasFiltradas[3]);
                case 6 -> conversiones(monedasFiltradas[3], monedasFiltradas[0]);
                case 0 -> System.out.println("Saliendo del programa");
                default -> System.out.println("Ingresa una opción correcta.");
            }

        }while (op!=0);
    }

}
