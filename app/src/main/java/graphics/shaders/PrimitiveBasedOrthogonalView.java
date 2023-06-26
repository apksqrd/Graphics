package graphics.shaders;

import graphics.linalg.Vector3;

public class PrimitiveBasedOrthogonalView extends PrimitiveBasedProjectorView {
    @Override
    protected double projectedX(Vector3 coord3d) {
        return coord3d.get(0);
    }

    @Override
    protected double projectedY(Vector3 coord3d) {
        return coord3d.get(1);
    }
}
