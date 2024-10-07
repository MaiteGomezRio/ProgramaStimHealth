package IOInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Utils {

    /**
     * Función que redondea los precios a 2 decimales
     * @param valor
     * @return
     */
    public static float redondear(float valor) {
        BigDecimal bd = new BigDecimal(Float.toString(valor));
        bd = bd.setScale(2, RoundingMode.HALF_UP); // Redondea hacia arriba si el decimal es >= 0.5
        return bd.floatValue();
    }

    /**
     * obtenerCodigoNumerico
     * Divide el código de lafactura/alabarán en parte de caracteres y números
     * @param cadena
     * @return
     */
    public static int obtenerCodigoNumerico(String cadena) {
        // Divide la cadena en dos partes usando el guión como separador
        String[] partes = cadena.split("-");

        // Si el formato es correcto, la segunda parte será el código numérico
        if (partes.length == 2) {
            try {
                // Convertir la segunda parte a un entero
                return Integer.parseInt(partes[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error: El código no es un número válido.");
            }
        } else {
            System.out.println("Error: La cadena no sigue el formato esperado.");
        }
        return -1;  // Devuelve un valor de error en caso de fallo
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


    public static long leerLong() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long longLeido;
        String stringLeida;
        while (true) {
            try {
                stringLeida = br.readLine();
                // Si el usuario presiona Enter sin introducir nada, asignamos un valor predeterminado
                if (stringLeida.isEmpty()) {
                    longLeido = 0L; // Valor predeterminado
                    return longLeido;
                }

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

    /**
     * comprobarCero introduce un carácter vacío en caso de que el usuario no introduzca nada
     * @param num_pedido
     * @return
     */
    public static String comprobarCero(long num_pedido){
        if(num_pedido==0){
            return "";
        }else{
            return String.valueOf(num_pedido);
        }
    }
    public static LocalDate leerFecha() {
        try (Scanner scanner = new Scanner(System.in)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fecha = null;

			while (fecha == null) {
			    System.out.print("Introduce una fecha en formato dd/MM/yyyy: ");
			    String fechaStr = scanner.nextLine();
			    if (fechaStr.isEmpty()) {
			        System.out.println("No has introducido nada. Por favor, intenta de nuevo.");
			        continue;  // Vuelve a pedir la fecha
			    }

			    try {
			        fecha = LocalDate.parse(fechaStr, formatter);
			    } catch (DateTimeParseException e) {
			        System.out.println("Fecha no válida. Por favor, intenta de nuevo.");
			    }
			}
			return fecha;
		}
    }

    public static LocalDate String2LocalDate(String dateString) {
        // Define el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convierte el string a LocalDate usando el formateador
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }
   
    public static LocalDate String2LocalDate2(String fechaStr) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        try {
            // Convertir el String en LocalDate usando el formato especificado
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            // Si el formato es incorrecto, lanzar una excepción con un mensaje personalizado
            throw new IllegalArgumentException("Formato de fecha inválido. Debe ser día/mes/año.");
        }
    }


}
