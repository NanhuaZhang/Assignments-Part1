/**
 * Created by eric on 2017/4/6.
 */

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x,y;
    public int isDrawed;
//    private static final Comparator<Point> BY_POINT=new ByPoint();
    public Point(){
        this.isDrawed=-1;
    }
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
        this.isDrawed=0;
    }                        // constructs the point (x, y)

    public   void draw()   {
        this.isDrawed=1;
    }                            // draws this point
    public   void drawTo(Point that){
        that.isDrawed=1;
        this.isDrawed=1;
    }                   // draws the line segment from this point to that point
    public String toString(){
        return "what?";
    }                           // string representation

    public int compareTo(Point that){
        if (this.y>that.y)
            return 1;
        else if(this.y==that.y&&this.x>that.x)
            return 1;
        return 0;
    }     // compare two points by y-coordinates, breaking ties by x-coordinates

    public double slopeTo(Point that){
        if(this.y==that.y&&this.x==that.x)
            return Double.NEGATIVE_INFINITY;
        if(this.x==that.x)
            return Double.POSITIVE_INFINITY;
        return (that.y-this.y)/(that.x-this.x);
    }       // the slope between this point and that point

    private static class ByPoint implements Comparator<Point>{
        private Point p0;
        public ByPoint(Point point){
            this.p0=point;
        }
        @Override
        public int compare(Point o1, Point o2) {
            if(p0.slopeTo(o1)<p0.slopeTo(o2)){
                return 0;
            }else
                return 1;

        }
    }
    public Comparator<Point> slopeOrder() {
        Comparator<Point> comparator=new ByPoint(this);
        return comparator;
    }             // compare two points by slopes they make with this point

    public static void main(String []args){
        Point a=new Point(0,1);
    }
}