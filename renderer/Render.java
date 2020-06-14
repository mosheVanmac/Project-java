package renderer;
import elements.Camera;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Float.POSITIVE_INFINITY;
import static primitives.Util.alignZero;

/**
 * class to render an image based on geometries ,light and color
 */
public class Render
{private static final int MAX_CALC_COLOR_LEVEL=10;
 private static final  double MIN_CALC_COLOR_K=0.001;
    Scene _scene;
    ImageWriter _imageWriter;
    //constant to ensure we don't include geometry this as a geometry blocking light from this
   private static final double DELTA=0.1;/**/

    /**
     * function that checks whether area is shaded  or not
     * * @param l
     * @param n
     * @param gp
     * @return boolean
     */
    private boolean unshaded(Vector l, Vector n, Intersectable.Geopoint gp,LightSource ls) {

        Vector lightDirection=l.scale(-1);
        Vector deltaVector=n.scale(n.dotProduct(lightDirection)>0?DELTA:-DELTA);
        Point3D point=(gp.point.add(deltaVector)).get_head();
        Ray lightRay=new Ray(gp.point,lightDirection,n);
        List<Intersectable.Geopoint>intersections=_scene.get_geometries().findIntersections(lightRay);
        if(intersections==null){return true;}
        int counter=0;
        double lightDistance=ls.getDistance(gp.point);
       for(Intersectable.Geopoint geop:intersections){
           if((alignZero(geop.point.distance(geop.point)-lightDistance)<=0)&&
                   (geop.geometry.get_material().get_kT()==0))
               return false;
       }
        return intersections.isEmpty();
    }
    private double transparency(Vector l, Vector n, Intersectable.Geopoint gp,LightSource ls) {
        double ktr=1.0;
        Vector lightDirection=l.scale(-1);
        Vector deltaVector=n.scale(n.dotProduct(lightDirection)>0?DELTA:-DELTA);
        Point3D point=(gp.point.add(deltaVector)).get_head();
        Ray lightRay=new Ray(gp.point,lightDirection,n);
        List<Intersectable.Geopoint>intersections=_scene.get_geometries().findIntersections(lightRay);
        if(intersections==null){return ktr;}
        double lightDistance=ls.getDistance(gp.point);

        for(Intersectable.Geopoint geop:intersections){
            if((alignZero(geop.point.distance(geop.point)-lightDistance)<=0)){
                ktr*=geop.geometry.get_material().get_kT();
                if(ktr<MIN_CALC_COLOR_K){return 0.0;}}
        }
        return ktr;
    }
        public Render( ImageWriter _imageWriter,Scene _scene) {
        this._scene = _scene;
        this._imageWriter = _imageWriter;
    }

    /**
     * function that renders an image
     */
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
                Intersectable.Geopoint closestpoint=getClosestPoint(ray);
                List<Intersectable.Geopoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints.isEmpty()) {
                    _imageWriter.writePixel(j, i,null);
                }
                else
                {
                _imageWriter.writePixel(j, i, calcColor(closestpoint,ray).getColor());}

            }
        }}

    /**
     * function that finds the closest intersection with ray and geometries
     * @param intersectionPoints
     * @return
     */
    private Intersectable.Geopoint getClosestPoint(List<Intersectable.Geopoint> intersectionPoints)
    {
        Intersectable.Geopoint closest = intersectionPoints.get(0);
    for (Intersectable.Geopoint geopoint : intersectionPoints) {
        if ((_scene.get_camera().get_p0().distance(geopoint.getPoint())<
                _scene.get_camera().get_p0().distance(closest.getPoint())))
            closest=geopoint;

    }
    return closest;
}

    /**
     * to find closest intersection of ray with scene
     * @param ray
     * @return Intersectable.Geopoint
     */
    private Intersectable.Geopoint getClosestPoint(Ray ray)
    {
       Intersectable.Geopoint closest=null;
       double closestDistance=POSITIVE_INFINITY;
       Point3D ray_p0=ray.get_p0();
       List<Intersectable.Geopoint>intersectionList=_scene.get_geometries().findIntersections(ray);
       for (Intersectable.Geopoint geopoint : intersectionList) {
            if (ray_p0.distance(geopoint.getPoint())<closestDistance)
                closest=geopoint;
            closestDistance=ray_p0.distance(geopoint.getPoint());

        }
        return closest;
    }
private Color calcColor(Intersectable.Geopoint geopoint, Ray inRay)
{
return calcColor(geopoint,inRay,MAX_CALC_COLOR_LEVEL,1.0);
}


    //In the intersectionPoints - find the point with minimal distance from the ray
   // begin point (now it is just the camera location) and return it
private Color calcColor(Intersectable.Geopoint geopoint,Ray ray,int level,double k)
{ Color color=geopoint.geometry.get_emmission();
        Vector v=geopoint.getPoint().subtract(_scene.get_camera().get_p0()).normalize();
        Vector n=geopoint.getGeometry().getNormal(geopoint.point);///
        int nShininess=geopoint.getGeometry().get_material().get_nShininess();
        double kd=geopoint.getGeometry().get_material().get_kD();
        double ks=geopoint.getGeometry().get_material().get_kS();
        for(LightSource lightsource:_scene.get_lights())
        {
            Vector I=lightsource.getL(geopoint.getPoint());
            if(alignZero((n.dotProduct(I)*(n.dotProduct(v))))>0.0)
            {
                double ktr=transparency(I, n, geopoint, lightsource);
                if(ktr*k>MIN_CALC_COLOR_K){
                Color lightIntensity= lightsource.getIntensity(geopoint.getPoint()).scale(ktr);
                        color=color.add(calcDiffusive(kd,I,n,lightIntensity),
                                calcSpecular(ks,I,n,v,nShininess,lightIntensity));}}
            }

    if(level==1){
        return Color.BLACK;
    }
    double kr=geopoint.geometry.get_material().get_kR(),kkr=k*kr;
    if(kkr>MIN_CALC_COLOR_K){
        Ray reflectedRay=constructReflectedRay(n,geopoint.point,ray);
        Intersectable.Geopoint reflectedPoint=getClosestPoint(reflectedRay);
        if(reflectedPoint !=null){
            color=color.add(calcColor(reflectedPoint,reflectedRay,level-1,kkr).scale(kr));
        }
        double kt=geopoint.geometry.get_material().get_kT(),kkt=kt*k;
        if(kkt>MIN_CALC_COLOR_K)
        {
        Ray refractedRay=constructRefractedRay(n,geopoint.point,ray);
        Intersectable.Geopoint refractedPoint=getClosestPoint(refractedRay);//findclosestintersection
        if(refractedPoint!=null){
            color=color.add(calcColor(refractedPoint,refractedRay,level-1,kkt).scale(kt));
        }
        }
    }

        return color;
}

    private Ray constructReflectedRay(Vector n, Point3D point, Ray ray)
    {Vector r=n.scale(2*(ray.get_dir().dotProduct(n))).subtract(ray.get_dir());
     return new Ray(point,r,n);

    }

    private Ray constructRefractedRay(Vector n, Point3D point, Ray ray)
    {
     Vector direction=ray.get_dir();
     return new Ray(point,direction,n);

    }

    private Color calcSpecular(double ks, Vector i, Vector n, Vector v, int nShininess, Color lightIntensity)
   { Vector r=i.subtract(n.scale((((i.dotProduct(n))*2.0))));
     Vector vOpDirection=v.scale(-1);
     Double dotVopR=vOpDirection.dotProduct(r);
     Double choice;
     if(dotVopR>0.0){
         choice=dotVopR;
     }
     else{
         choice=0.0;
     }
     for(int shiny=nShininess;shiny>0;--shiny)
     {
         choice*=choice;
     }
     Color specReturn=lightIntensity.scale(ks*choice);
     return specReturn;
    }

    /**
     * returns the degree of how much light spreads over surface of object
     * @param kd
     * @param i
     * @param n
     * @param lightIntensity
     * @return Color
     */
    private Color calcDiffusive(double kd, Vector i, Vector n, Color lightIntensity) {
        Double lin=i.dotProduct(n);
        Color col=lightIntensity.scale(kd*lin);
        return col;
    }

    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double coloumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < coloumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);}}}}
    public void writeToImage() {
        _imageWriter.writeToImage();
    }}
