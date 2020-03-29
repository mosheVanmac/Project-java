package primitives;

import java.util.Objects;

import static java.lang.Math.sqrt;

/**
 * class to represent vector
 */
public class Vector {
    Point3D _head;

    public final static Vector ZERO=new Vector(new Point3D(0.0,0.0,0.0));
    //*********constructors***********************************//
    public Vector(Point3D _head1,Point3D _head2){
        this(_head2.subtract(_head1));

    }
    public Vector(Point3D _head) {
        try {
            if (_head.get_x().get() == 0.0 && _head.get_y().get() == 0 && _head.get_z().get() == 0)
                throw new IllegalArgumentException("vector 0 is unacceptable");

            this._head = _head;
        }

        catch (IllegalArgumentException a)
        {System.out.println("do not insert zero vector");}

    }

    public Vector(double _p1,double _p2,double _p3) {
        this(new Coordinate(_p1),
                new Coordinate(_p2),
                new Coordinate(_p3));
    }
    public Vector(Coordinate _coord1,Coordinate _coord2,Coordinate _coord3)
    {
        this(new Point3D(_coord1,_coord2,_coord3));
    }
    public Vector(Vector _other)
    {
        this._head=_other.get_head();
    }

    /**
     * return head
     * @return Point3D
     */
    public Point3D get_head() {
        return _head;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return  _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }


    /***
     *calculates dot product between two vectors
     * @param other Vector
     * @return dotProduct (double)
     */
    public double dotProduct(Vector other){
        return other._head.get_x().get()*other._head.get_x().get()+_head._y.get()*other._head._y.get()+_head._z.get()*other._head._z.get();
    }

    /**
     * returns cross product between two vectors
     * @param other Vector
     * @return Vector for cross product using right hand rule of thumb
     */
    public Vector crossProduct(Vector other)
    {
        return new Vector(_head.get_y().get()*other._head.get_z().get()-_head.get_z().get()*other._head.get_y().get(),
                _head.get_z().get()*other._head.get_x().get()-_head.get_x().get()*other._head.get_z().get(),
                _head.get_x().get()*other._head.get_y().get()-_head.get_y().get()*other._head.get_x().get());

    }

    /**
     * function returns new vector-the answer of number(scalar) times vector
     * @param scalar
     * @return Vector
     */
    public Vector scale (double scalar){return new Vector(scalar * _head.get_x().get(), scalar*_head.get_y().get(), scalar * _head.get_z().get()); }

    /**
     * function returns length squared of vector
     * @return double
     */
    public  double lengthSquared(){
        double len = _head.get_x().get()*_head.get_x().get()+_head.get_y().get()*_head.get_y().get()+_head.get_z().get()*_head.get_z().get(); return len;}

    /**
     * function calculates length of vector
     * @return double
     */
    public double length(){return sqrt (lengthSquared());}

    /**
     * function makes "this" into a vector with length of 1
     * @return Vector
     */
    public Vector normalize(){_head._x = new Coordinate(_head.get_x().get() / this.length());
        _head._y =new Coordinate(_head.get_y().get() / this.length());
        _head._z = new Coordinate(_head.get_z().get() / this.length());
        return this;}

    /**
     * function returns a new vector which has the same direction as this but is now with a length of 1
     * @return Vector
     */
    public Vector normalized (){Vector newVector = this.normalize() ; return newVector;}
}