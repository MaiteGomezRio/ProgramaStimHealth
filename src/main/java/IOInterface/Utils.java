package IOInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static long generarNumAleatorio() {
        Random random = new Random();
        long numeroAleatorio = 1_000_000_000L + (long) (random.nextDouble() * 9_000_000_000L);
        return numeroAleatorio;
    }

    public static int generarNumAleatorio2(){
        return ThreadLocalRandom.current().nextInt(0000,10000);
    }


    public static float generarNumAleatorioWithBound(float origin,float bound){
        Random random = new Random();
        float numero = random.nextFloat(origin,bound);
        return numero;
    }

    public static int leerEntero() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int intLeido;
        String stringLeida;
        while (true) {
            try {
                stringLeida = br.readLine();
                intLeido = Integer.parseInt(stringLeida);
                return intLeido;
            } catch (IOException ioe) {
                System.out.println("Error al leer de teclado");
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Disculpe, debe introducir un numero entero");
            }
        }
    }

    public static String leerString () {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String stringLeida;
        while (true) {
            try {
                stringLeida = bufferedReader.readLine();
                return stringLeida;
            } catch (IOException ioe) {
                System.out.println("Error al leer de teclado");
            } catch (NumberFormatException nfe) {
                System.out.println("Disculpe, debe introducir una cadena de caracteres");
            }
        }
    }

    public static float leerFloat() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float floatLeido;
        String stringLeida;
        while (true) {
            try {
                stringLeida = br.readLine();
                floatLeido = Float.parseFloat(stringLeida);
                return floatLeido;
            } catch (IOException ioe) {
                System.out.println("Error al leer de teclado");
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Disculpe, debe introducir un numero real");
            }
        }
    }

    public static long leerLong() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long longLeido;
        String stringLeida;
        while (true) {
            try {
                stringLeida = br.readLine();
                longLeido = Long.parseLong(stringLeida);
                return longLeido;
            } catch (IOException ioe) {
                System.out.println("Error al leer de teclado");
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Disculpe, debe introducir un numero real");
            }
        }
    }
    public static LocalDate leerFecha() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;

        while (fecha == null) {
            System.out.print("Introduce una fecha en formato dd/MM/yyyy: ");
            String fechaStr = scanner.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Por favor, intenta de nuevo.");
            }
        }
        return fecha;
    }

    public static LocalDate String2LocalDate(String dateString) {
        // Define el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convierte el string a LocalDate usando el formateador
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }


}
