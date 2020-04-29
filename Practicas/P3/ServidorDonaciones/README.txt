Para probar el servicio:

IMPORTANTE EJECUTAR TODO EN EL MISMO DIRECTORIO
rmiregistry &
javac *.java

//Terminal 1
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy Servidor_Principal servidor1 servidor2

//Terminal 2
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy Servidor_Principal servidor2 servidor1

//Terminal 3 (Cliente)
java -cp . -Djava.security.policy=server.policy Cliente