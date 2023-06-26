package graphics.linalg;

public class LinAlg {
    public static double[][] IDENTITY4 = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

    public static double[][] matMult(double[][] mat0, double[][] mat1) {
        // TODO: Check dims

        double[][] product = new double[mat0.length][mat1[0].length];

        for (int row = 0; row < product.length; row++) {
            for (int col = 0; col < product[row].length; col++) {
                double dotProd = 0;

                for (int i = 0; i < mat0[0].length; i++) {
                    dotProd += mat0[row][i] * mat1[i][col];
                }

                product[row][col] = dotProd;
            }
        }

        return product;
    }

    public static double[][] composeMany(double[][]... transformations) {
        double[][] product = IDENTITY4;
        for (double[][] transformation : transformations) {
            product = matMult(product, transformation);
        }
        return product;
    }
}
