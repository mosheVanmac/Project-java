package geometries;

import primitives.Point3D;

/**
 *class that represents a triangle in a 3d environment
 */
public class Triangle extends Polygon
{
    public Triangle(Point3D p1, Point3D p2,Point3D p3){
        super(p1,p2,p3);
    }
}