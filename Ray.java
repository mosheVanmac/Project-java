package primitives;
import primitives.Vector;
import primitives.Point3D;


public class Ray {
    Point3D _p0;
    Vector _dir;

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
}