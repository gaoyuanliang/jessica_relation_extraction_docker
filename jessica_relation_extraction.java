/*
javac jessica_relation_extraction.java
java jessica_relation_extraction
*/


/*
https://nlp.stanford.edu/nlp/javadoc/javanlp/edu/stanford/nlp/pipeline/CoreDocument.html
*/

import edu.stanford.nlp.io.*;
import edu.stanford.nlp.pipeline.*;

import java.util.*;

public class jessica_relation_extraction {

	public static String text = "Marie was born in Paris.";

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,depparse,kbp");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		CoreDocument document = pipeline.processToCoreDocument(text);

		System.out.println(document.docDate());
	}

}
