package JAVA.Point;

/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 *
 * @author Shimon Port
 * @date 03/12/2020
 */

public class Segment1
{
    // Instance variables.
    private Point _poLeft;
    private Point _poRight;
    
    private final double DEFAULT_VAL = 0;

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @parm left - the left point of the segment.
     * @parm right - the right point of the segment.
     */
    
    public Segment1 (Point left, Point right)
    {
        _poLeft = new Point (left);
        _poRight = new Point (right);
        
        if ( _poRight.isAbove(_poLeft) || _poRight.isUnder(_poLeft))
            _poRight.setY(_poLeft.getY());
    }
    
    /**
     * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @parm leftX - X value of left point.
     * @parm leftY - Y value of left point.
     * @parm rightX - X value of right point.
     * @parm rightY - Y value of right point.
     */
    
    public Segment1 (double leftX ,double leftY, double rightX ,double rightY)
    {
        if (rightY != leftY)
            rightY = leftY;
        _poLeft = new Point (leftX, leftY);
        _poRight = new Point (rightX, rightY);
    }
    
    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @parm other - the reference segment.
     */
    
    public Segment1 (Segment1 other)
    {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }
    
    /**
     * Returns the left point of the segment.
     * @return The left point of the segment.
     */
    
    public Point getPoLeft ()
    {
        return _poLeft;
    }
    
    /**
     * Returns the right point of the segment.
     * @return The right point of the segment.
     */
    
    public Point getPoRight ()
    {
        return _poRight;
    }
    
    /**
     * Returns the segment length.
     * @return True if the reference segment is equal to this segment.
     */
    
    public double getLength ()
    {
        return _poLeft.distance (_poRight);
    }
    
    /**
     * toString in class java.lang.Object
     * @return String representation of this segment.
     */
    
    public String toString()
    {
        return _poLeft + "---" + _poRight; 
    }
    
    /**
     * Check if the reference segment is equal to this segment.
     * @parm other - the reference segment.
     * @return True if the reference segment is equal to this segment.
     */
    
    public boolean equals (Segment1 other)
    {
        if (_poLeft.equals(other._poLeft) && _poRight.equals(_poRight))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is above a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is above the reference segment.
     */
    
    public boolean isAbove (Segment1 other)
    {
        if (this._poLeft.isAbove(other._poLeft))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is under a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is under the reference segment.
     */
    
    public boolean isUnder (Segment1 other)
    {
        if (other._poLeft.isAbove(this._poLeft))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is left of a received segment.
     * @parm other - the reference segment.
     * @return True if this segment is left to the reference segment.
     */
    
    public boolean isLeft (Segment1 other)
    {
        if (this._poRight.isLeft(other._poLeft))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this segment is right of a received segment.
     * @parm other - the reference segment.
     * @return True if this segment is right to the reference segment.
     */
    
    public boolean isRight (Segment1 other)
    {
        if (this._poLeft.isRight(other._poRight))
            return true;
            else
                return false;
    }
    
    /**
     * Move the segment horizontally by delta.
     * @parm delta - the displacement size.
     */
    
    public void moveHorizontal (double delta)
    {
        _poLeft.move(delta , DEFAULT_VAL);
        _poRight.move(delta , DEFAULT_VAL);
    }
    
    /**
     * Move the segment vertically by delta.
     * @parm delta - the displacement size.
     */
    
    public void moveVertical (double delta)
    {
        _poLeft.move(DEFAULT_VAL , delta);
        _poRight.move(DEFAULT_VAL , delta);
    }
    
    /**
     * Change the segment size by moving the right point by delta.
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @parm delta - the length change.
     */
    
    public void changeSize (double delta)
    {
        if (_poRight.getX() + delta >= _poLeft.getX())
            _poRight.setX(_poRight.getX() + delta);
    }
    
    /**
     * Check if a point is located on the segment.
     * @parm p - a point to be checked.
     * @return True if p is on this segment.
     */
    
    public boolean pointOnSegment (Point p)
    {
        if (_poLeft.getX() <= p.getX() && _poRight.getX() >= p.getX())
            if (_poLeft.getY() == p.getY())
            return true; 
        return false;
    }
    
    /**
     * Check if this segment is bigger than a reference segment.
     * @parm other - the reference segment.
     * @return True if this segment is bigger than the reference segment.
     */
    
    public boolean isBigger (Segment1 other)
    {
        if (this.getLength() > other.getLength())
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
    
    public double overlap (Segment1 other)
    {
        if (_poLeft.getX() <= other._poLeft.getX() && _poRight.getX() <= other._poRight.getX())
            if (_poRight.getX() >= other._poLeft.getX())
            return _poRight.getX() - other._poLeft.getX();
        if (_poLeft.getX() <= other._poLeft.getX() && _poRight.getX() >= (other._poRight.getX()))
            return other._poRight.getX() - other._poLeft.getX();
        if (_poLeft.getX() >= other._poLeft.getX() && _poRight.getX() <= other._poRight.getX())
            return _poRight.getX() - _poLeft.getX();
        if (_poLeft.getX() >= other._poLeft.getX() && _poLeft.getX() <= other._poRight.getX())
            if (_poRight.getX() >= other._poRight.getX())
            return other._poRight.getX() - _poLeft.getX();
        return DEFAULT_VAL;
    }
    
    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     * @parm other - the reference segment.
     * @return The trapeze perimeter.
     */
    
    public double trapezePerimeter (Segment1 other)
    {
        return _poLeft.distance(_poRight) + _poRight.distance(other._poRight) + other._poRight.distance(other._poLeft) + other._poLeft.distance(_poLeft);
    }
}

