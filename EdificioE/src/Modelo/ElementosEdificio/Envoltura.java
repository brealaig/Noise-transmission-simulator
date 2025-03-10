package Modelo.ElementosEdificio;

public class Envoltura{

    private String identificador;
    private MaterialEnvolvente paredDerecha;
    private MaterialEnvolvente paredIzquierda;
    private MaterialEnvolvente paredFrontal;
    private MaterialEnvolvente paredTrasera;
    private MaterialEnvolvente techo;
    private MaterialEnvolvente suelo;
    
    
   public Envoltura(String identificador, MaterialEnvolvente paredDerecha, MaterialEnvolvente paredIzquierda, MaterialEnvolvente paredFrontal, MaterialEnvolvente paredTrasera, MaterialEnvolvente techo, MaterialEnvolvente suelo){
        this.identificador = identificador;
        this.paredDerecha = paredDerecha;
        this.paredIzquierda = paredIzquierda;
        this.paredFrontal = paredFrontal;
        this.paredTrasera = paredTrasera;
        this.techo = techo;
        this.suelo = suelo;
    }
    
    public double calcularReverberacion() {
        double absorcionTotal = calcularAbsorcionTotal();
        return 0.161 * 15 / absorcionTotal;
    }

    private double calcularAbsorcionTotal() {
        double absorcionTotal = 0;
        absorcionTotal += paredDerecha.getGrosor() * paredDerecha.getAbsorcionAcustica();
        absorcionTotal += paredIzquierda.getGrosor() * paredIzquierda.getAbsorcionAcustica();
        absorcionTotal += paredFrontal.getGrosor() * paredFrontal.getAbsorcionAcustica();
        absorcionTotal += paredTrasera.getGrosor() * paredTrasera.getAbsorcionAcustica();
        absorcionTotal += techo.getGrosor() * techo.getAbsorcionAcustica();
        absorcionTotal += suelo.getGrosor() * suelo.getAbsorcionAcustica();
        return absorcionTotal;
    }
    
    public String getIdentificador() {
        return identificador;
    }
    
    public MaterialEnvolvente getParedDerecha() {
        return paredDerecha;
    }

    public MaterialEnvolvente getParedIzquierda() {
        return paredIzquierda;
    }

    public MaterialEnvolvente getParedFrontal() {
        return paredFrontal;
    }

    public MaterialEnvolvente getParedTrasera() {
        return paredTrasera;
    }

    public MaterialEnvolvente getTecho() {
        return techo;
    }

    public MaterialEnvolvente getSuelo() {
        return suelo;
    }

}
