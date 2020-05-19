package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * class to represent tube
 */
public class Tube extends RadialGeometry implements Geometry {
    Ray _axisRay;

    /**
     * constructor
     *
     * @param _radius
     * @param _axisRay
     */
    public Tube(double _radius, Ray _axisRay) {
        super(_radius);
        this._axisRay = _axisRay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tube tube = (Tube) o;
        return Objects.equals(_axisRay, tube._axisRay);
    }



    /**
     * returns _axisRay
     *
     * @return Ray
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * return normal
     *
     * @param point
     * @return Vector
     */
    public Vector getNormal(Point3D point) {

        //The vector from the point of the cylinder to the given point
        Point3D p0 = _axisRay.get_p0();
        Vector v = _axisRay.get_dir();
        double t=v.dotProduct(p0.subtract(point));
        //We need the projection to multiply the _direction unit vector
        Vector vector1 = p0.subtract(point);

        if (!isZero(t)) {
            // projection of P-O on the ray:
            p0=(p0.add(v.scale(t))).get_head();
        }

        //This vector is orthogonal to the _direction vector.
        Vector check = p0.subtract(point);
        return check.normalize();
    }

    @Override
    public String toString() {
        return "ray: " + _axisRay +
                ", radius: " + _radius;
    }
    public ArrayList<Point3D> findIntersections(Ray ray){return null;}
}

