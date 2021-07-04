package ir.ac.kntu.postcompany;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        ArrayList<Double> trucks = new ArrayList<>();
        ArrayList<Double> boxes = new ArrayList<>();
        read(boxes,trucks);

        algorithm.packTrucks(trucks, boxes);

        write(trucks);
    }

    private static void write(ArrayList<Double> trucks) {
        System.out.println("packed trucks are :");
        for (int i = 0; i < trucks.size(); i++) {
            System.out.println("truck " +(i+1)+"th with " + trucks.get(i) +  " weight ");
        }
    }

    private static void read(ArrayList<Double> boxes, ArrayList<Double> trucks) {
        System.out.println("Enter number of boxes : ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight of " + (i+1) + "th box: ");
            boxes.add(scanner.nextDouble());
        }
        System.out.println("Enter number of trucks: ");
        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            trucks.add(0d);
        }
    }
}
