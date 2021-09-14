package JAVA.Point;

/**
 * Represents 2 dimensional conveged polygaon using points in an array. 
 * 
 * @author Shimon Port
 * @Date 11/12/2020
 */

public class Polygon
{
    // instance variables   
    private Point [] _vertices;
    private int _noOfVertices; 
        
    private final int MAX_POINTS = 10;
        
    // Methods and Constructors.
    /**
    * Constructor for objects of class Polygon. Construct a new Polygon with up to MAX_POINTS (10).
    */
   
    public Polygon()
    {
        _noOfVertices = 0;
        _vertices = new Point [MAX_POINTS];
    }

    /**
    * Method for objects of class Polygon. Construct and add a new point to a Polygon with the specified x y coordinates.
    * If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
    * 
    * @param x represents the x coordinates of the point.
    * @param y represents the y coordinates of the point.
    * @return true if there is an empty slot and point to add is not null. 
    */
   
    public boolean addVertex(double x, double y)
    {
        Point addPoint = new Point (x , y);
            if (_noOfVertices < MAX_POINTS)
            {
                _vertices [_noOfVertices] = addPoint;
                _noOfVertices ++; 
                return true;
            }
        return false;    
    }
    
    /**
     * Find the highest point in the polygon.
     * 
     * @return the highest point if the array is not empty.
     */
    
    public Point highestVertex()
    {
        if (_noOfVertices == 0)// Empty array
            return null; 
        Point max = new Point (_vertices[0]); 
        for (int i = 0; i < _noOfVertices; i++)
        {
            if (max.isUnder(_vertices[i]))
            {
                max = new Point (_vertices[i]);
            }
        }
        return max; 
    }
    
    /**
     * Returns a string representation of the polygons Points in the format ((x,y),(x2,y2),(x3,y3)).
     *
     * @return A String representation of the polygon.
     */
    
    public String toString()
    {
        if (_noOfVertices == 0)// Empty array
            return "The polygon has 0 vertices."; 
        String result = "The polygon has " + _noOfVertices + " vertices:\n" + "(";
        int i;
        for (i = 0; i < _noOfVertices - 1; i++)
        {
            result += _vertices[i] + ",";
        }
        if (i == _noOfVertices - 1)
            result += _vertices[i] + ")";
        return result; 
    }
    
    /**
     * Compute the polygon perimeter.
     * 
     * @return The polygon perimeter.
     */
    
    public double calcPerimeter()
    {
        int i;
        double perimeter = 0; 
        if (_noOfVertices < 2)// Only one point.
            return 0.0;
        for (i = 0; i < _noOfVertices - 1; i++)
        {
            perimeter += _vertices[i].distance(_vertices[i + 1]);
        }
        if (i == _noOfVertices - 1)
        {
            perimeter += _vertices[i].distance(_vertices[0]);
        }
        return perimeter;
    }
    
    /**
     * Compute the polygon area through deviding it into triangles set from point in [0] slot.
     * 
     * @return The polygon area.
     */
    
    public double calcArea()
    {
        if (_noOfVertices < 3)// Only 2 point = no erea to calculate.
            return 0.0;
        double triangleArea = 0;    
        for (int i = 0; i < _noOfVertices - 2; i++)
        //Using the Heron equation. 
        { 
            double sideA = _vertices[0].distance(_vertices[i + 1]);
            double sideB = _vertices[0].distance(_vertices[i + 2]);
            double sideC = _vertices[i + 1].distance(_vertices[i + 2]);
            double triangleHalfPerimeter = (sideA + sideB + sideC) / 2;
            triangleArea += Math.sqrt(triangleHalfPerimeter * (triangleHalfPerimeter - sideA) * (triangleHalfPerimeter - sideB) * (triangleHalfPerimeter - sideC));
        }
        return triangleArea;
    }
    
    /**
     * Check if this polygon is bigger than a reference polygon.
     * 
     * @parm other - the reference polygon.
     * @return True if this polygon is bigger than the reference polygon.
     */
    
    public boolean isBigger(Polygon other)
    {
        if (this != null)
            if (this.calcArea() > other.calcArea())
                return true;
        return false;                
    }
    
    /**
     * Check if a givin point exists in the polygon.
     * 
     * @parm p - the reference point.
     * @return the array index location of the point if it exsits in this polygon.
     */
    public int findVertex(Point p)
    {
        for (int i =0; i < _noOfVertices; i++)
        {
            if (p.equals(_vertices[i]))
                return i;            
        }
        return -1; // If not found.
    }
    
    /**
     * If a givin point exists in the polygon, for diffrient locations it will return diffrient points.
     * 
     * @parm p - the reference point.
     * @return Copy of first point if refrence point is at the last array slot.
     * @return Copy of refrence point if it is the only point in the array.
     * @return Copy of the following point for other cases as long as point is found.
     */
    
    public Point getNextVertex(Point p)
    {
        int possition = findVertex(p);
        if (possition != -1)// Point exsists in array.
        {  
            if (possition == MAX_POINTS -1)
            {
                Point copyFirstPoint = new Point (_vertices[0]);
                return copyFirstPoint;
            }    
            if (_noOfVertices == 1) // p is the only point in the polygon.
            {
                Point copyParm_p = new Point(p);
                return copyParm_p;
            }
            Point copyNextPoint = new Point (_vertices[possition + 1]);
            return copyNextPoint;
        }
        return null;
    }
    
    /**
     * Returns the bounding box around the polygon as a new polygon object.
     * Method finds the furthest point of opposite directions (x,-x,y,-y) but all found in the first quarter, then uses there coordinates to define 4 corner points.
     * 
     * @return bounding box polygon object for this polygon. 
     */
    
    public Polygon getBoundingBox()
    {
        if (_noOfVertices < 3)
            return null;     
        Point bottomPoint = new Point (_vertices[0]);
        Point rightPoint = new Point (_vertices[0]);
        Point topPoint = new Point (_vertices[0]);
        Point leftPoint = new Point (_vertices[0]);
        for (int i = 0; i < _noOfVertices; i++)
        {
            if (bottomPoint.isAbove(_vertices[i]))
            {
                bottomPoint = new Point (_vertices[i]);
            }
        }
        double y_Bottom = bottomPoint.getY();
        
        for (int i = 0; i < _noOfVertices; i++)
        {
            if (rightPoint.isLeft(_vertices[i]))
            {
                rightPoint = new Point (_vertices[i]);
            }
        }
        double x_Right = rightPoint.getX();

        topPoint = this.highestVertex();
        double y_Top = topPoint.getY();
        
        for (int i = 0; i < _noOfVertices; i++)
        {
            if (leftPoint.isRight(_vertices[i]))
            {
                leftPoint = new Point (_vertices[i]);
            }
        }
        double x_Left = leftPoint.getX();
        
        Polygon boundingBox = new Polygon();
        boundingBox.addVertex(x_Left , y_Bottom);
        boundingBox.addVertex(x_Right , y_Bottom);
        boundingBox.addVertex(x_Right , y_Top);
        boundingBox.addVertex(x_Left , y_Top);
        
        return boundingBox;
    }
}

