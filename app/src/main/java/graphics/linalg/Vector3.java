package graphics.linalg;

import graphics.model.Transformable;

public class Vector3 implements Transformable<Vector3> {
    public final double x, y, z;

    public Vector3(double[] idk) {
        // TODO: Rename
        x = idk[0];
        y = idk[1];
        z = idk[2];
    }

    public Vector3(double[][] idk) {
        // TODO: Rename
        x = idk[0][0];
        y = idk[1][0];
        z = idk[2][0];
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double get(int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    protected Vector3 clone() {
        return new Vector3(x, y, z);
    }

    public double[][] vstack() {
        return new double[][] {
                { x },
                { y },
                { z }
        };
    }

    public double[][] vstack4() {
        return new double[][] {
                { x },
                { y },
                { z },
                { 1 } // w, for translation
        };
    }

    @Override
    public Vector3 transform(double[][] transformation) {
        return new Vector3(LinAlg.matMult(transformation, vstack4()));
    }
}
