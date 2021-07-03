package ir.ac.kntu.boundingbox;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.Scanner;

import static ir.ac.kntu.boundingbox.Main.findMinAndMax;

public class Show extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        int numberOfPoints;
        System.out.println("enter number of points : ");
        Scanner scanner = new Scanner(System.in);
        numberOfPoints = scanner.nextInt();
        int[] xOfPoints = new int[numberOfPoints];
        int[] yOfPoints = new int[numberOfPoints];
        System.out.println("enter points :");
        for (int i = 0; i < numberOfPoints; i++) {
            xOfPoints[i] = scanner.nextInt();
            yOfPoints[i] = scanner.nextInt();
        }
        scanner.close();
        MyInt XMin = new MyInt(0);
        MyInt YMin = new MyInt(0);
        MyInt XMax = new MyInt(0);
        MyInt YMax = new MyInt(0);
        findMinAndMax(xOfPoints, 0, numberOfPoints, XMin, XMax);
        findMinAndMax(yOfPoints, 0, numberOfPoints, YMin, YMax);


        Group group = new Group();

        Circle[] points = new Circle[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Circle(xOfPoints[i],yOfPoints[i],2.5);
            points[i].setFill(Color.BLACK);
        }

        Polyline boundingBox = new Polyline(XMin.value , YMin.value
                , XMin.value , YMax.value
                , XMax.value , YMax.value
                ,XMax.value,YMin.value
                ,XMin.value , YMin.value);

        for (int i = 0; i < numberOfPoints ; i++) {
            group.getChildren().add(points[i]);
        }
        group.getChildren().add(boundingBox);
        Scene scene = new Scene(group, 600, 600);

        primaryStage.setTitle("bounding box :)");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
