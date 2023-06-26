package graphics.shaders;

import static graphics.linalg.LinAlg.matMult;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Graphics;

import javax.swing.JPanel;

import graphics.linalg.MathUtil;
import graphics.linalg.Vector3;
import graphics.model.Scene;
import graphics.model.Shape;
import graphics.model.Triangle;
import graphics.model.TriangleMesh;
import graphics.model.Vertex;

public class OrthogonalView extends JPanel {
    public Scene scene;

    public double[][] preViewTransformation = {
            { 1, 0, 0, 0 },
            { 0, cos(PI / 4), -sin(PI / 4), 0 },
            { 0, sin(PI / 4), cos(PI / 4), 0 },
            { 0, 0, 0, 1 }
    }; // view slightly from the top

    private int[] getDisplayCoords(Vertex vertex) {
        Vector3 rawPosition = vertex.getPosition();
        // TODO: apply some transformation here

        Vector3 displayPosition = new Vector3(matMult(preViewTransformation, rawPosition.vstack4()));

        // the old z is the new y
        double x = displayPosition.get(0), y = displayPosition.get(2);

        // FIXME: idk if the new max should be width or width - 1
        int displayX = (int) MathUtil.mapToRange(x, -1, 1, 0, this.getWidth());
        int displayY = (int) MathUtil.mapToRange(y, -1, 1, this.getHeight(), 0);

        return new int[] { displayX, displayY };
    }

    private void paintShape(Shape shape, Graphics g) {
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

            g.drawPolygon(displayXCoords, displayYCoords, 3);
        }
        if (shape instanceof TriangleMesh mesh) {
            for (Triangle triangle : mesh.getTriangles()) {
                paintShape(triangle, g);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : scene.getShapes()) {
            paintShape(shape, g);
        }
    }
}
