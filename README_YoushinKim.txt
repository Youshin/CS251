I submitted everything I downloaded, but I used only java files.

To compile them, 
You need command: 
javac -classpath .:stdlib.jar PercolationStats.java javac -classpath .:stdlib.jar Percolation.java javac -classpath .:stdlib.jar PercolationVisualizer.java javac -classpath .:stdlib.jar PercolationStats.java 
javac -classpath .:stdlib.jar PercolationQuick.java 
To run:
java -classpath .:stdlib.jar PercolationQuick.java < testCase.txt
java -classpath .:stdlib.jar Percolation < testCase.txt
java -classpath .:stdlib.jar PercolationVisualizer < testCase.txt 
If use WeightedQuickUnion :  java -classpath .:stdlib.jar PercolationStats 20 50 fast
If use QuickUnion:
java -classpath .:stdlib.jar PercolationStats 20 50 slow 
