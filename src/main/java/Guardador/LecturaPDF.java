package Guardador;

import Epidurolisis.Archivos.Albaran;
import Epidurolisis.Archivos.Factura;
import Epidurolisis.Caracteristicas_Producto.Hospital.Hospital;
import Epidurolisis.Producto;
import IOInterface.Utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class LecturaPDF {


    /**
     * AlbaranFromPDF
     * lee el pdf y lo guarda como objeto "Albarán"
     * @param ruta ruta del archivo
     * @param hospital hospital
     * @return albarán con la info leída del pdf
     */
    public static Albaran AlbaranFromPDF(String ruta, Hospital hospital){

        Albaran albaran=null;
        try {
            // Cargar el documento PDF
            File file = new File(ruta);
            PDDocument document = PDDocument.load(file);

            // Usar PDFTextStripperByArea para extraer texto de áreas específicas
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);


            Rectangle rectNumeroPedido = new Rectangle(165,160,50,20);
            Rectangle rectFechaEntrega = new Rectangle(80,200,100,20);
            Rectangle rectPrecioProducto = new Rectangle(450,300,50,20);
            Rectangle rectUdsProducto= new Rectangle(400,300,50,20);

            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            String precioProductoStr = stripper.getTextForRegion("precioProducto").trim();
            String unidadesStr=stripper.getTextForRegion("udsProducto").trim();

            //Convertir numero de pedido a long
            long numeroPedido=Long.parseLong(numeroPedidoStr.trim());

            //Convertir fecha de entrega a localdate

            LocalDate fechaEntrega= Utils.String2LocalDate(fechaEntregaStr.trim());

            //Convertir unidades a int
            int unidades=Integer.parseInt(unidadesStr.replace("€", "").trim());

            // Convertir el precio a float
            float precioProducto = Float.parseFloat(precioProductoStr.replace("€", "").trim());


            // Crear el objeto Albaran y asignar los valores
            float precio=precioProducto/unidades;
            Producto producto=new Producto(hospital,unidades,precio);
            albaran = new Albaran(numeroPedido,fechaEntrega,producto);


            // Cerrar el documento PDF
            document.close();

            return albaran;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return albaran;
    }

    public static Factura FacturaFromPDF(String ruta, Hospital hospital){
        Factura factura=null;
        try {
            // Cargar el documento PDF
            File file = new File(ruta);
            PDDocument document = PDDocument.load(file);

            // Usar PDFTextStripperByArea para extraer texto de áreas específicas
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);


            Rectangle rectNumeroPedido = new Rectangle(165,160,50,20);
            Rectangle rectFechaEntrega = new Rectangle(80,200,85,20);
            Rectangle rectPrecioProducto = new Rectangle(450,300,50,20);
            Rectangle rectUdsProducto= new Rectangle(400,300,50,20);

            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            String precioProductoStr = stripper.getTextForRegion("precioProducto").trim();
            String unidadesStr=stripper.getTextForRegion("udsProducto").trim();

            //Convertir numero de pedido a long
            long numeroPedido=Long.parseLong(numeroPedidoStr.trim());

            //Convertir fecha de entrega a localdate

            LocalDate fechaEntrega= Utils.String2LocalDate(fechaEntregaStr.trim());

            //Convertir unidades a int
            int unidades=Integer.parseInt(unidadesStr.replace("€", "").trim());

            // Convertir el precio a double
            float precioProducto = Float.parseFloat(precioProductoStr.replace("€", "").trim());


            // Crear el objeto Factura y asignar los valores
            float precio=precioProducto/unidades;
            Producto producto=new Producto(hospital,unidades,precio);
            factura = new Factura(numeroPedido,fechaEntrega,producto);


            // Cerrar el documento PDF
            document.close();

            return factura;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return factura;
    }

    public static void main(String[] args) {
        try {
            // Cargar el documento PDF
            String ruta="C:\\Users\\maipa\\OneDrive - Fundación Universitaria San Pablo CEU\\Documentos\\PRÁCTICAS\\3. PROGRAMA ALBARANES\\Archivos\\A2024-999_CENTRO EUROPEO MÉDICO TRAUMATOLÓGICO. SA..pdf";
            File file = new File(ruta);
            PDDocument document = PDDocument.load(file);

            // Usar PDFTextStripperByArea para extraer texto de áreas específicas
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);


            Rectangle rectNumeroPedido = new Rectangle(165,160,50,20);
            Rectangle rectFechaEntrega = new Rectangle(80,200,100,20);
            Rectangle rectPrecioProducto = new Rectangle(450,300,50,20);
            Rectangle rectUdsProducto= new Rectangle(400,300,50,20);

            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            String precioProductoStr = stripper.getTextForRegion("precioProducto").trim();
            String unidadesStr=stripper.getTextForRegion("udsProducto").trim();

            //Convertir numero de pedido a long
            long numeroPedido=Long.parseLong(numeroPedidoStr.trim());

            //Convertir fecha de entrega a localdate

            LocalDate fechaEntrega= Utils.String2LocalDate(fechaEntregaStr.trim());

            //Convertir unidades a int
            int unidades=Integer.parseInt(unidadesStr.replace("€", "").trim());

            // Convertir el precio a double
            float precioProducto = Float.parseFloat(precioProductoStr.replace("€", "").trim());

            Hospital hospital=new Hospital();
            hospital.hospital_CEMTRO();
            hospital.setPlantilla(ruta);
            // Crear el objeto Albaran y asignar los valores
            float precio=precioProducto/unidades;
            Producto producto=new Producto(hospital,unidades,precio);
            Albaran albaran = new Albaran(numeroPedido,fechaEntrega,producto);


            // Imprimir el objeto Albaran
            System.out.println(albaran);

            // Cerrar el documento PDF
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

