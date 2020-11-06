# jessica_relation_extraction_docker 

## start the service

```bash
docker pull gaoyuanliang/jessica_relation_extraction:1.0.1
docker run -it \
gaoyuanliang/jessica_relation_extraction:1.0.1
```

## usage 

```python
from jessica_relation_extraction import relation_extraction

for r in relation_extraction("Smith's wife is Jessica. Jessica is working for Apple. Jessica is 23 years old."):
	print(r)
```

## entity linking

```bash
java -Xmx16g edu.stanford.nlp.pipeline.StanfordCoreNLP -annotators tokenize,ssplit,pos,lemma,ner,entitylink -file example.txt
```

output:

```bash
Document: ID=example.txt (1 sentences, 7 tokens)

Sentence #1 (7 tokens):
Joe Smith was born in Oregon.

Tokens:
[Text=Joe CharacterOffsetBegin=0 CharacterOffsetEnd=3 PartOfSpeech=NNP Lemma=Joe NamedEntityTag=PERSON WikipediaEntity=Joe_Smith_(basketball)]
[Text=Smith CharacterOffsetBegin=4 CharacterOffsetEnd=9 PartOfSpeech=NNP Lemma=Smith NamedEntityTag=PERSON WikipediaEntity=Joe_Smith_(basketball)]
[Text=was CharacterOffsetBegin=10 CharacterOffsetEnd=13 PartOfSpeech=VBD Lemma=be NamedEntityTag=O WikipediaEntity=O]
[Text=born CharacterOffsetBegin=14 CharacterOffsetEnd=18 PartOfSpeech=VBN Lemma=bear NamedEntityTag=O WikipediaEntity=O]
[Text=in CharacterOffsetBegin=19 CharacterOffsetEnd=21 PartOfSpeech=IN Lemma=in NamedEntityTag=O WikipediaEntity=O]
[Text=Oregon CharacterOffsetBegin=22 CharacterOffsetEnd=28 PartOfSpeech=NNP Lemma=Oregon NamedEntityTag=STATE_OR_PROVINCE WikipediaEntity=Oregon]
[Text=. CharacterOffsetBegin=28 CharacterOffsetEnd=29 PartOfSpeech=. Lemma=. NamedEntityTag=O WikipediaEntity=O]

Extracted the following NER entity mentions:
Joe Smith	PERSON	PERSON:0.9994280560852734
Oregon	STATE_OR_PROVINCE	LOCATION:0.9982464307494803
```
