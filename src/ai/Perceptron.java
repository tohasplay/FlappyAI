package ai;

import java.util.ArrayList;

public class Perceptron {
    ArrayList<Double> X = new ArrayList<>();
    double W;
    double T;
    double net = 0;
    Type type = Type.IN;


    public Perceptron() {
        T = -0.7 + Math.random() * 1.4;
        W = -1 + Math.random() * 2;
    }

    public Perceptron(Type type) {
        this();
        this.type = type;
    }


    public double sigma() {
        calcNet();
        X.clear();
        return 1. / (1 + Math.pow(Math.E, -1 * net));
    }

    public double ReLU(){
        calcNet();
        X.clear();
        return net > 0 ? net : 0.01 * net;
    }


    public void calcNet() {
        net = 0;
        for (int i = 0; i < X.size(); i++) {
            net += X.get(i) * W;
        }
        net -= T;
        System.out.println(net);
    }

    public void addX(Double d){
        X.add(d);
    }

    public boolean empty(){
        return X.isEmpty();
    }

    public boolean out() {
        return type == Type.OUT;
    }
}
