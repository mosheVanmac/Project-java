package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.Objects;

public class Cylinder extends Tube implements Geometry {
    double _height;

    public Cylinder(double he){super(0.0,null);_height = he;}

    public double get_height() {
        return _height;
    }

    /*****toString
     *
     * @return string of height
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }


    @Override
    public Vector getNormal(Point3D _other) {
        return getNormal(null);
    }
}
