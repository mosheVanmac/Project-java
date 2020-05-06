package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 *class that represents a triangle in a 3d environment
 */
public class Triangle extends Polygon {//****CONSTRUCTOR*****/
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    /**
     * return vector normal
     *
     * @param _other
     * @return Vector
     */
    public Vector getNormal(Point3D _other) {
        return (super.getNormal(_other));
    }

    @Override
    public String toString() {
        String result = "";
        for (Point3D p : _vertices) {
            result += p.toString();
        }
        return result;
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> arl=_plane.findIntersections(ray);
        Vector v1=ray.get_p0().subtract(super.get_vertices().get(0));
        Vector v2=ray.get_p0().subtract(super.get_vertices().get(1));
        Vector v3=ray.get_p0().subtract(super.get_vertices().get(2));
        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();
        if((ray.get_p0().subtract(arl.get(0)).dotProduct(n1))*(ray.get_p0().subtract(arl.get(0)).dotProduct(n2))*(ray.get_p0().subtract(arl.get(0)).dotProduct(n3))>0.0)
        {ArrayList<Point3D>intersectsTriangle=new ArrayList<Point3D>();arl.add(arl.get(0));return intersectsTriangle;
        }
        return null;
         }
}
