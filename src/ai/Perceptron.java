package ai;

import java.util.ArrayList;

public class Perceptron {
    ArrayList<Double> W = new ArrayList<>();
    double net;

    public double sigma(){
        return 1/(1 + Math.pow(Math.E, -1 * net));
    }

}
