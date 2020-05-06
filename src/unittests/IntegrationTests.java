package unittests;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    public void CameraSphereIntersections(){
        //case one
        Sphere sp=new Sphere(1,new Point3D(0,0,3));
        Camera cam=new Camera(new Point3D(0,0,0),new Vector(0,1,0),new Vector(0,0,1));
        Ray r1=cam.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("two intersections required",2,sp.findIntersections(r1));
        //case two
        Sphere sph=new Sphere(2.5,new Point3D(0,0,2.5));
        Camera camer=new Camera(new Point3D(0,0,-0.5),new Vector(0,0.5,-0.5),new Vector(0,0,0));
        Ray r2=camer.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("18 intersections expected",18,sph.findIntersections(r2));
        //case three
        Sphere spher=new Sphere(2,new Point3D(0,0,2));
        Camera camera=new Camera(new Point3D(0,0,-0.5),new Vector(0,0.5,-0.5),new Vector(0,0,0));
        Ray r3=camera.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("10 intersections expected",10,spher.findIntersections(r2));
        //CASE 4 nine intersections expected
        Sphere sphera=new Sphere(2.49,new Point3D(1,0,0));
        Camera cam3=new Camera(new Point3D(2.98,-0.99,1.15),new Vector(-0.26,0.44,0.57),new Vector(-1.7,-1.15,0.11));
        Ray r4=cam3.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("nine intersections",9,sphera.findIntersections(r4));
        //case 5-zero intersection points
        Sphere s=new Sphere(1,new Point3D(0,0,-1));
        Camera came=new Camera(new Point3D(0,-4,0),new Vector(0,0,1),new Vector(-3,-2,0));
        Ray ray=came.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("no intersections expected",0,s.findIntersections(ray));
        //case six-nine intersection points,plane straight
        //plane
        Plane pl=new Plane(new Point3D(0,0,0),new Point3D(0,1,1),new Point3D(0,0,1));
        Camera camera2=new Camera(new Point3D(9.68,0,0),new Vector(-9.96,-2.08,0),new Vector(0,0,1));
        Ray ray2=camera2.constructRayThroughPixel(3,3,1,1,1,3,3);
        assertEquals("there needs to be nine intersections",9,pl.findIntersections(ray2));
        //triangle one intersection
        Triangle tr=new Triangle(new Point3D(-1,1,2),new Point3D(0,-1,2),new Point3D(1,1,2));
        Camera cam4=new Camera(new Point3D(0,0,0.5),new Vector(0,0,1),new Vector(0.37,0.18,-0.91));
        Ray ray3=cam4.constructRayThroughPixel(3,3,1,1,3,3,3);
        assertEquals("there needs to be one intersection",1,tr.findIntersections(ray3));
        //triangle two intersections
        Triangle tri=new Triangle(new Point3D(-1,1,2),new Point3D(0,-20,2),new Point3D(1,1,2));
        assertEquals("there needs tobe two intersections",2,tri.findIntersections(ray3));

    }
}
