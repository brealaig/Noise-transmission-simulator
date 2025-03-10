package Vista;

import Modelo.ElementosEdificio.Edificio;
import Modelo.ElementosEdificio.Piso;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Simulacion3D extends Application {

    private static final float WIDTH = 1000;
    private static final float HEIGHT = 600;
    private static Edificio edificio;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final PointLight pointLight = new PointLight();

    private ArrayList<Sphere> nodosEdificio = new ArrayList<>();

    // Variables de movimiento
    private double cameraSpeed = 10;
    private double cameraX = 0, cameraY = 0, cameraZ = 0;
    private double cameraDeltaX = 0, cameraDeltaY = 0, cameraDeltaZ = 0;

    @Override
    public void start(Stage primaryStage) {
       

        Box box = prepareBox(edificio);
        Button btn = new Button("Hola");
        SmartGroup group = new SmartGroup();
        group.getChildren().add(box);
        group.getChildren().add(new AmbientLight());
        
        

        crearEdificioGrafico(edificio);

        for (Sphere nodo : nodosEdificio) {
            group.getChildren().add(nodo);
        }

        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(8000);
        camera.translateZProperty().set(-200);

        Scene scene = new Scene(group, WIDTH, HEIGHT, true);
        scene.setFill(Color.AQUA);
        scene.setCamera(camera);

        group.translateXProperty().set(0);
        group.translateYProperty().set(0);
        group.translateZProperty().set(0);
        
        

        initMouseControl(group, scene, primaryStage, camera);

        primaryStage.setTitle("Genuine Coder");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateCameraPosition(camera);
                pointLight.setRotate(pointLight.getRotate() + 1);
            }
        };
        timer.start();
    }

    private void updateCameraPosition(Camera camera) {
        cameraX += cameraDeltaX;
        cameraY += cameraDeltaY;
        cameraZ += cameraDeltaZ;
        camera.setTranslateX(cameraX);
        camera.setTranslateY(cameraY);
        camera.setTranslateZ(cameraZ);
    }

    private void initMouseControl(SmartGroup group, Scene scene, Stage stage, Camera camera) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
            xRotate = new Rotate(0, Rotate.X_AXIS),
            yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX + (anchorY + event.getSceneY()));
            angleY.set(anchorAngleY - anchorX + event.getSceneX());
        });

        scene.setOnKeyPressed(event -> handleKeyPressed(event));
        scene.setOnKeyReleased(event -> handleKeyReleased(event));

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
        });
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W -> cameraDeltaZ = cameraSpeed;
            case S -> cameraDeltaZ = -cameraSpeed;
            case A -> cameraDeltaX = -cameraSpeed;
            case D -> cameraDeltaX = cameraSpeed;
            case SPACE -> cameraDeltaY = -cameraSpeed;
            case SHIFT -> cameraDeltaY = cameraSpeed;
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W, S -> cameraDeltaZ = 0;
            case A, D -> cameraDeltaX = 0;
            case SPACE, SHIFT -> cameraDeltaY = 0;
        }
    }

    private Box prepareBox(Edificio edificio) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GREEN);
        Box box = new Box(10000, 20, 10000);
        box.setMaterial(material);
        box.setTranslateY((edificio.getPisos().size() * -100) + 50);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void iniciarSimulacion3D(String[] args) {
        launch(args);
    }

    public void crearNodo(int rojo, int verde, int azul, double posicionX, double posicionY, double posicionZ) {
        Sphere nodoHabitacion = new Sphere(20);
        nodoHabitacion.setTranslateX(posicionX * 100);
        nodoHabitacion.setTranslateY(posicionY * 100);
        nodoHabitacion.setTranslateZ(posicionZ * 100);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.rgb(rojo, verde, azul));
        nodoHabitacion.setMaterial(material);
        this.nodosEdificio.add(nodoHabitacion);
    }

    public void crearEdificioGrafico(Edificio miEdificioGrafico) {
        int totalPisos = miEdificioGrafico.getPisos().size();
        for (int i = 0; i < totalPisos; i++) {
            crearPisoGrafico(miEdificioGrafico.getPisos().get(i), totalPisos - i - 1);
        }
    }

    public void crearPisoGrafico(Piso piso, int numeroDePisoInvertido) {
        int columnas = 4;
        int filas = 3;

        for (int i = 0; i < piso.getNodos().size(); i++) {
            int x = i % columnas;
            int y = -numeroDePisoInvertido;
            int z = i / columnas;
            crearHabitacionGrafico(piso.getNodos().get(i).getColorGrafico(), x, y, z);
        }
    }
    
    public void crearPisoGraficoo(Piso piso, int numeroDePisoInvertido) {
        int columnas = 4;
        int filas = 3;

        for (int i = 0; i < piso.getNodos().size(); i++) {
            int x = i % columnas;
            int y = -numeroDePisoInvertido;
            int z = i / columnas;
            crearHabitacionGrafico(piso.getNodos().get(i).getColorGrafico(), x, y, z);
        }
    }

    public void crearHabitacionGrafico(java.awt.Color color, int posicionX, int posicionY, int posicionZ) {
        crearNodo(color.getRed(), color.getGreen(), color.getBlue(), (double) posicionX, (double) posicionY, (double) posicionZ);
    }

    public int getCantidadNodos() {
        return this.nodosEdificio.size();
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
    
    
}