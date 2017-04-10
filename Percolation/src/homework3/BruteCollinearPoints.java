import org.junit.Test;

/**
 * Created by eric on 2017/4/10.
 */
public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegment;
    public BruteCollinearPoints(Point[] points){
        if (points.length==0)
            throw new NullPointerException();
        for (int i=0;i<points.length;++i){
            if(points[i].isDrawed==-1)
                throw new NullPointerException();
        }
        this.points=points;
    }    // finds all line segments containing 4 points
    public int numberOfSegments(){
        return lineSegment.length;
    }        // the number of line segments
    public LineSegment[] segments(){
        double [] resultSlops=new double[];
        int count=0;
        for (int i=0;i<points.length-1;++i){
            for(int j=i+1;j<points.length;++j){
                resultSlops[count]=points[i].slopeTo(points[j]);
            }
        }
    }                // the line segments
}
