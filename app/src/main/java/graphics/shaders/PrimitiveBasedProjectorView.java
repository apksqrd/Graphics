package graphics.shaders;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import graphics.gui.DelayedPriorityConsumerQueue;
import graphics.linalg.MathUtil;
import graphics.linalg.Vector3;
import graphics.model.Scene;
import graphics.model.Shape;
import graphics.model.Triangle;
import graphics.model.TriangleMesh;
import graphics.model.Vertex;

public abstract class PrimitiveBasedProjectorView extends JPanel {
    public Scene scene;

    public double[][] preViewTransformation;

    protected abstract double projectedX(Vector3 coord3d);

    protected abstract double projectedY(Vector3 coord3d);

    private int[] getDisplayCoords(Vertex vertex) {
        double x = projectedX(vertex.getPosition()), y = projectedY(vertex.getPosition());

        // FIXME: idk if the new max should be width or width - 1
        int displayX = (int) MathUtil.mapToRange(x, -1, 1, 0, this.getWidth());
        int displayY = (int) MathUtil.mapToRange(y, -1, 1, this.getHeight(), 0);

        return new int[] { displayX, displayY };
    }

    private void paintShape(Shape shape, DelayedPriorityConsumerQueue<Graphics> strokes) {
        if (shape instanceof Vertex vertex) {
            int[] displayCoords = getDisplayCoords(vertex);
            strokes.addConsumer(g -> g.fillOval(displayCoords[0] - 5, displayCoords[1] - 5, 10, 10),
                    vertex.getPosition().get(2));
        } else if (shape instanceof Triangle triangle) {
            int[] displayXCoords = new int[3];
            int[] displayYCoords = new int[3];

            for (int i = 0; i < 3; i++) {
                int[] displayCoords = getDisplayCoords(triangle.getVertex(i));
                displayXCoords[i] = displayCoords[0];
                displayYCoords[i] = displayCoords[1];
            }

            double depth = 0;
            for (Vertex vertex : triangle.getAllVertices()) {
                depth += vertex.getPosition().get(2);
            }

            depth /= 3.0;

            strokes.addConsumer(g -> {
                // ! the coloring is not meant to simulate actual shading right now. it is just
                // there to make things less bland.
                g.setColor(new Color((float) (Math.sin(triangle.getVertex(0).getPosition().get(0)) / 2 + 0.5),
                        (float) (Math.sin(triangle.getVertex(0).getPosition().get(0)) / 2 + 0.5),
                        (float) (Math.sin(triangle.getVertex(0).getPosition().get(0)) / 2 + 0.5)));
                g.fillPolygon(displayXCoords, displayYCoords, 3);
                g.setColor(Color.BLACK);
                g.drawPolygon(displayXCoords, displayYCoords, 3);
            }, depth);
        } else if (shape instanceof TriangleMesh mesh) {
            for (Triangle triangle : mesh.getTriangles()) {
                paintShape(triangle, strokes);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DelayedPriorityConsumerQueue<Graphics> strokes = new DelayedPriorityConsumerQueue<>();

        for (Shape shape : scene.transform(preViewTransformation).getShapes()) {
            paintShape(shape, strokes);
        }

        strokes.acceptAll(g);
    }
}
