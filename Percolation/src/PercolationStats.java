import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	private Percolation p;
	private int trials;
	private double []results;
	private int rows;
	public PercolationStats (int n,int trials){
		if(n<=0||trials<=0)
			throw new IllegalArgumentException("parameter is out of range!");
		p=new Percolation(n);
		results=new double[trials];
		this.trials=trials;
		rows=n;
	}
	private void run() {
		for(int i=0;i<trials;++i){
			while(!p.percolates()){
				int row=StdRandom.uniform(1, rows+1);
				int col=StdRandom.uniform(1, rows+1);
				p.open(row, col);
			}
			results[i]=(p.numberOfOpenSites()/(double)(rows*rows));
			p=new Percolation(rows);
		}
	}
	public double mean() {
		return StdStats.mean(results);
	}
	public double stddev() {
		return StdStats.stddevp(results);
	}
	public double confidenceLo() {
		return mean()-(1.96*stddev())/Math.sqrt(trials);
	}
	public double confidenceHi() {
		return mean()+(1.96*stddev())/Math.sqrt(trials);
	}
	public static void main(String[] args){
		PercolationStats percolationStatus=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		percolationStatus.run();
		System.out.println("mean= "+percolationStatus.mean()+"\n");
		System.out.println("stddev "+percolationStatus.stddev()+"\n");
		System.out.println("95% confidence interval = ["+percolationStatus.confidenceLo()+","+percolationStatus.confidenceHi()+"]");
	}
}
