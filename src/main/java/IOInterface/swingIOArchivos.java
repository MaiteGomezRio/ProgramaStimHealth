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


import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class swingIOArchivos {


    //CASE 1 y CASE 2
    /**
     * Crea un archivo de tipo albaran o de tipo factura dependiendo de lo que se pida
     * @param tipo tipo de archivo que se quiere crear
     * @param rutaAcarpeta ruta en donde se va a crear
     */
	public static void crearArchivo(int tipo, String rutaAcarpeta) {
		if (tipo == 1) {
		    // Seleccionar hospital
		    int opcion = swingIOArchivos.mostrarListaHospitales();
		    Hospital hospital = seleccionarHospital(opcion, "Albaranes", rutaAcarpeta);

		    // Introducir número de pedido
		    String numPedidoStr = JOptionPane.showInputDialog("Introduzca el número de pedido:\n(Pulse ENTER si no desea introducir nada)");
		    long numPedido = numPedidoStr.isEmpty() ? 0 : Long.parseLong(numPedidoStr); // Manejo de caso donde no se introduce número

		    // Introducir fecha 
		  
		    String fechaStr = JOptionPane.showInputDialog("Introduzca la fecha de realización del albarán (dia/mes/año):");
		    LocalDate fecha=Utils.String2LocalDate2(fechaStr);

		    // Introducir número de expediente
		    String numExpediente = JOptionPane.showInputDialog("Introduzca el número de expediente:\n(Pulse ENTER si no desea introducir nada)");

		    // Observaciones
		    String observacion = "";
		    int opcionObservacion = JOptionPane.showConfirmDialog(null, "¿Desea realizar alguna observación?", "Observaciones", JOptionPane.YES_NO_OPTION);
		    if (opcionObservacion == JOptionPane.YES_OPTION) {
		        observacion = JOptionPane.showInputDialog("Introduzca el texto:");
		    }

		    // Unidades del producto
		    String udsStr = JOptionPane.showInputDialog("¿Cuántas unidades desea el cliente del catéter de epidurolisis lumbar?");
		    int uds = Integer.parseInt(udsStr);

		    Producto producto = new Producto(hospital, uds, TipoProducto.LUMBAR);
		    Archivo a = new Archivo(numPedido, fecha, numExpediente, producto, observacion);
		    Albaran albaran = swingIOArchivos.leerInfoAlbaran(a);

		    // Añadir otro catéter
		    int cateterCervical = JOptionPane.showConfirmDialog(null, "¿Desea añadir otro tipo de catéter? (Cervical-18G)", "Añadir Catéter", JOptionPane.YES_NO_OPTION);
		    swingIOArchivos.anadirOtroCateterAalbaran(albaran, hospital, rutaAcarpeta, cateterCervical == JOptionPane.YES_OPTION ? 1 : 2);
		}


        if (tipo == 2) {
            // Seleccionar hospital
            int opcion = swingIOArchivos.mostrarListaHospitales();
            Hospital hospital = seleccionarHospital(opcion, "Facturas", rutaAcarpeta);

            // Introducir número de pedido
            String numPedidoStr = JOptionPane.showInputDialog("Introduzca el número de pedido:\n(Pulse ENTER si no desea introducir nada)");
            long numPedido = numPedidoStr.isEmpty() ? 0 : Long.parseLong(numPedidoStr); // Manejo de caso donde no se introduce número

            // Introducir fecha
            String fechaStr = JOptionPane.showInputDialog("Introduzca la fecha de realización de la factura (dia/mes/año):");
            LocalDate fecha =Utils.String2LocalDate2(fechaStr); 

            // Observaciones
            String observacion = "";
            int opcionObservacion = JOptionPane.showConfirmDialog(null, "¿Desea realizar alguna observación?", "Observaciones", JOptionPane.YES_NO_OPTION);
            if (opcionObservacion == JOptionPane.YES_OPTION) {
                observacion = JOptionPane.showInputDialog("Introduzca el texto:");
            }

            // Unidades del producto
            String udsStr = JOptionPane.showInputDialog("¿Cuántas unidades desea el cliente del catéter de epidurolisis lumbar?");
            int uds = Integer.parseInt(udsStr);

            Producto producto = new Producto(hospital, uds, TipoProducto.LUMBAR);
            Archivo a = new Archivo(numPedido, fecha, "", producto, observacion);
            Factura factura = swingIOArchivos.leerInfoFactura(a);

            // Añadir otro catéter
            int cateterCervical = JOptionPane.showConfirmDialog(null, "¿Desea añadir otro tipo de catéter? (Cervical-18G)", "Añadir Catéter", JOptionPane.YES_NO_OPTION);
            swingIOArchivos.anadirOtroCateterAfactura(factura, hospital, rutaAcarpeta, cateterCervical == JOptionPane.YES_OPTION ? 1 : 2);
        }
    }


	public static void anadirOtroCateterAalbaran(Albaran albaran, Hospital hospital, String rutaAcarpeta, int cateter_cervical) {
	    Guardador guardador = new Guardador(albaran, hospital);

	    if (cateter_cervical == 1) { // se preguntan los datos del siguiente
	        // Pedir unidades del nuevo catéter
	        String udsCervicalStr = JOptionPane.showInputDialog("¿Cuántas unidades del producto desea?");
	        int uds_cervical = Integer.parseInt(udsCervicalStr); // Convertir la entrada a entero

	        try {
	            // Generar Albarán con el nuevo catéter
	            guardador.Albaran2PDF_special(albaran, rutaAcarpeta, hospital, uds_cervical);
	            JOptionPane.showMessageDialog(null, "Albarán generado con éxito.");

	            // Abrir el archivo generado automáticamente
	            File archivo = new File(guardador.getOutputFile());
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(archivo);

	        } catch (IOException e) {
	            // Mostrar error si ocurre
	            JOptionPane.showMessageDialog(null, "Error al generar el Albarán: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }

	    } else { // Se guarda normal
	        try {
	            // Guardar albarán normalmente
	            guardador.Albaran2PDF(albaran, rutaAcarpeta, hospital);
	            JOptionPane.showMessageDialog(null, "Albarán generado con éxito.");

	            // Abrir el archivo generado automáticamente
	            File archivo = new File(guardador.getOutputFile());
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(archivo);

	        } catch (IOException e) {
	            // Mostrar error si ocurre
	            JOptionPane.showMessageDialog(null, "Error al generar el Albarán: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}


    
	public static void anadirOtroCateterAfactura(Factura factura, Hospital hospital, String rutaAcarpeta, int cateter_cervical) {
	    Guardador guardador = new Guardador(factura, hospital);

	    if (cateter_cervical == 1) { // se preguntan los datos del siguiente
	        // Pedir unidades del nuevo catéter
	        String udsCervicalStr = JOptionPane.showInputDialog("¿Cuántas unidades del producto desea?");
	        int uds_cervical = Integer.parseInt(udsCervicalStr); // Convertir la entrada a entero

	        try {
	            // Generar Factura con el nuevo catéter
	            guardador.Factura2PDF_special(factura, rutaAcarpeta, hospital, uds_cervical);
	            JOptionPane.showMessageDialog(null, "Factura generada con éxito.");

	            // Abrir el archivo generado automáticamente
	            File archivo = new File(guardador.getOutputFile());
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(archivo);

	        } catch (IOException e) {
	            // Mostrar error si ocurre
	            JOptionPane.showMessageDialog(null, "Error al generar la Factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }

	    } else { // Se guarda normal
	        try {
	            // Guardar factura normalmente
	            guardador.Factura2PDF(factura, rutaAcarpeta, hospital);
	            JOptionPane.showMessageDialog(null, "Factura generada con éxito.");

	            // Abrir el archivo generado automáticamente
	            File archivo = new File(guardador.getOutputFile());
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(archivo);

	        } catch (IOException e) {
	            // Mostrar error si ocurre
	            JOptionPane.showMessageDialog(null, "Error al generar la Factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

    //CASE 3

    /**
     * crearAlbaranyFactura()
     * Crea ambos factura y albarán
     */
	public static void crearAlbaranyFactura(String rutaAcarpeta) {
	    // Mostrar lista de hospitales en un cuadro de diálogo
	    int opcion = swingIOArchivos.mostrarListaHospitales();
	    Hospital hospital = seleccionarHospital(opcion, "Albaranes", rutaAcarpeta);

	    // Solicitar número de pedido
	    String numPedidoStr = JOptionPane.showInputDialog("Introduzca el número de pedido (o presione ENTER si no desea introducir nada):");
	    long num_pedido = numPedidoStr.isEmpty() ? 0 : Long.parseLong(numPedidoStr);

	    // Solicitar fecha de realización
	    String fechaStr = JOptionPane.showInputDialog("Introduzca la fecha de realización del archivo (dia/mes/ano):");
	    LocalDate fecha = Utils.String2LocalDate2(fechaStr);  

	    // Solicitar número de expediente
	    String num_expediente = JOptionPane.showInputDialog("Introduzca el número de expediente (o presione ENTER si no desea introducir nada):");

	    // Preguntar si desea hacer una observación
	    int opcion_observacion = JOptionPane.showConfirmDialog(null, "¿Desea realizar alguna observación?", "Observación", JOptionPane.YES_NO_OPTION);
	    String observacion = "";
	    if (opcion_observacion == JOptionPane.YES_OPTION) {
	        observacion = JOptionPane.showInputDialog("Introduzca el texto de la observación:");
	    }

	    // Solicitar cuántas unidades desea el cliente del catéter lumbar
	    String udsStr = JOptionPane.showInputDialog("¿Cuántas unidades desea el cliente del catéter de epidurolisis lumbar?");
	    int uds = Integer.parseInt(udsStr);

	    // Crear producto con la información ingresada
	    Producto producto = new Producto(hospital, uds, TipoProducto.LUMBAR);

	    // Crear archivo con los datos
	    Archivo a = new Archivo(num_pedido, fecha, num_expediente, producto, observacion);

	    // Solicitar el código del archivo
	    String codigoStr = JOptionPane.showInputDialog("Introduzca el código del archivo:");
	    int codigo = Integer.parseInt(codigoStr);

	    // Crear Albarán y Factura
	    Albaran albaran;
	    try {
	        albaran = new Albaran(a.getNumero_pedido(), codigo, a.getFecha(), a.getNumero_expediente(), a.getProducto(), a.getObservacion());
	    } catch (NotValidCodeException e) {
	        JOptionPane.showMessageDialog(null, "Error: Código no válido", "Error", JOptionPane.ERROR_MESSAGE);
	        throw new RuntimeException(e);
	    }

	    Factura factura = new Factura(a.getNumero_pedido(), codigo, a.getFecha_entrega(), "", a.getProducto(), a.getObservacion());

	    // Preguntar si se desea agregar otro tipo de catéter
	    int cateter_cervical = JOptionPane.showConfirmDialog(null, "¿Desea añadir otro tipo de catéter? (Cervical-18G)", "Añadir catéter", JOptionPane.YES_NO_OPTION);

	    // Llamar a la función para añadir catéter a ambos
	    swingIOArchivos.anadirOtroCateterAambos(albaran, factura, hospital, rutaAcarpeta, cateter_cervical == JOptionPane.YES_OPTION ? 1 : 2);

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
	public static void anadirOtroCateterAambos(Albaran albaran, Factura factura, Hospital hospital, String rutaAcarpeta, int cateter_cervical) {

	    if (cateter_cervical == 1) { // Si se van a preguntar los datos del siguiente catéter
	        try {
	            Guardador guardador1 = new Guardador(albaran, hospital);

	            // Preguntar cuántas unidades del producto desea
	            String udsStr = JOptionPane.showInputDialog("¿Cuántas unidades del producto desea?");
	            int uds_cervical = Integer.parseInt(udsStr);

	            // Generar el Albarán PDF
	            guardador1.Albaran2PDF_special(albaran, rutaAcarpeta, hospital, uds_cervical);
	            File archivo1 = new File(guardador1.getOutputFile());
	            Desktop.getDesktop().open(archivo1); // Abre el archivo generado

	            // Establecer tipo de archivo a "Facturas"
	            hospital.setTipoArchivo("Facturas");

	            // Generar la Factura PDF
	            Guardador guardador2 = new Guardador(factura, hospital);
	            guardador2.Factura2PDF_special(factura, rutaAcarpeta, hospital, uds_cervical);
	            File archivo2 = new File(guardador2.getOutputFile());
	            Desktop.getDesktop().open(archivo2); // Abre el archivo generado
	            
	            // Mensaje de éxito
	    	    JOptionPane.showMessageDialog(null, "Albarán y Factura generados con éxito.");
	    	    
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Error al generar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }

	    } else { // Si se guarda normalmente
	        try {
	            Guardador guardador1 = new Guardador(albaran, hospital);
	            guardador1.Albaran2PDF(albaran, rutaAcarpeta, hospital);
	            File archivo = new File(guardador1.getOutputFile());
	            Desktop.getDesktop().open(archivo); // Abre el archivo generado

	            // Establecer tipo de archivo a "Facturas"
	            hospital.setTipoArchivo("Facturas");
	            

	            // Generar la Factura PDF
	            Guardador guardador2 = new Guardador(factura, hospital);
	            guardador2.Factura2PDF(factura, rutaAcarpeta, hospital);
	            File archivo2 = new File(guardador2.getOutputFile());
	            Desktop.getDesktop().open(archivo2); // Abre el archivo generado
	            
	            // Mensaje de éxito
	    	    JOptionPane.showMessageDialog(null, "Albarán y Factura generados con éxito.");
	    	    
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Error al generar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        String codigoStr = JOptionPane.showInputDialog("Introduzca el código del albarán (A2024-CÓDIGO): ");
        
        if (codigoStr == null || codigoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Manejar el caso donde no se introduce un código
        }
        
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Manejar el caso de formato incorrecto
        }

        try {
            return new Albaran(a.getNumero_pedido(), codigo, a.getFecha(), a.getNumero_expediente(), a.getProducto(), a.getObservacion());
        } catch (NotValidCodeException e) {
            JOptionPane.showMessageDialog(null, "Código no válido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Manejar la excepción
        }
    }

    /**
     * leerInfoFactura()
     * Recoge la información necesaria para generar una factura
     *
     * @return archivo de tipo factura
     */
	public static Factura leerInfoFactura(Archivo a) {
        String codigoStr = JOptionPane.showInputDialog("Introduzca el código de la factura (FRA2024-CÓDIGO): ");
        
        if (codigoStr == null || codigoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Manejar el caso donde no se introduce un código
        }
        
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Manejar el caso de formato incorrecto
        }

        return new Factura(a.getNumero_pedido(), codigo, a.getFecha_entrega(), a.getNumero_expediente(), a.getProducto(), a.getObservacion());
    }


    /**
     * mostrarListaHospitales()
     * muestra la lista de todos los hospitales de los cuales de debe seleccionar uno para generar la factura
     */
	public static int mostrarListaHospitales() {
	    String[] opciones = {
	        "CEMTRO", "Cimeg", "Clinico San Carlos", "Cun", "HM Málaga","HM Puerta del Sur",
	        "Imd", "Infanta Elena", "Jantromed", "M.D Anderson", "Móstoles",
	        "Puerta de Hierro", "Quiron", "Rey Juan Carlos", "San Rafael", 
	        "Sanitas", "Santa Cruz", "Severo Ochoa", "Torrejón", 
	        "Quirón Toledo", "Vithas", "Salir"
	    };

	    // Crear un JList para mostrar las opciones
	    JList<String> hospitalList = new JList<>(opciones);
	    hospitalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    hospitalList.setVisibleRowCount(-1); // Mostrar todas las filas

	    // Crear un JScrollPane para agregar desplazamiento si hay muchas opciones
	    JScrollPane scrollPane = new JScrollPane(hospitalList);
	    scrollPane.setPreferredSize(new Dimension(250, 200)); // Ajustar el tamaño del JScrollPane

	    // Mostrar el diálogo
	    int opcion = JOptionPane.showOptionDialog(null,
	            scrollPane,
	            "Seleccione un hospital:",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            null, // No necesitamos un array de opciones ya que usaremos el JList
	            null);

	    if (opcion == -1) {
	        JOptionPane.showMessageDialog(null, "Operación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
	        return -1; // Manejar el caso de cancelación
	    }

	    // Obtener el índice seleccionado
	    int selectedIndex = hospitalList.getSelectedIndex();
	    return selectedIndex + 1; // Ajustar el índice para coincidir con el menú
	}

    public static Hospital seleccionarHospital(int opcion, String tipoArchivo, String rutaAcarpeta) {
        Hospital hospital = new Hospital(tipoArchivo, rutaAcarpeta);
        switch (opcion) {
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
            	hospital.hospital_HM_PuertaDelSur();
            	break;
            case 7:
                hospital.hospital_Imd();
                break;
            case 8:
                hospital.hospital_InfantaElena();
                break;
            case 9:
                hospital.hospital_Jantromed();
                break;
            case 10:
                hospital.hospital_MDAnderson();
                break;
            case 11:
                hospital.hospital_Mostoles();
                break;
            case 12:
                hospital.hospital_PuertaDeHierro();
                break;
            case 13:
                hospital.hospital_Quiron();
                break;
            case 14:
                hospital.hospital_ReyJuanCarlos();
                break;
            case 15:
                hospital.hospital_SanRafael();
                break;
            case 16:
                hospital.hospital_Sanitas();
                break;
            case 17:
                hospital.hospital_SantaCruz();
                break;
            case 18:
                hospital.hospital_SeveroOchoa();
                break;
            case 19:
                hospital.hospital_Torrejon();
                break;
            case 20:
                hospital.hospital_QuironToledo();
                break;
            case 21:
                hospital.hospital_Vithas();
                break;
        }
        return hospital;
    }
    
 // CASE 4 y 5 - MODIFICAR ARCHIVOS

    public static void modificarAlbaran(String rutaAcarpeta) {
        JOptionPane.showMessageDialog(null, 
            "RECUERDE QUE TODOS LOS ARCHIVOS DEBEN ESTAR CERRADOS PARA PODER REALIZAR ALGÚN TIPO DE MODIFICACIÓN.");

        String codigoStr = JOptionPane.showInputDialog("Qué albarán desea modificar. Introduzca su código: ");
        if (codigoStr == null || codigoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Manejar el caso donde no se introduce un código
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Manejar el caso de formato incorrecto
        }

        String anoStr = JOptionPane.showInputDialog("Introduzca el año en el que fue creado el albarán: ");
        int ano = Integer.parseInt(anoStr);

        String mesStr = JOptionPane.showInputDialog("Introduzca el mes (1-12): ");
        int num_mes = Integer.parseInt(mesStr);
        Month mes = Month.of(num_mes);

        int opcion1 = swingIOArchivos.mostrarListaHospitales();
        Hospital hospital = seleccionarHospital(opcion1, "Albaranes", rutaAcarpeta);

        String nombre_albaran = "A" + ano + "-" + codigo + "_" + hospital.getNombre() + ".pdf";

        String trimestre = Archivo.obtenerTrimestre(mes);
        String str_ano = String.valueOf(ano);
        String anio = str_ano.substring(str_ano.length() - 2); // obtiene los dos últimos dígitos del año
        String mes_carpeta = Archivo.obtenerMesCarpeta(mes, Integer.parseInt(anio));

        String rutaArchivo = rutaAcarpeta + "\\" + ano + " AÑO\\FACTURAS EMITIDAS " + ano + "\\" + trimestre + "\\" + mes_carpeta + "\\" + nombre_albaran;
        Albaran albaran = LecturaPDF.AlbaranFromPDF(rutaArchivo, hospital);
        albaran.setCodigo_albaran(codigo, ano);

        String[] opciones = {
            "Número de pedido",
            "Fecha entrega",
            "Número de expediente",
            "Producto",
            "Salir"
        };

        int opcion2 = JOptionPane.showOptionDialog(null, 
                "Qué dato desea modificar?", 
                "Modificar Albarán", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

        if (opcion2 == -1) {
            return; // Cancelado por el usuario
        }

        switch (opcion2) {
            case 0: // Número de pedido
                String nuevoPedidoStr = JOptionPane.showInputDialog("Introduzca el nuevo número de pedido: ");
                if (nuevoPedidoStr != null) {
                    int num = Integer.parseInt(nuevoPedidoStr);
                    albaran.setNumero_pedido(num);
                    JOptionPane.showMessageDialog(null, "Número de pedido modificado correctamente.");
                }
                break;

            case 1: // Fecha entrega
                String nuevaFechaStr = JOptionPane.showInputDialog("Introduzca la nueva fecha de entrega (dia/mes/año): ");
                if (nuevaFechaStr != null) {
                    LocalDate fecha_nueva = Utils.String2LocalDate2(nuevaFechaStr);
                    albaran.setFecha_entrega(fecha_nueva);
                    JOptionPane.showMessageDialog(null, "Fecha de entrega modificada correctamente.");
                }
                break;

            case 2: // Número de expediente
                String nuevoExpediente = JOptionPane.showInputDialog("Introduzca el nuevo número de expediente: ");
                if (nuevoExpediente != null) {
                    albaran.setNumero_expediente(nuevoExpediente);
                    JOptionPane.showMessageDialog(null, "Número de expediente modificado correctamente.");
                }
                break;

            case 3: // Producto
                Producto producto = albaran.getProducto();
                String[] opcionesProducto = {
                    "Unidades",
                    "Precio producto",
                    "Salir"
                };

                int opcion3 = JOptionPane.showOptionDialog(null, 
                    "Seleccione una opción:", 
                    "Modificar Producto", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    opcionesProducto, 
                    opcionesProducto[0]);

                switch (opcion3) {
                    case 0: // Unidades
                        String udsStr = JOptionPane.showInputDialog("Cuántas unidades del producto desea? ");
                        if (udsStr != null) {
                            int uds = Integer.parseInt(udsStr);
                            producto.setUnidades(uds); // cambia las unidades

                            float precio_original = producto.getPrecio_producto();
                            producto.setPrecio_producto(precio_original * uds); // cambia el precio del producto
                            producto.setPrecio_IVA(producto.getPrecio_producto() * 0.21f); // cambio precio con IVA
                            producto.calcularPrecioTotal(); // cambia precio total

                            JOptionPane.showMessageDialog(null, "Unidades modificadas. Cantidad: " + uds);
                        }
                        break;

                    case 1: // Precio producto
                        String precioNuevoStr = JOptionPane.showInputDialog("Introduzca el nuevo precio del producto: ");
                        if (precioNuevoStr != null) {
                            int precio_nuevo = Integer.parseInt(precioNuevoStr);
                            int unidades = producto.getUnidades();
                            producto.setPrecio_producto(precio_nuevo * unidades); // cambia el precio del producto
                            producto.setPrecio_IVA(producto.getPrecio_producto() * 0.21f); // cambio precio con IVA
                            producto.calcularPrecioTotal(); // cambia precio total

                            JOptionPane.showMessageDialog(null, "Precio alterado correctamente.");
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            // Eliminar el archivo
            if (archivo.delete()) {
                Guardador guardador = new Guardador(albaran, hospital);
                try {
                    guardador.Albaran2PDF(albaran, rutaAcarpeta, hospital);
                    JOptionPane.showMessageDialog(null, "El archivo ha sido modificado exitosamente.");
                    File file = new File(guardador.getOutputFile());
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void modificarFactura(String rutaAcarpeta) {
        JOptionPane.showMessageDialog(null,
            "RECUERDE QUE TODOS LOS ARCHIVOS DEBEN ESTAR CERRADOS PARA PODER REALIZAR ALGÚN TIPO DE MODIFICACIÓN.");

        String codigoStr = JOptionPane.showInputDialog("Qué factura desea modificar. Introduzca su código: ");
        if (codigoStr == null || codigoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Manejar el caso donde no se introduce un código
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Manejar el caso de formato incorrecto
        }

        String anoStr = JOptionPane.showInputDialog("Introduzca el año en el que fue creada la factura: ");
        int ano = Integer.parseInt(anoStr);

        String mesStr = JOptionPane.showInputDialog("Introduzca el mes (1-12): ");
        int num_mes = Integer.parseInt(mesStr);
        Month mes = Month.of(num_mes);
        
        String trimestre = Archivo.obtenerTrimestre(mes);
        String str_ano = String.valueOf(ano);
        String anio = str_ano.substring(str_ano.length() - 2); // obtiene los dos últimos dígitos del año
        String mes_carpeta = Archivo.obtenerMesCarpeta(mes, Integer.parseInt(anio));

        int opcion1 = swingIOArchivos.mostrarListaHospitales();
        Hospital hospital = seleccionarHospital(opcion1, "Facturas", rutaAcarpeta);

        String nombre_factura = "FRA" + ano + "-" + codigo + "_" + hospital.getNombre() + ".pdf";
        String rutaArchivo = rutaAcarpeta + "\\" + ano + " AÑO\\FACTURAS EMITIDAS " + ano + "\\" + trimestre + "\\" + mes_carpeta + "\\" + nombre_factura;

        Factura factura = LecturaPDF.FacturaFromPDF(rutaArchivo, hospital);
        factura.setCodigo_factura(codigo, ano);

        String[] opciones = {
            "Número de pedido",
            "Fecha entrega",
            "Producto",
            "Salir"
        };

        int opcion = JOptionPane.showOptionDialog(null,
                "Qué dato desea modificar?",
                "Modificar Factura",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcion == -1) {
            return; // Cancelado por el usuario
        }

        switch (opcion) {
            case 0: // Número de pedido
                String nuevoPedidoStr = JOptionPane.showInputDialog("Introduzca el nuevo número de pedido: ");
                if (nuevoPedidoStr != null) {
                    int num = Integer.parseInt(nuevoPedidoStr);
                    factura.setNumero_pedido(num);
                    JOptionPane.showMessageDialog(null, "Número de pedido modificado correctamente.");
                }
                break;

            case 1: // Fecha entrega
                String nuevaFechaStr = JOptionPane.showInputDialog("Introduzca la nueva fecha de entrega (dia/mes/año): ");
                if (nuevaFechaStr != null) {
                    LocalDate fecha_nueva = Utils.String2LocalDate2(nuevaFechaStr);
                    factura.setFecha_entrega(fecha_nueva);
                    JOptionPane.showMessageDialog(null, "Fecha de entrega modificada correctamente.");
                }
                break;

            case 2: // Producto
                Producto producto = factura.getProducto();
                String[] opcionesProducto = {
                    "Unidades",
                    "Precio producto",
                    "Salir"
                };

                int opcion2 = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción:",
                    "Modificar Producto",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesProducto,
                    opcionesProducto[0]);

                switch (opcion2) {
                    case 0: // Unidades
                        String udsStr = JOptionPane.showInputDialog("Cuántas unidades del producto desea? ");
                        if (udsStr != null) {
                            int uds = Integer.parseInt(udsStr);
                            producto.setUnidades(uds); // cambia las unidades

                            float precio_original = producto.getPrecio_producto();
                            producto.setPrecio_producto(precio_original * uds); // cambia el precio del producto
                            producto.setPrecio_IVA(producto.getPrecio_producto() * 0.21f); // cambio precio con IVA
                            producto.calcularPrecioTotal(); // cambia precio total

                            JOptionPane.showMessageDialog(null, "Unidades modificadas. Cantidad: " + uds);
                        }
                        break;

                    case 1: // Precio producto
                        String precioNuevoStr = JOptionPane.showInputDialog("Introduzca el nuevo precio del producto: ");
                        if (precioNuevoStr != null) {
                            int precio_nuevo = Integer.parseInt(precioNuevoStr);
                            int unidades = producto.getUnidades();
                            producto.setPrecio_producto(precio_nuevo * unidades); // cambia el precio del producto
                            producto.setPrecio_IVA(producto.getPrecio_producto() * 0.21f); // cambio precio con IVA
                            producto.calcularPrecioTotal(); // cambia precio total

                            JOptionPane.showMessageDialog(null, "Precio alterado correctamente.");
                        }
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }

        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            // Eliminar el archivo
            if (archivo.delete()) {
                Guardador guardador = new Guardador(factura, hospital);
                try {
                    guardador.Factura2PDF(factura, rutaAcarpeta, hospital);
                    JOptionPane.showMessageDialog(null, "El archivo ha sido modificado exitosamente.");
                    File file = new File(guardador.getOutputFile());
                    Desktop desktop2 = Desktop.getDesktop();
                    desktop2.open(file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
 

}

