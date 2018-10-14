/**
* Bovina Evgenia, 13.10.18
* grade 100
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufbackwash;
    private int openSites = 0;
    private boolean[] grid; //массив, в котором хранится состояние для каждой ячейки (true = opened)

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        this.n = n;
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();

        uf = new WeightedQuickUnionUF(n * n + 2); // 2 virtual nodes
        ufbackwash = new WeightedQuickUnionUF(n * n + 1); // 1 virtual node (only top)
        grid = new boolean[n * n + 2];
        grid[0] = true; // top node
        grid[n * n + 1] = true; // bottom node

        // all sites blocked
        for (int i = 1; i <= n * n; i++) {
            grid[i] = false;
        }
    }

    private void validation(int row, int col) {
        if (row <= 0 || row > n) throw new java.lang.IllegalArgumentException();
        if (col <= 0 || col > n) throw new java.lang.IllegalArgumentException();
    }

    private int xyToIndex(int row, int col) { // i, j >0, указывает на конкретный элемент массива
        validation(row, col);
        return (row - 1) * n + col;
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        validation(row, col);

        boolean open = isOpen(row, col);
        if (!open) {
            grid[xyToIndex(row, col)] = true;
            openSites++;
            if (row != 1 && isOpen(row - 1, col)) {  // down
                uf.union(xyToIndex(row - 1, col), xyToIndex(row, col));
                ufbackwash.union(xyToIndex(row - 1, col), xyToIndex(row, col));
            }
            if (row != n && isOpen(row + 1, col)) {  // up
                uf.union(xyToIndex(row + 1, col), xyToIndex(row, col));
                ufbackwash.union(xyToIndex(row + 1, col), xyToIndex(row, col));
            }
            if (col != 1 && isOpen(row, col - 1)) {  // left
                uf.union(xyToIndex(row, col - 1), xyToIndex(row, col));
                ufbackwash.union(xyToIndex(row, col - 1), xyToIndex(row, col));
            }
            if (col != n && isOpen(row, col + 1)) {  // right
                uf.union(xyToIndex(row, col + 1), xyToIndex(row, col));
                ufbackwash.union(xyToIndex(row, col + 1), xyToIndex(row, col));
            }
            if (row == 1) {
                uf.union(xyToIndex(row, col), 0);
                ufbackwash.union(xyToIndex(row, col), 0);
            }
            if (row == n) {
                uf.union(xyToIndex(row, col), n * n + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        validation(row, col);
        return grid[xyToIndex(row, col)];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        validation(row, col);
        return (ufbackwash.connected(xyToIndex(row, col), 0));
    }

    public int numberOfOpenSites() {       // number of open sites

        return openSites;
    }

    public boolean percolates() { // does the system percolate?
        return (uf.connected(0, n * n + 1));
    }

    public static void main(String[] args) { // test client (optional)
    }
}
