package Modelo.ElementosEdificio;

import Modelo.ElementosGrafo.Arista;
import Modelo.ElementosGrafo.Espacio;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Piso{
    
    private String identificador;
    private List<Espacio> nodos;
    private List<Arista> aristas = new ArrayList<>();
    
    public Piso(String identificador){
        this.identificador = identificador;
        nodos = new ArrayList<>();
    }
    
    public void agregarEspacio(Espacio espacio){
        nodos.add(espacio);
    }
    
    public void agregarAristas(){
        // Crear aristas 
        aristas.add(new Arista(nodos.get(0), nodos.get(1)));
        aristas.add(new Arista(nodos.get(0), nodos.get(5)));
        aristas.add(new Arista(nodos.get(0), nodos.get(4)));
        
        aristas.add(new Arista(nodos.get(1), nodos.get(0)));
        aristas.add(new Arista(nodos.get(1), nodos.get(4)));
        aristas.add(new Arista(nodos.get(1), nodos.get(5)));
        aristas.add(new Arista(nodos.get(1), nodos.get(2)));
        aristas.add(new Arista(nodos.get(1), nodos.get(6)));
        
        aristas.add(new Arista(nodos.get(2), nodos.get(1)));
        aristas.add(new Arista(nodos.get(2), nodos.get(5)));
        aristas.add(new Arista(nodos.get(2), nodos.get(6)));
        aristas.add(new Arista(nodos.get(2), nodos.get(3)));
        aristas.add(new Arista(nodos.get(2), nodos.get(7)));
        
        aristas.add(new Arista(nodos.get(3), nodos.get(2)));
        aristas.add(new Arista(nodos.get(3), nodos.get(7)));
        aristas.add(new Arista(nodos.get(3), nodos.get(6)));
        
        aristas.add(new Arista(nodos.get(4), nodos.get(0)));
        aristas.add(new Arista(nodos.get(4), nodos.get(1)));
        aristas.add(new Arista(nodos.get(4), nodos.get(5)));
        aristas.add(new Arista(nodos.get(4), nodos.get(8)));
        aristas.add(new Arista(nodos.get(4), nodos.get(9)));
        
        aristas.add(new Arista(nodos.get(5), nodos.get(0)));
        aristas.add(new Arista(nodos.get(5), nodos.get(4)));
        aristas.add(new Arista(nodos.get(5), nodos.get(8)));
        aristas.add(new Arista(nodos.get(5), nodos.get(1)));
        aristas.add(new Arista(nodos.get(5), nodos.get(9)));
        aristas.add(new Arista(nodos.get(5), nodos.get(2)));
        aristas.add(new Arista(nodos.get(5), nodos.get(6)));
        aristas.add(new Arista(nodos.get(5), nodos.get(10)));
        
        aristas.add(new Arista(nodos.get(6), nodos.get(2)));
        aristas.add(new Arista(nodos.get(6), nodos.get(1)));
        aristas.add(new Arista(nodos.get(6), nodos.get(3)));
        aristas.add(new Arista(nodos.get(6), nodos.get(7)));
        aristas.add(new Arista(nodos.get(6), nodos.get(9)));
        aristas.add(new Arista(nodos.get(6), nodos.get(10)));
        aristas.add(new Arista(nodos.get(6), nodos.get(11)));
        
        
        aristas.add(new Arista(nodos.get(7), nodos.get(2)));
        aristas.add(new Arista(nodos.get(7), nodos.get(3)));
        aristas.add(new Arista(nodos.get(7), nodos.get(6)));
        aristas.add(new Arista(nodos.get(7), nodos.get(10)));
        aristas.add(new Arista(nodos.get(7), nodos.get(11)));
        
        aristas.add(new Arista(nodos.get(8), nodos.get(4)));
        aristas.add(new Arista(nodos.get(8), nodos.get(5)));
        aristas.add(new Arista(nodos.get(8), nodos.get(9)));
        
        aristas.add(new Arista(nodos.get(9), nodos.get(8)));
        aristas.add(new Arista(nodos.get(9), nodos.get(4)));
        aristas.add(new Arista(nodos.get(9), nodos.get(5)));
        aristas.add(new Arista(nodos.get(9), nodos.get(6)));
        aristas.add(new Arista(nodos.get(9), nodos.get(10)));
        
        aristas.add(new Arista(nodos.get(10), nodos.get(5)));
        aristas.add(new Arista(nodos.get(10), nodos.get(6)));
        aristas.add(new Arista(nodos.get(10), nodos.get(7)));
        aristas.add(new Arista(nodos.get(10), nodos.get(9)));
        aristas.add(new Arista(nodos.get(10), nodos.get(11)));
        
        aristas.add(new Arista(nodos.get(11), nodos.get(6)));
        aristas.add(new Arista(nodos.get(11), nodos.get(10)));
        aristas.add(new Arista(nodos.get(11), nodos.get(7)));
    }
    
    public List<Espacio> getNodos(){
        return nodos;
    }
    
    public void setNodo(int numeroEspacio,Espacio espacio){
        nodos.set(numeroEspacio, espacio);
    }
    
    public List<Arista> getAristas(){
        return aristas;
    }
    
    public String getIdentificador(){
        return identificador;
    }
    
    public List<String> conexionNodosRojosVerdes() {
        List<String> rojosVerdesConexion = new ArrayList<>();

        for (Arista arista : aristas) {
            Espacio nodo1 = arista.from;
            Espacio nodo2 = arista.to;

            if (nodo1.getColorGrafico() == Color.RED && nodo2.getColorGrafico() == Color.GREEN) {
                rojosVerdesConexion.add("Nodo " + nodo1.getNumeroEspacio() + " está conectado con Nodo " + nodo2.getNumeroEspacio() + " generando conflicto de ruido");
            }
        }

        return rojosVerdesConexion;
    }


}
