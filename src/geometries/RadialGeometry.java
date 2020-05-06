package geometries;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * class that represents an abstact class that has a radius
 */
public abstract class RadialGeometry {
    double _radius;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadialGeometry that = (RadialGeometry) o;
        return Double.compare(that._radius, _radius) == 0;
    }


    /**
     * constructor
     * @param _radius
     */
    public RadialGeometry(double _radius) { if (isZero(_radius) || (_radius < 0.0))
        throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }

    /**
     * copy constructor
     * @param other
     */
    public  RadialGeometry (RadialGeometry other)
    {
        this._radius = other._radius;
    }

    /**
     * return _radius
     * @return
     */
    public double get_radius() {
        return _radius;
    }

}
