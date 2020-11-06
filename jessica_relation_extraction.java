/*
for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done

javac jessica_relation_extraction.java
java jessica_relation_extraction

https://stackoverflow.com/questions/20754129/how-to-call-java-from-python-using-py4j

https://stanfordnlp.github.io/CoreNLP/kbp.html#list-of-relations
*/

/*
https://nlp.stanford.edu/nlp/javadoc/javanlp/edu/stanford/nlp/pipeline/CoreDocument.html
*/

import java.util.*;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;

public class jessica_relation_extraction {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,depparse,kbp");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		CoreDocument document = pipeline.processToCoreDocument("Smith's wife is Jessica. He study in the New York University.");
		List<CoreSentence> s = document.sentences();

		CoreSentence s1;
		List<RelationTriple> tripltes;
		List<CoreEntityMention> entities;

		int sentence_len = s.size();
		for(int i =0; i < sentence_len; i++){
			s1 = s.get(i);
			tripltes = s1.relations();
			int triplet_num = tripltes.size();
			for(int j=0;j<triplet_num;j++){
				RelationTriple t = tripltes.get(j);
				String output = String.format("%s:%s -[%s]-> %s:%s (%f)", 
					t.subjectGloss(), 
					t.subjectHead().ner(),
					t.relationGloss(), 
					t.objectGloss(), 
					t.objectHead().ner(),
					t.confidence);  
				System.out.println(output);				
			}
		}

	}

}
