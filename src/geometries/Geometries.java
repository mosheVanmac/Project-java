package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Geometries implements Intersectable {
    public ArrayList<Intersectable> l1 = new ArrayList<Intersectable>();

    public Geometries() {
        this.l1 = null;
    }

    public Geometries(Intersectable... geometries) {
        this.l1 = l1;
    }

    public void add(Intersectable... geometries)
    { //Iterator _geometries=l1.iterator();
        if (geometries.length > 0) {
            for (Intersectable geom : geometries) {
                l1.add(geom);

            }
        }
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

}