package ai;

import java.util.ArrayList;
import java.util.Random;

public class Network {
    ArrayList<Perceptron> perceptrons = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();
    int score = 0;

    public Network() {
        for (int i = 0; i < 3; i++) {
            perceptrons.add(new Perceptron());
        }
        perceptrons.add(new Perceptron(Type.OUT));
        for (int i = 0; i < 3; i++) {
            connections.add(new Connection(perceptrons.get(i), perceptrons.get(perceptrons.size() - 1)));
        }
    }

    public void getX(double... doubles) {
        for (double aDouble : doubles) {
            for (Perceptron p :
                    perceptrons) {
                if (p.type == Type.IN) {
                    p.addX(aDouble);
                }
            }
        }
    }

    public void activateNetwork() {
        for (Connection c :
                connections) {
            c.activate();
        }
    }

    public double returnValue() {
        activateNetwork();
        ArrayList<Double> outW = new ArrayList<>();
        for (Perceptron p :
                perceptrons) {
            if (p.out()) {
                outW.add(p.sigma());
            }
        }
        return max(outW);
    }

    public void mutate() {
        int mutate = new Random().nextInt(3);
        System.out.println("random" + mutate);
        switch (mutate) {
            case 0:
                break;
            case 1:
                addConnection();
                break;
            case 2:
                addNode();
                break;

        }
    }

    private void addNode() {
        Connection connection = connections.get((int) (Math.random() * connections.size()));
        Perceptron p = new Perceptron();
        connection.disable();
        connections.add(Connection.create(connection.out, p));
        connections.add(Connection.create(p, connection.in));
    }

    private void addConnection() {
        Perceptron p;
        Perceptron p1;
        do {
            p = perceptrons.get((int) (Math.random() * perceptrons.size()));
            p1 = perceptrons.get((int) (Math.random() * perceptrons.size()));
        } while (p != p1 && !p.out() && !p1.out());
        connections.add(Connection.create(p, p1));
    }

    public double max(ArrayList<Double> doubles) {
        double max = doubles.get(0);
        int maxId = 0;
        for (int i = 1; i < doubles.size(); i++) {
            if (max < doubles.get(i)) {
                max = doubles.get(i);
                maxId = i;
            }
        }
        return max;
    }

    public void updateScore() {
        score++;
    }
}
