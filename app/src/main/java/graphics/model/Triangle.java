package graphics.model;

public class Triangle implements Shape {
    private Vertex vert0, vert1, vert2;

    public Vertex getVertex(int i) {
        return getAllVertices()[i];
    }

    public Vertex[] getAllVertices() {
        return new Vertex[] { vert0, vert1, vert2 };
    }

    public Triangle(Vertex vert0, Vertex vert1, Vertex vert2) {
        this.vert0 = vert0;
        this.vert1 = vert1;
        this.vert2 = vert2;
    }
}
