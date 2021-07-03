package ir.ac.kntu.shortestpath;

import javafx.geometry.Point2D;

public class Algorithm {

    public static int[][] getW(Point2D[] points) {
        int[][] w = new int[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    w[i][j] = (int) points[i].distance(points[j]);
                }
            }
        }
        return w;
    }

    public static void floyd(int[][] w, int[][] d, int[][] p) {
//        for (int i = 0; i < w.length; i++) {
//            for (int j = 0; j < w.length; j++) {
//                p[i][j] = 0;
//            }
//        }
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w.length; j++) {
                d[i][j] = w[i][j];
            }
        }
        for (int k = 0; k < w.length; k++) {
            for (int i = 0; i < w.length; i++) {
                for (int j = 0; j < w.length; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) {
                        p[i][j] = k;
//                        System.out.println(k);
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }

    public static void path(int q, int r, int[][] p)
    {
        if (p[q][r] !=0) {
            path(q, p[q][r], p);
            System.out.println("v" + p[q][r]);
            path(p[q][r], r, p);
        }
    }

}
