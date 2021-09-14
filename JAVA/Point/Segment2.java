package JAVA.Point;


/**
 * Write a description of class Segment2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Segment2
{
    private Point _poCenter;
    private double _length;
   
    private final double DEFAULT_VAL = 0;
    
    /**
     * Constructs a new segment using two Points.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @parm left - the left point of the segment
     * @parm right - the right point of the segment
     */
    
    public Segment2 (Point poCenter, double length)
    {
        _poCenter = new Point (poCenter);
        _length = length;
    }

        /**
     * Constructs a new segment using 4 specified x y coordinates: two coordinates for the left point and two coordinates for the right point.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @parm leftX - X value of left point.
     * @parm leftY - Y value of left point.
     * @parm rightX - X value of right point.
     * @parm rightY - Y value of right point.
     */
    
    public Segment2 (double leftX ,double leftY, double rightX ,double rightY)
    {
        if (rightY != leftY)
            rightY = leftY;
        _length = Math.sqrt(Math.pow(leftX - rightX , 2)); // Distance equation.
        _poCenter = new Point (_length / 2.0 + leftX, leftY);
    }
    
    /**
     * Constructs a new segment using two Points.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @parm left - the left point of the segment.
     * @parm right - the right point of the segment.
     */
    
    public Segment2 (Point left, Point right)
    {
        _length = left.distance(right);
        double y = left.getY();
        double x = _length / 2 + left.getX();
        _poCenter = new Point (x , y);
    }
    
    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @parm other - the reference segment.
     */
    
    public Segment2 (Segment2 other)
    {
        _length = other._length;
        _poCenter = new Point(other._poCenter);
    }
    
    /**
     * Returns the left point of the segment.
     * @return The left point of the segment.
     */
    
    public Point getPoLeft ()
    {
        double y = _poCenter.getY();
        double x = _poCenter.getX() - _length / 2; // Finding x value for poCenter.
        Point poLeft = new Point (x , y);
        return poLeft;
    }
    
    /**
     * Returns the right point of the segment.
     * @return The right point of the segment.
     */
    
    public Point getPoRight ()
    {
        double y = _poCenter.getY();
        double x = _poCenter.getX() + _length / 2; // Finding x value for poCenter.
        Point poRight = new Point (x , y);
        return poRight; 
        
    }
    
    /**
     * Returns the segment length.
     * @return The segment length.
     */
    
    public double getLength ()
    {
        return _length;
    }
    
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment.
     */
    
    public String toString()
    {
        return getPoLeft() + "---" + getPoRight(); 
    }
    
    /**
     * Check if the reference segment is equal to this segment.
     * @parm other - the reference segment.
     * @return True if the reference segment is equal to this segment.
     */
    
    public boolean equals (Segment2 other)
    {
        if (_poCenter.equals(other._poCenter) && _length == (other._length))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is above a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is above the reference segment.
     */
    
    public boolean isAbove (Segment2 other)
    {
        if (this._poCenter.isAbove(other._poCenter))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is under a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is under the reference segment.
     */
    
    public boolean isUnder (Segment2 other)
    {
        if (other._poCenter.isAbove(this._poCenter))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is left of a received segment.
     * @parm other - the reference segment.
     * @return True if this segment is left to the reference segment.
     */
    
    public boolean isLeft (Segment2 other)
    {
        if (this.getPoRight().isLeft(other.getPoLeft()))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is right of a received segment.
     * @parm other - the reference segment.
     * @return True if this segment is right to the reference segment.
     */
    
    public boolean isRight (Segment2 other)
    {
        if (this.getPoLeft().isRight(other.getPoRight()))
            return true;
            else
                return false;        
    }
    
    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta.
     * @parm delta - the displacement size.
     */
    
    public void moveHorizontal (double delta)
    {
        _poCenter.move(delta , DEFAULT_VAL);
    }
    
    /**
     * Move the segment vertically by delta. Will be implemented only for a valid delta.
     * @parm delta - the displacement size.
     */
    
    public void moveVertical (double delta)
    {
        _poCenter.move(DEFAULT_VAL , delta);
    }
    
    /**
     * Change the segment size by moving the right point by delta.
     * @parm delta - the length change.
     */
    
    public void changeSize (double delta)
    {
        double x = delta / 2;
        _poCenter.move(x , DEFAULT_VAL);
        _length = _length + delta;
    }
    
    /**
     * Check if a point is located on the segment.
     * @parm p - a point to be checked.
     * @return True if p is on this segment.
     */
    
    public boolean pointOnSegment (Point p)
    {
        if (getPoLeft().getX() <= p.getX() && getPoRight().getX() >= p.getX())
            if (getPoLeft().getY() == p.getY())
            return true; 
        return false;
    }
    
    /**
     * Check if this segment is bigger than a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is bigger than the reference segment.
     */
    
    public boolean isBigger (Segment2 other)
    {
        if (_length > other._length)
            return true;
            else
                return false;
    }
    
    /**
     * Returns the overlap size of this segment and a reference segment.
     * @parm other - the reference segment.
     * @return The overlap size.
     */
    // cheking four possiblities of overlaping. 
    
    public double overlap (Segment2 other)
    { 
        if (getPoLeft().getX() <= other.getPoLeft().getX() && getPoRight().getX() <= other.getPoRight().getX())
            if (getPoRight().getX() >= other.getPoLeft().getX())
            return getPoRight().getX() - other.getPoLeft().getX();
        if (getPoLeft().getX() <= other.getPoLeft().getX() && getPoRight().getX() >= (other.getPoRight().getX()))
            return other.getPoRight().getX() - other.getPoRight().getX();
        if (getPoLeft().getX() >= other.getPoLeft().getX() && getPoRight().getX() <= other.getPoRight().getX())
            return getPoRight().getX() - getPoRight().getX();
        if (getPoLeft().getX() >= other.getPoLeft().getX() && getPoLeft().getX() <= other.getPoRight().getX())
            if (getPoRight().getX() >= other.getPoRight().getX())
            return other.getPoRight().getX() - getPoLeft().getX();
        return DEFAULT_VAL;
    }
    
    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     * @parm other - the reference segment.
     * @return The trapeze perimeter.
     */
    
    public double trapezePerimeter (Segment2 other)
    {
        return _length + other._length + getPoLeft().distance(other.getPoLeft()) + getPoRight().distance(other.getPoRight()); 
    }
}

