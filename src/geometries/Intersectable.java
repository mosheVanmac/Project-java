package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface Intersectable
{
    default List<Geopoint>findIntersections(Ray ray){
        return findIntersections(ray,Double.POSITIVE_INFINITY);
    }
    LinkedList<Geopoint> findIntersections(Ray ray,double positiveinfinity);

    /**
     * Geopoint class for implementation of returning both the intersection point and the type of intersected geometry
     */
    public static class Geopoint{
        public Geometry geometry;
        public Point3D point;

        public Geometry getGeometry() {
            return geometry;
        }

        public Point3D getPoint() {
            return point;
        }

        /**
         *
         * @param geometry
         * @param point
         */
        public Geopoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Geopoint geopoint = (Geopoint) o;
            return Objects.equals(geometry, geopoint.geometry) &&
                    Objects.equals(point, geopoint.point);
        }

    }
}
