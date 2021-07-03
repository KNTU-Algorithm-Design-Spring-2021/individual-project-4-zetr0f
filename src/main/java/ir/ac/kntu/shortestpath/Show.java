package ir.ac.kntu.shortestpath;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.Scanner;

import static ir.ac.kntu.shortestpath.Algorithm.*;

public class Show extends Application {
    public static Point2D[] point2Ds;
    @Override
    public void start(Stage primaryStage) throws Exception {
        int numberOfPoints;
        System.out.println("enter number of points : ");
        Scanner scanner = new Scanner(System.in);
        numberOfPoints = scanner.nextInt();
//        numberOfPoints++;
        point2Ds = new Point2D[numberOfPoints];
        System.out.println("enter points(first point is origin and last point is destination :");
        for (int i = 0; i < numberOfPoints; i++) {
            point2Ds[i] = new Point2D(scanner.nextInt() ,scanner.nextInt() );
        }
        scanner.close();

        int[][] w = getW(point2Ds);
        w[0][numberOfPoints-1] = 10000;
        int[][] d = new int[numberOfPoints][numberOfPoints];
        int[][] p = new int[numberOfPoints][numberOfPoints];
        floyd(w,d, p);

        for (int i = 0; i < numberOfPoints; i++) {
            for (int j = 0; j < numberOfPoints; j++) {
                System.out.print(d[i][j]+ " ");
            }
            System.out.println();
        }
        path(0,numberOfPoints - 1,p);

        Group group = new Group();

        Circle[] points = new Circle[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Circle(point2Ds[i].getX(),point2Ds[i].getY(),2.5);
            points[i].setFill(Color.BLACK);
        }

        Polyline path = new Polyline();
        path.getPoints().addAll(point2Ds[0].getX(),point2Ds[0].getY());

        addToPolyLine(0,numberOfPoints - 1 , p ,path);
        path.getPoints().addAll(point2Ds[numberOfPoints-1].getX(),point2Ds[numberOfPoints-1].getY());

        for (int i = 0; i < numberOfPoints ; i++) {
            group.getChildren().add(points[i]);
        }
        group.getChildren().add(path);
        Scene scene = new Scene(group, 600, 600);

        primaryStage.setTitle("shortest path :)");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void addToPolyLine(int i, int i1,int[][] p, Polyline path) {
        if (p[i][i1] !=0) {
            addToPolyLine(i, p[i][i1], p,path);
            path.getPoints().addAll(point2Ds[p[i][i1]].getX(),point2Ds[p[i][i1]].getY());
            addToPolyLine(p[i][i1], i1, p,path);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

