##########jessica_relation_extraction.py##########
import os
import re
from py4j.java_gateway import JavaGateway

os.system(u"""
	for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done
	java jessica_relation_extraction &
	""")

while True:
	try:
		gateway = JavaGateway()
		random = gateway.jvm.java.util.Random()
		number1 = random.nextInt(10)
		break
	except:
		pass

jessica = gateway.jvm.jessica_relation_extraction('kbp')

def relation_extraction(text):
	relation = jessica.relation_extraction(text)
	outputs = [
	re.search(r'^(?P<subject_name>.*)\:(?P<subject_type>[^a-z]+) \-\[(?P<relation>.*)\]-> (?P<object_name>.*)\:(?P<object_type>[^a-z]+) \((?P<confidence>[\d\.]+)\) \| (?P<setence>.*)$', r).groupdict()
	for r in relation]
	return outputs
##########jessica_relation_extraction.py##########
