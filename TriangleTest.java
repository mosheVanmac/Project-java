package unittests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
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
}