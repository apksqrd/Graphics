package graphics.shaders;

import java.awt.Graphics;

import javax.swing.JPanel;

import graphics.linalg.MathUtil;
import graphics.linalg.Vector3;
import graphics.model.Scene;
import graphics.model.Shape;
import graphics.model.Triangle;
import graphics.model.Vertex;

public class OrthogonalView extends JPanel {
    public Scene scene;

    private int[] getDisplayCoords(Vertex vertex) {
        Vector3 position = vertex.getPosition();
        // TODO: apply some transformation here

        // the old z is the new y
        double x = position.get(0), y = position.get(2);

        // FIXME: idk if the new max should be width or width - 1
        int displayX = (int) MathUtil.mapToRange(x, -1, 1, 0, this.getWidth());
        int displayY = (int) MathUtil.mapToRange(y, -1, 1, this.getHeight(), 0);

        return new int[] { displayX, displayY };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : scene.getShapes()) {
            if (shape instanceof Vertex vertex) {
                int[] displayCoords = getDisplayCoords(vertex);
                g.fillOval(displayCoords[0] - 5, displayCoords[1] - 5, 10, 10);
            } else if (shape instanceof Triangle triangle) {
                int[] displayXCoords = new int[3];
                int[] displayYCoords = new int[3];

                for (int i = 0; i < 3; i++) {
                    int[] displayCoords = getDisplayCoords(triangle.getVertex(i));
                    displayXCoords[i] = displayCoords[0];
                    displayYCoords[i] = displayCoords[1];
                }

                g.fillPolygon(displayXCoords, displayYCoords, 3);
            }
        }
    }
}
