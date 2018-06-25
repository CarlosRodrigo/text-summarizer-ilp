package br.ufrpe.nlptoolkit.models;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sentence {

	private Number cohesion;

	@XmlAttribute
	private int id;

	@XmlAttribute(name = "has_coreference")
	private boolean hasCoreference;

	@XmlElement
	private String content;

	@XmlElement(name = "syntactictree")
	private String syntacticTree;

	@XmlElementWrapper
	@XmlElement(name = "token", type = Token.class)
	private List<Token> tokens;

	@XmlElementWrapper
	@XmlElement(name = "dependency", type = Dependency.class)
	private List<Dependency> dependencies;

	@XmlElementWrapper
	@XmlElement(name = "chunking", type = Chunking.class)
	private List<Chunking> chunkings;

	@XmlElementWrapper
	@XmlElement(name = "entity", type = Entity.class)
	private List<Entity> entities;

	public String getIdString() {
		return String.valueOf(id);
	}

	public void setIdString(String idString) {
	}

	public Number getCohesion() {
		return cohesion;
	}

	public void setCohesion(Number cohesion) {
		this.cohesion = cohesion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		content = content.replace("``", "\"");
		content = content.replace("&apost;&apost;", "\"");
		content = content.replace("&apost;", "'");

		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSyntacticTree() {
		return syntacticTree;
	}

	public void setSyntacticTree(String syntacticTree) {
		this.syntacticTree = syntacticTree;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public boolean hasCoreference() {
		return hasCoreference;
	}

	public void setHasCoreference(boolean hasCoreference) {
		this.hasCoreference = hasCoreference;
	}

	public List<Chunking> getChunkings() {
		return chunkings;
	}

	public void setChunkings(List<Chunking> chunkings) {
		this.chunkings = chunkings;
	}

	public HashMap<String, List<Sentence>> getMapSentencesDependency() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRemoved() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setSalienceScore(double doubleValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Sentence_" + getId();
	}

	public void RemoveToken(int index) {
		tokens.get(index).valid = false;
	}

}
