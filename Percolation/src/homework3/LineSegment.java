/**
 * Created by eric on 2017/4/6.
 */
public class LineSegment {
    private Point p,q;
    public LineSegment(Point p, Point q){
        this.p=p;
        this.q=q;
    }       // constructs the line segment between points p and q
    public   void draw(){
        this.p.isDrawed=1;
        this.q.isDrawed=1;
    }                // draws this line segment
    public String toString(){
        return "toString";
    }                    // string representation
}
