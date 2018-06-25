package br.ufrpe.summarization;

import java.io.PrintWriter;
import java.util.List;

import br.ufrpe.nlptoolkit.models.Sentence;

public class Writer {

	public void writeToTXT(String folderPath, String filename,
			List<Sentence> selectedSentences) {
		try {
			PrintWriter writer = new PrintWriter(folderPath + filename
					+ "_SumModel_1.txt", "UTF-8");

			for (Sentence sentence : selectedSentences) {
				writer.println(sentence.getContent());
			}

			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void writeToHTML(String folderPath, String filename,
			List<Sentence> selectedSentences) {
		try {
			PrintWriter writer = new PrintWriter(folderPath + filename
					+ "_SumModel_1.html", "UTF-8");

			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>");
			writer.println(filename + "_SumModel_1.html");
			writer.println("</title>");
			writer.println("</head>");
			writer.println("<body bgcolor=\"white\">");

			for (int i = 0; i < selectedSentences.size(); i++) {
				Sentence sentence = selectedSentences.get(i);
				int index = i + 1;
				writer.println("<a name=\"" + index + "\">[" + index
						+ "]</a> <a href=\"#" + index + "\" id=" + index + ">"
						+ sentence.getContent() + "</a>");
			}

			writer.println("</body>");
			writer.println("</html>");

			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
