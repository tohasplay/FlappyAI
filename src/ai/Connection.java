package ai;

public class Connection {
    Perceptron out;
    Perceptron in;
    boolean enabled;

    public Connection(Perceptron out, Perceptron in) {
        this.out = out;
        this.in = in;
        enabled = true;
    }

    public Connection(Perceptron out, Perceptron in, boolean enabled) {
        this.out = out;
        this.in = in;
        this.enabled = enabled;
        switch (out.type ){
            case IN:
                break;
            case OUT:
                out.type = Type.HIDDEN;
                break;
        }
        switch (in.type){
            case OUT:
                break;
            case IN:
                in.type = Type.HIDDEN;
                break;
        }

    }

    public void disable() {
        enabled = false;
    }

    public void enable(){
        enabled = true;
    }

    public void activate(){
        if (enabled){
            in.addX(out.ReLU());
        }
    }

    public static Connection create(Perceptron out, Perceptron in){
        return new Connection(out, in);
    }
}
