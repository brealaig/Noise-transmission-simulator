package Modelo.ElementosEdificio;

public class MaterialEnvolvente {
    
    private String tipo;
    private float absorcionAcustica;
    private float grosor;
    
    public MaterialEnvolvente(String tipo, float absorcionAcustica, float grosor){
        this.tipo = tipo;
        this.absorcionAcustica = absorcionAcustica;
        this.grosor = grosor;
    }
    
    public String getNombre(){
        return tipo;
    }
    
    public float getAbsorcionAcustica(){
        return absorcionAcustica;
    }
    
    public float getGrosor(){
        return grosor;
    }

}
