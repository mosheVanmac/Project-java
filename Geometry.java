package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry {
    /**
     * interface for all geometries with getNormal
     */
        Vector getNormal(Point3D point){return null;};

}

