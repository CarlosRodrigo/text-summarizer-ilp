package br.ufrpe.summarization;

import java.util.Scanner;

import br.ufrpe.algorithms.scoring.IScoreAlgorithm;
import br.ufrpe.algorithms.scoring.TF_ISF;
import br.ufrpe.algorithms.scoring.WordCount;
import br.ufrpe.algorithms.scoring.WordFrequency;

public class UserInteration {

	public void user() {

		Scanner scanner = new Scanner(System.in);

		Summarizer summarizer = new Summarizer();

		System.out
				.print("Which DUC dataset would you like to summarize? (duc2001 or duc2002):");
		String duc = scanner.nextLine();

		String source = "datasets/duc/data/duc-2001-stanford/";
		String destination = "datasets/duc/system-summaries/duc-2001/";

		String defaultFilePath = "datasets/duc/data/duc-2001-stanford/AP880217-0175.xml";

		if (duc.equals("duc2002")) {
			source = "datasets/duc/data/duc-2002-stanford/";
			destination = "datasets/duc/system-summaries/duc-2002/";

			defaultFilePath = "datasets/duc/data/duc-2002-stanford/AP880217-0100.xml";
		}

		System.out
				.print("\nChoose the concept scoring algorithm (tfisf or wordcount o wordfrequency):");
		String userAlgorithm = scanner.nextLine();
		IScoreAlgorithm algorithm = new TF_ISF();
		if (userAlgorithm.equals("wordcount")) {
			algorithm = new WordCount();
		} else if (userAlgorithm.equals("wordfrequency")) {
			algorithm = new WordFrequency();
		}

		System.out
				.println("\nChoose the theta to be applied on the concept scoring");
		System.out
				.println("(ex: 1 will no apply theta, 2 will be concepts/2, 3 will be concepts/3 and so on)");
		int theta = Integer.parseInt(scanner.nextLine());

		System.out
				.print("\nWould you like to summarize one or all files? (one or all):");
		String numberOfFiles = scanner.nextLine();
		if (numberOfFiles.equals("one")) {

			System.out.println("\nPlease, provide the full file path:");
			System.out
					.println("leave blank if you want to use the default file");
			System.out.println("Default: " + defaultFilePath);
			String filePath = scanner.nextLine();
			if (!filePath.equals("")) {
				defaultFilePath = filePath;
			}

			boolean showGraph = false;
			System.out
					.print("Would you like to see the bipartite graph representation? (yes or no):");
			String graphRepresentation = scanner.nextLine();
			if (graphRepresentation.equals("yes")) {
				showGraph = true;
			}

			summarizer.summarizeFile(defaultFilePath, algorithm, theta,
					showGraph);
		}

		else {
			System.out
					.println("\nPlease, provide the full file path to the documents:");
			System.out
					.println("leave blank if you want to use the default source)");
			System.out.println("Default: " + source);
			String sourcePath = scanner.nextLine();
			if (!sourcePath.equals("")) {
				source = sourcePath;
			}

			System.out
					.println("\nPlease, provide the full file path where you want to save the summaries:");
			System.out
					.println("leave blank if you want to use the default destination)");
			System.out.println("Default: " + destination);
			String destinationPath = scanner.nextLine();
			if (!destinationPath.equals("")) {
				destination = destinationPath;
			}

			summarizer.summarizeAllFiles(source, destination, algorithm, theta);
		}

		scanner.close();

	}

}
