FROM azul/zulu-openjdk:8u212
COPY build/libs/*.jar mifuchi.jar
CMD java ${JAVA_OPTS} -jar mifuchi.jar
