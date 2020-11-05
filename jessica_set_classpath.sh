for file in `find . -name "*.jar"`; do export CLASSPATH="$CLASSPATH:`realpath $file`"; done

echo $CLASSPATH

export CORENLP_HOME="/stanford-corenlp-4.1.0"

