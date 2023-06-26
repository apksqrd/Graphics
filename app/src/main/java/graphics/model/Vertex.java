package graphics.model;

import graphics.linalg.Vector3;

public class Vertex implements Shape, Transformable<Vertex> {
    private Vector3 position;

    public Vertex(Vector3 position) {
        this.position = position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    @Override
    public Vertex transform(double[][] transformation) {
        return new Vertex(position.transform(transformation));
    }
}
