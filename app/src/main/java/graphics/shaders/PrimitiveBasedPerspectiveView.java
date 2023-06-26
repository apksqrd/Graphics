package graphics.shaders;

import graphics.linalg.Vector3;

public class PrimitiveBasedPerspectiveView extends PrimitiveBasedProjectorView {
    double degFov = 90, near = 0.1;

    @Override
    protected double projectedX(Vector3 coord3d) {
        return (coord3d.get(0) / (coord3d.get(2) - near)) * Math.tan(Math.toRadians(degFov) / 2)
                * ((double) this.getWidth()) / ((double) this.getHeight());
    }

    @Override
    protected double projectedY(Vector3 coord3d) {
        return (coord3d.get(1) / (coord3d.get(2) - near)) * Math.tan(Math.toRadians(degFov) / 2);
    }
}
