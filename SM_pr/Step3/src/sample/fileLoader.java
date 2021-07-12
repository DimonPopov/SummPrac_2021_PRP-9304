package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


/* Класс открывает файл и производит считывание данных для графа. */
public class fileLoader {

    private Node node;
    private Stage stage;
    final FileChooser fileChooser;
    ArrayList<String[]> data;


    public fileLoader(ActionEvent event){
        this.node = (Node)event.getSource();
        this.stage = (Stage)node.getScene().getWindow();
        this.fileChooser = new FileChooser();
        this.data = new ArrayList<>();
    }

    public ArrayList<String[]> getData(){
        return data;
    }

    public void openDialog(){
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("text", "*.txt");
        fileChooser.setTitle("Выберите файл.");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            //System.out.println("Файл открылся!");
            readFile(file);
        }
    }

    private void readFile(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                String[] words = line.split(" ");
                line = reader.readLine();
                if(words.length != 3){
                    continue;
                }
                Integer.parseInt(words[2]);
                this.data.add(words);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            data.clear();
        }
    }
}
