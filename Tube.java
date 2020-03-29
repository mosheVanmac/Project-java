package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to represent tube
 */
public class Tube extends RadialGeometry implements Geometry{
    Ray _axisRay;

    /**
     * constructor
     * @param _radius
     * @param _axisRay
     */
    public Tube(double _radius, Ray _axisRay) {
        super(_radius);
        this._axisRay = _axisRay;
    }

    /**
     * returns _axisRay
     * @return Ray
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * return normal
     * @param point
     * @return Vector
     */
    public Vector getNormal(Point3D point){
        return getNormal(null);
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                '}';
    }
}
