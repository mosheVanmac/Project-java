package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class that represents sphere
 */
public class Sphere extends RadialGeometry implements Geometry{
    Point3D _center;

    /**
     * constructor
     * @param _radius
     * @param _center
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }

    /**
     * returns normal
     * @param _x
     * @return Vector
     */
    public Vector getNormal(Point3D _x){
        return  getNormal(null);
    }

    /**
     * returns _center
     * @return Point3D
     */
    public Point3D get_center() {
        return _center;
    }
/*toString Function*/
    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }
}
