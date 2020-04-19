package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getNormal() {
        //Equivalence partitioning tests
        //There is a single simple test here
        Sphere sph=new Sphere(1.0,new Point3D(0,0,1));
        assertEquals("Bad normal to sphere",new Vector(0,0,1),sph.getNormal(new Point3D(0,0,2)));
        assertTrue("Not yet implemented",false);
    }
}