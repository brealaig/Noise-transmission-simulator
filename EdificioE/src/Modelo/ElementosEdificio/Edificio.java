package Modelo.ElementosEdificio;

import Modelo.ElementosEdificio.Piso;
import java.util.ArrayList;

public class Edificio {
    
    private String identificador;
    private ArrayList<Piso> pisos;
    
    public Edificio(String identificador){
        this.identificador = identificador;
        pisos = new ArrayList<>();
    }
    
    public void agregarPiso(Piso piso){
        pisos.add(piso);
    }
    
    public ArrayList<Piso> getPisos(){
        return pisos;
    }
    
    public String getIdentificador(){
        return identificador;
    }
    
}
