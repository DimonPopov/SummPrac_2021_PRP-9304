package sample.Prim;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
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
    public static ArrayList<Integer> edges = new ArrayList<>();
    public static ArrayList<Pair<Integer,Integer>> vertices = new ArrayList<>();
    public static ArrayList<Integer> ostovVertices = new ArrayList<>();
    public static ArrayList<Integer> ostovEdges = new ArrayList<>();
    private final Graph<Integer, Integer> graph;
    private ISOMLayout<Integer, Integer> layout;
    private VisualizationViewer<Integer,Integer> vv;
    private VisualizationImageServer<Integer, Integer> vis;
    private BufferedImage image;
    private int iter = 0;

    public VisualGraph(ArrayList<Integer> input) {
        this.graph = new SparseMultigraph<Integer, Integer>();
        int SIZE = input.size();
        int edgeId = 0;
        Integer i = 0;
        while (i<SIZE) {
            graph.addEdge((Integer) edgeId, input.get(i+1),input.get(i+2));
            edges.add(input.get(i));
            vertices.add(new Pair<Integer,Integer>(input.get(i+1),input.get(i+2)));
            i += 3;
            edgeId += 1;
        }

        this.layout = new ISOMLayout<Integer,Integer>(this.graph);
        layout.setSize(new Dimension(500,500));
        this.vv = new VisualizationViewer<>(layout);                    // !!!
        vv.setPreferredSize(new Dimension(500,500));
        this.vis = new VisualizationImageServer<Integer, Integer>(vv.getGraphLayout(),vv.getGraphLayout().getSize());
        vis.setBackground(Color.WHITE); //цвет фона
        vis.getRenderContext().setEdgeLabelTransformer(new Transformer<Integer, String>() { //Преобразование id ребра на его вес при рисовании
            public String transform(Integer input) {
                return Integer.toString(VisualGraph.edges.get(input));
            }
        });
        vis.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer, Integer>());
        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
        vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

    }

    public File visualizationGraph(){
        vis.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<Integer, Paint>() { //Окрашивание ребер в нужный цвет
            @Override
            public Paint transform(Integer integer) {
                int SIZE = VisualGraph.ostovEdges.size();
                for(int i=0; i<SIZE;i++){
                    if(integer == VisualGraph.ostovEdges.get(i)){
                        return Color.RED;
                    }
                }
                return Color.BLACK;
            }
        });

        vis.getRenderContext().setVertexFillPaintTransformer(this.vertexPaint());
        this.image = (BufferedImage) vis.getImage(
                new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2,
                        vv.getGraphLayout().getSize().getHeight() / 2),
                new Dimension(vv.getGraphLayout().getSize()));

        File outputFile = new File("./src/sample/Result/" + Integer.toString(iter)+".png");
        iter += 1;

        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return outputFile;
    }


    public int getImageCounter(){
        return this.iter;
    }

    public void setOstov(int weight, int v1, int v2){
        ostovVertices.add(v1);
        ostovVertices.add(v2);
        int curV1, curV2;
        for(int i = 0; i<edges.size();i++){
            curV1 = vertices.get(i).getKey();
            curV2 = vertices.get(i).getValue();
            if(edges.get(i) == weight && (curV1 == v1 | curV1 == v2) && (curV2 == v2 | curV2 == v1)){
                ostovEdges.add(i);
            }
        }
    }

    private Transformer<Integer,Paint> vertexPaint() {
        return new Transformer<Integer, Paint>() { //окрашивание вершин в разные цвета в соотв. с их именами
            public Paint transform(Integer i) {
                int SIZE = VisualGraph.ostovVertices.size();
                for (int j = 0; j < SIZE; j++) {
                    if (i.equals(VisualGraph.ostovVertices.get(j))) {
                        return Color.YELLOW;
                    }
                }
                return Color.WHITE;
            }
        };
    }
}