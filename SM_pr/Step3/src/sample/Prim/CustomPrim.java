package sample.Prim;

import java.util.ArrayList;
import java.util.PriorityQueue;



public class CustomPrim implements IAlg{

    private PriorityQueue<CustomGraph.Edge> minHeap;
    private ArrayList<CustomGraph.Edge> mst;
    private ArrayList<Boolean> marked;
    private ArrayList<String> result;
    private ArrayList<String> logs;
    private CustomGraph graph;
    private String start;

    public CustomPrim(CustomGraph graph){
        if(graph.getGraph().isEmpty()){
            throw new IllegalArgumentException("CustomPrim: graph is empty!");
        }
        this.graph = graph;
        logs = new ArrayList<>();
        this.start = new String(graph.getGraph().get(0).get(0).getA().toString());       // Получаем имя самой первой вершины.
        createMinHeap();
    }

    private void createMinHeap(){
        minHeap = new PriorityQueue<>((e1 ,e2)->{
            return (int)e1.getWeight() - (int)e2.getWeight();
        });
        marked = new ArrayList<>();
        for(int i = 0; i < graph.getGraph().size(); ++i){
            marked.add(false);
        }
        mst = new ArrayList<>();
    }

    private void visit(String str){
        int index = graph.getPosition().get(str);
        marked.set(index, true);
        for(CustomGraph.Edge edge : graph.getGraph().get(index)){
            if(!marked.get(graph.getPosition().get(edge.getOther(str).toString()))){
                minHeap.offer(edge);
            }
        }
    }

    public void setStartVertex(String newStart){
        if(graph.getPosition().containsKey(newStart)){
            this.start = newStart;
        }
    }

    public
    ArrayList<sample.Prim.CustomGraph.Edge> getMst(){
        return mst;
    }

    public void doIt(){
        this.result = new ArrayList<>();
        visit(start);
        logs.add("Start: " + start);
        while (!minHeap.isEmpty()) {
            CustomGraph.Edge minEdge = minHeap.poll();
            if (marked.get(graph.getPosition().get(minEdge.getA())) && marked.get(graph.getPosition().get(minEdge.getB()))){
                // Ели посещены обе вершины.
                continue;
            }
            logs.add("[" + minEdge.getA() + "] -> [" + minEdge.getB() + "] : " + minEdge.getWeight());
            mst.add(minEdge);
            result.add(minEdge.getWeight().toString() + " " + minEdge.getA().toString() + " " + minEdge.getB().toString());

            if (!marked.get(graph.getPosition().get(minEdge.getA()))) {
                visit(minEdge.getA().toString());
            } else {
                visit(minEdge.getB().toString());
            }
        }
    }

    @Override
    public ArrayList<String> getLog(){ return logs; }

    @Override
    public ArrayList<String> getResult(){
        return result;
    }
}