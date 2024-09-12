package Epidurolisis;

import Epidurolisis.Caracteristicas_Producto.Hospital.Hospital;
import Epidurolisis.Caracteristicas_Producto.TipoProducto;

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
    public Producto(Hospital hospital, int unidades) {
        this.tipo = TipoProducto.LUMBAR;
        if(this.tipo==TipoProducto.LUMBAR){
            this.lote=20231002;
            this.caducidad=LocalDate.of(2026,10,01);
        }else{
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
        this.precio_IVA= (float) (0.21* precio_producto);
        this.precio_total=this.calcularPrecioTotal();
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

    public String toPDF(){
        return unidades+" "+precio_producto+" "+precio_IVA+" "+precio_total;
    }

    /**
     * calcularPrecioTotal
     * calcula el coste total
     * @return
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

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
}
