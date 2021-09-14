package JAVA.Point;

/**
 * Represents 2 dimensional points through receevings and returning the coordinates of a Cartesian system. 
 * The calculation in the class is done through the Polar system.
 * @author Shimon Port
 * @Date 02/12/2020
 */
public class Point
{
    // Instance variables.
    private double _radius;
    private double _alpha;
    
    private final double DEFAULT_VAL = 0;
    
    /**
    * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
    * If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
    * @param x represents the x coordinates of the point.
    * @param y represents the y coordinates of the point.
    */
    public Point (double x, double y)
    {   
        if (x < DEFAULT_VAL)
            x = DEFAULT_VAL;

        if (y < DEFAULT_VAL)
            y = DEFAULT_VAL;

        _radius = Math.sqrt(Math.pow(DEFAULT_VAL - x , 2) + Math.pow(DEFAULT_VAL - y, 2)); // Distance equation.
        
        if (x == DEFAULT_VAL)
            _alpha = DEFAULT_VAL;
            else 
                _alpha = Math.atan(y/x); // Degree equation.
    }
    
    /**
     * Constructor for objects of class Point.
     * Copy constructor, construct a point using another point.
     * @parm other - The point from which to construct the new object.
     */
    public Point (Point other)
    {
        _radius = other._radius;
        _alpha = other._alpha;
    }
    
    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX()
    {
     return _radius * (Math.cos (_alpha)); // Degree equation for finding x coordinate.
    }
    
    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY()
    {
     return _radius * (Math.sin (_alpha)); // Degree equation for finding y coordinate.
    }
    
    /**
     * This method sets the x coordinate of the point.
     * If the new x coordinate is negative the old x coordinate will remain unchanged.
     * @parm x - The new x coordinate.
     * @parm y - The old y coordinate.
     */
    public void setX (double num)
    {
        double x = _radius * (Math.cos (_alpha)); // Degree equation for finding x coordinate.
        double y = _radius * (Math.sin (_alpha)); // Degree equation for finding y coordinate.
        if (num >= DEFAULT_VAL)
            x = num;
        _alpha = Math.atan(y/x); // Degree equation.
        _radius = Math.sqrt(Math.pow(DEFAULT_VAL - x , 2) + Math.pow(DEFAULT_VAL - y, 2)); // Distance equation.
    }
    
    /**
     * This method sets the y coordinate of the point.
     * If the new x coordinate is negative the old y coordinate will remain unchanged.
     * @parm x - The old x coordinate.
     * @parm y - The new y coordinate.
     */
    public void setY (double num)
    {
        double x = _radius * (Math.cos (_alpha)); 
        double y = _radius * (Math.sin (_alpha)); 
        if (num >= DEFAULT_VAL)
            y = num;
        _alpha = Math.atan(y/x); // Degree eqation.
        _radius = Math.sqrt(Math.pow(DEFAULT_VAL - x , 2) + Math.pow(DEFAULT_VAL - y, 2)); // Distance equation.
    }
    
    /**
     * Returns a string representation of the Point in the format (x,y).
     * @return A String representation of the Point.
     */
    public String toString ()
    {
        double x = _radius * (Math.cos (_alpha)); 
        double y = _radius * (Math.sin (_alpha)); 
        double x_Rounded = Math.round(x*10000)/(double)10000; // Rounded number.
        double y_Rounded = Math.round(y*10000)/(double)10000; // Rounded number.
        return "(" + x_Rounded + "," + y_Rounded + ")";
    }
    
    /**
     * Check if the given point is equal to this point.
     * @parm other - The point to check equality with.
     * @return True if the given point is equal to this point.
     */
    public boolean equals (Point other)
    { 
       if (_radius == other._radius && _alpha == other._alpha)
            return true;
            else
              return false;
    }
    
    /**
     * Check if this point is above a received point.
     * @parm other - The point to check if this point is above.
     * @return True if this point is above the other point.
     */
    public boolean isAbove (Point other)
    {
       double y_Main = _radius * (Math.sin (_alpha)); // Degree equation for finding "this" y coordinate. 
       double y_Parameter = other._radius * (Math.sin (_alpha)); // Degree equation for finding other y coordinate.
       if (y_Main > y_Parameter)
            return true;
            else
                return false;
    }
    
    /**
     * Check if this point is below a received point.
     * @parm other - The point to check if this point is right of.
     * @return True if this point is right of the other point
     */
    public boolean isUnder (Point other)
    {
       if ( other.isAbove(this) )
            return true;
            else
                return false;
    }
    
    /**
     * Check if this point is left of a received point.
     * @parm other - The point to check if this point is left of.
     * @return True if this point is left of the other point.
     */
    public boolean isLeft (Point other)
    {
        if (_radius * (Math.cos (_alpha)) > other._radius * (Math.cos (_alpha)))
            return true;
            else
                return false;
    }
    
    /**
     * Check if this point is right of a received point.
     * @parm other - The point to check if this point is right of.
     * @return True if this point is right of the other point.
     */
    public boolean isRight (Point other)
    {
        if (other.isLeft(this) )
            return true;
            else
                return false;
    }
    
    /**
     * Check the distance between this point and a given point.
     * @parm p - The point to check the distance from.
     * @return The distance
     */
    public double distance (Point p)
    {
        double x = _radius * (Math.cos (_alpha)); // Degree equation for "this" x coordinate.
        double y = _radius * (Math.sin (_alpha)); // Degree equation for "this" y coordinate.
        double x_p = p._radius * (Math.cos (_alpha)); // Degree equation for "p" x coordinate.
        double y_p = p._radius * (Math.sin (_alpha)); // Degree equation for "p" y coordinate.
        return Math.sqrt(Math.pow(x - x_p , 2) + Math.pow(y - y_p, 2)); // Distance equation.
    }
    
    /**
     * Moves a point. If either coordinate becomes negative the point remains unchanged.
     * @parm dx - The difference to add to x.
     * @parm dy - The difference to add to y.
     */
    public void move (double dx, double dy)
    {
        double x = getX();
        double y = getY();
        if ((y + dy >= DEFAULT_VAL) && (x + dx >= DEFAULT_VAL))
            setX (x + dx);
            setY (y + dy);
    }
}

