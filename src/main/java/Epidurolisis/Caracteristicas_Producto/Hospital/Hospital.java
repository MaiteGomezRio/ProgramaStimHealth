package Epidurolisis.Caracteristicas_Producto.Hospital;

public class Hospital {

    private String rutaAcarpeta;

    private String tipoArchivo;
    private String nombre;
    private String plantilla;
    private float precio;
    private String facturaA;

    /**
     * Constructor para el main
     * @param tipoArchivo albarán o factura
     * @param rutaAcarpeta ruta a la carpeta del programa
     */
    public Hospital(String tipoArchivo,String rutaAcarpeta) {
        this.tipoArchivo=tipoArchivo;
        this.rutaAcarpeta=rutaAcarpeta;
    }


    /**
     * contructor por defecto
     */
    public Hospital() {
        this.plantilla="";
        this.precio = 0.0F;
        this.facturaA="";
    }

    /**
     * toString
     * @return string con la info del hospital
     */
    @Override
    public String toString() {
        return "Hospital{ plantilla= "+plantilla+", precio= " + precio +
                ", facturaA= " + facturaA + "}";
    }

    /**
     * LISTA DE MÉTODOS CON TODOS LOS HOSPITALES DEFINIDOS POR EL PROGRAMA
     */
    public void hospital_CEMTRO(){
        this.nombre="CENTRO EUROPEO MÉDICO TRAUMATOLÓGICO. SA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Cemtro.pdf";
        this.precio=88.0f;
        this.facturaA="CEMTRO";

    }
    public void hospital_Cimeg(){
        this.nombre="IBERMEDICARE S.L";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Cimeg.pdf";
        this.precio=88.0f;
        this.facturaA="Ibermedicare SL";
    }
    public void hospital_ClinicoSanCarlos(){
        this.nombre="HOSPITAL UNIVERSITARIO CLÍNICO SAN CARLOS";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_ClinicoSanCarlos.pdf";
        this.precio=114.35f;
        this.facturaA="Clinico San Carlos";
    }
    public void hospital_Cun(){
        this.nombre="CLÍNICA UNIVERSIDAD NAVARRA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Cun.pdf";
        this.precio=94.50f;
        this.facturaA="Cun";
    }
    public void hospital_HM_Malaga(){
        this.nombre="HOSPITAL HM MÁLAGA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_HMMalaga.pdf";
        this.precio=94.50f;
        this.facturaA="Desarrollos asistenciales sur S.L";
    }

    public void hospital_HM_PuertaDelSur(){
        this.nombre="HOSPITAL UNIV. HM PUERTA DEL SUR";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_HM_puertaDelSur.pdf";
        this.precio=94.50f;
        this.facturaA="";
    }
    public void hospital_Imd(){
        this.nombre="INSTITUTO MADRILEÑO DEL DOLOR S.L. (RUIZ HUERTA SERRANO ASOCIADOS)";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Imd.pdf";
        this.precio=88.0f;
        this.facturaA="Ruiz Huerta Asociados";
    }
    public void hospital_InfantaElena(){
        this.nombre="HOSPITAL INFANTA ELENA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_InfantaElena.pdf";
        this.precio=84.0f;
        this.facturaA="Servicios, personas, y salud";
    }
    public void hospital_Jantromed(){
        this.nombre="JANTROMED DISTRIBUCIONES QUIRÚRGICAS S.L";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Jantromed.pdf";
        this.precio=88.0f;
        this.facturaA="Jantromed";
    }
    public void hospital_MDAnderson(){
        this.nombre="MD ANDERSON INTERNATIONAL-ESPAÑA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_M.D. ANDERSON.pdf";
        this.precio=94.50f;
        this.facturaA="M.D Anderson";
    }
    public void hospital_Mostoles(){
        this.nombre="HOSPITAL DE MÓSTOLES-POLIGONO DE VILLAVERDE";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Mostoles.pdf";
        this.precio=94.50f;
        this.facturaA="Móstoles";
    }
    public void hospital_PuertaDeHierro(){
        this.nombre="HOSPITAL UNIVERSITARIO HM PUERTA DEL SUR. HM HOSPITALES 1989 SA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_PuertaDeHierro.pdf";
        this.precio=88.0f;
        this.facturaA="Puerta de Hierro";
    }
    public void hospital_Quiron(){
        this.nombre="IDCQ HOSPITALES Y SANIDAD SLU. HOSPITAL UNIVERSITARIO QUIRON SALUD MADRID";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_QuironSalud.pdf";
        this.precio=94.50f;
        this.facturaA="Idcq";
    }
    public void hospital_ReyJuanCarlos(){
        this.nombre="HOSPITAL UNIVERSITARIO REY JUAN CARLOS";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_ReyJuanCarlos.pdf";
        this.precio=94.50f;
        this.facturaA="Servicios, personas, y salud";
    }
    public void hospital_SanRafael(){
        this.nombre="HOSPITAL SAN RAFAEL";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_SanRafael.pdf";
        this.precio=94.50f;
        this.facturaA="Gestión hospitalaria";
    }
    public void hospital_Sanitas(){
        this.nombre="SANITAS S.A DE HOSPITALES";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Sanitas.pdf";
        this.precio=94.50f;
        this.facturaA="Sanitas";
    }
    public void hospital_SantaCruz(){
        this.nombre="CENTRO MÉDICO SALUD CANARIAS S.A";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_SalusCanarias.pdf";
        this.precio=84.0f;
        this.facturaA="CENTRO MEDICOS SALUS CANARIAS SA";
    }
    public void hospital_SeveroOchoa(){
        this.nombre="HOSPITAL UNIVERSITARIO SEVERO OCHOA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_SeveroOchoa.pdf";
        this.precio=94.50f;
        this.facturaA="Severo Ochoa";
    }

    public void hospital_Torrejon(){
        this.nombre="TORREJON SALUD S.L. HOSPITAL UNIVERSITARIO DE TORREJON";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Torrejon.pdf";
        this.precio=88.0f;
        this.facturaA="Hospital Torrejon";
    }
    public void hospital_QuironToledo(){
        this.nombre="QUIRON SALUD TOLEDO";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_QuironToledo.pdf";
        this.precio=84.0f;
        this.facturaA="Idcq";
    }
    public void hospital_Vithas(){
        this.nombre="VITHAS MADRID ARTURO SORIA-CLINSA. Sª DE FARMACIA";
        this.plantilla=this.rutaAcarpeta+"\\PlantillasHospitales\\"+this.tipoArchivo+"\\Plantilla_Vithas.pdf";
        this.precio=94.50f;
        this.facturaA="Clinsa";
    }

    public static Hospital obtenerInfoXNombre(String nombre_Hospital){
        Hospital hospital = new Hospital();
        switch(nombre_Hospital.toUpperCase()){
            case "CEMTRO":
                hospital.hospital_CEMTRO();
                break;
            case "CIMEG":
                hospital.hospital_Cimeg();
                break;
            case "CLINICO SAN CARLOS":
                hospital.hospital_ClinicoSanCarlos();
                break;
            case"CUN":
                hospital.hospital_Cun();
                break;
            case "HM MALAGA":
                hospital.hospital_HM_Malaga();
                break;
            case "HM PUERTA DEL SUR":
                hospital.hospital_HM_PuertaDelSur();
            case "IMD":
                hospital.hospital_Imd();
                break;
            case "INFANTA ELENA":
                hospital.hospital_InfantaElena();
                break;
            case "JANTROMED":
                hospital.hospital_Jantromed();
                break;
            case "MD.ANDERSON":
                hospital.hospital_MDAnderson();
                break;
            case "MOSTOLES":
                hospital.hospital_Mostoles();
                break;
            case "PUERTA DE HIERRO":
                hospital.hospital_PuertaDeHierro();
                break;
            case "QUIRON":
                hospital.hospital_Quiron();
                break;
            case "REY JUAN CARLOS":
                hospital.hospital_ReyJuanCarlos();
                break;
            case "SAN RAFAEL":
                hospital.hospital_SanRafael();
            case "SANITAS":
               hospital.hospital_Sanitas();
                break;
            case "SANTA CRUZ":
                hospital.hospital_SantaCruz();
                break;
            case "SEVERO OCHOA":
                hospital.hospital_SeveroOchoa();
                break;
            case "TORREJON":
                hospital.hospital_Torrejon();
                break;
            case "QUIRON TOLEDO":
                hospital.hospital_QuironToledo();
                break;
            case "VITHAS":
                hospital.hospital_Vithas();
                break;
            default:
                break;

        }
        return hospital;
    }


    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public static void main(String[] args) {
        Hospital hospital=new Hospital();
        System.out.println(hospital);
        hospital.hospital_PuertaDeHierro();
        System.out.println(hospital);
    }
}
