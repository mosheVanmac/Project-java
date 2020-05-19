package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

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
     * @param _other
     * @return Vector
     */
    public Vector getNormal(Point3D _other){
        return  _center.subtract(_other).normalize();
        //return  getNormal(new Coordinate(_other.get_x().get()-_center.get_x().get()),
         //       new Coordinate(_other.get_x().get()));
    }

    /**
     * returns _center
     * @return Point3D
     */
    public Point3D get_center() {
        return _center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> arl=new ArrayList<Point3D>();
        Point3D p1;
        Point3D p2;
        Vector u = ray.get_p0().subtract(this.get_center());
        double tm = ray.get_dir().dotProduct(u);
        double d = sqrt(u.lengthSquared() - tm * tm);
        if (d > get_radius()) {
            return null;
        }
        double th = sqrt(this.get_radius() * this.get_radius() - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if (t1 >= 0) {
            p1 = (ray.get_p0().add(ray.get_dir().scale(t1))).get_head();
            arl.add(p1);
        }
        if (t2 >= 0) {

             p2 = (ray.get_p0().add(ray.get_dir().scale(t2))).get_head();
             arl.add(p2);
        }
        return arl;
    }
}
