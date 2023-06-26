package graphics.model;

public interface Transformable<T extends Transformable<T>> {
    public T transform(double[][] transformation);
}
