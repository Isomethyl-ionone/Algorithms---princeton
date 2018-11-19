/* *****************************************************************************
 *  Name: dfgvdefge
 *  Date:fgefbet
 *  Description:getge
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {  // finds all line segments containing 4 points
        validation(points);
        int n = points.length;
        Point[] clonepoints = points.clone();
        Arrays.sort(clonepoints);
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                for (int j = k + 1; j < n; j++) {
                    for (int m = j + 1; m < n; m++) {
                        if (clonepoints[j].slopeTo(clonepoints[i]) == clonepoints[m]
                                .slopeTo(clonepoints[i]) && (clonepoints[k].slopeTo(clonepoints[i])
                                == clonepoints[j].slopeTo(clonepoints[i]))) {
                            Point p = clonepoints[i];
                            Point s = clonepoints[m];
                            segmentsList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {     // the number of line segments
        int num = segmentsList.size();
        return num;
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

    public LineSegment[] segments() {     // the line segments
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
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        // for (LineSegment lineSegments : collinear.segments()) {
        //     StdOut.println("main " + lineSegments);
        // }
        //
        // System.out.println(collinear.numberOfSegments());
    }
}
