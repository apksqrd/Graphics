package graphics.linalg;

public class LinAlg {
    public static double[][] matMult(double[][] mat0, double[][] mat1) {
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
}
