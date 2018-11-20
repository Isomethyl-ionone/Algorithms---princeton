/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(
            Point[] points) {    // finds all line segments containing 4 or more points

        // validation
        if (points == null) throw new IllegalArgumentException("null points");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0)
                    throw new IllegalArgumentException();
            }
        }


        int n = points.length;
        Point[] clonepoints = points.clone();  // don't mutate input array
        for (int i = 0; i < n; i++) {
            Arrays.sort(clonepoints);
            Point origin = clonepoints[i];

            Arrays.sort(clonepoints, origin.slopeOrder());

            for (int j = 1; j < n - 2; j++) {
                if (i == 3) {
                    // for (Point p : clonepoints) {
                    //     StdOut.println("clonepoints " + p);
                    // }
                    // StdOut.println("j " + origin.slopeTo(clonepoints[j]));
                    // StdOut.println("j+1 " + origin.slopeTo(clonepoints[j+1]));
                    // StdOut.println("j+2 " + origin.slopeTo(clonepoints[j+2]));
                    //
                    // StdOut.println("j " + j);
                    // StdOut.println("j " + clonepoints[j]);

                }
                if (origin.slopeTo(clonepoints[j]) == clonepoints[j]
                        .slopeTo(clonepoints[j + 1])
                        && clonepoints[j].slopeTo(clonepoints[j + 1]) == clonepoints[j + 1]
                        .slopeTo(clonepoints[j + 2]) && origin.compareTo(clonepoints[j]) < 0) {

                    Point[] gotpoints = new Point[4];
                    gotpoints[0] = origin;
                    gotpoints[1] = clonepoints[j];
                    gotpoints[2] = clonepoints[j + 1];
                    gotpoints[3] = clonepoints[j + 2];
                    Arrays.sort(gotpoints);

                    segmentsList.add(new LineSegment(gotpoints[0], gotpoints[3]));

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


    public static void main(String[] args) {
        // // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        // }
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment lineSegments : collinear.segments()) {
        //     StdOut.println("main " + lineSegments);
        // }

    }
}
