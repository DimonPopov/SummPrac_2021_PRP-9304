package sample.Prim;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Pair {
    public StringProperty first;
    public Vertex second;

    Pair(String f, Vertex s){
        this.first = new SimpleStringProperty(f);
        this.second = s;
    }
}
