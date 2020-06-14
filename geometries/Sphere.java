package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class that represents sphere
 */
public class Sphere extends RadialGeometry {
    Point3D _center;

    /**
     * constructor
     * @param _radius
     * @param _center
     */
    public Sphere(double _radius, Point3D _center) {
this(Color.BLACK,new Material(0,0,0),_radius,_center);

    }

    public Sphere(Color color, Material material,double radius, Point3D point3D) {
        super(color,material,radius);
        this._center=point3D;
    }
    public Sphere(Material material,double radius,Point3D point3D)
    {this(Color.BLACK,material,radius,point3D);

    }

    /**
     * returns normal
     * @param _other
     * @return Vector
     */
    public Vector getNormal(Point3D _other){
        return  _center.subtract(_other).normalize();
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
    public LinkedList<Geopoint> findIntersections(Ray ray,double maximumdistance) {
        LinkedList<Geopoint> arl=new LinkedList<>();
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
        double distancetone=alignZero(t1-maximumdistance);
        double distancettwo=alignZero(t2-maximumdistance);
        if ((t1 >0)&&(t2>0)&&(distancetone<=0)&&(distancettwo<=0)) {
            p1 = (ray.get_p0().add(ray.get_dir().scale(t1))).get_head();
            arl.add(new Geopoint(this,p1));
             p2 = (ray.get_p0().add(ray.get_dir().scale(t2))).get_head();
             arl.add(new Geopoint(this,p2));
        }
        return arl;
    }

public int findNumIntersections(Ray ray,Double maxdistance)
{int size;
List<Geopoint> listone=findIntersections(ray);
if(listone!=null)
{ size=listone.size();}
else{size=0;}
return size;

}
}