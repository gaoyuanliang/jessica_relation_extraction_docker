# jessica_relation_extraction_docker 

## relation extraction 

```bash
echo "Joe Smith was born in Oregon." > example.txt

java -Xmx16g edu.stanford.nlp.pipeline.StanfordCoreNLP -annotators tokenize,ssplit,pos,lemma,ner,parse,coref,kbp -coref.md.type RULE -file example.txt
```

output: 

```
Document: ID=example.txt (1 sentences, 7 tokens)

Sentence #1 (7 tokens):
Joe Smith was born in Oregon.

Tokens:
[Text=Joe CharacterOffsetBegin=0 CharacterOffsetEnd=3 PartOfSpeech=NNP Lemma=Joe NamedEntityTag=PERSON]
[Text=Smith CharacterOffsetBegin=4 CharacterOffsetEnd=9 PartOfSpeech=NNP Lemma=Smith NamedEntityTag=PERSON]
[Text=was CharacterOffsetBegin=10 CharacterOffsetEnd=13 PartOfSpeech=VBD Lemma=be NamedEntityTag=O]
[Text=born CharacterOffsetBegin=14 CharacterOffsetEnd=18 PartOfSpeech=VBN Lemma=bear NamedEntityTag=O]
[Text=in CharacterOffsetBegin=19 CharacterOffsetEnd=21 PartOfSpeech=IN Lemma=in NamedEntityTag=O]
[Text=Oregon CharacterOffsetBegin=22 CharacterOffsetEnd=28 PartOfSpeech=NNP Lemma=Oregon NamedEntityTag=STATE_OR_PROVINCE]
[Text=. CharacterOffsetBegin=28 CharacterOffsetEnd=29 PartOfSpeech=. Lemma=. NamedEntityTag=O]

Constituency parse: 
(ROOT
  (S
    (NP (NNP Joe) (NNP Smith))
    (VP (VBD was)
      (VP (VBN born)
        (PP (IN in)
          (NP (NNP Oregon)))))
    (. .)))


Dependency Parse (enhanced plus plus dependencies):
root(ROOT-0, born-4)
compound(Smith-2, Joe-1)
nsubj:pass(born-4, Smith-2)
aux:pass(born-4, was-3)
case(Oregon-6, in-5)
obl:in(born-4, Oregon-6)
punct(born-4, .-7)

Extracted the following NER entity mentions:
Joe Smith	PERSON	PERSON:0.9994280560852734
Oregon	STATE_OR_PROVINCE	LOCATION:0.9982464307494803

Extracted the following KBP triples:
1.0	Joe Smith	per:stateorprovince_of_birth	Oregon
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
