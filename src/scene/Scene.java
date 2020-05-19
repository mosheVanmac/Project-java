package scene;
import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class Scene {
    String _name;
    Color _background;
    AmbientLight _ambientLight;
    Geometries _geometries;
    Camera _camera;
    double _distance;
///getters and setters
    public void set_background(Color _background) {
        this._background = _background;
    }

    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }


    public String get_name() {
        return _name;
    }

    public Color get_background() {
        return _background;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public Geometries get_geometries() {
        return _geometries;
    }

    public Camera get_camera() {
        return _camera;
    }

    public double get_distance() {
        return _distance;
    }
    public Scene(String name)
    {
        _geometries=new Geometries();
    }
    public void addGeometries(Intersectable... geometries)
    {
        for (Intersectable geo :geometries)
        {
            _geometries.add(geo);
        }
    }

    public void setCamera(Camera cam)
    {
     _camera=new Camera(cam.get_p0(),cam.get_vUp(),cam.get_vTo());
    }

    public void setDistance(int i) {
        set_distance(i);

    }

    public void setBackground(Color color) {
        set_background(color);
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        set_ambientLight(ambientLight);
    }

    public Camera getCamera() {
        return _camera;
    }

    public Intersectable getGeometries() {
        return _geometries;
    }

    public Color getBackground() {
        return _background;
    }
}
