package Vista;

import Modelo.ElementosGrafo.Espacio;

import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

public class MostrarHabitacionPanel extends JPanel {
    private Espacio habitacion;

    public MostrarHabitacionPanel(Espacio habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 5;
        int y = 10;
        int width = 100;
        int height = 30;
        int count = 0;
        
        for (int i =0 ; i<= habitacion.getEspacioHabitacion().size()- 1 ; i++) {
            g.setColor(Color.getHSBColor(334, 46, 85));
            g.fillRect(x, y, width, height);
            g.setColor(Color.black);
            if(habitacion.getEspacioHabitacion().get(i).getFuentes().size() > 0){
                g.drawString(habitacion.getEspacioHabitacion().get(i).getFuentes().get(0).getIdentificador(), x + 5, y + 20);
            }
            
            x += width + 10;
            count++;
            if (count % 5 == 0) {
                x = 5;
                y += height + 20;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(541, 304); // Ajusta el tamaño según sea necesario
    }
}
