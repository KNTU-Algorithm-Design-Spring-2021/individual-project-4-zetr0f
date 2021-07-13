package ir.ac.kntu.postcompany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

public class Algorithm {
    private Double getT( ArrayList<Double> boxes,ArrayList<Double> trucks) {
        double ave = boxes.stream().mapToDouble(n -> n).sum() /trucks.size();
        AtomicReference<Double> max = new AtomicReference<>(0d);
        boxes.forEach((n)->{
            if ( max.get() <  n) {
                max.set(n);
            }
        } );

        return Math.max(max.get(),ave);

    }

    public void packTrucks(ArrayList<Double> trucks, ArrayList<Double> boxes){
        if (trucks == null || boxes == null) return;
        Double t = getT(boxes,trucks);
        boxes.sort(Double::compareTo);
        int j = 0;
        Collections.reverse(boxes);

        for (int i = 0; i < trucks.size(); i++) {
            for (;boxes.size() > j && trucks.get(i) < t; j++) {
                trucks.set(i, trucks.get(i) +  boxes.get(j)) ;
            }
        }

        trucks.sort(Double::compareTo);
        for (int i = 0; i < trucks.size()-1; i++) {
            for (;boxes.size() > j && trucks.get(i) < trucks.get(i+1); j++) {
                trucks.set(i, trucks.get(i) +  boxes.get(j)) ;
            }
        }
    }
}
