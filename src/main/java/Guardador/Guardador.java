package Guardador;

import Epidurolisis.Archivos.Albaran;
import Epidurolisis.Archivos.Factura;

import Epidurolisis.Caracteristicas_Producto.Hospital.Hospital;
import Epidurolisis.Caracteristicas_Producto.TipoProducto;
import Epidurolisis.Producto;
import IOInterface.Utils;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Guardador {

    private String nombre_Archivo;

    public Guardador(Albaran albaran, Hospital hospital) {
        this.nombre_Archivo =albaran.getCodigo_albaran() + "_" + hospital.getNombre()+".pdf";
    }
    public Guardador(Factura factura, Hospital hospital){
        this.nombre_Archivo=factura.getCodigo_factura() + "_" + hospital.getNombre()+".pdf";
    }



    /**
     * Albaran2PDF
     * Rellena la plantilla del albarán que se va a crear, genera una copia y lo guarda
     * @param albaran albarán a guardar
     * @param hospital hospital para el que se genera
     * @param rutaAcarpeta ruta a la carpeta del programa
     * @throws IOException excepción
     */

    public void Albaran2PDF(Albaran albaran, String rutaAcarpeta, Hospital hospital) throws IOException {

        String templateFile =hospital.getPlantilla();
        String outputFile = rutaAcarpeta+"\\Archivos\\"+this.nombre_Archivo;

        // Create a PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(outputFile));
        Document document = new Document(pdfDoc);

        //-----------------PARTE 1-----------------------------------------
        //1. NÚMERO DE PEDIDO
        float cx_pedido=convertirCoordenada(58.34f);
        float cy_pedido=convertirCoordenada(273.0f);
        String num_pedido = String.valueOf(albaran.getNumero_pedido());
        Paragraph p1=new Paragraph(num_pedido);
        p1.setFontSize(10);
        p1.setFixedPosition(cx_pedido,cy_pedido, 100f);
        document.add(p1);

        //2. CÓDIGO ALBARÁN
        float cx_codigo=convertirCoordenada(182.2f);
        float cy_codigo=convertirCoordenada(291.95f);
        String codigo=albaran.getCodigo_albaran();
        Paragraph p2=new Paragraph(codigo);
        p2.setFontSize(10);
        p2.setFixedPosition(cx_codigo,cy_codigo,100f);
        document.add(p2);

        //3.FECHA
        float cx_fecha=convertirCoordenada(37.2f);
        float cy_fecha=convertirCoordenada(260.0f);
        LocalDate fecha=albaran.getFecha();
        Paragraph p3=new Paragraph(fecha.toString());
        p3.setFontSize(10);
        p3.setFixedPosition(cx_fecha,cy_fecha,100f);
        document.add(p3);

        //-------------------------PARTE 2----------------------------------

        //4.CANTIDAD
        float cx_uds=convertirCoordenada(144.2f);
        float cy_uds=convertirCoordenada(221.73f);
        int uds=albaran.getProducto().getUnidades();
        Paragraph p4=new Paragraph(String.valueOf(uds));
        p4.setFontSize(8);
        p4.setFixedPosition(cx_uds,cy_uds,100f);
        document.add(p4);

        //5.PRECIO
        float cx_precio=convertirCoordenada(160.1f);
        float cy_precio=convertirCoordenada(221.73f);
        float precio=albaran.getProducto().getPrecio_producto();
        Paragraph p5=new Paragraph(precio+" €");
        p5.setFontSize(8);
        p5.setFixedPosition(cx_precio,cy_precio,100f);
        document.add(p5);

        //6.IVA
        float cx_iva=convertirCoordenada(180.0f);
        float cy_iva=convertirCoordenada(221.73f);
        float iva=albaran.getProducto().getPrecio_IVA();
        Paragraph p6=new Paragraph(iva+" €");
        p6.setFontSize(8);
        p6.setFixedPosition(cx_iva,cy_iva,100f);
        document.add(p6);

        //7.TOTAL
        float cx_total=convertirCoordenada(197.7f);
        float cy_total=convertirCoordenada(221.73f);
        float total=albaran.getProducto().getPrecio_total();
        Paragraph p7=new Paragraph(total+" €");
        p7.setFontSize(8);
        p7.setFixedPosition(cx_total,cy_total,100f);
        document.add(p7);

        //---------------------PARTE 3----------------------------------

        //8. TOTAL2-> precio producto
        float cx_total2=convertirCoordenada(43.5f);
        float cy_total2=convertirCoordenada(88.5f);
        Paragraph p8=new Paragraph(precio+" €");
        p8.setFontSize(9);
        p8.setFixedPosition(cx_total2,cy_total2,100f);
        document.add(p8);

        //9. BASE IVA->
        float cx_baseIVA=convertirCoordenada(78.0f);
        float cy_baseIVA=convertirCoordenada(88.5f);
        Paragraph p9=new Paragraph(precio+" €");
        p9.setFontSize(9);
        p9.setFixedPosition(cx_baseIVA,cy_baseIVA,100f);
        document.add(p9);

        //10.IMP IVA-> precio con el iva
        float cx_impIVA=convertirCoordenada(119.0f);
        float cy_impIVA=convertirCoordenada(88.5f);
        Paragraph p10=new Paragraph(iva+" €");
        p10.setFontSize(9);
        p10.setFixedPosition(cx_impIVA,cy_impIVA,100f);
        document.add(p10);

        //11.TOTAL-> producto+iva
        float cx_total3=convertirCoordenada(184.3f);
        float cy_total3=convertirCoordenada(88.5f);
        Paragraph p11=new Paragraph(total+" €");
        p11.setFontSize(9);
        p11.setFixedPosition(cx_total3,cy_total3,100f);
        document.add(p11);

        document.close();
        pdfDoc.close();


    }

    /**
     * Factura2PDF
     * Genera una copia de la plantilla de la factura que se va a crear,la rellena con los datos y lo guarda en la
     * ruta indicada
     * @param factura factura a guardar
     * @param hospital hospital para el que se genera
     * @param rutaAcarpeta ruta a la carpeta del programa
     * @throws IOException excepción
     */
    public void Factura2PDF(Factura factura, String rutaAcarpeta, Hospital hospital) throws IOException {

        String templateFile =hospital.getPlantilla();
        String outputFile = rutaAcarpeta+"\\Archivos\\"+this.nombre_Archivo;

        // Create a PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(outputFile));
        Document document = new Document(pdfDoc);

        //-----------------PARTE 1-----------------------------------------
        //1. NÚMERO DE PEDIDO
        float cx_pedido=convertirCoordenada(58.34f);
        float cy_pedido=convertirCoordenada(273.0f);
        String num_pedido = String.valueOf(factura.getNumero_pedido());
        Paragraph p1=new Paragraph(num_pedido);
        p1.setFontSize(10);
        p1.setFixedPosition(cx_pedido,cy_pedido, 100f);
        document.add(p1);

        //2. CÓDIGO FACTURA
        float cx_codigo=convertirCoordenada(182.2f);
        float cy_codigo=convertirCoordenada(291.95f);
        String codigo=factura.getCodigo_factura();
        Paragraph p2=new Paragraph(codigo);
        p2.setFontSize(10);
        p2.setFixedPosition(cx_codigo,cy_codigo,100f);
        document.add(p2);

        //3.FECHA
        float cx_fecha=convertirCoordenada(37.2f);
        float cy_fecha=convertirCoordenada(260.0f);
        LocalDate fecha=factura.getFecha();
        Paragraph p3=new Paragraph(fecha.toString());
        p3.setFontSize(10);
        p3.setFixedPosition(cx_fecha,cy_fecha,100f);
        document.add(p3);

        //4. ALBARÁN/FECHA DE ENTREGA
        float cx_codigoF=convertirCoordenada(59.0f);
        float cy_codigoF=convertirCoordenada(260.0f);
        String codigoF="A2024-"+ Utils.generarNumAleatorio2();
        Paragraph p4=new Paragraph(codigoF);
        p4.setFontSize(10);
        p4.setFixedPosition(cx_codigoF,cy_codigoF,100f);
        document.add(p4);

        float cx_fechaE=convertirCoordenada(84.1f);
        float cy_fechaE=convertirCoordenada(260.0f);
        LocalDate fecha_entrega=factura.getFecha_entrega();
        Paragraph p=new Paragraph(fecha_entrega.toString());
        p.setFontSize(10);
        p.setFixedPosition(cx_fechaE,cy_fechaE,100f);
        document.add(p);

        //-------------------------PARTE 2----------------------------------

        //5.CANTIDAD
        float cx_uds=convertirCoordenada(144.2f);
        float cy_uds=convertirCoordenada(221.73f);
        int uds=factura.getProducto().getUnidades();
        Paragraph p5=new Paragraph(String.valueOf(uds));
        p5.setFontSize(8);
        p5.setFixedPosition(cx_uds,cy_uds,100f);
        document.add(p5);

        //6.PRECIO
        float cx_precio=convertirCoordenada(160.1f);
        float cy_precio=convertirCoordenada(221.73f);
        float precio=factura.getProducto().getPrecio_producto();
        Paragraph p6=new Paragraph(precio+" €");
        p6.setFontSize(8);
        p6.setFixedPosition(cx_precio,cy_precio,100f);
        document.add(p6);

        //7.IVA
        float cx_iva=convertirCoordenada(180.0f);
        float cy_iva=convertirCoordenada(221.73f);
        float iva=factura.getProducto().getPrecio_IVA();
        Paragraph p7=new Paragraph(iva+" €");
        p7.setFontSize(8);
        p7.setFixedPosition(cx_iva,cy_iva,100f);
        document.add(p7);

        //8.TOTAL
        float cx_total=convertirCoordenada(197.7f);
        float cy_total=convertirCoordenada(221.73f);
        float total=factura.getProducto().getPrecio_total();
        Paragraph p8=new Paragraph(total+" €");
        p8.setFontSize(8);
        p8.setFixedPosition(cx_total,cy_total,100f);
        document.add(p8);

        //---------------------PARTE 3----------------------------------

        //9. TOTAL2-> precio producto
        float cx_total2=convertirCoordenada(43.5f);
        float cy_total2=convertirCoordenada(88.5f);
        Paragraph p9=new Paragraph(precio+" €");
        p9.setFontSize(9);
        p9.setFixedPosition(cx_total2,cy_total2,100f);
        document.add(p9);

        //10. BASE IVA->
        float cx_baseIVA=convertirCoordenada(78.0f);
        float cy_baseIVA=convertirCoordenada(88.5f);
        Paragraph p10=new Paragraph(precio+" €");
        p10.setFontSize(9);
        p10.setFixedPosition(cx_baseIVA,cy_baseIVA,100f);
        document.add(p10);

        //11.IMP IVA-> precio con el iva
        float cx_impIVA=convertirCoordenada(119.0f);
        float cy_impIVA=convertirCoordenada(88.5f);
        Paragraph p11=new Paragraph(iva+" €");
        p11.setFontSize(9);
        p11.setFixedPosition(cx_impIVA,cy_impIVA,100f);
        document.add(p11);

        //12.TOTAL-> producto+iva
        float cx_total3=convertirCoordenada(184.3f);
        float cy_total3=convertirCoordenada(88.5f);
        Paragraph p12=new Paragraph(total+" €");
        p12.setFontSize(9);
        p12.setFixedPosition(cx_total3,cy_total3,100f);
        document.add(p12);

        document.close();
        pdfDoc.close();

    }

    /**
     * Guardador utilizado cuando hay más de un tipo de catéter
     * Escribe toda la info del catéter de tipo lumbar pero no el total del final
     * @param albaran albarán
     * @param rutaAcarpeta ruta
     * @param hospital hospital
     * @throws IOException
     */
    public void Albaran2PDF_special(Albaran albaran, String rutaAcarpeta, Hospital hospital) throws IOException {
        String templateFile =hospital.getPlantilla();
        String outputFile = rutaAcarpeta+"\\Archivos\\"+this.nombre_Archivo;

        // Create a PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(outputFile));
        Document document = new Document(pdfDoc);

        //-----------------PARTE 1-----------------------------------------
        //1. NÚMERO DE PEDIDO
        float cx_pedido=convertirCoordenada(58.34f);
        float cy_pedido=convertirCoordenada(273.0f);
        String num_pedido = String.valueOf(albaran.getNumero_pedido());
        Paragraph p1=new Paragraph(num_pedido);
        p1.setFontSize(10);
        p1.setFixedPosition(cx_pedido,cy_pedido, 100f);
        document.add(p1);

        //2. CÓDIGO ALBARÁN
        float cx_codigo=convertirCoordenada(182.2f);
        float cy_codigo=convertirCoordenada(291.95f);
        String codigo=albaran.getCodigo_albaran();
        Paragraph p2=new Paragraph(codigo);
        p2.setFontSize(10);
        p2.setFixedPosition(cx_codigo,cy_codigo,100f);
        document.add(p2);

        //3.FECHA
        float cx_fecha=convertirCoordenada(37.2f);
        float cy_fecha=convertirCoordenada(260.0f);
        LocalDate fecha=albaran.getFecha();
        Paragraph p3=new Paragraph(fecha.toString());
        p3.setFontSize(10);
        p3.setFixedPosition(cx_fecha,cy_fecha,100f);
        document.add(p3);

        //-------------------------PARTE 2----------------------------------

        //4.CANTIDAD
        float cx_uds=convertirCoordenada(144.2f);
        float cy_uds=convertirCoordenada(221.73f);
        int uds=albaran.getProducto().getUnidades();
        Paragraph p4=new Paragraph(String.valueOf(uds));
        p4.setFontSize(8);
        p4.setFixedPosition(cx_uds,cy_uds,100f);
        document.add(p4);

        //5.PRECIO
        float cx_precio=convertirCoordenada(160.1f);
        float cy_precio=convertirCoordenada(221.73f);
        float precio=albaran.getProducto().getPrecio_producto();
        Paragraph p5=new Paragraph(precio+" €");
        p5.setFontSize(8);
        p5.setFixedPosition(cx_precio,cy_precio,100f);
        document.add(p5);

        //6.IVA
        float cx_iva=convertirCoordenada(180.0f);
        float cy_iva=convertirCoordenada(221.73f);
        float iva=albaran.getProducto().getPrecio_IVA();
        Paragraph p6=new Paragraph(iva+" €");
        p6.setFontSize(8);
        p6.setFixedPosition(cx_iva,cy_iva,100f);
        document.add(p6);

        //7.TOTAL
        float cx_total=convertirCoordenada(197.7f);
        float cy_total=convertirCoordenada(221.73f);
        float total=albaran.getProducto().getPrecio_total();
        Paragraph p7=new Paragraph(total+" €");
        p7.setFontSize(8);
        p7.setFixedPosition(cx_total,cy_total,100f);
        document.add(p7);
        document.close();
        pdfDoc.close();
    }

    /**
     * Guardador utilizado cuando hay más de un tipo de catéter
     * Escribe toda la info del catéter de tipo lumbar pero no el total del final
     * @param factura factura
     * @param rutaAcarpeta ruta
     * @param hospital hospital
     * @throws IOException
     */
    public void Factura2PDF_special(Factura factura, String rutaAcarpeta, Hospital hospital) throws IOException {
        String templateFile =hospital.getPlantilla();
        String outputFile = rutaAcarpeta+"\\Archivos\\"+this.nombre_Archivo;

        // Create a PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(outputFile));
        Document document = new Document(pdfDoc);

        //-----------------PARTE 1-----------------------------------------
        //1. NÚMERO DE PEDIDO
        float cx_pedido=convertirCoordenada(58.34f);
        float cy_pedido=convertirCoordenada(273.0f);
        String num_pedido = String.valueOf(factura.getNumero_pedido());
        Paragraph p1=new Paragraph(num_pedido);
        p1.setFontSize(10);
        p1.setFixedPosition(cx_pedido,cy_pedido, 100f);
        document.add(p1);

        //2. CÓDIGO FACTURA
        float cx_codigo=convertirCoordenada(182.2f);
        float cy_codigo=convertirCoordenada(291.95f);
        String codigo=factura.getCodigo_factura();
        Paragraph p2=new Paragraph(codigo);
        p2.setFontSize(10);
        p2.setFixedPosition(cx_codigo,cy_codigo,100f);
        document.add(p2);

        //3.FECHA
        float cx_fecha=convertirCoordenada(37.2f);
        float cy_fecha=convertirCoordenada(260.0f);
        LocalDate fecha=factura.getFecha();
        Paragraph p3=new Paragraph(fecha.toString());
        p3.setFontSize(10);
        p3.setFixedPosition(cx_fecha,cy_fecha,100f);
        document.add(p3);

        //4. ALBARÁN/FECHA DE ENTREGA
        float cx_codigoF=convertirCoordenada(58.0f);
        float cy_codigoF=convertirCoordenada(260.0f);
        String codigoF="A2024-"+ Utils.generarNumAleatorio2();
        Paragraph p4=new Paragraph(codigoF);
        p4.setFontSize(10);
        p4.setFixedPosition(cx_codigoF,cy_codigoF,100f);
        document.add(p4);

        float cx_fechaE=convertirCoordenada(84.1f);
        float cy_fechaE=convertirCoordenada(260.0f);
        LocalDate fecha_entrega=factura.getFecha_entrega();
        Paragraph p=new Paragraph(fecha_entrega.toString());
        p.setFontSize(10);
        p.setFixedPosition(cx_fechaE,cy_fechaE,100f);
        document.add(p);

        //-------------------------PARTE 2----------------------------------

        //5.CANTIDAD
        float cx_uds=convertirCoordenada(144.2f);
        float cy_uds=convertirCoordenada(221.73f);
        int uds=factura.getProducto().getUnidades();
        Paragraph p5=new Paragraph(String.valueOf(uds));
        p5.setFontSize(8);
        p5.setFixedPosition(cx_uds,cy_uds,100f);
        document.add(p5);

        //6.PRECIO
        float cx_precio=convertirCoordenada(160.1f);
        float cy_precio=convertirCoordenada(221.73f);
        float precio=factura.getProducto().getPrecio_producto();
        Paragraph p6=new Paragraph(precio+" €");
        p6.setFontSize(8);
        p6.setFixedPosition(cx_precio,cy_precio,100f);
        document.add(p6);

        //7.IVA
        float cx_iva=convertirCoordenada(180.0f);
        float cy_iva=convertirCoordenada(221.73f);
        float iva=factura.getProducto().getPrecio_IVA();
        Paragraph p7=new Paragraph(iva+" €");
        p7.setFontSize(8);
        p7.setFixedPosition(cx_iva,cy_iva,100f);
        document.add(p7);

        //8.TOTAL
        float cx_total=convertirCoordenada(197.7f);
        float cy_total=convertirCoordenada(221.73f);
        float total=factura.getProducto().getPrecio_total();
        Paragraph p8=new Paragraph(total+" €");
        p8.setFontSize(8);
        p8.setFixedPosition(cx_total,cy_total,100f);
        document.add(p8);

        document.close();
        pdfDoc.close();
    }

    /**
     * Añade el precio/info del catéter cervical a la factura/albarán creada
     * @param producto paso por referencia el catéter lumbar para obtener su precio y calcular el total
     * @param hospital de la plantilla
     * @param rutaArchivo ruta al archivo generado
     */

    public static void AnadirCateterCervical(Producto producto, Hospital hospital, String rutaArchivo, int uds) throws IOException, InterruptedException {
        producto.setTipo(TipoProducto.CERVICAL);
        String descripcion = "CAX18G09C CATETER EPIDUROLISIS AXON 18G 9CM C/AGUJA";
        float precio_cateter_cervical = precioXHospital(hospital);
        float iva = (float) (0.21 * precio_cateter_cervical);
        float total = precio_cateter_cervical + iva;
        String codigo = codigoXHospital(hospital);

        String templateFile = rutaArchivo;
        String outputFile =rutaArchivo.replace(".pdf","_new.pdf");


        // Retry mechanism
        int maxRetries = 3;
        int retryDelay = 5000; // 5 seconds

        for (int i = 0; i < maxRetries; i++) {
            try {
                if (Files.isWritable(Paths.get(rutaArchivo))) {
                    try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(outputFile));
                         Document document = new Document(pdfDoc)) {
                        // Código
                        float cx_codigo = convertirCoordenada(40.0f);
                        float cy_codigo = convertirCoordenada(217.3f);
                        Paragraph p = new Paragraph(codigo);
                        p.setFontSize(7);
                        p.setFixedPosition(cx_codigo, cy_codigo, 100f);
                        document.add(p);

                        // Descripción
                        float cx_descripcion = convertirCoordenada(54.9f);
                        float cy_descripcion = convertirCoordenada(217.3f);
                        Paragraph p1 = new Paragraph(descripcion);
                        p1.setFontSize(7);
                        p1.setFixedPosition(cx_descripcion, cy_descripcion, 250f);
                        document.add(p1);

                        // Unidades
                        float cx_uds = convertirCoordenada(144.2f);
                        float cy_uds = convertirCoordenada(217.3f);
                        Paragraph p2 = new Paragraph(String.valueOf(uds));
                        p2.setFontSize(8);
                        p2.setFixedPosition(cx_uds, cy_uds, 100f);
                        document.add(p2);

                        // Precio
                        float cx_precio = convertirCoordenada(160.1f);
                        float cy_precio = convertirCoordenada(217.3f);
                        Paragraph p3 = new Paragraph(precio_cateter_cervical + " €");
                        p3.setFontSize(8);
                        p3.setFixedPosition(cx_precio, cy_precio, 100f);
                        document.add(p3);

                        // IVA
                        float cx_iva = convertirCoordenada(180.0f);
                        float cy_iva = convertirCoordenada(217.3f);
                        Paragraph p4 = new Paragraph(iva + " €");
                        p4.setFontSize(8);
                        p4.setFixedPosition(cx_iva, cy_iva, 100f);
                        document.add(p4);

                        // Total
                        float cx_total = convertirCoordenada(197.7f);
                        float cy_total = convertirCoordenada(217.3f);
                        Paragraph p5 = new Paragraph(total + " €");
                        p5.setFontSize(8);
                        p5.setFixedPosition(cx_total, cy_total, 100f);
                        document.add(p5);

                        //---------------------------PARTE 3----------------------------

                        float precio2 = producto.getPrecio_total() + precio_cateter_cervical;

                        // 8. TOTAL2-> precio producto
                        float cx_total2 = convertirCoordenada(43.5f);
                        float cy_total2 = convertirCoordenada(88.5f);
                        Paragraph p8 = new Paragraph(precio2 + " €");
                        p8.setFontSize(9);
                        p8.setFixedPosition(cx_total2, cy_total2, 100f);
                        document.add(p8);

                        // 9. BASE IVA->
                        float cx_baseIVA = convertirCoordenada(78.0f);
                        float cy_baseIVA = convertirCoordenada(88.5f);
                        Paragraph p9 = new Paragraph(precio2 + " €");
                        p9.setFontSize(9);
                        p9.setFixedPosition(cx_baseIVA, cy_baseIVA, 100f);
                        document.add(p9);

                        // 10. IMP IVA-> precio con el iva 21% del precio 2
                        float iva2 = (float) (0.21 * precio2);
                        float cx_impIVA = convertirCoordenada(119.0f);
                        float cy_impIVA = convertirCoordenada(88.5f);
                        Paragraph p10 = new Paragraph(iva2 + " €");
                        p10.setFontSize(9);
                        p10.setFixedPosition(cx_impIVA, cy_impIVA, 100f);
                        document.add(p10);

                        // 11. TOTAL-> producto+iva
                        float total_final = precio2 + iva2;
                        float cx_total3 = convertirCoordenada(184.3f);
                        float cy_total3 = convertirCoordenada(88.5f);
                        Paragraph p11 = new Paragraph(total_final + " €");
                        p11.setFontSize(9);
                        p11.setFixedPosition(cx_total3, cy_total3, 100f);
                        document.add(p11);

                        document.close();


                        }
                        break; // Exit loop on success
                    } else {
                        throw new IOException("File is currently locked or in use.");
                    }
            } catch (IOException e) {
                if (i < maxRetries - 1) {
                        System.err.println("File is locked, retrying in " + retryDelay + "ms...");
                        Thread.sleep(retryDelay);
                } else {
                        throw new IOException("Failed to access the file after " + maxRetries + " attempts.", e);
                }
            }
        }
        System.out.println("Archivo generado con éxito");
        Path path = Paths.get(rutaArchivo);
        Files.delete(path);

    }




    /**
     * Convierte milimetros a puntos (PDF usa puntos)
     * @param coordenada en mm
     * @return coordenadas pasadas a puntos del PDF
     */
    public static float convertirCoordenada(float coordenada){
        return coordenada*72f/25.4f;
    }

    public static float precioXHospital(Hospital hospital){

        float precio;

        switch(hospital.getNombre()){
            case "QUIRON SALUD TOLEDO":
                precio=105.0f;
                break;
            case "HOSPITAL UNIVERSITARIO REY JUAN CARLOS":
                precio=84.0f;
                break;

            case "HOSPITAL SAN RAFAEL":
                precio=94.5f;
                break;

            default:
                precio=hospital.getPrecio();

        }
        return precio;
    }

    public static String codigoXHospital(Hospital hospital){

        String codigo;
        switch(hospital.getNombre()){

            case "HOSPITAL UNIVERSITARIO REY JUAN CARLOS":
                codigo="MS01027910";
                break;
            case "HOSPITAL SAN RAFAEL":
                codigo="CCA7907";
                break;
            default:
               codigo="CAX18G9C";

        }
        return codigo;
    }

    public String getNombre_Archivo() {
        return nombre_Archivo;
    }


}




