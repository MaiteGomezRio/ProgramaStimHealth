package Epidurolisis.Archivos;

import Epidurolisis.Producto;
import Excepciones.NotValidCodeException;

import java.time.LocalDate;

public class Albaran extends Archivo {


    private String codigo_albaran;// debe ser de 4 dígitos


    public Albaran(long numero_pedido, LocalDate fecha, String num_expediente,Producto producto, String codigo_albaran, String observacion) throws NotValidCodeException {
        super(numero_pedido, fecha, num_expediente, producto, observacion);
        this.codigo_albaran = "A2024-"+codigo_albaran;
    }

    /**
     * Constructor empleado para obtener info del albarán y cambiar los datos necesarios
     * @param numero_pedido número de pedido
     * @param fecha_entrega fecha de entrega
     * @param producto catéter
     */
    public Albaran(long numero_pedido, LocalDate fecha_entrega,String num_expediente, Producto producto, String observacion){
        super(numero_pedido,fecha_entrega,num_expediente,producto, observacion);
    }

    @Override
    public String toString() {
        return "Albaran{" +
                "codigo_albaran='" + codigo_albaran + '\'' +
                //", direccion_entrega='" + direccion_entrega + '\'' +
                '}';
    }



    public String getCodigo_albaran() {
        return codigo_albaran;
    }

    public void setCodigo_albaran(int codigo_albaran) {
        this.codigo_albaran = "A2024-"+codigo_albaran;
    }
}
