# jessica_relation_extraction_docker 

## start the service

```bash
docker pull gaoyuanliang/jessica_relation_extraction:1.0.1
docker run -it gaoyuanliang/jessica_relation_extraction:1.0.1
python3
```

## usage 

```python
>>> from jessica_relation_extraction import relation_extraction
jessica relation extraction service started
[Thread-2] INFO edu.stanford.nlp.pipeline.StanfordCoreNLP - Adding annotator tokenize
[Thread-2] INFO edu.stanford.nlp.pipeline.StanfordCoreNLP - Adding annotator kbp
[Thread-2] INFO edu.stanford.nlp.pipeline.KBPAnnotator - Loading KBP classifier from: edu/stanford/nlp/models/kbp/english/tac-re-lr.ser.gz
model laoding time: 36 seconds.
>>> 
>>> text = u"""
... Jessica Liang works for Group 42 Inc. She was born in China. She studies at Heriot-Watt University. Jessica is married to Smith.
... """
>>> 
>>> for r in relation_extraction(text):
...     if r['relation'] != 'mention':
...             print(r)
... 
{'subject_name': 'Jessica Liang', 'subject_type': 'PERSON', 'subject': '5294656663411692480', 'object_name': 'Group 42 Inc.', 'object_type': 'ORGANIZATION', 'object': '-8801252103921655459', 'relation': 'per:employee_or_member_of'}
{'subject_name': 'Jessica Liang', 'subject_type': 'PERSON', 'subject': '5294656663411692480', 'object_name': 'China', 'object_type': 'COUNTRY', 'object': '7262786698407811651', 'relation': 'per:country_of_birth'}
{'subject_name': 'Jessica Liang', 'subject_type': 'PERSON', 'subject': '5294656663411692480', 'object_name': 'Heriot - Watt University', 'object_type': 'ORGANIZATION', 'object': '-7927693203698316438', 'relation': 'per:schools_attended'}
{'subject_name': 'Smith', 'subject_type': 'PERSON', 'subject': '5520747100304772131', 'object_name': 'Jessica Liang', 'object_type': 'PERSON', 'object': '5294656663411692480', 'relation': 'per:spouse'}
{'subject_name': 'Jessica Liang', 'subject_type': 'PERSON', 'subject': '5294656663411692480', 'object_name': 'Smith', 'object_type': 'PERSON', 'object': '5520747100304772131', 'relation': 'per:spouse'}
>>> for r in relation_extraction(text):
...     if r['relation'] == 'mention':
...             print(r)
... 
{'subject_name': 'Jessica Liang works for Group 42 Inc.', 'subject_type': 'SENTENCE', 'subject': '-3679695777525361992', 'relation': 'mention', 'object_name': 'Jessica Liang', 'object_type': 'PERSON', 'object': '5294656663411692480'}
{'subject_name': 'Jessica Liang works for Group 42 Inc.', 'subject_type': 'SENTENCE', 'subject': '-3679695777525361992', 'relation': 'mention', 'object_name': 'Group 42 Inc.', 'object_type': 'ORGANIZATION', 'object': '-8801252103921655459'}
{'subject_name': 'She was born in China.', 'subject_type': 'SENTENCE', 'subject': '-3027891050649601944', 'relation': 'mention', 'object_name': 'Jessica Liang', 'object_type': 'PERSON', 'object': '5294656663411692480'}
{'subject_name': 'She was born in China.', 'subject_type': 'SENTENCE', 'subject': '-3027891050649601944', 'relation': 'mention', 'object_name': 'China', 'object_type': 'COUNTRY', 'object': '7262786698407811651'}
{'subject_name': 'She studies at Heriot-Watt University.', 'subject_type': 'SENTENCE', 'subject': '-4254305780209262768', 'relation': 'mention', 'object_name': 'Jessica Liang', 'object_type': 'PERSON', 'object': '5294656663411692480'}
{'subject_name': 'She studies at Heriot-Watt University.', 'subject_type': 'SENTENCE', 'subject': '-4254305780209262768', 'relation': 'mention', 'object_name': 'Heriot - Watt University', 'object_type': 'ORGANIZATION', 'object': '-7927693203698316438'}
{'subject_name': 'Jessica is married to Smith.', 'subject_type': 'SENTENCE', 'subject': '-5153803059060747347', 'relation': 'mention', 'object_name': 'Smith', 'object_type': 'PERSON', 'object': '5520747100304772131'}
{'subject_name': 'Jessica is married to Smith.', 'subject_type': 'SENTENCE', 'subject': '-5153803059060747347', 'relation': 'mention', 'object_name': 'Jessica Liang', 'object_type': 'PERSON', 'object': '5294656663411692480'}
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
