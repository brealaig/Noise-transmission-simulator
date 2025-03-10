/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ElementosEdificio;

import java.awt.Color;
import java.util.ArrayList;
import java.math.*;

/**
 *
 * @author tic-asisweb01
 */
public class Actividad {
    
    protected String identificador;
    protected ArrayList<FuenteInterna> fuentesInternas;
    protected int tolerancia;
    
    public Actividad(String identificador){
        this.identificador = identificador;
        fuentesInternas = new ArrayList<>();
    }
    
    public String getIdentificador(){
        return identificador;
    }
    
    public void inicializarActividad(){
        switch(identificador){
            case "Fiestas" -> {
                fuentesInternas.add(new FuenteInterna("Parlante", 80, 3600, "Música")); // Nivel alto de ruido
                fuentesInternas.add(new FuenteInterna("Gente Hablando", 70, 3600, "Charla")); // Nivel alto de ruido
                this.tolerancia = 120;
            }
            case "Oficina" -> {
                fuentesInternas.add(new FuenteInterna("Impresora", 20, 1800, "Trabajo")); // Nivel medio de ruido
                fuentesInternas.add(new FuenteInterna("Teclado", 10, 14400, "Trabajo")); // Nivel bajo de ruido
                fuentesInternas.add(new FuenteInterna("Teléfono", 15, 7200, "Trabajo")); // Nivel medio de ruido
                this.tolerancia = 65;
            }
            case "Cocina" -> {
                fuentesInternas.add(new FuenteInterna("Extractor", 10, 1800, "Cocina")); // Nivel alto de ruido
                fuentesInternas.add(new FuenteInterna("Refrigerador", 15, 7200, "Cocina")); // Nivel medio de ruido
                fuentesInternas.add(new FuenteInterna("Licuadora", 20, 1200, "Cocina")); // Nivel alto de ruido
                this.tolerancia = 90;
            }
            case "Dormitorio" -> {
                fuentesInternas.add(new FuenteInterna("Ventilador", 10, 28800, "Descanso")); // Nivel bajo de ruido
                fuentesInternas.add(new FuenteInterna("Reloj", 5, 43200, "Descanso")); // Nivel bajo de ruido
                this.tolerancia = 35;
            }
            case "Gimnasio" -> {
                fuentesInternas.add(new FuenteInterna("Cinta de correr", 25, 3600, "Ejercicio")); // Nivel alto de ruido
                fuentesInternas.add(new FuenteInterna("Pesas", 30, 5400, "Ejercicio")); // Nivel alto de ruido
                this.tolerancia = 100;
            }
            case "Biblioteca" -> {
                fuentesInternas.add(new FuenteInterna("Página de libro", 5, 28800, "Estudio")); // Nivel bajo de ruido
                fuentesInternas.add(new FuenteInterna("Tos ocasional", 5, 14400, "Estudio")); // Nivel bajo de ruido
                this.tolerancia = 50;
            }
            default -> System.out.println("Identificador de actividad no validado");
        }
    }
    
     // Método para inicializar las fuentes internas de la actividad (como lo hicimos anteriormente)
    
    public int calcularDecibelesFuentesInternas() {
        int totalDecibeles = 0;
        for (FuenteInterna fuente : fuentesInternas) {
            //Formula de decibeles en el espacio - heuristica del documento
            totalDecibeles += (double) (10*(Math.log10(fuente.getFrecuencia())));
        }
        
        return totalDecibeles;
    }
    
    public Color getColorEspacioPorDecibeles(int decibeles){
        //Control de decibeles por fuentes internas
        if(decibeles<45){
            return Color.GREEN;
        } else if(decibeles < 100){
            return Color.YELLOW;
        } else{
            return Color.RED;
        }
        
    }

    public Color getColorGraficoPorDecibeles(int decibeles) {
        //Control de decibeles por fuentes internas
        if(decibeles<45){
            return Color.GREEN;
        } else if(decibeles < 100){
            return Color.YELLOW;
        } else{
            return Color.RED;
        }
    }
    
}
