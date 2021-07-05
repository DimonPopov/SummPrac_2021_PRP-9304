package sample.Prim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;



public class Graph {

    private ArrayList<Vertex> my_sons = new ArrayList();
    public ObservableList<layotVertex> layot = FXCollections.observableArrayList();
    public String startVertex = "";

    public void addVertex(String a, String b, String c){

        Vertex v1 = new Vertex(a);
        Vertex v2 = new Vertex(b);
        boolean f1 = false;
        boolean f2 = false;

        for(Vertex son1 : my_sons){
            if(son1.getName().equals(a)){
                System.out.println("1");
                v1 = son1;
                f1 = true;
                break;
            }
        }
        if(!f1){
            my_sons.add(v1);
        }
        for(Vertex son2 : my_sons){
            if(son2.getName().equals(b)){
                System.out.println("2");
                v2 = son2;
                f2 = true;
                break;
            }
        }
        if(!f2){
            my_sons.add(v2);
        }

        v1.addFriend(c, v2);

        layotVertex newLayot = new layotVertex(a,b,c);
        layot.add(newLayot);
    }

    public int getCount(){
        return my_sons.size();
    }

    public void print(){
        for(Vertex v : my_sons){
            System.out.print(v.getName());
            for(Pair p : v.getFriends()){
                System.out.print(" " + p.first + " " + p.second);
            }
            System.out.println("");
        }
    }
}
