package Controlador;

import Modelo.ElementosGrafo.Espacio;
import Modelo.ElementosEdificio.Edificio;
import Modelo.ElementosEdificio.Piso;
import Modelo.ElementosEdificio.FachadaCreacionEdificio;
import Modelo.ElementosEdificio.GestorHabitabilidad;
import Modelo.ElementosGrafo.GrafoPanel;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controlador implements ActionListener{
    
    private final MenuCreacion menuCreacion;
    private final Simulacion simulacion = new Simulacion();
    private final Simulacion3D simulacion3d = new Simulacion3D();
    private final FachadaCreacionEdificio fachada = FachadaCreacionEdificio.getFachada();
    private final String[] args;
    private GestorHabitabilidad gestor;
    
    //Constructor controlador
    public Controlador(MenuCreacion ventana, String[] args){
        
        this.menuCreacion = ventana;
        this.menuCreacion.Simular.addActionListener(e -> actionPerformed(e));
        
        this.simulacion.simularPiso.addActionListener(e -> actionPerformed(e));
        this.simulacion.simularHabitacion.addActionListener(e -> actionPerformed(e));
        this.simulacion.recomendacionesPiso.addActionListener(e -> actionPerformed(e));
        this.simulacion.solucionarPiso.addActionListener(e -> actionPerformed(e));
        
        this.args = args;
    }
    
    //Método para iniciar la ventana del menú
    public void iniciar() {
        menuCreacion.setVisible(true);
        menuCreacion.setEnabled(true);
        menuCreacion.setLocationRelativeTo(null);
        menuCreacion.setTitle("Proyecto Final - Habitabilidad Edificio");
        menuCreacion.setResizable(false);
    }
    
    //Métódo para iniciar la ventana de simulación
    //Métódo para iniciar la ventana de simulación
    public void iniciarVentanaSimulacion() {
        Edificio miEdificioGrafico = this.fachada.getEdificio();
        simulacion3d.setEdificio(miEdificioGrafico);

        Thread simulacionThread = new Thread(() -> {
            simulacion.setVisible(true);
            simulacion.setEnabled(true);
            simulacion.setLocationRelativeTo(null);
            simulacion.setTitle("Proyecto Final - Habitabilidad Edificio");
            simulacion.setResizable(false);
        });

        Thread simulacion3dThread = new Thread(() -> {
            simulacion3d.iniciarSimulacion3D(args);
        });

        simulacionThread.start();
        simulacion3dThread.start();
    }

    
    //Método para inicializar el edificio
    public void crearEdificio(int cantidadPisos){
        this.fachada.crearEdificio("Continental", cantidadPisos);
    }
    
    public GrafoPanel crearGrafoPisos(Piso piso) {
        // Crear y retornar el panel del grafo
        return new GrafoPanel(piso.getNodos(), piso.getAristas());
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Condicional que ejecuta la ventana de simulación
        if(e.getSource() == menuCreacion.Simular){
            //Inicialización de los valores en memoria
            this.crearEdificio(Integer.parseInt(menuCreacion.cantidadPisos.getText()));
            //Cerrar ventana de creación
            gestor = new GestorHabitabilidad(fachada.getEdificio());
            menuCreacion.setVisible(false);
            this.iniciarVentanaSimulacion();
        }
        
        //Condicional que ejecuta el dibujado del grafo 2d del piso a simular
        if (e.getSource() == simulacion.simularPiso) {
            Edificio miEdificio = this.fachada.getEdificio();
            
            try{
                //Obtener el piso seleccionado
                Piso piso = miEdificio.getPisos().get(Integer.parseInt(simulacion.targetPiso.getText())-1);
                //Inicializar el Jpanel con el grafo del piso
                GrafoPanel grafoPisos;
                grafoPisos = crearGrafoPisos(piso);
                simulacion.setDisplayPanel(grafoPisos);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Ingrese un número de piso válido");
            }

        }
        //Condicional que ejecuta la simulación por metro cuadrado de las habitaciones del piso
        if(e.getSource() == simulacion.simularHabitacion){
            Edificio miEdificio = this.fachada.getEdificio();
            try{
                Espacio habitacion = miEdificio.getPisos().get(Integer.parseInt(simulacion.targetPiso.getText())-1).getNodos().get(Integer.parseInt(simulacion.targetHabitacion.getText()));
                MostrarHabitacionPanel panelHabitacion = new MostrarHabitacionPanel(habitacion);
                simulacion.setDisplayPanel(panelHabitacion);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Ingrese un número de habitación válido");
            }          
        }
        
        //Condicional que muestra en la caja de texto las recomendaciones del piso seleccionado
        if (e.getSource() == simulacion.recomendacionesPiso) {
            Edificio miEdificio = this.fachada.getEdificio();
            simulacion.boxRecomendacion.setText("Recomendaciones del piso - " + Integer.valueOf(simulacion.targetPiso.getText()) + "\n");
            // Encontrar conexiones RED-GREEN
            List<String> conexiones = miEdificio.getPisos().get(Integer.parseInt(simulacion.targetPiso.getText()) - 1).conexionNodosRojosVerdes();
            // Imprimir las conexiones encontradas
            for (String conexion : conexiones) {
                simulacion.boxRecomendacion.append(conexion + "\n");
            }
        }
        
        //Condicional que ejecuta el algoritmo para cambiar el grafo del piso seleccionado según las recomendaciones de conexión
        if(e.getSource() == simulacion.solucionarPiso){
            
            simulacion.boxRecomendacion.setText("Solución del piso - " + Integer.valueOf(simulacion.targetPiso.getText()) + "\n");
            try {
                //Piso piso = miEdificio.getPisos().get(Integer.parseInt(simulacion.targetPiso.getText()) - 1);
                simulacion3d.stop();
            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(gestor.getEspaciosConflictivos());
            gestor.encontrarEspaciosConflictivos();
            gestor.cambiarEspaciosConflictivos();
            this.fachada.solucionarHabitabilidad();
        }

    } 
}
