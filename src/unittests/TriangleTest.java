package unittests;

import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void getNormal() {
        Triangle tr=new Triangle(new Point3D(0,1,0),new Point3D(0,0,1),new Point3D(1,1,1));
        assertEquals("is wrong normal",new Vector(1/sqrt(3),1/sqrt(3),1/sqrt(3)),tr.getNormal(new Point3D(1,2,3)));
        assertTrue("not yet implemented",false);
    }
    public void findIntersections(Ray ray)
    {    Plane pv=new Plane(new Point3D(1,0,0),new Point3D(2,0,0),new Point3D(0,0,1));
        //////EP Tests========================///////////////////////
        //Test one-hits side of triangle
        assertEquals("wrong amount of intersections when goes past side of triangle",
                0,
                pv.findIntersections(new Ray(new Point3D(-1.78,1.4,0),new Vector(-0.06,-1.4,0.55))));
        ////Test two-hits middle of triangle
        assertEquals("wrong amount of intersections for when ray hits middle of triangle",
                1,
                pv.findIntersections(new Ray(new Point3D(-1.78,1.4,0),new Vector(-0.61,-1.4,0.27))));
        //////Test three-hits just below opposite triangle
        assertEquals("wrong amount of intersections for when ray hits below triangle",
                0,new Ray(new Point3D(-1.78,1.4,0),new Vector(0.48,-1.4,-0.19))
                );
        ////////BVA tests========================================////
        ////3 tests of ray starts at plane
        //Test one-hits side of triangle

        assertNull( "ray starts at plane",
                pv.findIntersections(new Ray(new Point3D(-1,0,0),new Vector(-0.58,0,0.66))));

        //////Test two-hits middle of triangle

        assertNull( "ray starts at plane bva two,one ",
                pv.findIntersections(new Ray(new Point3D(-2.5,0,0),new Vector(-1.23,0,1.54))));
        //////test three-hits bottom of triangle

            assertNull("ray starts at plane,bva three ",
                    pv.findIntersections(new Ray(new Point3D(-6.62,0,-3.18),new Vector(1.62,0.58,3.18))));

        /////3 tests ray begins before plane
        //test one-ray hits middle of tzela

        assertNull("hits middle of tzela",
                pv.findIntersections(new Ray(new Point3D(-2.71,2.01,0),new Vector(0.43,-2.01,0.4))));
        ///////test two ray hits point
        assertNull("ray hits point",pv.findIntersections(new Ray(new Point3D(-3.22,
                2.74,
                0),new Vector(2.22,-2.74,0))));
        ///////test three-ray hits continuation of tsela
        assertNull("ray hits continuation of tzela",
                pv.findIntersections(new Ray(new Point3D(-2,3,0),new Vector(-0.25,-3,1.42))));
        /////////three tests-ray starts at plane
        ///test one-ray intersects middle of tsela
        assertNull("ray hits middle of tsela",
                pv.findIntersections(new Ray(new Point3D(-2.5,0,0),new Vector(0.22,0,0.4))));
        ////test two-ray intersects point of triangle
        assertNull("ray intersects point",pv.findIntersections(new Ray(new Point3D(-2.5,0,0),
                new Vector(0.5,0,0))));
//////test three-ray intersects continuation of tsela
        assertNull("ray intersects continuation of tsela",
                pv.findIntersections(new Ray(new Point3D(-2.5,0,0),new Vector(0.25,0,1.42))));
        /////









    }
}