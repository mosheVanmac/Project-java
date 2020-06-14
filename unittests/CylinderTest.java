package unittests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * checks whether get normal of cylinder works properly
 */
public class CylinderTest {
    @Test
    public void getNormal()
    {///*****Equivalence Partition*****///////
        Cylinder one=new Cylinder(1.0);
         assertEquals("wrong normal",new Vector(-105/15303,132/15303,159/15303),one.getNormal(new Point3D(0,0,1)));
         assertTrue("NOT YET IMPLEMENTED",false);

    }
}