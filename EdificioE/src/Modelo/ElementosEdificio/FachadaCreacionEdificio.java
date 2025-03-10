package Modelo.ElementosEdificio;

import Modelo.ElementosGrafo.Espacio;
import java.awt.Color;
import java.util.Random;

public class FachadaCreacionEdificio {
    
    private Edificio edificio;
    private static FachadaCreacionEdificio fachadaCreacionEdificio;
    
    private FachadaCreacionEdificio(){
        
    }
    
    public static FachadaCreacionEdificio getFachada(){
        if(fachadaCreacionEdificio == null){
            fachadaCreacionEdificio = new FachadaCreacionEdificio();
        }
        return fachadaCreacionEdificio;
    }
    
    public void crearEdificio(String identificadorEdificio, int cantidadPisos){
        
        this.edificio = new Edificio(identificadorEdificio);
        crearPisos(edificio,cantidadPisos);
        
    }
    
    //Por cada cantidad de pisos, inicializa el edificio con el piso indicado
    private void crearPisos(Edificio edificio, int cantidadPisos){
        
        for(int i=0; i<cantidadPisos; i++){
            Piso piso = new Piso("Piso "+String.valueOf(i+1));
            crearEspacios(piso);
            edificio.agregarPiso(piso);
        }
        
    }
    
    //Por casa piso, añade los nodos correspondientes
    private void crearEspacios(Piso piso){
        int fila = 0;
        int columna = 0;
        Random random = new Random();
        
        System.out.println("------ Piso "+piso.getIdentificador()+"------");
        for(int i = 0; i < 12; i++){
            if (i % 4 == 0 && i != 0) {
                fila++;
                columna = 0;
            }
            //Crea los espacios para cada piso, inicializando las actividades de las habitaciones
            Actividad actividadEspacio = elegirActividadAleatoria();
            actividadEspacio.inicializarActividad();
            //Añade el color al nodo (Habitación) dependiendo la cantidad de ruedo que exista en ella
            Color colorEspacio = actividadEspacio.getColorEspacioPorDecibeles(actividadEspacio.calcularDecibelesFuentesInternas());
            Color colorGrafico = actividadEspacio.getColorGraficoPorDecibeles(actividadEspacio.calcularDecibelesFuentesInternas());
            //Inicializa el espacio con los valores apropiados en las posiciones del Jlabel y su color adecuado
            Espacio espacio = new Espacio(100 + columna * 150, 50 + fila * 150, colorEspacio, colorGrafico,actividadEspacio, i);
            //Crea los metros cuadrados y añade las fuentes internas con base la actividad designada
            crearMetroCuadrado(espacio, 5, 5);
            espacio.getEspacioHabitacion().get(random.nextInt(24)).agregarFuente(actividadEspacio.fuentesInternas.get(0));
            //Antes de agregar espacio, modificados el color del espacio al hacer analisis de tolerancia y habitabilidad por fuentes internas y externas
            espacio.calcularTolerancia();
            espacio.setColorGrafico(espacio.calcularHabitabilidad());
            piso.agregarEspacio(espacio);
            //Muestro de consola de la información por espacio del ruido
            System.out.println("Habitacion "+ i + " con actividad = "+ actividadEspacio.getIdentificador() +" y tolerancia de ruido de= "+ espacio.getTolerancia());
            
            columna++;
        }
        //Dados los nodos creados, añade las aristas correspondientes
        piso.agregarAristas();
    }
    
    public void solucionarHabitabilidad(){
        for(int i=0; i<edificio.getPisos().size(); i++){
            for(int j=0; j<edificio.getPisos().get(i).getNodos().size(); j++){
                edificio.getPisos().get(i).getNodos().get(j).setColorGrafico(Color.GREEN);
            }
        }
    }
    
    
    //Método para elegir la actividad de forma aleatoria en el espacio
    public static Actividad elegirActividadAleatoria() {
        String[] actividades = {"Fiestas", "Oficina", "Cocina", "Dormitorio", "Gimnasio", "Biblioteca"}; // Todas las actividades disponibles
        Random random = new Random();
        int indice = random.nextInt(actividades.length);
        return new Actividad(actividades[indice]);
    }
   
    //Dada el espacio creado en un piso respectivo, genera los metros cuadrados para gestionar las fuentes de ruido
    private void crearMetroCuadrado(Espacio espacio, int metrosLargo, int metrosAncho){
        for(int i=0; i<metrosLargo; i++){
            for(int j=0; j<metrosAncho; j++){
                MetroCuadrado metroCuadrado = new MetroCuadrado(metrosLargo,metrosAncho);
                espacio.agregarMetro(metroCuadrado);
            }
        }
    }
    
    public Edificio getEdificio(){
        return edificio;
    }
    
    public void setEdificio(Edificio edificio){
        this.edificio = edificio;
    }
    
}

