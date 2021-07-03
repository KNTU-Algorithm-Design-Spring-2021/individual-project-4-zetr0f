package ir.ac.kntu.boundingbox;

import java.util.Scanner;

public class Main {

    public static void findMinAndMax(int[] arr ,int L, int R, MyInt min , MyInt max ) {
        if (R - L == 0) {
            return;
        }
        if (R - L == 1) {
            min.value = max.value = arr[L];
            return;
        }
        if (R - L == 2) {
            if (arr[L] > arr[L+1]) {
                min.value = arr[R - 1];
                max.value = arr[L];
            } else {
                min.value = arr[L];
                max.value = arr[R - 1];
            }
            return;
        }
        int mid;
        MyInt LMax = new MyInt(0);
        MyInt RMax = new MyInt(0);
        MyInt LMin = new MyInt(0);
        MyInt RMin = new MyInt(0);
        mid = (L+R-1)/2;
        findMinAndMax(arr, L , mid + 1, LMin , LMax);
        findMinAndMax(arr, mid + 1 , R, RMin , RMax);

        min.value = Math.min(LMin.value, RMin.value);
        max.value = Math.max(LMax.value, RMax.value);
    }


    public static void main(String[] args) {
        int numberOfPoints;
        System.out.println("enter number of points : ");
        Scanner scanner = new Scanner(System.in);
        numberOfPoints = scanner.nextInt();
        int[] XOfPoints = new int[numberOfPoints];
        int[] YOfPoints = new int[numberOfPoints];
        System.out.println("enter points :");
        for (int i = 0; i < numberOfPoints; i++) {
            XOfPoints[i] = scanner.nextInt();
            YOfPoints[i] = scanner.nextInt();
        }
        MyInt XMin = new MyInt(0);
        MyInt YMin = new MyInt(0);
        MyInt XMax = new MyInt(0);
        MyInt YMax = new MyInt(0);
        findMinAndMax(XOfPoints, 0, numberOfPoints, XMin, XMax);
        findMinAndMax(YOfPoints, 0, numberOfPoints, YMin, YMax);

    }
}
