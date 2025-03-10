/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ElementosGrafo;

/**
 *
 * @author tic-asisweb01
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GrafoPanel extends JPanel {
    private List<Espacio> nodes;
    private List<Arista> edges;

    public GrafoPanel(List<Espacio> nodes, List<Arista> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar aristas
        g2d.setColor(Color.BLACK);
        for (Arista edge : edges) {
            g2d.drawLine(edge.from.x, edge.from.y, edge.to.x, edge.to.y);
        }

        // Dibujar nodos
        int radius = 20;
        for (Espacio node : nodes) {
            g2d.setColor(node.colorGrafico);
            g2d.fillOval(node.x - radius, node.y - radius, 2 * radius, 2 * radius);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
}
