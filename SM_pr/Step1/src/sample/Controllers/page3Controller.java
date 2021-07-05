package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class page3Controller extends mainController{

    @FXML private Button butt_stepForward;
    @FXML private Button butt_finish;
    @FXML private Button butt_stepBack;
    @FXML private Button butt_refresh;
    @FXML private TableView<?> logTable;
    @FXML private TableColumn<?, ?> col_first;
    @FXML private TableColumn<?, ?> col_second;
    @FXML private TableColumn<?, ?> col_price;
    @FXML private Button butt_newData;

    @FXML
    void backScene(ActionEvent event) {
        this.setScene(event,"/sample/FXML/page2.fxml");
    }

}
