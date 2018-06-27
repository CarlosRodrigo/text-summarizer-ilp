# Towards Coherent Single-Document Automatic Text Summarization: An Integer Linear Programming-based Approach

Automatic text summarization tool developed as Bachelor Thesis in Computer Science at the Federal Rural University of Pernambuco (UFRPE).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - The software development environment used for building the application
* [Gurobi](http://www.gurobi.com/downloads/download-center) - The Linear Programming (LP) optimization solver used in the project
* [ROUGE](https://github.com/RxNLP/ROUGE-2.0) - The software package used for evaluating the generated automatic text summarization

### Installing

First you need to install and have a license of the Gurobi software on your machine.
You can refer to the documentation at:

https://www.gurobi.com/documentation/8.0/quickstart_mac/obtaining_a_gurobi_license.html

Clone this repository on your local machine.

```
$ git clone https://github.com/CarlosRodrigo/text-summarizer-ilp.git
```

Once you have the repository on your local machine you can open the project with your favorite IDE.

All the project dependencies are inside the project folder located in:

```
libs
```

The main project file is located in:

```
src.br.ufrpe.summarization.Summarizer
```

From this class you can run a typical Java application and you should be presented with a series of inputs on the console where you can pass different parameters to the summarizer.

You should provide the location of the dataset and the location where you want to save the summaries. The project alreagy bundles the dataset in a folder used by default. The dataset can be found in:

```
datasets.duc.data
```

There you will find two folders: *duc-2001-stanford* and *duc-2002-stanford*. Both folders have the complete respective duc dataset competition annotated with the [CoreNLP](https://stanfordnlp.github.io/CoreNLP/) framework, which is used by the summarization algorithm.

Once you run the application, the default folder location where the summaries are generated is located in

```
datasets.duc.system-summaries
```

Here, you have the same structue as before with two folders: *duc-2001* and *duc-2001*. Containing the generated summaries for their respective datasets: *duc-2001-stanford* and *duc-2002-stanford*.

## Evaluating

After generating the summaries you will use the [ROUGE](https://github.com/RxNLP/ROUGE-2.0) package to evaluate the summaries. You may want to follow the steps provided in the ROUGE github repository:

https://github.com/RxNLP/ROUGE-2.0#quick-start

## Authors

* **Carlos Rodrigo Garcia** - [CarlosRodrigo](https://github.com/CarlosRodrigo)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.
