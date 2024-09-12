package Epidurolisis.Archivos;


import Epidurolisis.Producto;

import java.time.LocalDate;

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
