package sample.Prim;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;



public class Vertex {
    private final StringProperty name;
    private ArrayList<Pair> friends;

    public Vertex(String name){
        this.name = new SimpleStringProperty(name);
        friends = new ArrayList<Pair>();
    }

    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public ArrayList<Pair> getFriends(){ return friends; }

    public void addFriend(String price, Vertex newFriend){

        Pair n = new Pair(price, newFriend);
        friends.add(n);
    }

    public boolean isDuplicate(Vertex imposter){
        for(Pair p : friends){
            if(p.second == imposter){
                return true;
            }
        }
        return false;
    }
}
