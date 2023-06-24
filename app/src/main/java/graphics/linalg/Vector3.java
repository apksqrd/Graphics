package graphics.linalg;

public class Vector3 {
    public final double x, y, z;

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
}
