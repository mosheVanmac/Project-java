package geometries;

    import primitives.Point3D;
    import primitives.Vector;

    public class Plane implements Geometry {
        Point3D point;
        Vector normal;
        /*constructors*/
        public Plane(Point3D vertex, Point3D vertex1, Point3D vertex2) {
           normal = null;
           point = vertex;
        }
        public Plane(Point3D vertex, Vector otherNormal){point = vertex; normal = otherNormal;}
/***getter and toString***/
        @Override
        public Vector getNormal(Point3D point) {return  Vector.ZERO;}

        public Point3D getPoint() {
            return point;
        }

        @Override
        public String toString() {
            return "Plane{" +
                    "point=" + point +
                    ", normal=" + normal +
                    '}';
        }
    }
