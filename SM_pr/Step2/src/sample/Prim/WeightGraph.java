package sample.Prim;

import java.util.*;



public class WeightGraph {

    public static class Edge {
        private int a;      // Номер первой вершины
        private int b;      // Номер второй вершины
        private int weight; // Вес

        private String a1;
        private String b1;
        private String price;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

/*
        public Edge(Edge edge) {
            this.a = edge.a;
            this.b = edge.b;
            this.weight = edge.weight;
        }
*/
        public int getV() { return a; }

        public int getW() { return b; }

        public int getWeight() { return weight; }

        public int getOther(int x) {
            assert x == a || x == b;
            return x == a ? b : a;
        }

        @Override
        public String toString() {
            return String.format("%d-%d: %d", a, b, weight);
        }

    }


    private int vertexNum;          // количество вершин
    private int edgeNum;            // количество ребер
    private List<Edge>[] graph;     // Взвешенный граф, каждая вершина содержит связанный список с ребрами
    private boolean directed;       // Это ориентированный граф

    public WeightGraph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;
        // Инициализируем список смежности
        graph = (LinkedList<Edge>[]) new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            graph[i] = new LinkedList<Edge>();
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public List<Edge>[] getGraph() {
        return graph;
    }

    public void addEdge(int w, int v, int weight) {
        // Получаем список краев w и добавляем отношение w-> v
        List<Edge> edges1 = graph[w];
        Edge newEdge1 = new Edge(w, v, weight);
        edges1.add(newEdge1);
        if (!directed) {
            // Ненаправленный граф, увеличивающий отношение с другой стороны
            // Получаем список краев v и добавляем отношение v-> w
            List<Edge> edges2 = graph[v];
            Edge newEdge2 = new Edge(v, w, weight);
            edges2.add(newEdge2);
        }

        // Количество ребер увеличивается
        edgeNum++;
    }
    public void print(){
        System.out.println(String.format("Количество вершин:% d, Количество ребер:% d", vertexNum, edgeNum));
        for (int i = 0; i < graph.length; i++) {
            // Вынимаем список, связанный с каждой стороны, чтобы пройти
            List<Edge> edges = graph[i];
            Iterator<Edge> iterator = edges.iterator();
            //StringBuilder str = new StringBuilder(i + "-\t");
            while (iterator.hasNext()) {
                Edge edge = iterator.next();
                System.out.println(String.format("%d - %d : %d", edge.a, edge.b, edge.weight));
            }
        }
    }

    public List<Integer> getResult() {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < graph.length; i++) {
            // Вынимаем список, связанный с каждой стороны, чтобы пройти
            List<Edge> edges = graph[i];
            Iterator<Edge> iterator = edges.iterator();
            //StringBuilder str = new StringBuilder(i + "-\t");
            while (iterator.hasNext()) {
                Edge edge = iterator.next();
                int weightr = (Integer) edge.weight;
                int a = (Integer) edge.getV();
                int b = (Integer) edge.getW();
                //System.out.println(String.format("%d - %d : %d", a, b, weightr));
                result.add(weightr);
                result.add(a);
                result.add(b);
            }
        }
        return result;
    }

    public void addEdgeImage(int w, int v, int weight) {
        // Получаем список краев w и добавляем отношение w-> v
        List<Edge> edges1 = graph[w];
        Edge newEdge1 = new Edge(w, v, weight);
        edges1.add(newEdge1);
        /*if (!directed) {
            // Ненаправленный граф, увеличивающий отношение с другой стороны
            // Получаем список краев v и добавляем отношение v-> w
            List<Edge> edges2 = graph[v];
            Edge newEdge2 = new Edge(v, w, weight);
            edges2.add(newEdge2);
        }

        // Количество ребер увеличивается
        edgeNum++;*/
    }


}
