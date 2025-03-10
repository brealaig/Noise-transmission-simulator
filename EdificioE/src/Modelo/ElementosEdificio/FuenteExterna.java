package Modelo.ElementosEdificio;


public class FuenteExterna extends Fuente {
    private String direccionDeProveniencia;

    public FuenteExterna(String identificador, int frecuencia, int duracion, String tipo, String direccionDeProveniencia) {
        super(identificador, frecuencia, duracion, tipo);
        this.direccionDeProveniencia = direccionDeProveniencia;
    }

    public String getDireccionDeProveniencia() {
        return direccionDeProveniencia;
    }

    public void setDireccionDeProveniencia(String direccionDeProveniencia) {
        this.direccionDeProveniencia = direccionDeProveniencia;
    }

    @Override
    public String toString() {
        return "FuenteExterna{" +
                "identificador='" + getIdentificador() + '\'' +
                ", frecuencia=" + getFrecuencia() +
                ", duracion=" + getDuracion() +
                ", tipo='" + getTipo() + '\'' +
                ", direccionDeProveniencia='" + direccionDeProveniencia + '\'' +
                '}';
    }
}


