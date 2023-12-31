/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package graphics;

import javax.swing.JFrame;

import graphics.linalg.LinAlg;
import graphics.model.Scene;
import graphics.model.TriangleMesh;
import graphics.shaders.PrimitiveBasedOrthogonalView;
import graphics.shaders.PrimitiveBasedPerspectiveView;
import graphics.shaders.PrimitiveBasedProjectorView;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void showOrthogonalProjection() {
        Scene scene = new Scene(
                TriangleMesh.parseIndexedTriangles("app/src/main/resources/shapes/cube.csv"));

        JFrame frame = new JFrame("Orthogonal Projection");
        frame.setSize(540, 540);

        PrimitiveBasedProjectorView panel = new PrimitiveBasedPerspectiveView();

        panel.scene = scene;

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        int count = 0;

        while (true) {
            double rotFactor = count / 3600000.0;

            panel.preViewTransformation = LinAlg.composeMany(
                    new double[][] { // shift further
                            { 1, 0, 0, 0 },
                            { 0, 1, 0, 1 },
                            { 0, 0, 1, 3 },
                            { 0, 0, 0, 1 }
                    },
                    // new double[][] { // scale
                    // { 0.5, 0, 0, 0 },
                    // { 0, 0.5, 0, 0 },
                    // { 0, 0, 0.5, 0 },
                    // { 0, 0, 0, 1 } },
                    new double[][] { // rotate cube up
                            { 1, 0, 0, 0 },
                            { 0, Math.cos(Math.PI / 4), -Math.sin(Math.PI / 4), 0 },
                            { 0, Math.sin(Math.PI / 4), Math.cos(Math.PI / 4), 0 },
                            { 0, 0, 0, 1 }
                    },
                    new double[][] { // rotate right
                            { Math.cos(rotFactor), -Math.sin(rotFactor), 0, 0 },
                            { Math.sin(rotFactor), Math.cos(rotFactor), 0, 0 },
                            { 0, 0, 1, 0 },
                            { 0, 0, 0, 1 }
                    },
                    new double[][] { // shift so center is origin
                            { 1, 0, 0, -0.5 },
                            { 0, 1, 0, -0.5 },
                            { 0, 0, 1, -0.5 },
                            { 0, 0, 0, 1 }
                    });

            frame.repaint();
            count++;

            // try {
            // Thread.sleep(100);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
    }

    public static void main(String[] args) {
        showOrthogonalProjection();
    }
}
