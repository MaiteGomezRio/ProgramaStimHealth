package Epidurolisis;

import Epidurolisis.Caracteristicas_Producto.Hospital.Hospital;
import Epidurolisis.Caracteristicas_Producto.TipoProducto;
import IOInterface.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Producto {

    private TipoProducto tipo;

    private long lote;

    private LocalDate caducidad;

    private int unidades;

    private float precio_producto;
    private float precio_IVA;
    private float precio_total;

    /**
     * constructor por defecto- epidurolisis lumbar
     * @param hospital
     * @param unidades
     */
    public Producto(Hospital hospital, int unidades, TipoProducto tipo) {
        //TODO modificar en caso de cambio de lote o de fecha de caducidad

        if(tipo==TipoProducto.LUMBAR){
            this.lote=20231002;
            this.caducidad=LocalDate.of(2026,10,01);
        }
        if(tipo==TipoProducto.CERVICAL){
            this.lote=20230102;
            this.caducidad=LocalDate.of(2026,01,01);
        }
        this.unidades = unidades;
        this.precio_producto=hospital.getPrecio()* unidades;
        this.precio_IVA= (float) (0.21* precio_producto);
        this.precio_total=this.calcularPrecioTotal();
    }

    /**
     * Constructor empleado al leer datos del pdf
     * @param hospital
     * @param unidades
     * @param precio_producto
     */
    public Producto(Hospital hospital, int unidades, float precio_producto){

        this.tipo=TipoProducto.LUMBAR;
        this.unidades=unidades;
        this.precio_producto=precio_producto;
        this.precio_IVA= Utils.redondear((float) (0.21* precio_producto));
        this.precio_total=Utils.redondear(this.calcularPrecioTotal());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "unidades=" + unidades +
                ", precio_producto=" + precio_producto +
                ", precio_IVA=" + precio_IVA +
                ", precio_total=" + precio_total +
                '}';
    }



    /**
     * calcularPrecioTotal
     * calcula el coste total
     * @return precio total
     */
    public float calcularPrecioTotal(){
        return this.precio_producto+this.precio_IVA;
    }


    public int getUnidades() {
        return unidades;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public long getLote() {
        return lote;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public float getPrecio_IVA() {
        return precio_IVA;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }

    public void setPrecio_IVA(float precio_IVA) {
        this.precio_IVA = precio_IVA;
    }


}
