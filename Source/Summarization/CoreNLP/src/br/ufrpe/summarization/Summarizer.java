package br.ufrpe.summarization;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import br.ufrpe.ILP.ILPParser;
import br.ufrpe.algorithms.CoreferenceResolution;
import br.ufrpe.algorithms.DependencyAnalyzer;
import br.ufrpe.algorithms.scoring.IScoreAlgorithm;
import br.ufrpe.graph.BipartiteGraph;
import br.ufrpe.graph.GraphVisualizer;
import br.ufrpe.jaxbparser.JaxbParser;
import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class Summarizer {

	private List<Token> tokens = new ArrayList<>();
	private HashMap<Token, ArrayList<Sentence>> mapConceptsentences;

	public static void main(String[] args) {
		new UserInteration().user();
	}

	public void summarizeAllFiles(String source, String destination,
			IScoreAlgorithm algorithm, int theta) {

		File folder = new File(source);
		File[] listOfFiles = folder.listFiles();

		Writer writer = new Writer();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				List<Sentence> selectedSentences = summarizeFile(source
						+ listOfFiles[i].getName(), algorithm, theta, false);

				String filename = listOfFiles[i].getName().split("\\.")[0];

				writer.writeToHTML(destination, filename, selectedSentences);
			}
		}
	}

	public List<Sentence> summarizeFile(String file, IScoreAlgorithm algorithm,
			int theta, boolean showGraph) {

		System.out.println("\nStart summarizing file " + file);

		Document document = JaxbParser.parseXML(new File(file));

		CoreferenceResolution resolution = new CoreferenceResolution();
		resolution.resolve(document);

		ArrayList<Token> t = new ArrayList<Token>();
		for (Sentence sentence : document.getSentences()) {
			for (Token token : sentence.getTokens()) {
				t.add(token);
			}
		}

		tokens = algorithm.calculate(document, document.getAllTokens(), theta);

		MapConceptsSentenceBuilder builder = new MapConceptsSentenceBuilder();
		mapConceptsentences = builder.build(document, tokens);

		BipartiteGraph bg = new BipartiteGraph(document, tokens);
		DirectedGraph<Object, DefaultEdge> graph = bg
				.buildDocumentRepresentation();

		if (showGraph) {
			GraphVisualizer graphVisualizer = new GraphVisualizer(graph);
			graphVisualizer.visualize();
		}

		DependencyAnalyzer dep = new DependencyAnalyzer();
		dep.Analyze(document);

		List<Sentence> selectedSentences = ilp();

		System.out.println(selectedSentences);

		return selectedSentences;
	}

	public List<Sentence> ilp() {
		ILPParser ilp = new ILPParser();
		return ilp.executeILPOptimization(mapConceptsentences, true, 100, true,
				null);
	}

}
