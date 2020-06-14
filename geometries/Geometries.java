package geometries;
import primitives.Point3D;
import primitives.Ray;
import java.util.LinkedList;
import java.util.List;

/**
 * class for adding intersections of ray with more than one geometries which have intersections with ray.
 */
public class Geometries implements Intersectable {
    public LinkedList<Intersectable> l1 = new LinkedList<Intersectable>();
/********constructors******************/
    public Geometries() {
        this.l1 = null;
    }

    public Geometries(Intersectable... geometries)
    {
        this.l1 = l1;
    }

    /**
     * adds an intersection to LinkedList of intersections
     * @param geometries
     */
    public void add(Intersectable... geometries){
//if(geometries!=null){
            //for (Intersectable geom : geometries)
            //{if(geom!=null)
            //{ l1.add(geom);}
            //}
        for(Intersectable geom:geometries){
        l1.push(geom);
        }}
    @Override
    public LinkedList<Geopoint> findIntersections(Ray ray,double maxdistance) {
        LinkedList<Geopoint>intersectionPoints=null;
        for(Intersectable geom:l1)
        {List<Geopoint>geometryIntersections=geom.findIntersections(ray);
          for(Geopoint intersection:geometryIntersections){
            intersectionPoints.add(intersection);

        }}return intersectionPoints;
    }}

