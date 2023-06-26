package graphics.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graphics.linalg.Vector3;

public class TriangleMesh implements Shape {
    private Triangle[] triangles;

    public Triangle[] getTriangles() {
        return triangles;
    }

    public void setTriangles(Triangle[] triangles) {
        this.triangles = triangles;
    }

    public TriangleMesh(Triangle... triangles) {
        this.triangles = triangles;
    }

    public static TriangleMesh parseIndexedTriangles(String csvFileLocation) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(csvFileLocation));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Vertex[] vertices = new Vertex[scanner.nextInt()];
        scanner.nextLine();

        for (int i = 0; i < vertices.length; i++) {
            String[] coordinates = scanner.nextLine().split(",");
            vertices[i] = new Vertex(new Vector3(
                    Double.parseDouble(coordinates[0]),
                    Double.parseDouble(coordinates[1]),
                    Double.parseDouble(coordinates[2])));
        }

        Triangle[] triangles = new Triangle[scanner.nextInt()];
        scanner.nextLine();

        for (int i = 0; i < triangles.length; i++) {
            String[] vertexIndices = scanner.nextLine().split(",");
            triangles[i] = new Triangle(
                    vertices[Integer.parseInt(vertexIndices[0])],
                    vertices[Integer.parseInt(vertexIndices[1])],
                    vertices[Integer.parseInt(vertexIndices[2])]);

        }

        return new TriangleMesh(triangles);
    }
}
