##########jessica_relation_extraction.py##########
import os
from py4j.java_gateway import JavaGateway

os.system(u"""
	for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done
	java jessica_relation_extraction &
	""")

gateway = JavaGateway()
random = gateway.jvm.java.util.Random()
number1 = random.nextInt(10)

jessica = gateway.jvm.jessica_relation_extraction('kbp')
relation = jessica.relation_extraction("Smith's wife is Jessica. Jessica is working for Apple. Jessica is 23 years old.")

for r in relation:
	print(r)
##########jessica_relation_extraction.py##########
