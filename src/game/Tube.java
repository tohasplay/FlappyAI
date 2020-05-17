package game;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tube {
    final double speed = 5;
    final double safe = 100;
    ArrayList<Rectangle> objects = new ArrayList<>();
    long time = System.currentTimeMillis();
    AnchorPane pane;


    public Tube(AnchorPane pane) {
        this.pane = pane;
        new Thread(() -> {
            while (true){
                if (System.currentTimeMillis() - time < 800){
                }else {
                    generateSet();
                    time = System.currentTimeMillis();
                }
            }
        }).start();
    }

    public ArrayList<Rectangle> getObjects() {
        return objects;
    }

    public ArrayList<Rectangle> generate() {
        update();
        return objects;
    }

    public void generateSet(){
            Rectangle rectangle = new Rectangle(pane.getWidth(), 0, 50, 50 + Math.random() * 150);
            rectangle.setFill(Color.GREEN);
            Rectangle rectangle1 = new Rectangle(pane.getWidth(), safe + rectangle.getHeight(), 50, pane.getHeight() - safe - rectangle.getHeight());
            rectangle1.setFill(Color.GREEN);
            objects.add(rectangle);
            objects.add(rectangle1);
    }

    private void update() {
        for (Rectangle r :
                objects) {
            r.setX(r.getX() - speed);
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getX() < -1 * 50){
                objects.set(i,null);
            }
        }
        ArrayList<Rectangle> tmp = new ArrayList<>();
        for (Rectangle object : objects) {
            if (object != null) {
                tmp.add(object);
            }
        }
        objects.clear();
        objects.addAll(tmp);
    }


    public void clear() {
        objects.clear();
    }
}
