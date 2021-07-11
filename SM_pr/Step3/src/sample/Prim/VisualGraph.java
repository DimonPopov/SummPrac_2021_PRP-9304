package sample.Prim;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import javafx.util.Pair;
import org.apache.commons.collections15.Transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class VisualGraph {

    private final Graph<String, Integer> graph;
    private final ISOMLayout<String, Integer> layout;
    private final VisualizationImageServer<String, Integer> vis;
    private BufferedImage image;
    private int iter = 0;

    public VisualGraph(ArrayList<String> input) {               //вес,вершина1,вершина2,...

        this.graph = new SparseMultigraph<String, Integer>();
        int SIZE = input.size();
        int edgeId = 0;
        int i = 0;

        while (i<SIZE) {
            String[] res = input.get(i).split(" ");
            graph.addEdge((Integer) edgeId, res[1], res[2]);
            edges.add(res[0]);
            vertices.add(new Pair<String,String>(res[1], res[2]));
            i += 1;
            edgeId += 1;
        }
        
        this.layout = new ISOMLayout<String,Integer>(this.graph);
        layout.setSize(new Dimension(500,500));
        this.vis = new VisualizationImageServer<String, Integer>(layout,new Dimension(500,500));
        vis.setBackground(Color.WHITE); //цвет фона
        vis.getRenderContext().setEdgeLabelTransformer(new Transformer<Integer, String>() { //Преобразование id ребра на его вес при рисовании
            public String transform(Integer input) {
                return VisualGraph.edges.get(input);
            }
        });
        vis.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, Integer>());
        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

    }

    public File visualizationGraph(){
        vis.getRenderContext().setEdgeDrawPaintTransformer(this.edgePaint());
        vis.getRenderContext().setVertexFillPaintTransformer(vertexPaint());
        this.image = (BufferedImage) vis.getImage(new Point2D.Double(500 / 2,500 / 2), new Dimension(500,500));
        File outputFile;
        try {
            outputFile = File.createTempFile("./src/sample/Result/" + Integer.toString(iter) + ".png", null);
            outputFile.deleteOnExit();
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            outputFile = null;
        }
        iter += 1;
        return outputFile;
    }

    public void clean(){
        edges.clear();
        vertices.clear();
        ostovEdges.clear();
        ostovVertices.clear();
    }


    public void setOstov(String weight, String v1, String v2){
        String V1 = v1;
        String V2 = v2;
        ostovVertices.add(V1);
        ostovVertices.add(V2);
        String curV1, curV2;
        for(int i = 0; i<edges.size();i++){
            curV1 = vertices.get(i).getKey();
            curV2 = vertices.get(i).getValue();
            if(edges.get(i).equals(weight) && (curV1.equals(V1) | curV1.equals(V2)) && (curV2.equals(V2) | curV2.equals(V1))){
                ostovEdges.add(i);
            }
        }
    }

    private Transformer<String,Paint> vertexPaint() { //окрашивание вершин в разные цвета в соотв. с их именами
        return new Transformer<String, Paint>() {
            @Override
            public Paint transform(String str) {
                int SIZE = VisualGraph.ostovVertices.size();
                for (int j = 0; j < SIZE; j++) {
                    if (str.equals(VisualGraph.ostovVertices.get(j))) {
                        return Color.YELLOW;
                    }
                }
                return Color.WHITE;
            }
        };
    }

    private Transformer<Integer,Paint> edgePaint() { //Окрашивание ребер в нужный цвет
        return new Transformer<Integer, Paint>() {
            @Override
            public Paint transform(Integer integer) {
                int SIZE = VisualGraph.ostovEdges.size();
                for (int i = 0; i < SIZE; i++) {
                    if (integer == VisualGraph.ostovEdges.get(i)) {
                        return Color.RED;
                    }
                }
                return Color.BLACK;
            }
        };
    }

    private static ArrayList<String> edges = new ArrayList<>();
    private static ArrayList<Pair<String,String>> vertices = new ArrayList<>();
    private static ArrayList<String> ostovVertices = new ArrayList<>();
    private static ArrayList<Integer> ostovEdges = new ArrayList<>();
}