package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.concurrent.*;
import sample.*;

import java.io.File;
import java.util.ArrayList;



public class page3Controller extends mainController{

    @FXML private Button butt_stepForward;
    @FXML private Button butt_finish;
    @FXML private Button butt_stepBack;
    @FXML private Button butt_refresh;
    @FXML private TableView<?> logTable;
    @FXML private TableColumn<?, ?> col_log;
    @FXML private Button butt_newData;
    @FXML private ImageView imageArea;
    private ArrayList<File> png;    // Лист файлов с рисунками.
    private int iter;               // Итератор по рисункам.

    @FXML
    void backScene(ActionEvent event) {
        for(File f : this.png){
            f.delete();
        }
        this.setScene(event,"/sample/FXML/page2.fxml");
    }

    @FXML
    void onFinish(ActionEvent event) {
        try {
            while (png.size() > this.iter + 1) {
                this.iter += 1;
                imageArea.setImage(new Image(png.get(iter).toURI().toString()));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
    }
}
