package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class page1Controller extends mainController{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button butt_nextScene;
    @FXML private Button butt_about;
    private Stage stageAbout;

    @FXML /* По нажатию на "О программе" выводит окно с информацией. */
    void viewAbout(ActionEvent event) {
        if(!stageAbout.isShowing()) {
            stageAbout.showAndWait();
        }
    }

    @FXML /* Смена сцены на page2 по нажатию кнопки далее. */
    void nextScene(ActionEvent event) {
        this.setScene(event,"/sample/FXML/page2.fxml");
    }

    @FXML
    void initialize() {
        stageAbout = this.createStage("/sample/FXML/aboutProgram.fxml", "О программе");
    }
}