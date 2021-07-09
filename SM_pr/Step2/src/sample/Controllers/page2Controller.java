package sample.Controllers;

import java.util.ArrayList;
import java.util.List;
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
        this.count = this.getEdgeCount(this.vertex);
        WeightGraph graph1 = Main.getGraph1();
        WeightGraph graph2 = Main.getGraph2();
        graph1 = new WeightGraph(count, false);
        graph2 = new WeightGraph(count, false);

        for(layotVertex layot : this.vertex){
            graph1.addEdge(Integer.parseInt(layot.getName()),
                           Integer.parseInt(layot.getFriend()),
                           Integer.parseInt(layot.getPrice()));
            graph2.addEdgeImage(Integer.parseInt(layot.getName()),
                           Integer.parseInt(layot.getFriend()),
                           Integer.parseInt(layot.getPrice()));
        }
        List<Integer> valueof = graph2.getResult();
        VisualGraph graph = new VisualGraph((ArrayList<Integer>) valueof);
        Main.png = new ArrayList<>();
        Main.png.add(graph.visualizationGraph());
        LazyPrimMST lazyPrimMST;
        if(this.che_startNode.isSelected()){
            try{
                //System.out.println("+");
                int start = Integer.parseInt(this.che_startNode.getText());
                lazyPrimMST = new LazyPrimMST(graph1, graph, start);
            }catch (Exception e){
                lazyPrimMST = new LazyPrimMST(graph1, graph, 0);
            }
        }else{
            lazyPrimMST = new LazyPrimMST(graph1, graph, 0);
        }
        System.out.println(lazyPrimMST.getMst());
        Main.png.addAll(lazyPrimMST.source);
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
                Integer.parseInt(fie_fNode.getText());
                Integer.parseInt(fie_sNode.getText());
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

    public Integer getEdgeCount(ObservableList<layotVertex> layot){
        String trueEdge = "";
        if(layot.isEmpty()){
            return 0;
        }else{
            trueEdge += layot.get(0).getName();
        }
        for(layotVertex l : layot){
            if(trueEdge.indexOf(l.getName()) == -1){
                trueEdge += l.getName();
            }
            if(trueEdge.indexOf(l.getFriend()) == -1){
                trueEdge += l.getFriend();
            }
        }
        return trueEdge.length();
    }

    @FXML
    void initialize() {
        this.vertex = FXCollections.observableArrayList();
        //Main.png.clear();
        col_fNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("name"));
        col_sNode.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("friend"));
        col_price.setCellValueFactory(new PropertyValueFactory<layotVertex, String>("price"));
        table.setItems(this.vertex);
    }
}
