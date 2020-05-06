package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;

public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;

    public Camera(Point3D _p0, Vector _vUp, Vector _vTo) {
        this._p0 = _p0;
        try{if(_vUp.normalize().dotProduct(_vTo)!=0)
        {throw new IllegalArgumentException("did not insert orthogonal vectors");}this._vUp = _vUp.normalize();
        this._vTo = _vTo.normalize();
        this._vRight=_vTo.crossProduct(_vUp).normalize();}catch(IllegalArgumentException e){}
    }
    public Ray constructRayThroughPixel(int nX,
                                        int nY,
                                        int j,
                                        int i,
                                        double screenDistance,
                                        double screenWidth,
                                        double screenHeight)
    {Point3D _pC=(_p0.add(_vTo.scale(screenDistance))).get_head();
        double _rY=screenHeight/nY;
        double _rX=screenWidth/nX;
        double _yI=(i-nY/2)*_rY+_rY/2;
        Point3D _pIJ=_pC.add((_vUp.scale((i-(nY-1)/2)*_rY)).subtract(_vRight.scale(_rX*(j-(nX-1)/2)))).get_head();
        Vector vij=_p0.subtract(_pIJ);
        return new Ray(_p0,vij.normalize());
    }
}
