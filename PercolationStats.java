import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] results;
    private double mean = -1;
    private double stddev = -1;


    public PercolationStats(int n,
                            int trials) { // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.trials = trials;
        results = new double[trials];
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            int row;
            int col;
            while (!percolation.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            double openSites = percolation.numberOfOpenSites();
            results[i] = openSites / (n * n);
        }
    }

    public double mean() { // sample mean of percolation threshold
        mean = StdStats.mean(results);
        return mean;
    }

    public double stddev() {                    // sample standard deviation of percolation threshold
        if (trials == 1) {
            return Double.NaN;
        }
        stddev = StdStats.stddev(results);
        return stddev;
    }

    private void check() {
        if (mean == -1) {
            mean = mean();
        }
        if (stddev == -1) {
            stddev = stddev();
        }
    }

    public double confidenceLo() {                 // low  endpoint of 95% confidence interval
        check();
        return mean - (CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }

    public double confidenceHi() {               // high endpoint of 95% confidence interval
        check();
        return (mean + (CONFIDENCE_95 * stddev / Math.sqrt(trials)));
    }

    public static void main(String[] args) {         // test client (described below)

    }
}


