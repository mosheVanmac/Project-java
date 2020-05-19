package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class Render {
    Scene _scene;
    ImageWriter _imageWriter;

    public void renderImage(){
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        int nX = _imageWriter.get_nX();
        int nY=_imageWriter.getNy();
        double distance=_scene.get_distance();
        double height=_imageWriter.getHeight();
        double width=_imageWriter.getWidth();

        //for each point (i,j) in the view plane // i is pixel row number and j is pixel in the row number
        for(int i=0;i<=nY;i++) {
            for (int j = 0; j <= nX; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints.isEmpty()) {
                    _imageWriter.writePixel(j, i, background);
                } else
                {Point3D closestPoint = getClosestPoint(intersectionPoints);
                _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());}

            }
        }}
private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
    Point3D closest = intersectionPoints.get(0);
    for (Point3D point : intersectionPoints) {
        if ((_scene.get_camera().get_p0().distance(point)>_scene.get_camera().get_p0().distance(closest)))
            closest=point;

    }
    return closest;
}

    //In the intersectionPoints - find the point with minimal distance from the ray
   // begin point (now it is just the camera location) and return it
private Color calcColor(Point3D point){
    return _scene.get_ambientLight().get_intensity();
}
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double coloumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < coloumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);
                }
            }
        }
    }
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

}

