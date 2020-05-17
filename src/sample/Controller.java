package sample;
import game.Habitat;
import game.Player;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public AnchorPane pane;
    Habitat habitat = new Habitat(0.3, 0);
    Player player = new Player(50, 0, habitat, 10);

    Stage stage;

    public Controller(Stage stage){
        this.stage = stage;
    }


    public void initialize() {
        stage.addEventHandler(KeyEvent.KEY_RELEASED , new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    player.flap();
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (player.show().getCenterY() >= pane.getHeight()){
                    stop();
                }
                pane.getChildren().clear();
                Ellipse ellipse = player.show();
                pane.getChildren().add(ellipse);
                player.update(habitat);
            }
        };
        timer.start();
    }

}
