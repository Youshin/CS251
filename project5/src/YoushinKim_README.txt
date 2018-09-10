I used java to work with this project.

To compile files:

javac -cp .:stdlib:algs4.jar BurrowsWheeler.java
javac -cp .:stdlib:algs4.jar MoveToFront.java

To Test files:
encode :
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar:algs4.jar HexDump 16
java -cp .:stdlib.jar:algs4.jar MoveToFront - < abra.txt | java -cp .:stdlib.jar:algs4.jar HexDump 16
decode :
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar:algs4.jar BurrowsWheeler +
java -cp .:stdlib.jar:algs4.jar MoveToFront - < abra.txt | java -cp .:stdlib.jar:algs4.jar MoveToFront +

