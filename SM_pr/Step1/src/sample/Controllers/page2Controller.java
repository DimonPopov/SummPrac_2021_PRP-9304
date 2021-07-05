package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Prim.*;



public class page2Controller extends mainController{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button butt_nextScene;
    @FXML private Button butt_backScene;
    @FXML private Button butt_addNode;
    @FXML private Button butt_loadFromFile;
    @FXML private CheckBox che_randomNode;
    @FXML private CheckBox che_startNode;
    @FXML private TextField fie_forNode;
    @FXML private TextField fie_fNode;
    @FXML private TextField fie_sNode;
    @FXML private TextField fie_price;
    @FXML private TableView<layotVertex> table;
    @FXML private TableColumn<layotVertex, String> col_fNode;
    @FXML private TableColumn<layotVertex, String> col_sNode;
    @FXML private TableColumn<layotVertex, String> col_price;
    Graph graph;


    @FXML /* Смена сцены на page1 по нажатию кнопки "Назад". */
    void backScene(ActionEvent event) {
        this.setScene(event,"../FXML/page1.fxml");
    }

    @FXML /* Смена сцены на page3 по нажатию кнопки "Далее". */
    void nextScene(ActionEvent event) {
        if(graph.getCount() >= 2){
            if(che_startNode.isSelected()){
                if(!fie_forNode.getText().isEmpty()){
                    this.setScene(event,"/sample/FXML/page3.fxml");
                }
            }else{
                this.setScene(event,"/sample/FXML/page3.fxml");
            }
        }
    }

    @FXML /* Загрузка из файла по нажатию кнопки "Загрузить из файла". */
    void loadFromFile(ActionEvent event) {    }

    @FXML /* Добавить данные в граф из полей (fie_fNode, fir_sNode, fie_price) по нажатию кнопки "Добавить". */
    void addNode(ActionEvent event) {
        if(!fie_fNode.getText().isEmpty() || !fie_sNode.getText().isEmpty() || !fie_price.getText().isEmpty()){
            try{
                Integer.parseInt(fie_price.getText());
                graph.addVertex(fie_fNode.getText(), fie_sNode.getText(), fie_price.getText());
            }catch(Exception e){
                e.getMessage();
            }
        }
        fie_fNode.clear();
        fie_sNode.clear();
        fie_price.clear();
    }

    @FXML /* Выбран чек-бокс с рандомной вершиной. */
    void selectRandomNode(ActionEvent event) {
        if(che_randomNode.isSelected()){
            che_startNode.setSelected(false);
            fie_forNode.setDisable(true);
        }else{
            che_randomNode.setSelected(true);
        }
    }

    @FXML /* выбран чек-бокс со стартовой вершиной. */
    void selectStartNode(ActionEvent event) {
        if(che_startNode.isSelected()){
            che_randomNode.setSelected(false);
            fie_forNode.setDisable(false);
        }else{
            che_startNode.setSelected(true);
        }
    }

    @FXML
    void initialize() {
        graph = Main.getGraph();
        col_fNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("name"));
        col_sNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("friend"));
        col_price.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("price"));
        table.setItems(graph.layot);
    }
}
