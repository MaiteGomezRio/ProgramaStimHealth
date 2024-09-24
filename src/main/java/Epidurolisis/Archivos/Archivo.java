package Epidurolisis.Archivos;


import Epidurolisis.Producto;

import java.time.LocalDate;
import java.time.Month;


public class Archivo {


    private long numero_pedido;


    private LocalDate fecha;//fecha en la que se realiza el albarán/ factura

    private LocalDate fecha_entrega;

    private String numero_expediente;

    private Producto producto;

    private String observacion;

    public Archivo(long numero_pedido,LocalDate fecha, String numero_expediente, Producto producto, String observacion) {

        this.numero_pedido=numero_pedido;
        this.fecha=fecha;
        this.fecha_entrega=fecha.plusDays(1);//se añaden 24h a la fecha en la que se realiza
        this.numero_expediente=numero_expediente;
        this.producto=producto;
        this.observacion=observacion;

    }

    @Override
    public String toString() {
        return "Archivo{" +
                "numero_pedido=" + numero_pedido +
                ", fecha=" + fecha +
                ", fecha_entrega=" + fecha_entrega +
                ", producto=" + producto.toString() +
                '}';
    }

    /**
     * obtenerTrimestre se utiliza para seleccionar la carpeta en laa que guardar el archivo generado dependiendo
     * del mes en el que se genere
     * @param mes
     * @return nombre de la carpeta en la que se va a guardar
     */
    public static String obtenerTrimestre(Month mes){

        switch (mes) {
            case JANUARY:
            case FEBRUARY:
            case MARCH:
                return "1T"; // Primer trimestre
            case APRIL:
            case MAY:
            case JUNE:
                return "2T"; // Segundo trimestre
            case JULY:
            case AUGUST:
            case SEPTEMBER:
                return "3T"; // Tercer trimestre
            case OCTOBER:
            case NOVEMBER:
            case DECEMBER:
                return "4T"; // Cuarto trimestre
            default:
                throw new IllegalArgumentException("Mes inválido");
        }
    }

    /**
     * obtenerMes se utiliza para seleccionar la carpeta en laa que guardar el archivo generado dependiendo
     * del mes en el que se genere
     * @param mes
     * @return nombre de la carpeta en la que se va a guardar
     */
    public static String obtenerMesCarpeta(Month mes, int ano){

        switch (mes) {
            case JANUARY:
                return "ENERO "+ano;
            case FEBRUARY:
                return "FEBRERO "+ano;
            case MARCH:
                return "MARZO "+ano;
            case APRIL:
                return "ABRIL "+ano;
            case MAY:
                return "MAYO "+ano;
            case JUNE:
                return "JUNIO "+ano;
            case JULY:
                return "JULIO "+ano;
            case AUGUST:
                return "AGOSTO "+ano;
            case SEPTEMBER:
                return "SEPTIEMBRE "+ano;
            case OCTOBER:
                return "OCTUBRE "+ano;
            case NOVEMBER:
                return "NOVIEMBRE "+ano;
            case DECEMBER:
                return "DICIEMBRE "+ano;
            default:
                throw new IllegalArgumentException("Mes inválido");
        }
    }



    public long getNumero_pedido() {
        return numero_pedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDate getFecha_entrega() {
        return fecha_entrega;
    }

    public String getNumero_expediente() {
        return numero_expediente;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setFecha_entrega(LocalDate fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public void setNumero_pedido(long numero_pedido) {
        this.numero_pedido = numero_pedido;
    }
}
