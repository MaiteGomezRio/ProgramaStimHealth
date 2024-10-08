package IOInterface.ExternalFiles;

import javax.swing.*;
import IOInterface.swingIOArchivos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class swingMenu {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new swingMenu().crearVentana());
    }

    
	private void crearVentana() {
        // Crear el marco de la ventana
        JFrame marco = new JFrame("Programa de Albaranes y Facturas");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400, 300);
        marco.setLocationRelativeTo(null); // Centrar la ventana

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú
        JMenu menu = new JMenu("Menú");

        // Crear los elementos del menú
        JMenuItem generarAlbaran = new JMenuItem("Generar albarán de producto");
        JMenuItem generarFactura = new JMenuItem("Generar factura de producto");
        JMenuItem generarAmbos = new JMenuItem("Generar albarán y factura de producto");
        JMenuItem modificarAlbaran = new JMenuItem("Modificar albarán");
        JMenuItem modificarFactura = new JMenuItem("Modificar factura");
        JMenuItem salir = new JMenuItem("Salir");

        // Ruta a la carpeta
        // TODO Por favor introduzca (copie y pegue) la ruta en la que se encuentra la carpeta con el programa en su ordenador
        String rutaAcarpeta="C:\\Users\\maria\\OneDrive - STIMHEALTH MEDICAL SL\\StimHealth\\02 Contabilidad";
        

        // Agregar ActionListener a cada opción del menú
        generarAlbaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la operación en segundo plano usando SwingWorker
                new SwingWorker<Void, Void>() {
                	
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            swingIOArchivos.crearArchivo(1, rutaAcarpeta); // Tipo 1 es albarán
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(marco, "Error al generar albarán: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                   
                }.execute();
            }
        });

        generarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la operación en segundo plano usando SwingWorker
                new SwingWorker<Void, Void>() {
                
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            swingIOArchivos.crearArchivo(2, rutaAcarpeta); // Tipo 2 es factura
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(marco, "Error al generar factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                   
                }.execute();
            }
        });

        generarAmbos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la operación en segundo plano usando SwingWorker
                new SwingWorker<Void, Void>() {
                	
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            swingIOArchivos.crearAlbaranyFactura(rutaAcarpeta);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(marco, "Error al generar albarán y factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                    
                }.execute();
            }
        });

        modificarAlbaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la operación en segundo plano usando SwingWorker
                new SwingWorker<Void, Void>() {
                	
                
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            swingIOArchivos.modificarAlbaran(rutaAcarpeta);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(marco, "Error al modificar albarán: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                    
                }.execute();
            }
        });

        modificarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ejecutar la operación en segundo plano usando SwingWorker
                new SwingWorker<Void, Void>() {
                	
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            swingIOArchivos.modificarFactura(rutaAcarpeta);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(marco, "Error al modificar factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                    
                }.execute();
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir de la aplicación
            }
        });

        // Agregar los elementos del menú
        menu.add(generarAlbaran);
        menu.add(generarFactura);
        menu.add(generarAmbos);
        menu.add(modificarAlbaran);
        menu.add(modificarFactura);
        menu.addSeparator(); // Separador en el menú
        menu.add(salir);

        // Agregar el menú a la barra de menú
        menuBar.add(menu);

        // Establecer la barra de menú en el marco
        marco.setJMenuBar(menuBar);

        // Hacer visible la ventana
        marco.setVisible(true);
    }
}
