Group 05

LONG PROJECT 0

Finding Euler Tours

The zip files contains the following source files:

1. Edge.java - made some additions to the variables by adding a "count", "visited" and "index" fields.
2. Vertex.java - made some additions - "remDegree" and "edgeCount" fields were added.
3. Graph.java - The class is rewritten by adding the methods to check for Eulerian graphs and find the Euler Tour and 
				Euler Path.
4. Timer.java - A utility class to print the running time of the program
5. LongProjectDriver.java - Driver program for the project

Envirnoment:
 
Java SE 1.7

Compilation:

javac Edge.java Vertex.java Graph.java Timer.java LongProjectDriver.java

Running the program:

java LongProjectDriver.java /Users/karthikrk/Desktop/Courses/IADSA/simpleIO2.txt


Sample IO:

Sample Input 3
 * /Users/karthikrk/Desktop/Courses/IADSA/simpleIO2.txt
 * 

Sample Output 3
 * 
 *Size 6
 *01
 *4
 *4
 *4
 *4
 *(1,2)
 *(2,3)
 *(3,4)
 *(4,5)
 *(5,3)
 *(3,1)
 *Time: 1 msec.
 *Memory: 2 MB / 128 MB.
 *
 */