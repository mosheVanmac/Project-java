package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

public class Plane implements Geometry
{
    Point3D _p;
    primitives.Vector _normal;

    /**
     * constructor
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        _p=new Point3D(p1);
        Vector U=new Vector(p1,p2);
        Vector V=new Vector(p1,p3);
        Vector N=U.crossProduct(V);
        N.normalize();
        _normal=N.scale(-1);
    }

    /**
     * constructor
     * @param p1
     * @param _normal
     */
    public Plane(Point3D p1,Vector _normal)
    {
        _p=new Point3D(p1);
        this._normal=_normal;
    }

    /**
     * return vector normal
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point3D p)
    {
        return _normal;

    }
    //because of Polygon
    public Vector getNormal() {
        return getNormal(null);

    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray ray)
    {

        double t=alignZero(alignZero(this.getNormal().dotProduct(this._p.subtract(this.getNormal().get_head())))/getNormal().dotProduct(ray.get_dir()));
        Point3D p=(ray.get_p0().add((ray.get_dir().scale(t)))).get_head();
        if(t>0){ArrayList<Point3D>arl=new ArrayList<Point3D>();arl.add(p);return arl;}
        return null;
    }
}
