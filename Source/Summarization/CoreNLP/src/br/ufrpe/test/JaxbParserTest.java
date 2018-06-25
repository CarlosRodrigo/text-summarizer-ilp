package br.ufrpe.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.ufrpe.jaxbparser.JaxbParser;
import br.ufrpe.nlptoolkit.models.Chunking;
import br.ufrpe.nlptoolkit.models.Coreference;
import br.ufrpe.nlptoolkit.models.Dependency;
import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Entity;
import br.ufrpe.nlptoolkit.models.Mention;
import br.ufrpe.nlptoolkit.models.Referenced;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class JaxbParserTest {

	Document document = JaxbParser.parseXML(new File(
			"src/br/ufrpe/test/DocumentTest.xml"));

	@Test
	public void testDocument() {
		assertEquals(0, document.getId());
		assertEquals("AP880217-0175", document.getName());
	}

	@Test
	public void testSentences() {
		assertEquals(1, document.getSentences().size());

		Sentence sentece = document.getSentences().get(0);

		assertEquals(1, sentece.getId());
		assertTrue(sentece.hasCoreference());
		assertEquals(
				"A coalition of members of Congress announced Wednesday that they plan to sue the Census Bureau in an effort to force the agency to delete illegal aliens from its count in 1990.",
				sentece.getContent());

		assertEquals(
				"(ROOT (S (NP (NP (DT A) (NN coalition)) (PP (IN of) (NP (NP (NNS members)) (PP (IN of) (NP (NNP Congress)))))) (VP (VBD announced) (NP-TMP (NNP Wednesday)) (SBAR (IN that) (S (NP (PRP they)) (VP (VBP plan) (S (VP (TO to) (VP (VB sue) (NP (DT the) (NNP Census) (NNP Bureau)) (PP (IN in) (NP (DT an) (NN effort) (S (VP (TO to) (VP (VB force) (NP (DT the) (NN agency) (S (VP (TO to) (VP (VB delete) (NP (JJ illegal) (NNS aliens)) (PP (IN from) (NP (NP (PRP$ its) (NN count)) (PP (IN in) (NP (CD 1990))))))))))))))))))))) (. .)))",
				sentece.getSyntacticTree());
	}

	@Test
	public void testTokens() {
		assertEquals(33, document.getSentences().get(0).getTokens().size());

		Token token = document.getSentences().get(0).getTokens().get(1);

		assertEquals(2, token.getId());
		assertEquals("coalition", token.getString());
		assertEquals("coalition", token.getLemma());
		assertEquals("coalit", token.getStem());
		assertEquals("NN", token.getPos());
		assertEquals("Word", token.getType());
		assertFalse(token.isStopWord());
		assertFalse(token.isReferenced());
		assertFalse(token.isRefers());
	}

	@Test
	public void testChunkings() {
		assertEquals(22, document.getSentences().get(0).getChunkings().size());

		Chunking chunking = document.getSentences().get(0).getChunkings()
				.get(0);

		assertEquals(1, chunking.getId());
		assertEquals("illegal aliens", chunking.getString());
		assertEquals("NP", chunking.getType());

		assertEquals(27, chunking.getTokens().get(1).getId());
		assertEquals("aliens", chunking.getTokens().get(1).getString());
	}

	@Test
	public void testDependencies() {
		assertEquals(32, document.getSentences().get(0).getDependencies()
				.size());

		Dependency dependency = document.getSentences().get(0)
				.getDependencies().get(0);

		assertEquals("det", dependency.getType());

		assertEquals(2, dependency.getGovernor().getId());
		assertEquals("coalition", dependency.getGovernor().getContent());

		assertEquals(1, dependency.getDependent().getId());
		assertEquals("A", dependency.getDependent().getContent());
	}

	@Test
	public void testEntities() {
		assertEquals(4, document.getSentences().get(0).getEntities().size());

		Entity entity = document.getSentences().get(0).getEntities().get(2);

		assertEquals(3, entity.getId());
		assertEquals("Census Bureau", entity.getString());
		assertEquals("ORGANIZATION", entity.getType());
		assertEquals(0, entity.getScore(), 0);

		assertEquals(16, entity.getTokens().get(1).getId());
		assertEquals("Bureau", entity.getTokens().get(1).getString());
	}

	@Test
	public void testCoreferences() {
		assertEquals(1, document.getCoreferences().size());

		Coreference coreference = document.getCoreferences().get(0);

		assertEquals(3, coreference.getId());
		assertEquals("PROPER", coreference.getType());

		Referenced referenced = coreference.getReferenced();
		assertEquals("14-15-16", referenced.getTokenIds());
		assertEquals(1, referenced.getIdSentence());
		assertEquals("the Census Bureau", referenced.getString());

		Mention mention = coreference.getMentions().get(0);

		assertEquals("17", mention.getTokenIds());
		assertEquals(1, referenced.getIdSentence());
		assertEquals("the Census Bureau", referenced.getString());
	}
}
