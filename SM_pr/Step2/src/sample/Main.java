package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import sample.Prim.*;


public class Main extends Application {

    private Stage primaryStage;
    private static WeightGraph graph1;
    private static WeightGraph graph2;
    public static ArrayList<File> png;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SP2021");
        initRootLayot();
    }

    public void initRootLayot(){
        try{
            InputStream iconStream = getClass().getResourceAsStream("/sample/assets/icon3.png");
            Image image = new Image(Objects.requireNonNull(iconStream));
            primaryStage.getIcons().add(image);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/FXML/page2.fxml")));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static WeightGraph getGraph1(){ return graph1; }

    public static WeightGraph getGraph2(){ return graph2; }

    public static ArrayList<File> getPngList(){ return png; }

    public static void main(String[] args) { launch(args); }
}
