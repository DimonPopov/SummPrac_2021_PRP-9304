package sample.Controllers;

import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Time;
import java.util.concurrent.*;

import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;
import java.io.File;
import java.util.ArrayList;



public class page3Controller extends mainController{

    @FXML private Button butt_stepForward;
    @FXML private Button butt_finish;
    @FXML private Button butt_stepBack;
    @FXML private Button butt_refresh;
    @FXML private TableView<String> logTable;
    @FXML private TableColumn<String, String> col_log;
    @FXML private Button butt_newData;
    @FXML private ImageView imageArea;
    private ArrayList<File> png;        // Лист файлов с рисунками.
    private int iter;                   // Итератор по рисункам.

    @FXML
    void backScene(ActionEvent event) {
        Main.png.clear();
        this.setScene(event,"/sample/FXML/page2.fxml");
    }

    private class Anim extends AnimationTimer{
        @Override
        public void handle(long now){
            if(png.size() > iter + 1){
                stepF(new ActionEvent());
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }else{
                stop();
            }

        }
    }

    @FXML
    void onFinish(ActionEvent event) {
        Anim a = new Anim();
        a.start();
    }

    @FXML
    void refresh(ActionEvent event) {
        imageArea.setImage(new Image(png.get(0).toURI().toString()));
        this.iter = 0;
    }

    @FXML
    void stepB(ActionEvent event) {
        if(iter > 0){
            this.iter--;
            imageArea.setImage(new Image(png.get(iter).toURI().toString()));
        }
    }

    @FXML
    void stepF(ActionEvent event) {
        if(png.size() > this.iter + 1){
            this.iter++;
            imageArea.setImage(new Image(png.get(iter).toURI().toString()));
        }
    }

    @FXML
    void initialize(){
        this.png = Main.png;
        this.iter = 0;
        imageArea.setImage(new Image(png.get(0).toURI().toString()));
        ObservableList<String> details = FXCollections.observableArrayList(Main.getLog());
        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> col1 = new TableColumn<>();
        tableView.getColumns().addAll(col1);
        col_log.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        logTable.setItems(details);
    }
}
