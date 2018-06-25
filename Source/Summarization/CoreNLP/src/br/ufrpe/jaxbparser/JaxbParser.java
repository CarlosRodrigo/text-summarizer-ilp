package br.ufrpe.jaxbparser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.ufrpe.nlptoolkit.models.Document;

public class JaxbParser {

	public static Document parseXML(File file) {
		JAXBContext context;
		JAXBElement<Document> element;
		try {
			context = JAXBContext.newInstance(Document.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			element = unmarshaller.unmarshal(new StreamSource(file),
					Document.class);

			return element.getValue();
		} catch (JAXBException e) {
			System.err.println("Error trying to parse the file");
			e.printStackTrace();
		}

		return new Document();
	}

}
