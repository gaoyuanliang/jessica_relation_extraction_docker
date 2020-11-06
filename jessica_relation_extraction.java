/////////////jessica_relation_extraction.java////////
/*
https://stackoverflow.com/questions/20754129/how-to-call-java-from-python-using-py4j

https://stanfordnlp.github.io/CoreNLP/kbp.html#list-of-relations

https://nlp.stanford.edu/nlp/javadoc/javanlp/edu/stanford/nlp/pipeline/CoreDocument.html

http://manmustbecool.github.io/MyWiki/Wiki/Python/python_java.html

for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done
javac jessica_relation_extraction.java
jar -cvf jessica_relation_extraction.jar jessica_relation_extraction.class

java jessica_relation_extraction &
*/

import java.util.*;
import py4j.GatewayServer;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;

public class jessica_relation_extraction {

	public static StanfordCoreNLP pipeline;

	public jessica_relation_extraction(){
	}

	public jessica_relation_extraction(String relation_model){
		long startTime;
		long elapsedTime;

		startTime = System.nanoTime();
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,depparse,"+relation_model);
		pipeline = new StanfordCoreNLP(props);
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("model laoding time: "+ elapsedTime/1000000000 +" seconds.");	
	}

	public static List<String> relation_extraction(String text){
		CoreDocument document = pipeline.processToCoreDocument(text);
		List<CoreSentence> s = document.sentences();

		CoreSentence s1;
		List<RelationTriple> tripltes;
		List<CoreEntityMention> entities;
		List<String> relation_triplets = new ArrayList<>();

		for(int i =0; i < s.size(); i++){
			s1 = s.get(i);
			tripltes = s1.relations();
			for(int j=0;j<tripltes.size();j++){
				RelationTriple t = tripltes.get(j);
				String output = String.format("%s:%s -[%s]-> %s:%s (%f) | %s", 
					t.subjectGloss(), 
					t.subjectHead().ner(),
					t.relationGloss(), 
					t.objectGloss(), 
					t.objectHead().ner(),
					t.confidence,
					s1.text());  
				relation_triplets.add(output);		
			}
		}
		return relation_triplets;
	}

	public static void main(String[] args) {
	    jessica_relation_extraction app = new jessica_relation_extraction();
		GatewayServer server = new GatewayServer(app);
		server.start();
		System.out.println("jessica relation extraction service started");
	}

}
/////////////jessica_relation_extraction.java////////
