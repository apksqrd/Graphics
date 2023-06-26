package graphics.model;

public class Triangle implements Shape, Transformable<Triangle> {
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

    @Override
    public Triangle transform(double[][] transformation) {
        return new Triangle(vert0.transform(transformation),
                vert1.transform(transformation),
                vert2.transform(transformation));
    }
}
