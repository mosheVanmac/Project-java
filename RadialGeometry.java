package geometries;

public abstract class RadialGeometry {
    double _radius;

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }
    /*constructor*/
public  RadialGeometry (RadialGeometry other)
{
    this._radius = other._radius;
}
/****getter ans setter****/
    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }
}
