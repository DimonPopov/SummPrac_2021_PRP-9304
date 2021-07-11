package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import sample.Prim.*;

import static java.lang.System.exit;


public class Main extends Application {

    private Stage primaryStage;
    private static CustomGraph graph = new CustomGraph(false);
    private static ArrayList<String> log = new ArrayList();
    public static ArrayList<File> png = new ArrayList<>();

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

    public static CustomGraph getGraph(){ return graph; }
    public static Collection getLog(){ return log; }

    public static void main(String[] args) {

        for(String str : args){
            if(str.equals("CLI")){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Введите кол-во ребер.");
                try{
                    String count = reader.readLine();
                    int c = Integer.parseInt(count);
                    System.out.println("Введите данные(Вершина1 Вершина2 Вес)");
                    while(c != 0){
                        String[] reg = reader.readLine().split(" ");
                        graph.addEdge(reg[0], reg[1], Integer.parseInt(reg[2]));
                        c--;
                    }
                    CustomPrim prim = new CustomPrim(graph);
                    prim.doIt();
                    System.out.println(prim.getLog());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                exit(0);
            }else{
                launch(args);
            }
        }


    }
}
