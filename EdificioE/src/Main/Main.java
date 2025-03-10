package Main;


import Controlador.Controlador;
import Vista.MenuCreacion;

public class Main{

    public static void main(String[] args){

        //Inicialización de la interfaz grafica

        MenuCreacion ventana = new MenuCreacion();
        Controlador miControlador = new Controlador(ventana,args);

        miControlador.iniciar();  

    }
}
