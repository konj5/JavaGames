package Games;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**Add BUTTONS*/



public class Scrambled_Numbers extends Application {

    private static int[][] plosca = new int[5][5];
    private static int[][] resenaPlosca = new int[5][5];

    private static int width = 500;
    private static int height = 500;
    private static int circleR = 50;
    private static GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Graphics setup
        Group root;
        Canvas canvas;
        primaryStage.setTitle("Domaca Naloga Game");
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        root = new Group();
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        gc.setFill(Color.WHITE);


        setupNumbers();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::tick, 0, 1000, TimeUnit.MILLISECONDS);

    }


    private void tick() {
        clearScreen();
        drawGrid();
        drawNumbers();
        Scanner sc = new Scanner(System.in);
        System.out.println("Vpisi s cim bos zamenjal prazno mesto!");
        int input = sc.nextInt();
        switchNumbers(input);
        if(winCondition()){aWinnerIsYou();}
    }

    private void drawGrid() {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,500,500);

        gc.strokeLine(100,0,100,500); gc.strokeLine(200,0,200,500); gc.strokeLine(300,0,300,500); gc.strokeLine(400,0,400,500);
        gc.strokeLine(0,100,500,100); gc.strokeLine(0,200,500,200); gc.strokeLine(0,300,500,300); gc.strokeLine(0,400,500,400);
    }

    private void drawNumbers(){
        gc.setFill(Color.BLACK);
        for(int i = 0; i<plosca.length; i++){
            for(int j = 0; j<plosca[i].length;j++){
                if(plosca[i][j] != 0) {
                    gc.fillText(String.valueOf(plosca[i][j]), 50 + 100 * j, 50 + 100 * i);
                }
            }
        }
    }

    private void switchNumbers(int input){
        int temp = 0;
        boolean check = false;
        int xi = 0, yi = 0,x0 = 0,y0 = 0;
        for(int i = 0; i<plosca.length; i++){
            for(int j = 0; j<plosca[i].length;j++){
                if(plosca[i][j]==input){check = true;xi = i; yi = j; break;}
            }
            if(check)break;
        }
        check = false;
        for(int i = 0; i<plosca.length; i++){
            for(int j = 0; j<plosca[i].length;j++){
                if(plosca[i][j]==0){check = true;x0 = i; y0 = j; break;}
            }
            if(check)break;
        }
        if(
                ((xi==x0+1)&&(yi==y0))||
                ((xi==x0-1)&&(yi==y0))||
                ((xi==x0)&&(yi==y0+1))||
                ((xi==x0)&&(yi==y0-1))
        ){
            temp = plosca[xi][yi];
            plosca[xi][yi] = plosca[x0][y0];
            plosca[x0][y0] = temp;
        }else{
            System.out.println("Prosim izberite LEGALNO mesto, OR ELSE");
            System.out.println();
        }
    }

    static boolean winCondition(){
        return Arrays.equals(plosca,resenaPlosca);
    }



    private void clearScreen() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
    }

    static void setupNumbers(){
        int num = 0;
        for(int i = 0; i<plosca.length;i++){
            for(int j = 0; j<plosca[i].length;j++){
                plosca[i][j] = num;
                resenaPlosca[i][j] = num;
                num++;
            }
        }
        System.out.println(Arrays.toString(plosca[0]));
        System.out.println(Arrays.toString(plosca[1]));
        System.out.println(Arrays.toString(plosca[2]));
        System.out.println(Arrays.toString(plosca[3]));
        System.out.println(Arrays.toString(plosca[4]));
        System.out.println();



        for(int i = 0; i<50; i++){
            shuffle();
        }
        System.out.println(Arrays.toString(plosca[0]));
        System.out.println(Arrays.toString(plosca[1]));
        System.out.println(Arrays.toString(plosca[2]));
        System.out.println(Arrays.toString(plosca[3]));
        System.out.println(Arrays.toString(plosca[4]));
        System.out.println();
    }

    static void shuffle(){
        int temp = 0;
        int x1 = (int)(Math.random()*4); int y1 = (int)(Math.random()*4);
        int x2 = (int)(Math.random()*4); int y2 = (int)(Math.random()*4);

        temp = plosca[x1][x2];
        plosca[x1][x2] = plosca[y1][y2];
        plosca[y1][y2] = temp;
    }

    private void aWinnerIsYou(){
        clearScreen();
        gc.setFill(Color.DARKGREEN);
        gc.setFont(new Font("Times New Roman", 60));
        gc.fillText("A winner is you",50,height/2);


        try
        {
            Thread.sleep(100000000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}