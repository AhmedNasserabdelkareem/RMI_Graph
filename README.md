# RMI_Graph

How to run Ya awlad ?
1) compile java files
Command>> javac -cp jgrapht.jar:jheaps.jar *.java
2) run the RMIC tools
Command>> rmic RemoteObject
3) Start RMI Registery
Command>> rmiregistry &
4) Run the server Jar
Command>> java -jar Server.jar
5) Finally run the clinet from another terminal
Command>> java RMIClient

#Notes
results directory > has 3 directories for each algorithms
each algorithm has 3 files for responses time
graphs directory > I implement graph generator to generate 15 file one foreach graph of size #
