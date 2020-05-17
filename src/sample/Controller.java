package sample;

import game.Habitat;
import game.Player;
import game.Tube;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public AnchorPane pane;
    @FXML
    public Text scoreText;


    Habitat habitat = new Habitat(0.3, 0);
    Player player = new Player(50, 0, habitat, 10);
    Tube tube;
    AnimationTimer gameProcess;
    private long lastUpdate;
    private int score = 0;

    static boolean GAME_OVER = false;

    Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!GAME_OVER) {
                    player.flap();
                } else {
                    restart();
                }
            }
        });

        tube = new Tube(pane);

        gameProcess = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (GAME_OVER){
                    stop();
                }
                game();
                if (now - lastUpdate >= 3000) {
                    repaint();
                    lastUpdate = now ;
                }
            }


        };
        gameProcess.start();

    }
    private void game() {
        if (player.show().getCenterY() >= pane.getHeight()) {
            GAME_OVER = true;

        }
        for (int i = 0; i < tube.getObjects().size(); i++) {
            if (player.show().getCenterX() + player.show().getRadiusX() * 2 > tube.getObjects().get(i).getX()
                && player.show().getCenterX() + player.show().getRadiusX() * 2 < tube.getObjects().get(i).getX()
                                                                            + tube.getObjects().get(i).getWidth()) {
                if (i % 2 == 0) {
                    if (player.show().getCenterY() + player.getSize()
                            < tube.getObjects().get(i).getHeight()) {
                        GAME_OVER = true;
                    }
                } else {
                    if (player.show().getCenterY() + player.getSize()
                            > tube.getObjects().get(i).getY()) {
                        GAME_OVER = true;
                    }else {
                        score++;
                    }
                }
            }
        }
    }

    private void repaint() {
        pane.getChildren().clear();
        Ellipse ellipse = player.show();
        pane.getChildren().addAll(tube.generate());
        pane.getChildren().add(ellipse);
        player.update(habitat);
        scoreText.setText(String.valueOf(score / 9));
    }

    private void restart() {
        score = 0;
        GAME_OVER = false;
        tube.clear();
        player = new Player(50, 0, habitat, 10);
        gameProcess.start();
    }

}
