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


            Rectangle rectNumeroPedido = new Rectangle(144,145,60,10);
            Rectangle rectNumeroExpediente=new Rectangle(150,170,80,20);
            Rectangle rectFechaEntrega = new Rectangle(93,170,40,20);
            Rectangle rectPrecioProducto = new Rectangle(395,270,40,10);
            Rectangle rectUdsProducto= new Rectangle(360,270,40,10);


            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("numeroExpediente", rectNumeroExpediente);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            String numeroExpStr=stripper.getTextForRegion("numeroExpediente").trim();
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
            
            Producto producto=new Producto(hospital,unidades,precioProducto);
            albaran = new Albaran(numeroPedido,numeroExpStr,fechaEntrega,producto);
            

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

            Rectangle rectNumeroPedido = new Rectangle(144,145,60,10);
            Rectangle rectFechaEntrega = new Rectangle(91,170,40,20);
            Rectangle rectPrecioProducto = new Rectangle(395,270,40,10);
            Rectangle rectUdsProducto= new Rectangle(360,270,40,10);


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
            
            Producto producto=new Producto(hospital,unidades,precioProducto);
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
        /*try {
            // Cargar el documento PDF
            String ruta="C:\\Users\\maipa\\OneDrive - Fundación Universitaria San Pablo CEU\\StimHealth\\02 Contabilidad\\2024 AÑO\\FACTURAS EMITIDAS 2024\\4T\\OCTUBRE 24\\A2024-123_TORREJON SALUD S.L. HOSPITAL UNIVERSITARIO DE TORREJON.pdf";
            File file = new File(ruta);
            PDDocument document = PDDocument.load(file);

            // Usar PDFTextStripperByArea para extraer texto de áreas específicas
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);


            Rectangle rectNumeroPedido = new Rectangle(144,145,60,10);
            Rectangle rectNumeroExpediente=new Rectangle(150,170,80,20);
            Rectangle rectFechaEntrega = new Rectangle(93,170,40,20);
            Rectangle rectPrecioProducto = new Rectangle(395,270,40,10);
            Rectangle rectUdsProducto= new Rectangle(360,270,40,10);

            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("numeroExpediente", rectNumeroExpediente);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            System.out.println("num pedido  "+numeroPedidoStr);
            String numeroExpStr=stripper.getTextForRegion("numeroExpediente").trim();
            System.out.println("num exp "+numeroExpStr);
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            System.out.println("fecha entrega "+fechaEntregaStr);
            String precioProductoStr = stripper.getTextForRegion("precioProducto").trim();
            System.out.println("precio producto "+precioProductoStr);
            String unidadesStr=stripper.getTextForRegion("udsProducto").trim();
            System.out.println("unidades "+unidadesStr);

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
            Albaran albaran = new Albaran(numeroPedido,numeroExpStr,fechaEntrega,producto);
            albaran.setCodigo_albaran(123,2024);

            // Imprimir el objeto Albaran
            System.out.println(albaran);

            // Cerrar el documento PDF
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            // Cargar el documento PDF
            String ruta="C:\\Users\\maipa\\OneDrive - Fundación Universitaria San Pablo CEU\\StimHealth\\02 Contabilidad\\2024 AÑO\\FACTURAS EMITIDAS 2024\\4T\\OCTUBRE 24\\FRA2024-661_HOSPITAL UNIVERSITARIO HM PUERTA DEL SUR. HM HOSPITALES 1989 SA.pdf";
            File file = new File(ruta);
            PDDocument document = PDDocument.load(file);

            // Usar PDFTextStripperByArea para extraer texto de áreas específicas
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);


            Rectangle rectNumeroPedido = new Rectangle(144,145,60,10);
            Rectangle rectFechaEntrega = new Rectangle(91,170,40,20);
            Rectangle rectPrecioProducto = new Rectangle(395,270,40,10);
            Rectangle rectUdsProducto= new Rectangle(360,270,40,10);

            // Añadir las áreas a stripper
            stripper.addRegion("numeroPedido", rectNumeroPedido);
            stripper.addRegion("fechaEntrega", rectFechaEntrega);
            stripper.addRegion("precioProducto", rectPrecioProducto);
            stripper.addRegion("udsProducto", rectUdsProducto);

            // Aplicar stripper a la primera página del documento
            stripper.extractRegions(document.getPage(0));

            // Obtener los datos extraídos
            String numeroPedidoStr = stripper.getTextForRegion("numeroPedido").trim();
            System.out.println("num pedido  "+numeroPedidoStr);
            String fechaEntregaStr = stripper.getTextForRegion("fechaEntrega").trim();
            System.out.println("fecha entrega "+fechaEntregaStr);
            String precioProductoStr = stripper.getTextForRegion("precioProducto").trim();
            System.out.println("precio producto "+precioProductoStr);
            String unidadesStr=stripper.getTextForRegion("udsProducto").trim();
            System.out.println("unidades "+unidadesStr);

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
            Factura factura = new Factura(numeroPedido,fechaEntrega,producto);
            factura.setCodigo_factura(661,2024);

            // Imprimir el objeto Factura
            System.out.println(factura);

            // Cerrar el documento PDF
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

