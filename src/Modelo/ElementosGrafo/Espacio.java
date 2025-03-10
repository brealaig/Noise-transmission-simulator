package Modelo.ElementosGrafo;

import Modelo.ElementosEdificio.Actividad;
import Modelo.ElementosEdificio.MaterialEnvolvente;
import Modelo.ElementosEdificio.MetroCuadrado;
import Modelo.ElementosEdificio.Fuente;
import Modelo.ElementosEdificio.Envoltura;
import Modelo.ElementosEdificio.FuenteExterna;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Espacio{
    
    //Variables de información del espacio
    protected ArrayList<MetroCuadrado> espacioHabitacion;
    protected ArrayList<Fuente> fuentesExternas;
    protected ArrayList<MaterialEnvolvente> materialesEnvolventes;
    //Variable nombre del espacio
    protected String identificador;
    // Variables para la gestión de habitabilidad
    protected int tiempoDeReverberacion;
    protected short cantidadDePersonas;
    protected short indiceDeHabitabilidad;
    protected Envoltura envolturaDelEspacio;
    protected double tolerancia;
    
    //protected boolean esPerimetral;
    //protected String orientacionPerimetro;
    protected int numeroEspacio;
    
    //Variables de posición en el grafo
    public int x, y;
    public Color colorEspacio;
    public Color colorGrafico;
    
    //Variables de actividad
    public Actividad actividad;
    
    
    public Espacio(int x, int y, Color colorEspacio, Color colorGrafico, Actividad actividad, int numeroEspacio) {
        //Inicialización de variables para muestro en grafos y vista
        this.x = x;
        this.y = y;
        this.colorEspacio = colorEspacio;
        this.colorGrafico = colorGrafico;
        this.actividad = actividad;
        this.numeroEspacio = numeroEspacio;
        espacioHabitacion = new ArrayList<>();
        
        //Inicialización de los materiales del espacio y las envolturas correspondientes
        generarMaterialesEnvolventes();
        crearEnvoltura();
        
        //Inicialización de las fuentes externas
        fuentesExternas = new ArrayList<>();
    }
    
    //Nota: Las suma de las fuentes internas se genera por la actividad que esta provoque en el espacio
    //Las fuentes externas son generadas por puntos de ruido que suceden alrededor del espacio
    
    public void inicializarFuentesExternas() {
        List<FuenteExterna> posiblesFuentesExternas = List.of(
                new FuenteExterna("Tráfico", 70, 7200, "Ruido de la calle", "Norte"),
                new FuenteExterna("Construcción", 90, 10800, "Obra en construcción", "Este"),
                new FuenteExterna("Parque", 50, 14400, "Gente en el parque", "Sur"),
                new FuenteExterna("Aire acondicionado", 20, 36000, "Ruido constante", "Oeste"),
                new FuenteExterna("Bocina de auto", 50, 300, "Ruido intermitente", "Norte"),
                new FuenteExterna("Música de fiesta", 75, 18000, "Música alta", "Este")
        );

        Random random = new Random();
        int numeroDeFuentesAAgregar = random.nextInt(4) + 1; // Entre 1 y 4 fuentes

        for (int i = 0; i < numeroDeFuentesAAgregar; i++) {
            FuenteExterna fuenteSeleccionada = posiblesFuentesExternas.get(random.nextInt(posiblesFuentesExternas.size()));
            fuentesExternas.add(fuenteSeleccionada);
        }
    }

    public int calcularSumatoriaFrecuencias() {
        int sumatoriaFrecuencias = 0;
        for (Fuente fuente : fuentesExternas) {
            //Formula de decibeles en el espacio
            sumatoriaFrecuencias += (double) (10*(Math.log10(fuente.getFrecuencia())));
        }
        return sumatoriaFrecuencias;
    }

    public ArrayList<Fuente> getFuentesExternas() {
        return fuentesExternas;
    }
    
    //Método que calcula la tolerancia de ruido con base la heuristica de las fuentes internas y externas
    public void calcularTolerancia(){
        //Antes de calcular la tolerancia inicializamos las fuentes externas
        inicializarFuentesExternas();
        //La tolerancia equivale a la suma de las frecuencias de las fuentes internas y externas de los espacios
        this.tolerancia =  ((double) this.actividad.calcularDecibelesFuentesInternas() + calcularSumatoriaFrecuencias()) - envolturaDelEspacio.calcularReverberacion();
    }
    
    public Color calcularHabitabilidad(){
        //Dada la tolerancia se calcula el color de habitabilidad
        if(this.tolerancia< 50){
            return Color.GREEN;
        } else if(this.tolerancia < 90){
            return Color.YELLOW;
        } else{
            return Color.RED;
        }
    }
    
    public void crearEnvoltura(){
        envolturaDelEspacio = new Envoltura("NN",materialesEnvolventes.get(1),materialesEnvolventes.get(1),materialesEnvolventes.get(0),materialesEnvolventes.get(1),materialesEnvolventes.get(4),materialesEnvolventes.get(4));
    }
    
    protected void generarMaterialesEnvolventes(){
        materialesEnvolventes = new ArrayList<>();
        
        MaterialEnvolvente vidrio = new MaterialEnvolvente("Vidrio",(float) 27,(float) 0.03);
        MaterialEnvolvente ladrilloCeramico = new MaterialEnvolvente("Ladrillo ceramico",(float) 49,(float) 13);
        MaterialEnvolvente hormigonArmado = new MaterialEnvolvente("Hormigon armado",(float) 52,(float) 12);
        MaterialEnvolvente tabiqueHuecoDoble = new MaterialEnvolvente("Tabique hueco doble",(float) 36,(float) 12);
        MaterialEnvolvente losaAlveolarMaciza = new MaterialEnvolvente("Lozas alveolares sencillas",(float) 71,(float) 15);
        
        materialesEnvolventes.add(vidrio);
        materialesEnvolventes.add(hormigonArmado);
        materialesEnvolventes.add(ladrilloCeramico);
        materialesEnvolventes.add(tabiqueHuecoDoble);
        materialesEnvolventes.add(losaAlveolarMaciza);    
    }
    
    public void agregarMetro(MetroCuadrado metroCuadrado){
        espacioHabitacion.add(metroCuadrado);
    }
    
    public ArrayList<MetroCuadrado> getEspacioHabitacion(){
        return espacioHabitacion;
    }
    
    public double getTolerancia(){
        return this.tolerancia;
    }
    
    public String getIdentificador(){
        return identificador;
    }
    
    public Envoltura getEnvolturaDelEspacio() {
        return envolturaDelEspacio;
    }
    
    public Color getColorEspacio(){
        return colorEspacio;
    }
    
    public void setColorEspacio(Color color){
        this.colorEspacio = color;
    }
    public Color getColorGrafico() {
        return colorGrafico;
    }
    
    public void setColorGrafico(Color color){
        this.colorGrafico = color;
    }
    
    public int getNumeroEspacio(){
        return numeroEspacio;
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    
    public void setActividad(Actividad actividad){
        this.actividad = actividad;
    }
    
    public Actividad getActividad(){
        return actividad;
    }
}
