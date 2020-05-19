package primitives;

import java.util.Objects;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class to represent vector
 */
public class Vector {
    Point3D _head;

    public final static Vector ZERO=new Vector(new Point3D(0.0,0.0,0.0));
    //*********constructors***********************************//
public Vector(Point3D _head1,Point3D _head2){
    this(_head1.subtract(_head2));

}
    public Vector(Point3D _head) {
        try {
            if (_head.get_x().get() == 0.0 && _head.get_y().get() == 0 && _head.get_z().get() == 0)
                throw new IllegalArgumentException("vector 0 is unacceptable");

            this._head = _head;
        }

        catch (IllegalArgumentException a)
        {}

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
    public double dotProduct(Vector other) {
        double answer=0.0;
        try {
            if (this.get_head().get_x().get() * other.get_head().get_x().get() +
                    this.get_head().get_y().get() * other.get_head().get_y().get() +
                    this.get_head().get_z().get() * other.get_head().get_z().get() == 0.0) {
                throw new IllegalArgumentException("parrallel vectors are not valid arguments for dot product");
            }
            answer = other._head.get_x().get() * this._head.get_x().get() +
                    this._head._y.get() * other._head._y.get() +
                    this._head._z.get() * other._head._z.get();
        } catch (IllegalArgumentException a) {
        }
        return answer;
    }

    /**
     * returns cross product between two vectors
     * @param other Vector
     * @return Vector for cross product using right hand rule of thumb
     */
    public Vector crossProduct(Vector other)
    {Vector answer=null;
      try{    if((this._head.get_x().get()/other._head.get_x().get()==this._head.get_y().get()/other._head.get_y().get())&&
              (this._head.get_y().get()/other._head.get_y().get()==this._head.get_z().get()/other._head.get_z().get()))
                       {throw new IllegalArgumentException("these vectors are parallel!");}
          answer=(new Vector(_head.get_y().get()*other._head.get_z().get()-_head.get_z().get()*
                          other._head.get_y().get(),
              _head.get_z().get()*other._head.get_x().get()-_head.get_x().get()*other._head.get_z().get(),
              _head.get_x().get()*other._head.get_y().get()-_head.get_y().get()*other._head.get_x().get()));

         }

          catch(IllegalArgumentException exp){}
      return answer;

    }

    /**
     * function returns new vector-the answer of number(scalar) times vector
     * @param scalar
     * @return Vector
     */
    public Vector scale (double scalar){Vector ans=null;
    try{
        ans=new Vector(scalar * _head.get_x().get(), scalar*_head.get_y().get(), scalar * _head.get_z().get());
        if(ans._head.get_x().get()==0&&ans._head.get_y().get()==0&&ans._head.get_z().get()==0)
            throw new IllegalArgumentException("vectors gave a zero vector");}catch(IllegalArgumentException exp){}
       return ans;
    }

    /**
     * function returns length squared of vector
     * @return double
     */
    public  double lengthSquared(){
        double len = _head.get_x().get()*_head.get_x().get()+_head.get_y().get()*_head.get_y().get()+_head.get_z().get()*
                _head.get_z().get(); return len;}

    /**
     * function calculates length of vector
     * @return double
     */
    public double length(){return sqrt (lengthSquared());}

    /**
     * function makes "this" into a vector with length of 1
     * @return Vector
     */
    public Vector normalize(){
        this._head._x = new Coordinate(alignZero(this._head.get_x().get() / this.length()));
            this._head._y =new Coordinate(alignZero(this._head.get_y().get() / this.length()));
        this._head._z = new Coordinate(alignZero(this._head.get_z().get() / this.length()));
          return this;}

    /**
     * function returns a new vector which has the same direction as this but is now with a length of 1
      * @return Vector
     */
    public Vector normalized (){Vector newVector = this.normalize() ; return newVector;}
    /**
     * a function which performs vector addition
     * @param other
     * @return Vector
     */
public Vector add(Vector other) {
    Vector answer = null;
    try {
        answer = new Vector(this.get_head().get_x().get() + other.get_head().get_x().get(),
                this.get_head().get_y().get() + other.get_head().get_y().get(),
                this.get_head().get_z().get() + other.get_head().get_z().get());
        if (answer._head.get_x().get() == 0 && answer._head.get_y().get() == 0 && answer._head.get_z().get() == 0) {
            throw new IllegalArgumentException("answer is zero vector");
        }
    } catch (IllegalArgumentException exp) {
    }
    return answer;
}

    /**
     * a function which performs vector subtraction- other minus this
     * @param other
     * @return Vector
     */
    public Vector subtract(Vector other)
{ Vector minusThis=this.scale(-1);
    return new Vector(other.get_head().get_x().get()+minusThis.get_head().get_x().get() ,
        other.get_head().get_y().get()+minusThis.get_head().get_y().get(),
        other.get_head().get_z().get()+minusThis.get_head().get_z().get());
}
}