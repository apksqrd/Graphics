package graphics.linalg;

public class MathUtil {
    public static double mapToRange(double point, double initialMin, double initialMax, double newMin, double newMax) {
        return newMin + ((point - initialMin) / (initialMax - initialMin)) * (newMax - newMin);
    }
}
