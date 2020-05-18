package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Player {
    double x;
    double y;
    double velX;
    double velY;
    double size = 5;



    public Player(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.velX = speed;
        this.velY = 0;
    }

    public Player(double x, double y, Habitat habitat, double size) {
        this(x, y, habitat.speed);
        this.size = size;
    }

    public double getVelY() {
        return velY;
    }

    public double getSize() {
        return size;
    }


    public Ellipse show() {
        Ellipse ellipse = new Ellipse(x, y, size, size);
        ellipse.setFill(Color.RED);

        return ellipse;
    }

    public void update(Habitat habitat) {
        velY += habitat.gravity;
        if (velY < 0) {
            if (y > 20) {
                y += velY;
            }else {
                velY = 0;
            }
        } else {
            y += velY;
        }
        x += velX;
    }

    public void flap() {
        if (velY > -3) {
            velY -= 7;
        }
    }

}
