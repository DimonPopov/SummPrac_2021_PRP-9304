package sample.Prim;

import edu.uci.ics.jung.graph.Graph;

import java.io.File;
import java.util.*;

public class LazyPrimMST{
    private VisualGraph visGraph;
    private WeightGraph graph;                          // ссылка на рисунок
    private PriorityQueue<WeightGraph.Edge> minHeap;    // минимальная куча
    private boolean[] marked;                           // Отметьте массив, отметьте, посещается ли узел i во время работы алгоритма
    private List<WeightGraph.Edge> mst;                 // Все ребра включены в минимальное остовное дерево
    private Integer mstWeight;                          // Вес минимального остовного
    public ArrayList<File> source;


    public LazyPrimMST(WeightGraph graph, VisualGraph visGraph, int start) {
        this.graph = graph;
        this.visGraph = visGraph;
        this.source = new ArrayList<File>();
        // Строим минимальную кучу Edge
        minHeap = new PriorityQueue<>((e1 ,e2)->{
            return e1.getWeight() - e2.getWeight();
        });
        marked = new boolean[graph.getVertexNum()];
        mst = new ArrayList<>();

        // Lazy Prim
        // 1. Сначала заходим в 0-ю вершину
        visit(start);
        // 2. Сбор MST, когда minHeap не пуст
        while (!minHeap.isEmpty()) {
            WeightGraph.Edge minEdge = minHeap.poll();
            // Оба конца этой стороны были посещены, затем отброшены
            if (marked[minEdge.getV()] && marked[minEdge.getW()]) {
                continue;
            }
            mst.add(minEdge);
            visGraph.setOstov(minEdge.getWeight(),minEdge.getV(),minEdge.getW());
            source.add(visGraph.visualizationGraph());
            // Продолжаем посещать непосещенные вершины
            if (!marked[minEdge.getV()]) {
                visit(minEdge.getV());
            } else {
                visit(minEdge.getW());
            }
        }
        visGraph.clean();
    }

    public List<WeightGraph.Edge> getMst() {
        return mst;
    }

    public int getWeight() {
        // Суммируем веса в mst и возвращаем
        int total = 0;
        for (WeightGraph.Edge edge : mst) {
            total += edge.getWeight();
        }
        return total;
    }

    private void visit(int v) {
        if (marked[v] || v >= graph.getVertexNum()) {
            return;
        }
        // метка доступа
        marked[v] = true;
        // Добавляем смежные края v, которые не были посещены, в наименьшую кучу (ребра с поперечным разрезом)
        for (WeightGraph.Edge edge : graph.getGraph()[v]) {
            // Измененный узел нельзя посещать, иначе это не поперечная кромка
            if (!marked[edge.getOther(v)]) {
                minHeap.offer(edge);
            }
        }
    }
}