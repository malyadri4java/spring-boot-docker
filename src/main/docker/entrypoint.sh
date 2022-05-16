export JAVA_HOME=/usr/lib/jvm/java-8-oracle
export PATH=$JAVA_HOME/bin:$PATH

JVM_PARAM=" -Xms1024m -Xmx2048m "
JVM_PARAM="${JVM_PARAM} -Djava.security.egd=file:/dev/urandom "

if [ "$DEPLOY_ENV" == "dev" ]; then
    JVM_PARAM="${JVM_PARAM} -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8004,suspend=n "
fi

export JAVA_OPTS=$JVM_PARAM

java $JAVA_OPTS -jar spring-boot-docker.jar
