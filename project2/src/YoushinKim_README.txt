I used java to work with this project.

To compile files:

javac -classpath .:stdlib.jar Brute.java
javac -classpath .:stdlib.jar Fast.java

To Test files:
java -classpath .:stdlib.jar Brute < inputfilename.txt
java -classpath .:stdlib.jar Fast < inputfilename.txt

Then there will be output file named "visualPoints.txt", you can check right segments have been created at this.

