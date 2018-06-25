package br.ufrpe.graph;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;
import br.ufrpe.utils.Utils;

public class BipartiteGraph {

	private Document document;
	private List<Token> tokens;

	public BipartiteGraph(Document document, List<Token> tokens) {
		this.document = document;
		this.tokens = tokens;
	}

	public DirectedGraph<Object, DefaultEdge> buildDocumentRepresentation() {

		DirectedGraph<Object, DefaultEdge> graph = new DefaultDirectedGraph<Object, DefaultEdge>(
				DefaultEdge.class);

		for (Sentence sentence : document.getSentences()) {
			graph.addVertex(sentence);

			for (Token token : tokens) {
				if (sentence.getContent().contains(token.getString())) {
					token = addTokenVertex(graph, token);

					graph.addVertex(token);
					graph.addEdge(sentence, token);
				}
			}
		}

		for (Sentence sentence : document.getSentences()) {
			sentence.setCohesion(graph.outDegreeOf(sentence));
		}

		document.setSentences(Utils.normalizeSentences(document.getSentences()));

		return graph;
	}

	private Token addTokenVertex(Graph<Object, DefaultEdge> graph, Token token) {
		for (Object existingVertex : graph.vertexSet()) {
			if (existingVertex instanceof Token) {
				if (token.equals(existingVertex)) {
					return (Token) existingVertex;
				}
			}
		}

		return token;
	}

}
