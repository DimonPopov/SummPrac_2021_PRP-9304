package sample.Prim;

import org.apache.commons.collections15.map.HashedMap;
import java.util.ArrayList;
import java.util.Map;


// Класс графа.
public class CustomGraph {

    // Класс ребра.
    public static class Edge<T,B>{

        private T a;        // Имя первой вершины.
        private T b;        // Имя второй вершины.
        private B weight;   // Стоймость перехода.

        public Edge(T a, T b, B weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public T getA(){ return a; }

        public T getB(){ return b; }

        public T getOther(T other) { return other.equals(a) ? b : a; }

        public B getWeight(){ return weight; }

        @Override
        public String toString() {
            String outString = new String();
            outString = outString.concat(a + " - " + b + " : " + weight);
            return outString;
        }
    }

    private ArrayList<ArrayList<Edge>> graph;   // Представление нашего графа.
    private Map<String, Integer> position;      // Позиция в листе.
    private final boolean directed;             // Ориентированный ли граф.
    private ArrayList<String> forVisu;          // Представление графа как ориентированного.

    public CustomGraph(boolean directed){
        this.directed = directed;
        this.position = new HashedMap<String, Integer>();
        this.graph = new ArrayList<ArrayList<Edge>>();
        this.forVisu = new ArrayList<>();
    }

    public ArrayList<ArrayList<Edge>> getGraph(){
        return this.graph;
    }

    public ArrayList<String> getVis(){ return forVisu; }

    public ArrayList<String> getStringGraph(){
        ArrayList<String> out = new ArrayList<>();
        for(ArrayList<CustomGraph.Edge> a : graph){
            for(Edge b : a){
                out.add(new String(b.getWeight().toString() + " " + b.getA().toString() + " " + b.getB().toString()));
            }
        }
        return out;
    }

    public Map<String, Integer> getPosition(){
        return this.position;
    }

    public <W, V> void addEdge(W w, W v, V weight){
        if(position.containsKey(w)){
            Edge newEdge = new Edge(w, v, weight);
            graph.get(position.get(w)).add(newEdge);
        }else{
            graph.add(new ArrayList<Edge>());
            int index = graph.size() - 1;
            Edge newEdge = new Edge(w, v, weight);
            graph.get(index).add(newEdge);

            position.put(w.toString(), index);
        }
        forVisu.add(new String(weight.toString() + " " + w.toString() + " " + v.toString()));
        if(!directed){
            //addEdge(v, w, weight);        // Хз как этот момент поправить.
            if(position.containsKey(v)){
                Edge newEdge = new Edge(v, w, weight);
                graph.get(position.get(v)).add(newEdge);
            }else{
                graph.add(new ArrayList<Edge>());
                int index = graph.size() - 1;
                Edge newEdge = new Edge(v, w, weight);
                graph.get(index).add(newEdge);
                position.put(v.toString(), index);
            }
        }
    }

    public void addEdge(Edge other) throws NullPointerException{
        if(position.containsKey(other.getA().toString())){
            graph.get(position.get(other.getA().toString())).add(other);
        }else{
            graph.add(new ArrayList<Edge>());
            int index = graph.size() - 1;
            graph.get(index).add(other);
            position.put(other.getA().toString(), index);
        }
        forVisu.add(new String(other.weight.toString() + " " + other.a.toString() + " " + other.b.toString()));
        if(!directed){
            if(position.containsKey(other.getB().toString())){
                graph.get(position.get(other.getB().toString())).add(other);
            }else{
                graph.add(new ArrayList<Edge>());
                int index = graph.size() - 1;
                graph.get(index).add(other);
                position.put(other.getB().toString(), index);
            }
        }
    }

    public void clear(){
        position.clear();
        forVisu.clear();
        graph.clear();
    }

    @Override
    public String toString() {
        String outString = new String("");
        String preString = new String("");
        for(ArrayList<Edge> t : graph){
            for(Edge v : t){
                outString = outString.concat(v.toString() + "\n");
            }
            outString.concat(preString);
        }
        return outString;
    }
}