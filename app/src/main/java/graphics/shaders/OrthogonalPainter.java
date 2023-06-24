package graphics.shaders;

import java.awt.Image;

import graphics.gui.ImagePainting;
import graphics.linalg.MathUtil;
import graphics.linalg.Vector3;
import graphics.model.Scene;
import graphics.model.Shape;
import graphics.model.Vertex;

public class OrthogonalPainter implements ScenePainter {
    public int displayWidth = 10, displayHeight = 10;

    @Override
    public Image paintScene(Scene scene) {
        int[][] painting = new int[displayHeight][displayWidth];

        for (Shape shape : scene.getShapes()) {
            if (shape instanceof Vertex vertex) {
                Vector3 position = vertex.getPosition();
                // TODO: apply some transformation here

                // the old z is the new y
                double x = position.get(0), y = position.get(2);

                // FIXME: idk if the new max should be width or width - 1
                int displayCol = (int) MathUtil.mapToRange(x, -1, 1, 0, displayWidth);
                int displayRow = (int) MathUtil.mapToRange(y, -1, 1, displayHeight, 0);

                if (displayCol < 0 || displayRow < 0 || displayCol >= displayWidth || displayRow >= displayHeight) {
                    continue;
                }

                painting[displayRow][displayCol] = 0xFF000000;
            }
        }

        return ImagePainting.intArgbGridToImage(painting);
    }
}
