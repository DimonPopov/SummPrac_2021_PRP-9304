package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


/* Родительский класс всех контроллеров, осуществляет задачу смену сцен и создание дополнительных окон. */
public class mainController {

    public void setScene(ActionEvent event, String path){
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Stage createStage(String path, String name){
        Stage newStage = new Stage();
        try{
            FXMLLoader fmxloader = new FXMLLoader();
            fmxloader.setLocation(getClass().getResource(path));
            Scene scene = new Scene(fmxloader.load());
            newStage.setTitle(name);
            newStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
        return newStage;
    }
}
