package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *class to implement spot light, a light which has different intensity based on the degree between object and light
 */
public class SpotLight extends PointLight {
    Vector _direction;

    /**
     * constructor
     * @param _intensity
     * @param _position
     * @param _kC
     * @param _kL
     * @param _kQ
     * @param _direction
     */
    public SpotLight(Color _intensity, Point3D _position,
                     double _kC, double _kL, double _kQ, Vector _direction)
    {
        super(_intensity, _position, _kC, _kL, _kQ);
        this._direction = _direction;
    }

    public SpotLight(Color intensity, Point3D position, Vector vector, int kL, double kQ, double v) {
        this(intensity,position,1.0,kL,kQ,vector);
    }


    /**
     * returns color of object when illuminated by spotlight
     * @param point
     * @return
     */
    @Override
    public Color getIntensity(Point3D point) {
        double dirl=_direction.dotProduct(getL(point));
        double distance=_position.distance(point);
        double answer;
        if(dirl>0)
        {answer=dirl;}
        else{
            answer=0;
        }
        Color il=(getIntensity(point).scale(answer)).scale(1/(_kC+distance*_kL+distance*distance*_kQ));
        return il;
    }

    @Override
    public double getDistance(Point3D point) {
        return 0;
    }
}
