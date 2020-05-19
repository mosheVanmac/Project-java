package geometries;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface to force implementation of getNormal
 */
public interface Geometry extends Intersectable{
    Vector getNormal(Point3D point);
}

