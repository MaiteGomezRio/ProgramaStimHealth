package IOInterface;


public class Menu {
    public static void main(String[] args) {

        System.out.println("---------------------- BIENVENIDO AL PROGRAMA DE  ALBARANES Y FACTURAS ----------------------");
        //Por favor introduzca (copie y pegue) la ruta en la que se encuentra la carpeta con el programa en su ordenador
        String rutaAcarpeta="C:\\Users\\maria\\OneDrive - STIMHEALTH MEDICAL SL\\StimHealth\\02 Contabilidad";
        

        while(true) {
            int opcion = MenuPrincipal();
            switch (opcion) {

                case 1:
                    //genera albaran
                    //tipo=1=el tipo es porque se utiliza una misma funcion para el archivo de tipo "factura" y tipo "alabarán"
                    //para poder diferenciarlas a la hora de pedir datos
                    IOArchivos.crearArchivo(1, rutaAcarpeta);
                    break;
                case 2:
                    //generar factura
                    //tipo=2-> factura
                    IOArchivos.crearArchivo(2,rutaAcarpeta);
                    break;
                case 3:
                    //generar ambos
                    IOArchivos.crearAlbaranyFactura(rutaAcarpeta);
                    break;
                case 4:
                    //modificar albaran
                    IOArchivos.modificarAlbaran(rutaAcarpeta);
                    break;
                case 5:
                    //modificar factura
                    IOArchivos.modificarFactura(rutaAcarpeta);
                    break;

                default:
                    return;
            }
        }
    }

    public static int MenuPrincipal(){
        int opcion;
        System.out.println("-----------------------------MENÚ-------------------------");
        System.out.println("\t1.Generar albarán de producto");
        System.out.println("\t2.Generar factura de producto");
        System.out.println("\t3.Generar albarán y factura de producto");
        System.out.println("\t4.Modificar albarán");
        System.out.println("\t5.Modificar factura");
        System.out.println("\t7.Salir");

        do{
            System.out.println("Introduzca opcion del menu: ");
            opcion = Utils.leerEntero();
        }while(opcion < 0 || opcion > 7);
        return opcion;
    }

}

