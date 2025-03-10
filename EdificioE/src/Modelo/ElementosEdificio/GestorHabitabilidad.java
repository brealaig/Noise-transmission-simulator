package Modelo.ElementosEdificio;

import Modelo.ElementosGrafo.Arista;
import Modelo.ElementosGrafo.Espacio;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class GestorHabitabilidad {
    
    private Edificio miEdificio;
    private ArrayList<Espacio> espaciosConflictivos = new ArrayList<Espacio>();
    private ArrayList<Integer> pisosEspaciosConflictivos = new ArrayList<Integer>();
    private ArrayList<Integer> numeroNodoEspaciosConflictivos = new ArrayList<Integer>();
    
    
    public GestorHabitabilidad(Edificio miEdificio){
        this.miEdificio = miEdificio;
    }
    
    public void cambiarEspaciosConflictivos(){
        for(int k=0; k<espaciosConflictivos.size(); k++){
            for(int i=0; i<miEdificio.getPisos().size(); i++){
                for(int j=0; j<miEdificio.getPisos().get(i).getNodos().size(); j++){
                    if(miEdificio.getPisos().get(i).getNodos().get(j).getColorGrafico().equals(Color.GREEN) || miEdificio.getPisos().get(i).getNodos().get(j).getColorGrafico().equals(Color.YELLOW)){
                        if(esAdyacenteRojo(miEdificio,i,j) == false){
                            Espacio espacioDisponible = miEdificio.getPisos().get(i).getNodos().get(j);
                            //miEdificio.getPisos().get(i).setNodo(j, espaciosConflictivos.get(k));
                            //miEdificio.getPisos().get(pisosEspaciosConflictivos.get(k)).getNodos().set(numeroNodoEspaciosConflictivos.get(k), espacioDisponible);
                            System.out.println("Se cambió el nodo: "+ numeroNodoEspaciosConflictivos.get(k) + " del piso: "+ (pisosEspaciosConflictivos.get(k)+1));
                        }
                    }
                }
            }   
        }
    }
    
    public void encontrarEspaciosConflictivos(){
        
        for(int i=0; i<miEdificio.getPisos().size(); i++){
            List<Arista> aristas = this.miEdificio.getPisos().get(i).getAristas();
            
            System.out.println(aristas.size());
            for (Arista arista : aristas) {
                Espacio nodo1 = arista.from;
                Espacio nodo2 = arista.to;

                // Solo agregar conexiones donde el nodo1 es rojo y el nodo2 es verde
                if (nodo1.getColorGrafico().equals(Color.RED) && nodo2.getColorGrafico().equals(Color.GREEN) && estaAgregado(nodo1)== false) {
                    espaciosConflictivos.add(nodo1);
                    numeroNodoEspaciosConflictivos.add(nodo1.getNumeroEspacio());
                    pisosEspaciosConflictivos.add(i);
                    System.out.println(nodo1.getActividad().getIdentificador());
                }
            }
        }    
    }
    
    public boolean estaAgregado(Espacio nodo1){
        for(int i=0; i<espaciosConflictivos.size(); i++){
                    if(nodo1.equals(espaciosConflictivos.get(i))){
                        return true;
                    }else{
                        return false;
                    }
                }
        return false;
    }
    
    public ArrayList<Espacio> getEspaciosConflictivos(){
        return this.espaciosConflictivos;
    }

    public boolean esAdyacenteRojo(Edificio miEdificio, int piso, int espacio){
        
        List<Espacio> espacios = miEdificio.getPisos().get(piso).getNodos();
     
        try{
            if(espacio%2==0){
                if(espacios.get(espacio-5).getColorGrafico().equals(Color.RED) || espacios.get(espacio-4).getColorGrafico().equals(Color.RED) || espacios.get(espacio-3).getColorGrafico().equals(Color.RED) || espacios.get(espacio-1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+3).getColorGrafico().equals(Color.RED) || espacios.get(espacio+4).getColorGrafico().equals(Color.RED) || espacios.get(espacio+5).getColorGrafico().equals(Color.RED)){
                    return true;
                }
            }else if(espacio%3==0){
                if(espacios.get(espacio-5).getColorGrafico().equals(Color.RED) || espacios.get(espacio-4).getColorGrafico().equals(Color.RED) || espacios.get(espacio-3).getColorGrafico().equals(Color.RED) || espacios.get(espacio-1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+3).getColorGrafico().equals(Color.RED) || espacios.get(espacio+4).getColorGrafico().equals(Color.RED) || espacios.get(espacio+5).getColorGrafico().equals(Color.RED)){
                    return true;
                }
            }else if(espacio%4==0){
                if(espacios.get(espacio-5).getColorGrafico().equals(Color.RED) || espacios.get(espacio-4).getColorGrafico().equals(Color.RED) || espacios.get(espacio-1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+3).getColorGrafico().equals(Color.RED) || espacios.get(espacio+4).getColorGrafico().equals(Color.RED)){
                    
                }    
            }else{
                if(espacios.get(espacio-4).getColorGrafico().equals(Color.RED) || espacios.get(espacio-3).getColorGrafico().equals(Color.RED) || espacios.get(espacio+1).getColorGrafico().equals(Color.RED) || espacios.get(espacio+4).getColorGrafico().equals(Color.RED) || espacios.get(espacio+5).getColorGrafico().equals(Color.RED)){
                    
                }
            }
            
        }catch(Exception e){   
        }     
        return false;
    }
    
    public Edificio getEdificio(){
        return this.miEdificio;
    }
    
    public int getCantidadEspacios(){
        return this.espaciosConflictivos.size();
    }
    
}
