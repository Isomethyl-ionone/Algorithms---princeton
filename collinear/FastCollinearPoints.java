/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(
            Point[] points) {    // finds all line segments containing 4 or more points
        validation(points);
        int n = points.length;
        Point[] clonepoints = points.clone();
        for (int i = 0; i < n; i++) {
            // for (Point p : clonepoints) {
            //     StdOut.println("clonepoints " + p);
            // }
            Arrays.sort(clonepoints);
            // for (Point p : clonepoints) {
            //     StdOut.println("clonepoints sorted " + p);
            // }
            Arrays.sort(clonepoints, clonepoints[i].slopeOrder());
            // for (Point p : clonepoints) {
            //     StdOut.println("clonepoints sorted slope " + p);
            // }
            for (int j = i + 1; j < n - 2; j++) {
                // System.out.println("i " + i);
                // System.out.println("j " + j);
                if (clonepoints[i].slopeTo(clonepoints[j]) == clonepoints[j]
                        .slopeTo(clonepoints[j + 1])
                        && clonepoints[j].slopeTo(clonepoints[j + 1]) == clonepoints[j + 1]
                        .slopeTo(clonepoints[j + 2])) {
                    Point[] gotpoints = new Point[4];
                    gotpoints[0] = clonepoints[i];
                    gotpoints[1] = clonepoints[j];
                    gotpoints[2] = clonepoints[j + 1];
                    gotpoints[3] = clonepoints[j + 2];
                    Arrays.sort(gotpoints);
                    // for (Point p : gotpoints) {
                    //     StdOut.println("gotpoints " + p);
                    // }
                    segmentsList.add(new LineSegment(gotpoints[0], gotpoints[3]));
                    // for (LineSegment s : segmentsList) {
                    //     StdOut.println("segmentsList " + s);
                    // }
                }
            }
        }
    }

    public int numberOfSegments() {     // the number of line segments
        int num = segmentsList.size();
        return num;
    }

    public LineSegment[] segments() {               // the line segments
        LineSegment[] lineSegments = new LineSegment[segmentsList.size()];
        lineSegments = segmentsList.toArray(lineSegments);
        return lineSegments;
    }

    private void validation(Point[] points) {
        if (points == null) throw new IllegalArgumentException("null points");
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) throw new IllegalArgumentException("null points");
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("null points");
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment lineSegments : collinear.segments()) {
            StdOut.println("main " + lineSegments);
        }
        
    }
}
