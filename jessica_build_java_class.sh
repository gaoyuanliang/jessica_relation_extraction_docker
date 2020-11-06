for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done
javac jessica_relation_extraction.java
jar -cvf jessica_relation_extraction.jar jessica_relation_extraction.class
