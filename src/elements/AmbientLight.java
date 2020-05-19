package elements;

import primitives.Color;

public class AmbientLight {
    Color _intensity;
   // Color _iP;
    public AmbientLight(Color iA,double kA)
    {
        _intensity=iA.scale(kA);
    }

    public Color get_intensity() {
       return  _intensity;
    }
}
