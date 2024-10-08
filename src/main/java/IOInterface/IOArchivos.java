package IOInterface;

import Epidurolisis.Archivos.Albaran;
import Epidurolisis.Archivos.Archivo;
import Epidurolisis.Archivos.Factura;
import Epidurolisis.Caracteristicas_Producto.Hospital.Hospital;
import Epidurolisis.Caracteristicas_Producto.TipoProducto;
import Epidurolisis.Producto;
import Excepciones.NotValidCodeException;
import Guardador.Guardador;
import Guardador.LecturaPDF;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class IOArchivos {


    //CASE 1 y CASE 2
    /**
     * Crea un archivo de tipo albaran o de tipo factura dependiendo de lo que se pida
     * @param tipo tipo de archivo que se quiere crear
     * @param rutaAcarpeta ruta en donde se va a crear
     */
    public static void crearArchivo(int tipo, String rutaAcarpeta){

        if(tipo==1){
            System.out.println("Seleccione el hospital para el que se va a crear el archivo: ");
            int opcion=IOArchivos.mostrarListaHospitales();
            Hospital hospital=seleccionarHospital(opcion, "Albaranes",rutaAcarpeta);

            System.out.println("Introduzca el número de pedido: ");
            System.out.println("Pulse ENTER si no desea introducir nada.");
            long num_pedido=Utils.leerLong();

            System.out.println("Introduzca la fecha de realización del albarán (dia/mes/ano) ");
            LocalDate fecha=Utils.leerFecha();


            System.out.println("Introduzca el número de expediente: ");
            System.out.println("Pulse la tecla ENTER si no desea introducir nada.");
            String num_expediente=Utils.leerString();

            System.out.println("¿Desea realizar alguna observación?");
            System.out.println("\t1.Si");
            System.out.println("\t2. No");
            String observacion;
            int opcion_observacion=Utils.leerEntero();
            if(opcion_observacion==1){
                System.out.println("Introduzca el texto: ");
                observacion=Utils.leerString();
            }else{
                observacion="";
            }

            System.out.println("Cuántas unidades desea el cliente del catéter de epidurolisis lumbar? ");
            int uds=Utils.leerEntero();

            Producto producto=new Producto(hospital,uds, TipoProducto.LUMBAR);

            Archivo a=new Archivo(num_pedido,fecha,num_expediente,producto, observacion);
            Albaran albaran=IOArchivos.leerInfoAlbaran(a);

            System.out.println("Desea anadir otro tipo de catéter? (Cervical-18G)");
            System.out.println("\t1. Si");
            System.out.println("\t2. No");

            int cateter_cervical=Utils.leerEntero();

            IOArchivos.anadirOtroCateterAalbaran(albaran,hospital, rutaAcarpeta, cateter_cervical);

        }
        if(tipo==2){
            System.out.println("Seleccione el hospital para el que se va a crear el archivo: ");
            int opcion=IOArchivos.mostrarListaHospitales();
            Hospital hospital=seleccionarHospital(opcion, "Facturas",rutaAcarpeta);

            System.out.println("Introduzca el número de pedido: ");
            System.out.println("Pulse ENTER si no desea introducir nada.");
            long num_pedido=Utils.leerLong();

            System.out.println("Introduzca la fecha de realización de la factura (dia/mes/ano) ");
            LocalDate fecha=Utils.leerFecha();

            System.out.println("¿Desea realizar alguna observación?");
            System.out.println("\t1.Si");
            System.out.println("\t2. No");
            String observacion;
            int opcion_observacion=Utils.leerEntero();
            if(opcion_observacion==1){
                System.out.println("Introduzca el texto: ");
                observacion=Utils.leerString();
            }else{
                observacion="";
            }

            System.out.println("Cuántas unidades desea el cliente del catéter de epidurolisis lumbar? ");
            int uds=Utils.leerEntero();

            Producto producto=new Producto(hospital,uds, TipoProducto.LUMBAR);

            Archivo a=new Archivo(num_pedido,fecha,"",producto, observacion);
            Factura factura =IOArchivos.leerInfoFactura(a);

            System.out.println("Desea anadir otro tipo de catéter? (Cervical-18G)");
            System.out.println("\t1. Si");
            System.out.println("\t2. No");

            int cateter_cervical=Utils.leerEntero();

            IOArchivos.anadirOtroCateterAfactura(factura,hospital,rutaAcarpeta, cateter_cervical);
        }
    }

    public static void anadirOtroCateterAalbaran(Albaran albaran, Hospital hospital, String rutaAcarpeta, int cateter_cervical){


        Guardador guardador=new Guardador(albaran, hospital);


        if(cateter_cervical==1){//se preguntan los datos del siguiente
            System.out.println("Cuántas unidades del producto desea? ");
            int uds_cervical=Utils.leerEntero();
            try {
                guardador.Albaran2PDF_special(albaran,rutaAcarpeta,hospital, uds_cervical);
                System.out.println("Albarán generado con éxito.");
                File archivo=new File(guardador.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{//se guarda normal
            try {
                guardador.Albaran2PDF(albaran,rutaAcarpeta,hospital);
                System.out.println("Albarán generado con éxito.");
                File archivo=new File(guardador.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public static void anadirOtroCateterAfactura(Factura factura, Hospital hospital, String rutaAcarpeta, int cateter_cervical){


        Guardador guardador=new Guardador(factura, hospital);


        if(cateter_cervical==1){//se preguntan los datos del siguiente

            try {
                System.out.println("Cuántas unidades del producto desea? ");
                int uds_cervical=Utils.leerEntero();
                guardador.Factura2PDF_special(factura,rutaAcarpeta,hospital, uds_cervical);
                System.out.println("Factura generada con éxito.");
                File archivo=new File(guardador.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{//se guarda normal
            try {
                guardador.Factura2PDF(factura,rutaAcarpeta,hospital);
                System.out.println("Factura generada con éxito.");
                File archivo=new File(guardador.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    //CASE 3

    /**
     * crearAlbaranyFactura()
     * Crea ambos factura y albarán
     */
    public static void crearAlbaranyFactura(String rutaAcarpeta) {

        System.out.println("Seleccione el hospital para el que se van a crear los archivos: ");
        int opcion=IOArchivos.mostrarListaHospitales();
        Hospital hospital=seleccionarHospital(opcion, "Albaranes", rutaAcarpeta);


        System.out.println("Introduzca el número de pedido: ");
        System.out.println("Pulse ENTER si no desea introducir nada.");
        long num_pedido=Utils.leerLong();

        System.out.println("Introduzca la fecha de realización del archivo (dia/mes/ano) ");
        LocalDate fecha=Utils.leerFecha();

        System.out.println("Introduzca el número de expediente: ");
        System.out.println("Pulse la tecla de ENTER si no desea introducir nada.");
        String num_expediente=Utils.leerString();


        System.out.println("¿Desea realizar alguna observación?");
        System.out.println("\t1.Si");
        System.out.println("\t2. No");
        String observacion;
        int opcion_observacion=Utils.leerEntero();
        if(opcion_observacion==1){
            System.out.println("Introduzca el texto: ");
            observacion=Utils.leerString();
        }else{
            observacion="";
        }

        System.out.println("Cuántas unidades desea el cliente del catéter de epidurolisis lumbar? ");
        int uds=Utils.leerEntero();


        Producto producto=new Producto(hospital,uds, TipoProducto.LUMBAR);

        Archivo a=new Archivo(num_pedido,fecha,num_expediente,producto, observacion);

        System.out.println("ALBARÁN Y FACTURA: ");
        System.out.println("Introduzca el código del archivo: ");
        int codigo=Utils.leerEntero();


        Albaran albaran;
        try {
            albaran = new Albaran(a.getNumero_pedido(),codigo,a.getFecha(),a.getNumero_expediente(),a.getProducto(), a.getObservacion());
        } catch (NotValidCodeException e) {
            throw new RuntimeException(e);
        }
        
        

        Factura factura=new Factura(a.getNumero_pedido(),codigo,a.getFecha_entrega(),a.getProducto(), a.getObservacion());


        System.out.println("Desea anadir otro tipo de catéter? (Cervical-18G)");
        System.out.println("\t1. Si");
        System.out.println("\t2. No");

        int cateter_cervical=Utils.leerEntero();

        IOArchivos.anadirOtroCateterAambos(albaran, factura,hospital,rutaAcarpeta,cateter_cervical);

    }

    /** anadirOtroCateterAambos()
     * Guarda de una forma u otra dependiendo de si el cliente solicita 1 o ambos tipos de catéter
     *
     * @param albaran albarán
     * @param factura factura
     * @param hospital hospital
     * @param rutaAcarpeta ruta a la carpeta
     * @param cateter_cervical opción-> si se va a añadir otro tipo de catéter o no
     */
    public static void anadirOtroCateterAambos(Albaran albaran,Factura factura, Hospital hospital, String rutaAcarpeta, int cateter_cervical){

        if(cateter_cervical==1){//se preguntan los datos del siguiente

            try {
                Guardador guardador1=new Guardador(albaran,hospital);
                System.out.println("Cuántas unidades del producto desea? ");
                int uds_cervical=Utils.leerEntero();
                guardador1.Albaran2PDF_special(albaran,rutaAcarpeta,hospital,uds_cervical);
                System.out.println("Albarán generado con éxito.");
                File archivo1=new File(guardador1.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo1);

                hospital.setTipoArchivo("Facturas");
                
                Guardador guardador2=new Guardador(factura, hospital);
                guardador2.Factura2PDF_special(factura,rutaAcarpeta,hospital, uds_cervical);
                System.out.println("Factura generada con éxito.");
                File archivo2=new File(guardador2.getOutputFile());
                Desktop desktop2=Desktop.getDesktop();
                desktop2.open(archivo2);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{//se guarda normal
            try {
                Guardador guardador1=new Guardador(albaran,hospital);
                guardador1.Albaran2PDF(albaran,rutaAcarpeta,hospital);
                System.out.println("Albarán generado con éxito.");
                File archivo=new File(guardador1.getOutputFile());
                Desktop desktop=Desktop.getDesktop();
                desktop.open(archivo);

                hospital.setTipoArchivo("Facturas");
                Guardador guardador2=new Guardador(albaran,hospital);
                guardador2.Factura2PDF(factura,rutaAcarpeta,hospital);
                System.out.println("Factura generada con éxito.");
                File archivo2=new File(guardador2.getOutputFile());
                Desktop desktop2=Desktop.getDesktop();
                desktop2.open(archivo2);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }



    /**
     * leerInfoAlbaran()
     * Recoge la información necesaria para generar un albaran
     *
     * @return archivo de tipo albaran
     */
    public static Albaran leerInfoAlbaran(Archivo a) {
        System.out.println("Introduzca el código del albarán (A2024-CÓDIGO): ");
        int codigo=Utils.leerEntero();

        try {
            return new Albaran(a.getNumero_pedido(),codigo,a.getFecha(),a.getNumero_expediente(),a.getProducto(), a.getObservacion());
        } catch (NotValidCodeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * leerInfoFactura()
     * Recoge la información necesaria para generar una factura
     *
     * @return archivo de tipo factura
     */
    public static Factura leerInfoFactura(Archivo a) {
        System.out.println("Introduzca el codigo de la factura(FRA2024-CÓDIGO): ");
        int codigo=Utils.leerEntero();

        return new Factura(a.getNumero_pedido(),codigo,a.getFecha_entrega(),a.getProducto(), a.getObservacion());
    }


    /**
     * mostrarListaHospitales()
     * muestra la lista de todos los hospitales de los cuales de debe seleccionar uno para generar la factura
     */
    public static int mostrarListaHospitales(){
        int opcion;
        System.out.println("-----------------------------HOSPITALES-------------------------");
        System.out.println("\t1.CEMTRO");
        System.out.println("\t2.Cimeg");
        System.out.println("\t3.Clinico San Carlos");
        System.out.println("\t4.Cun");
        System.out.println("\t5.HM Málaga");
        System.out.println("\t6.Imd");
        System.out.println("\t7.Infanta Elena");
        System.out.println("\t8.Jantromed");
        System.out.println("\t9.M.D Anderson");
        System.out.println("\t10.Móstoles");
        System.out.println("\t11.Puerta de Hierro");
        System.out.println("\t12.Quiron");
        System.out.println("\t13.Rey Juan Carlos");
        System.out.println("\t14.San Rafael");
        System.out.println("\t15.Sanitas");
        System.out.println("\t16.Santa Cruz");
        System.out.println("\t17.Severo Ochoa");
        System.out.println("\t18.Torrejón");
        System.out.println("\t19.Quirón Toledo");
        System.out.println("\t20.Vithas");
        System.out.println("\t21.Salir");

        do{
            System.out.println("Introduzca opcion del menu: ");
            opcion = Utils.leerEntero();
        }while(opcion < 0 || opcion > 21);
        return opcion;
    }
    
    public static Hospital seleccionarHospital(int opcion, String tipoArchivo, String rutaAcarpeta){
        Hospital hospital = new Hospital(tipoArchivo, rutaAcarpeta);
        switch(opcion){
            case 1:
                hospital.hospital_CEMTRO();
                break;
            case 2:
                hospital.hospital_Cimeg();
                break;
            case 3:
                hospital.hospital_ClinicoSanCarlos();
                break;
            case 4:
                hospital.hospital_Cun();
                break;
            case 5:
                hospital.hospital_HM_Malaga();
                break;
            case 6:
                hospital.hospital_Imd();
                break;
            case 7:
                hospital.hospital_InfantaElena();
                break;
            case 8:
                hospital.hospital_Jantromed();
                break;
            case 9:
                hospital.hospital_MDAnderson();
                break;
            case 10:
                hospital.hospital_Mostoles();
                break;
            case 11:
                hospital.hospital_PuertaDeHierro();
                break;
            case 12:
                hospital.hospital_Quiron();
                break;
            case 13:
                hospital.hospital_ReyJuanCarlos();
                break;
            case 14:
                hospital.hospital_SanRafael();
                break;
            case 15:
                hospital.hospital_Sanitas();
                break;
            case 16:
                hospital.hospital_SantaCruz();
                break;
            case 17:
                hospital.hospital_SeveroOchoa();
                break;
            case 18:
                hospital.hospital_Torrejon();
                break;
            case 19:
                hospital.hospital_QuironToledo();
                break;
            case 20:
                hospital.hospital_Vithas();
                break;
        }
        return hospital;
    }

    //CASE 4 y 5-MODIFICAR ARCHIVOS

    public static void modificarAlbaran(String rutaAcarpeta){

        System.out.println("RECUERDE QUE TODOS LOS ARCHIVOS DEBEN ESTAR CERRADOS PARA PODER REALIZAR ALGÚN TIPO DE MODIFICACIÓN.");
        System.out.println("Qué albaran desea modificar. Introduzca su código: ");
        int codigo=Utils.leerEntero();

        System.out.println("Introduzca la fecha en la que fue creado el albarán: ");
        System.out.println("Año: ");
        int ano=Utils.leerEntero();
        System.out.println("Mes: ");
        int num_mes=Utils.leerEntero();
        Month mes= Month.of(num_mes);

        System.out.println("Seleccione el hospital del que desea cambiar el albarán.");
        int opcion1=IOArchivos.mostrarListaHospitales();
        Hospital hospital=seleccionarHospital(opcion1,"Albaranes",rutaAcarpeta);

        String nombre_albaran="A"+ano+"-" + codigo + "_" + hospital.getNombre()+".pdf";

        String trimestre=Archivo.obtenerTrimestre(mes);
        String str_ano= String.valueOf(ano);
        String anio = str_ano.substring(str_ano.length() - 2);//obtiene los dos últimos dígitos del año
        String mes_carpeta=Archivo.obtenerMesCarpeta(mes,Integer.parseInt(anio));
        //ruta a contabilidad+año+"AÑO"+FACTURAS EMITIDAS +año+trimestre+nombre_archivo
        String rutaArchivo=rutaAcarpeta+"\\"+ano+" AÑO\\FACTURAS EMITIDAS "+ano+"\\"+trimestre+"\\"+mes_carpeta+"\\"+nombre_albaran;
        Albaran albaran= LecturaPDF.AlbaranFromPDF(rutaArchivo, hospital);
        albaran.setCodigo_albaran(codigo, ano);

        System.out.println("Qué dato desea modificar? ");
        System.out.println("------------------------------------------------------");
        System.out.println("\t1.Número de pedido");
        System.out.println("\t2.Fecha entrega");
        System.out.println("\t3.Número de expediente");
        System.out.println("\t4.Producto");
        System.out.println("\t5.Salir");
        System.out.println("------------------------------------------------------");
        int opcion2;
        do{
            System.out.println("Introduzca opcion del menu: ");
            opcion2 = Utils.leerEntero();

        }while(opcion2 < 0 || opcion2 > 5);

        switch(opcion2){
            case 1:
                System.out.println("Introduzca el nuevo número de pedido: ");
                int num=Utils.leerEntero();
                albaran.setNumero_pedido(num);
                System.out.println("Número de pedido modificado correctamente. ");
                break;

            case 2:
                System.out.println("Introduzca la nueva fecha de entrega: ");
                LocalDate fecha_nueva=Utils.leerFecha();
                albaran.setFecha_entrega(fecha_nueva);
                System.out.println("Fecha de entrega modificada correctamente. ");
                break;
            case 3:
                System.out.println("Introduzca el nuevo número de expediente: ");
                String num_expediente=Utils.leerString();
                albaran.setNumero_expediente(num_expediente);
            case 4:
                Producto producto=albaran.getProducto();
                System.out.println("Seleccione una opción: \n\n");
                System.out.println("\t1.Unidades");
                System.out.println("\t2.Precio producto");
                System.out.println("\t3.Salir");
                int opcion3=Utils.leerEntero();
                if(opcion3==1){
                    System.out.println("Cuántas unidades del producto desea? ");
                    int uds=Utils.leerEntero();
                    producto.setUnidades(uds);//cambia las unidades

                    float precio_original=producto.getPrecio_producto();
                    producto.setPrecio_producto(precio_original*uds);//cambia el precio del producto
                    producto.setPrecio_IVA(producto.getPrecio_producto()*0.21f);//cambio precio con IVA
                    producto.calcularPrecioTotal(); //cambia precio total

                    System.out.println("Unidades modificadas. Cantidad: "+uds);
                }else if(opcion3==2){
                    System.out.println("Introduzca el nuevo precio del producto: ");
                    int precio_nuevo=Utils.leerEntero();
                    int unidades=producto.getUnidades();
                    producto.setPrecio_producto(precio_nuevo*unidades);//cambia el precio del producto
                    producto.setPrecio_IVA(producto.getPrecio_producto()*0.21f);//cambio precio con IVA
                    producto.calcularPrecioTotal(); //cambia precio total

                    System.out.println("Precio alterado correctamente. ");
                }
                break;
            default:
                break;
        }

        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            //Eliminar el archivo
            if (archivo.delete()) {
                Guardador guardador=new Guardador(albaran,hospital);
                try {
                    guardador.Albaran2PDF(albaran,rutaAcarpeta,hospital);
                    System.out.println("El archivo ha sido modificado exitosamente.");
                    File file=new File(guardador.getOutputFile());
                    Desktop desktop=Desktop.getDesktop();
                    desktop.open(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("No se pudo modificar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }


    }
    public static void modificarFactura(String rutaAcarpeta){

        System.out.println("RECUERDE QUE TODOS LOS ARCHIVOS DEBEN ESTAR CERRADOS PARA PODER REALIZAR ALGÚN TIPO DE MODIFICACIÓN.");
        System.out.println("Qué factura desea modificar. Introduzca su código: ");
        int codigo=Utils.leerEntero();

        System.out.println("Introduzca la fecha en la que fue creada la factura: ");
        System.out.println("Año: ");
        int ano=Utils.leerEntero();
        System.out.println("Mes: ");
        int num_mes=Utils.leerEntero();
        Month mes= Month.of(num_mes);
        String trimestre=Archivo.obtenerTrimestre(mes);
        String str_ano= String.valueOf(ano);
        String anio = str_ano.substring(str_ano.length() - 2);//obtiene los dos últimos dígitos del año
        String mes_carpeta=Archivo.obtenerMesCarpeta(mes,Integer.parseInt(anio));

        System.out.println("Seleccione el hospital del que desea cambiar la factura.");
        int opcion1=IOArchivos.mostrarListaHospitales();
        Hospital hospital=seleccionarHospital(opcion1,"Facturas",rutaAcarpeta);

        String nombre_factura="FRA"+ano+"-" + codigo + "_" + hospital.getNombre()+".pdf";
        String rutaArchivo=rutaAcarpeta+"\\"+ano+" AÑO\\FACTURAS EMITIDAS "+ano+"\\"+trimestre+"\\"+mes_carpeta+"\\"+nombre_factura;

        Factura factura= LecturaPDF.FacturaFromPDF(rutaArchivo,hospital);

        factura.setCodigo_factura(codigo, ano);
        System.out.println("Qué dato desea modificar? ");
        System.out.println("------------------------------------------------------");
        System.out.println("\t1.Número de pedido");
        System.out.println("\t2.Fecha entrega");
        System.out.println("\t3.Producto");
        System.out.println("\t4.Salir");
        System.out.println("------------------------------------------------------");
        int opcion;
        do{
            System.out.println("Introduzca opcion del menu: ");
            opcion = Utils.leerEntero();

        }while(opcion < 0 || opcion > 4);

        switch(opcion){
            case 1:
                System.out.println("Introduzca el nuevo número de pedido: ");
                int num=Utils.leerEntero();
                factura.setNumero_pedido(num);
                System.out.println("Número de pedido modificado correctamente. ");
                break;

            case 2:
                System.out.println("Introduzca la nueva fecha de entrega: ");
                LocalDate fecha_nueva=Utils.leerFecha();
                factura.setFecha_entrega(fecha_nueva);
                System.out.println("Fecha de entrega modificada correctamente. ");
                break;

            case 3:
                Producto producto=factura.getProducto();
                System.out.println("Seleccione una opción: \n\n");
                System.out.println("\t1.Unidades");
                System.out.println("\t2.Precio producto");
                System.out.println("\t3.Salir");
                int opcion2=Utils.leerEntero();
                if(opcion2==1){
                    System.out.println("Cuántas unidades del producto desea? ");
                    int uds=Utils.leerEntero();
                    producto.setUnidades(uds);//cambia las unidades

                    float precio_original=producto.getPrecio_producto();
                    producto.setPrecio_producto(precio_original*uds);//cambia el precio del producto
                    producto.setPrecio_IVA(producto.getPrecio_producto()*0.21f);//cambio precio con IVA
                    producto.calcularPrecioTotal(); //cambia precio total

                    System.out.println("Unidades modificadas. Cantidad: "+uds);
                }else if(opcion2==2){
                    System.out.println("Introduzca el nuevo precio del producto: ");
                    int precio_nuevo=Utils.leerEntero();
                    int unidades=producto.getUnidades();
                    producto.setPrecio_producto(precio_nuevo*unidades);//cambia el precio del producto
                    producto.setPrecio_IVA(producto.getPrecio_producto()*0.21f);//cambio precio con IVA
                    producto.calcularPrecioTotal(); //cambia precio total

                    System.out.println("Precio alterado correctamente. ");
                }
                break;

            default:
                break;
        }
        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            //Eliminar el archivo
            if (archivo.delete()) {
                Guardador guardador=new Guardador(factura,hospital);
                try {
                    guardador.Factura2PDF(factura,rutaAcarpeta,hospital);
                    System.out.println("El archivo ha sido modificado exitosamente.");
                    File file=new File(guardador.getOutputFile());
                    Desktop desktop2=Desktop.getDesktop();
                    desktop2.open(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("No se pudo modificar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }

}
