package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

public class PlaneTest {
    @Test
    public void getNormal() {
        Plane newP=new Plane(new Point3D(0.0,1.0,0.0),new Point3D(0.0,0.0,1.0),new Point3D(1.0,1.0,1.0));
        assertEquals("wrong normal",new Vector(1/sqrt(3),1/sqrt(3),1/sqrt(3)),newP.getNormal());
        assertTrue("not yet implemented",false);
    }
}