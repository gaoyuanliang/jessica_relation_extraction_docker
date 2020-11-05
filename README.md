# jessica_relation_extraction_docker

echo "Joe Smith was born in Oregon." > example.txt

java -Xmx16g edu.stanford.nlp.pipeline.StanfordCoreNLP -annotators tokenize,ssplit,pos,lemma,ner,parse,coref,kbp -coref.md.type RULE -file example.txt

java -Xmx16g edu.stanford.nlp.pipeline.StanfordCoreNLP -annotators tokenize,ssplit,pos,lemma,ner,entitylink -file example.txt
