package sample.Controllers;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;
import sample.Prim.*;



public class page2Controller extends mainController{

    @FXML private CheckBox che_randomNode;
    @FXML private CheckBox che_startNode;
    @FXML private CheckBox che_debugInfo;
    @FXML private TextField fie_forNode;
    @FXML private TextField fie_fNode;
    @FXML private TextField fie_sNode;
    @FXML private TextField fie_price;
    @FXML private TableView<layotVertex> table;
    @FXML private TableColumn<layotVertex, String> col_fNode;
    @FXML private TableColumn<layotVertex, String> col_sNode;
    @FXML private TableColumn<layotVertex, String> col_price;
    private int count = 0;                          // Подсчет введенных пользователем вершин.
    private ObservableList<layotVertex> vertex;     // Наблюдаемый лист для TableView.


    @FXML /* Смена сцены на page1 по нажатию кнопки "Назад". */
    void backScene(ActionEvent event) {
        this.setScene(event,"/sample/FXML/page1.fxml");
    }


    @FXML /* Смена сцены на page3 по нажатию кнопки "Далее". */
    void nextScene(ActionEvent event) {
        CustomGraph graph = Main.getGraph();
        for(layotVertex layot : this.vertex){
            graph.addEdge(layot.getName(),
                           layot.getFriend(),
                           Integer.parseInt(layot.getPrice()));
        }
        CustomPrim prim = new CustomPrim(graph);
        VisualGraph vis = new VisualGraph(graph.getVis());

        if(this.che_startNode.isSelected()){
            prim.setStartVertex(this.fie_forNode.getText());
        }

        prim.doIt();
        Main.png.add(vis.visualizationGraph());
        //System.out.println(prim.getMst().size());
        for(int i = 0; i < prim.getMst().size(); ++i){
            vis.setOstov(prim.getMst().get(i).getWeight().toString(), prim.getMst().get(i).getA().toString(), prim.getMst().get(i).getB().toString());
            Main.png.add(vis.visualizationGraph());
        }
        vis.clean();
        Main.getLog().addAll(prim.getLog());
        this.setScene(event,"/sample/FXML/page3.fxml");
    }


    @FXML /* Загрузка из файла по нажатию кнопки "Загрузить из файла". */
    void loadFromFile(ActionEvent event) {
        this.vertex.clear();
        fileLoader file = new fileLoader(event);
        file.openDialog();
        ArrayList<String[]> vertexs = file.getData();
        for(String[] str : vertexs){
            this.vertex.add(new layotVertex(str[0], str[1], str[2]));
        }
    }


    @FXML /* Добавить данные в граф из полей (fie_fNode, fir_sNode, fie_price) по нажатию кнопки "Добавить". */
    void addNode(ActionEvent event) {
        if(!fie_fNode.getText().isEmpty() || !fie_sNode.getText().isEmpty() || !fie_price.getText().isEmpty()){
            try{
                Integer.parseInt(fie_price.getText());
                this.vertex.add(new layotVertex(fie_fNode.getText(), fie_sNode.getText(), fie_price.getText()));
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
    void debugInfo(ActionEvent event) {
        if(che_debugInfo.isSelected()){
            che_debugInfo.setSelected(false);
        }else{
            che_debugInfo.setSelected(true);
        }
    }


    @FXML
    void initialize() {
        Main.getGraph().clear();
        Main.getLog().clear();
        this.vertex = FXCollections.observableArrayList();
        col_fNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("name"));
        col_sNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("friend"));
        col_price.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("price"));
        table.setItems(this.vertex);
    }
}
