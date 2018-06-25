package br.ufrpe.graph;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;

public class GraphVisualizer {

	private Graph<Object, DefaultEdge> graph;

	public GraphVisualizer(Graph<Object, DefaultEdge> graph) {
		this.graph = graph;
	}

	public void visualize() {
		JGraphModelAdapter<Object, DefaultEdge> jGraphModelAdapter = new JGraphModelAdapter<Object, DefaultEdge>(
				graph);

		JGraph jgraph = new JGraph(jGraphModelAdapter);

		JFrame frame = new JFrame();
		frame.getContentPane().add(jgraph);
		frame.setTitle("Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
