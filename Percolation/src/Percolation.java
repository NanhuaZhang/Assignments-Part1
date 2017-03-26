import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backflow;
    private boolean[] siteisopen;
    private int upsiteindex;
    private int rows;
    public Percolation(int n){
        if(n<=0){
            throw new IllegalArgumentException("n can not be smaller than 1.");
        }
        upsiteindex=n*n+1;
        rows=n;
        uf=new WeightedQuickUnionUF(upsiteindex+1);
        backflow=new WeightedQuickUnionUF(upsiteindex);
        siteisopen=new boolean[upsiteindex+1];
        siteisopen[0]=true;
        siteisopen[upsiteindex]=true;
        for(int i=1;i<upsiteindex;++i)
            siteisopen[i]=false;
    } 
 // convert the row and col to index
    private int rowColToIndex(int row, int col){
        row=row - 1;
        int index=row * rows + col;
        if(index>rows*rows||index<0||row <0||row>=rows||col<=0||col>rows)
            throw new IndexOutOfBoundsException();
        return index;
    }// create n-by-n grid, with all sites blocked
    private int[] pushArray (int[]array,int x){
        int len=array.length;
        int []result=new int [len+1];
        for(int i=0;i<len;++i){
            result[i]=array[i];
        }
        result[len]=x;
        return result;
    }
    private int[] aroundIndex(int row,int col) {
        int [][]directions={{-1,0},{0,-1},{1,0},{0,1}};
        int [] aroundIndexs={};
        for(int []dir:directions){
            try {
                int index=rowColToIndex(row+dir[0], col+dir[1]);
                aroundIndexs=pushArray(aroundIndexs, index);
            } catch (IndexOutOfBoundsException e) {
                // TODO: handle exception
                continue;
            }
        }
        return aroundIndexs;
    }
    public void open(int row, int col){
        int index=rowColToIndex(row, col);
        siteisopen[index]=true;
        int []arroundIndexs=aroundIndex(row, col);
        for(int arroundindex:arroundIndexs){
            if(siteisopen[arroundindex])
                uf.union(index, arroundindex);
            if(siteisopen[arroundindex]&&arroundindex!=upsiteindex)
            	backflow.union(index, arroundindex);
        }
        if(row==1){
            uf.union(0,index);
            backflow.union(0, index);
        }
        if(row==rows)
            uf.union(index, upsiteindex);
    }  // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col){
        int index=rowColToIndex(row, col);
        return siteisopen[index];
//        return uf.connected(0,(row-1)*ro.count()+col);
    } // is site (row, col) open?
    public boolean isFull(int row, int col){
        int index=rowColToIndex(row, col);
        return backflow.connected(0,index);
    } // is site (row, col) full?
    public int numberOfOpenSites(){
        int count=0;
        for(int i=1;i<upsiteindex;++i)
            if(siteisopen[i]==true)
                ++count;
        return count;
    }      // number of open sites
    public boolean percolates(){
        return uf.connected(0, upsiteindex);
    }              // does the system percolate?
       
    public static void main(String[] args){

//    	 Percolation pe=new Percolation(3);
//       pe.open(1, 3);
//       pe.open(2, 3);
//       pe.open(3, 3);
//       pe.open(3, 1);
//       StdOut.print(pe.isFull(3, 1));
//       pe.open(2, 1);
//       pe.open(1, 1);
//        Percolation pe=new Percolation(7);
//      pe.open(6, 1);
//      pe.open(7, 1);
//      pe.open(7, 2);
//      pe.open(7, 4);
//      pe.open(1, 1);
//      pe.open(1, 5);
//      pe.open(2, 5);
//      pe.open(3, 5);
//      pe.open(4, 5);
//      pe.open(5, 5);
//      pe.open(6, 5);
//      pe.open(7, 5);
//      pe.open(2, 1);
//      pe.open(4, 1);
//      pe.open(5, 1);
//      pe.open(3, 1);
//    StdOut.println(pe.percolates());
   }  // test client (optional)

}

