package Games.Fly;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Engine extends Application {
     int counter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    static int ScreenSize = 1024;

    /**
     * Key Bindings:
     * UP, DOWN, LEFT, RIGHT (Normal speed movement)
     * W, A, S, D (Schnell movement)
     *
     */



    @Override
    public void start(Stage stage) {
        stage.setFullScreen(true);
        stage.setTitle("Plane");
        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        /**BACKGROUND**/
        Image image = new Image("https://www.gamedevelopment.blog/wp-content/uploads/2019/01/spacebg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        pane.setBackground(background);

        /**EXPLOSION**/
        Image explosionImportedJPG = new Image(getClass().getResourceAsStream("Megumin.png"));
        ImageView explosionView = new ImageView(explosionImportedJPG);

        Media meguminExplosion = new Media(new File("src/Games/Fly/MeguminExplosion.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(meguminExplosion);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        final MediaPlayer mediaPlayerFinal = mediaPlayer;



        /**Create Plane**/
        Plane Jet = new Plane(ScreenSize/2,ScreenSize/2);
        Rectangle planeVisual = new Rectangle(10,10,Color.GREEN);
        planeVisual.setX(Jet.x);planeVisual.setY(Jet.y);
        pane.getChildren().add(planeVisual);

        /**Keys And Events  -- Plane**/
        double speedLimit = 1.5;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.UP) {
                if(Jet.speedY >= -speedLimit)
                    Jet.speedY -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.DOWN) {
                if(Jet.speedY <= speedLimit)
                    Jet.speedY += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.RIGHT) {
                if(Jet.speedX <= speedLimit)
                    Jet.speedX += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.LEFT) {
                if(Jet.speedX >= -speedLimit)
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.UP & key.getCode()== KeyCode.RIGHT) {
                if(Jet.speedY >= -speedLimit)
                    Jet.speedY -= 0.1;
                if(Jet.speedX <= speedLimit)
                    Jet.speedX += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.UP & key.getCode()== KeyCode.LEFT) {
                if(Jet.speedY >= -speedLimit)
                    Jet.speedY -= 0.1;
                if(Jet.speedX >= -speedLimit)
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.DOWN & key.getCode()== KeyCode.LEFT) {
                if(Jet.speedY <= speedLimit)
                    Jet.speedY += 0.1;
                if(Jet.speedX >= -speedLimit)
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.DOWN & key.getCode()== KeyCode.RIGHT) {
                if(Jet.speedY <= speedLimit)
                    Jet.speedY += 0.1;
                if(Jet.speedX <= speedLimit)
                    Jet.speedX += 0.1;
            }
        });


        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.W) {
                Jet.speedY -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.S) {
                    Jet.speedY += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.D) {
                    Jet.speedX += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.A) {
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.W & key.getCode()== KeyCode.D) {
                    Jet.speedY -= 0.1;
                    Jet.speedX += 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.W & key.getCode()== KeyCode.A) {
                    Jet.speedY -= 0.1;
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.S & key.getCode()== KeyCode.A) {
                    Jet.speedY += 0.1;
                    Jet.speedX -= 0.1;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.S & key.getCode()== KeyCode.D) {
                    Jet.speedY += 0.1;
                    Jet.speedX += 0.1;
            }
        });


        /**Create Asteroid**/
        double radius = 40;
        Asteroid Asteroid = new Asteroid(ScreenSize/3,ScreenSize/3,0,0, radius);
        /*Circle asteroidVisual = new Circle( radius,Color.GRAY);
        asteroidVisual.setCenterX(Asteroid.x);asteroidVisual.setCenterY(Asteroid.y);
         */

        Rectangle asteroidVisual = new Rectangle(50,50,Color.GRAY);
        asteroidVisual.setX(Asteroid.x);asteroidVisual.setY(Asteroid.y);

        pane.getChildren().add(asteroidVisual);



        Timeline scheduler = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /**Update Plane**/
                Jet.move();
                sync(Jet,planeVisual);
                Jet.collideBorder();

                /**Update Asteroid**/
                Asteroid.move();
                sync(Asteroid,asteroidVisual);
                Asteroid.collideBorder();
                //Asteroid.magicInfluence();

                /**Check Collision between objects**/
                if(counter != 0)counter--;
                if(counter == 1){
                    pane.getChildren().remove(explosionView);
                    mediaPlayer.stop();

                }
                if(arePlaneAsteroidColliding(Jet, Asteroid) && counter == 0) {
                    explosionView.setX(Jet.x - explosionImportedJPG.getWidth()/2); explosionView.setY(Jet.y - explosionImportedJPG.getHeight()/2);

                    mediaPlayer.play();

                    pane.getChildren().add(explosionView);
                    try { Thread.sleep(2500); } catch(InterruptedException ex) { Thread.currentThread().interrupt();}



                    //Remember, jet fuel might not melt steel beams, but it sure does explode
                    Jet.speedX = Math.random()*10/Jet.mass; Jet.speedY = Math.random()*10/Jet.mass;
                    Asteroid.speedX = Math.random()*10/Asteroid.mass; Asteroid.speedY = Math.random()*10/Asteroid.mass;


                    counter = 500;

                }

            }
        }));
        scheduler.setCycleCount(Timeline.INDEFINITE);
        scheduler.play();




        stage.setScene(scene);
        stage.show();
    }



    void sync(Thing obj, Rectangle visual){
        visual.setX(obj.x);
        visual.setY(obj.y);
    }

    void sync(Thing obj, Circle visual){
        visual.setCenterX(obj.x);
        visual.setCenterY(obj.y);
    }


    static boolean arePlaneAsteroidColliding(Plane plane, Asteroid asteroid){
        if (plane.x < asteroid.x + 50 &&
                plane.x + 10 > asteroid.x &&
                plane.y < asteroid.y + 50 &&
                plane.y + 10 > asteroid.y) {
            System.out.println("collision");
            return true;
        }
        return false;
    }



}
