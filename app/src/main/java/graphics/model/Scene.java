package graphics.model;

import java.util.Arrays;
import java.util.List;

public class Scene implements Transformable<Scene> {
    private List<Shape> shapes;

    public Scene(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Scene(Shape... shapes) {
        this.shapes = Arrays.asList((Shape[]) shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public Scene transform(double[][] transformation) {
        List<Shape> transformedShapes = shapes.stream().map((shape) -> {
            if (shape instanceof Transformable transformable) {
                return (Shape) transformable.transform(transformation);
            } else {
                return shape;
            }
        }).toList();

        return new Scene(transformedShapes);
    }
}
