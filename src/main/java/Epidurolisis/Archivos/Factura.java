package Epidurolisis.Archivos;


import Epidurolisis.Producto;

import java.time.LocalDate;

public class Factura extends Archivo {

    private String codigo_factura;


    public Factura(long numero_pedido,int codigo_factura, LocalDate fecha,String num_expediente, Producto producto, String observacion) {
        super(numero_pedido, fecha, num_expediente, producto, observacion);
        this.codigo_factura = "FRA"+fecha.getYear()+"-"+codigo_factura;
    }
    public Factura(long numero_pedido,String codigo_factura, LocalDate fecha,String num_expediente, Producto producto, String observacion) {
        super(numero_pedido, fecha, num_expediente, producto, observacion);
        this.codigo_factura = codigo_factura;
    }

    public Factura(long numero_pedido, LocalDate fecha_entrega, String num_expediente,Producto producto, String observacion){
        super(numero_pedido,fecha_entrega,num_expediente,producto, observacion);
    }

    /**
     * Constructor empleado para obtener info de la factura y cambiar los datos necesarios
     * @param numero_pedido número de pedido
     * @param fecha_entrega fecha de entrega
     * @param producto catéter
     */
    public Factura(long numero_pedido, LocalDate fecha_entrega, Producto producto){
        super(numero_pedido,fecha_entrega,"",producto,"");
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

    public void setCodigo_factura(int codigo_factura, int ano) {
        this.codigo_factura = "FRA"+ano+"-"+codigo_factura;
    }
}
