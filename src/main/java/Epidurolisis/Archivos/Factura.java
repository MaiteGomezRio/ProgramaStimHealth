package Epidurolisis.Archivos;


import Epidurolisis.Producto;

import java.time.LocalDate;

public class Factura extends Archivo {

    private String codigo_factura;


    public Factura(long numero_pedido, LocalDate fecha,String num_expediente, Producto producto, String codigo_factura, String observacion) {
        super(numero_pedido, fecha, num_expediente, producto, observacion);
        this.codigo_factura = "FRA2024-"+codigo_factura;
    }

    public Factura(long numero_pedido, LocalDate fecha_entrega, String num_expediente,Producto producto, String observacion){
        super(numero_pedido,fecha_entrega,num_expediente,producto, observacion);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo_factura='" + codigo_factura + '\'' +
                '}';
    }


    public String getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = "FRA2024-"+codigo_factura;
    }
}
