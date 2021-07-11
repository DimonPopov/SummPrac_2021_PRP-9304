package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Prim.*;

import java.util.ArrayList;
import java.util.List;

public class CustomPrimTest {
    private CustomPrim prim1;
    private CustomPrim prim2;

    @Before
    public void initTest(){
        prim1 = new CustomPrim(getGraph1());
        prim2 = new CustomPrim((getGraph2()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGraphIsNull(){
        CustomGraph graph = new CustomGraph(false);
        CustomPrim prim = new CustomPrim(graph);
    }

    @Test
    public void setStartVertex() {
        prim1.setStartVertex("5");
        Assert.assertEquals(prim1.getStartVertex(),"5" );

        prim2.setStartVertex("T");
        Assert.assertEquals(prim2.getStartVertex(), "A");
    }

    @Test
    public void getMst() {
        prim1.doIt();
        List<CustomGraph.Edge> result1 = new ArrayList<>();
        result1.add( new CustomGraph.Edge("1","4",3));
        result1.add(new CustomGraph.Edge("4","3",9));
        result1.add(new CustomGraph.Edge("3","2",2));
        result1.add(new CustomGraph.Edge("3","5",6));
        Assert.assertEquals(prim1.getMst().toString(), result1.toString());

        prim2.doIt();
        List<CustomGraph.Edge> result2 = new ArrayList<>();
        result2.add( new CustomGraph.Edge("A","B",7));
        result2.add(new CustomGraph.Edge("B","E",1));
        result2.add(new CustomGraph.Edge("B","C",3));
        result2.add(new CustomGraph.Edge("B","D",3));
        result2.add(new CustomGraph.Edge("D","F",10));
        result2.add(new CustomGraph.Edge("F","G",6));
        Assert.assertEquals(prim2.getMst().toString(), result2.toString());
    }

    @Test
    public void getMstNotNull() {
        prim1.doIt();
        Assert.assertNotNull(prim1.getMst().toString());
    }

    @Test
    public void getLog() {
        prim1.doIt();
        ArrayList<String> result1 = new ArrayList<>();
        result1.add("Start: 1");
        result1.add("[1] -> [4] : 3");
        result1.add("[4] -> [3] : 9");
        result1.add("[3] -> [2] : 2");
        result1.add("[3] -> [5] : 6");
        Assert.assertEquals(prim1.getLog(),result1);

        prim2.doIt();
        ArrayList<String> result2 = new ArrayList<>();
        result2.add("Start: A");
        result2.add("[A] -> [B] : 7");
        result2.add("[B] -> [E] : 1");
        result2.add("[B] -> [C] : 3");
        result2.add("[B] -> [D] : 3");
        result2.add("[D] -> [F] : 10");
        result2.add("[F] -> [G] : 6");
        Assert.assertEquals(prim2.getLog(),result2);
    }

    @Test
    public void getLogNotNull() {
        prim1.doIt();
        Assert.assertNotNull(prim1.getLog());
    }


    private CustomGraph getGraph1(){
        CustomGraph graph = new CustomGraph(false);
        graph.addEdge("1","2",10);
        graph.addEdge("2","3",2);
        graph.addEdge("5","1",12);
        graph.addEdge("3","4",9);
        graph.addEdge("4","1",3);
        graph.addEdge("5","3",6);
        return graph;
    }

    private CustomGraph getGraph2(){
        CustomGraph graph = new CustomGraph(false);
        graph.addEdge("A","B",7);
        graph.addEdge("A","C",11);
        graph.addEdge("B","C",3);
        graph.addEdge("B","D",3);
        graph.addEdge("B","E",1);
        graph.addEdge("E","F",15);
        graph.addEdge("F","D",10);
        graph.addEdge("D","C",5);
        graph.addEdge("C","G",20);
        graph.addEdge("F","G",6);
        return graph;
    }

}