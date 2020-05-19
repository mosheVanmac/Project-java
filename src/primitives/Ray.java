package primitives;
import primitives.Vector;
import primitives.Point3D;

import java.util.Objects;
/**
 * class that represents a ray
 */

/**
 * Constructor for class Ray
 */
public class Ray {
    Point3D _p0;
    Vector _dir;

    /**
     * function that returns point of origin (p0)
     * @return Point3D
     */
    public Point3D get_p0() {
        return _p0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_p0, ray._p0) &&
                Objects.equals(_dir, ray._dir);
    }


    /**
     * function that returns _dir(direction vector)
     * @return Vector
     */
    public Vector get_dir() {
        return _dir;
    }

    /**
     * constructor
     * @param _p0
     * @param _dir
     */
    public Ray(Point3D _p0, Vector _dir) {

        this._p0 = _p0;
        if(_dir.length()!=1)
        {this._dir=_dir.normalize();}
        this._dir = _dir;
    }

    public void getPoint3D(double t) {
        Point3D p=(this.get_p0().add(this.get_dir().scale(t))).get_head();
    }
}