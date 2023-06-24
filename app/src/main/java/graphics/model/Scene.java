package graphics.model;

import java.util.Arrays;
import java.util.List;

public class Scene {
    private List<Shape> shapes;

    public Scene(Shape... shapes) {
        this.shapes = Arrays.asList((Shape[]) shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
