package Modelo.ElementosEdificio;

import Modelo.ElementosGrafo.Espacio;
import java.awt.Color;

public class Habitacion extends Espacio{
    
    public Habitacion(int x, int y, Color colorEspacio, Color colorGrafico, Actividad actividad, int numeroEspacio){
        super(x,y,colorEspacio, colorGrafico, actividad, numeroEspacio);
    }
    
}
