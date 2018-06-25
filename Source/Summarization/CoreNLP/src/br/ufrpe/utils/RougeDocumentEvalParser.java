package br.ufrpe.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RougeDocumentEvalParser {

	public static void main(String[] args) {
		// Usage
		RougeDocumentEvalParser dp = new RougeDocumentEvalParser();
		dp.parseSystemConfig("...");
		dp.parse("...");
	}

	public void parseSystemConfig(String folderPath) {
		try {
			PrintWriter writer = new PrintWriter(folderPath + "settings.xml",
					"UTF-8");

			writer.println("<ROUGE_EVAL version=\"1.5.5\">");

			File folder = new File(folderPath + "System");
			File[] listOfFiles = folder.listFiles();

			int p_index = 1;
			int m_index = 1;
			for (int i = 0; i < listOfFiles.length; i++) {

				int index = i + 1;

				writer.println("\t<EVAL ID=\"" + index + "\">");
				writer.println("\t\t<PEER-ROOT>systems</PEER-ROOT>");
				writer.println("\t\t<MODEL-ROOT>models</MODEL-ROOT>");
				writer.println("\t\t<INPUT-FORMAT TYPE=\"SEE\"></INPUT-FORMAT>");

				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());

					String filename = listOfFiles[i].getName().split("\\.")[0];

					writer.println("\t\t<PEERS>");
					writer.println("\t\t\t<P ID=\"P" + p_index + "\">"
							+ filename + ".html</P>");
					p_index++;
					writer.println("\t\t</PEERS>");

					writer.println("\t\t<MODELS>");
					writer.println("\t\t\t<M ID=\"M" + m_index + "\">"
							+ filename + ".gold.html</M>");
					m_index++;
					writer.println("\t\t\t<M ID=\"M" + m_index + "\">"
							+ filename.replace("_SumModel_1", "_SumModel_2")
							+ ".gold.html</M>");
					m_index++;
					writer.println("\t\t</MODELS>");
				}

				writer.println("\t</EVAL>");
			}
			writer.println("</ROUGE_EVAL>");

			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void parse(String folderPath) {
		File folder = new File(folderPath + "Summaries");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				File file = listOfFiles[i];

				List<String> selectedSentences = new ArrayList<String>();

				try (BufferedReader br = new BufferedReader(
						new FileReader(file))) {
					String line;
					while ((line = br.readLine()) != null) {
						selectedSentences.add(line);
					}
				} catch (FileNotFoundException e) {
					System.err.println("File not found.");
				} catch (IOException e) {
					System.err.println("Error trying to read the file.");
				}

				String filename = listOfFiles[i].getName().split("\\.")[0];

				writeToHTML(folderPath, filename, selectedSentences);
			}
		}
	}

	public void writeToHTML(String folderPath, String filename,
			List<String> selectedSentences) {
		try {
			PrintWriter writer = new PrintWriter(folderPath + filename
					+ ".gold.html", "UTF-8");

			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>");
			writer.println(filename + ".gold.html");
			writer.println("</title>");
			writer.println("</head>");
			writer.println("<body bgcolor=\"white\">");

			for (int i = 0; i < selectedSentences.size(); i++) {
				String sentence = selectedSentences.get(i);
				int index = i + 1;
				writer.println("<a name=\"" + index + "\">[" + index
						+ "]</a> <a href=\"#" + index + "\" id=" + index + ">"
						+ sentence + "</a>");
			}

			writer.println("</body>");
			writer.println("</html>");

			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
