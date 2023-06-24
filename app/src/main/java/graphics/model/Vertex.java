package graphics.model;

import graphics.linalg.Vector3;

public class Vertex implements Shape {
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
}
