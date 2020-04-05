import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class RMIClient {
	   public static void main(String[] args) {
           System.setProperty("java.security.policy","client.policy");
           Scanner in = new Scanner(System.in);
		   if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }

	        try {
	            String name = "graph";
							System.setProperty("java.rmi.server.hostname","localhost");
	            Registry registry = LocateRegistry.getRegistry("localhost");
	            QueriesInterface comp = (QueriesInterface) registry.lookup(name);
	            boolean flag = true;
            	System.out.println("Welcome");
							System.out.println("Please enter number of nodes in the graph");
							int nodes = in.nextInt();


while(flag) {
		            String response = "";
		            int node1=0;int node2=0;
System.out.println("\n1- to add edge between two nodes enter\n2-to delete edge between two nodes enter \n3-to calculate shortest path between two nodes enter \n4- to get graph press\n5- To add initial graph from file\nPress CTRL+C to close");
	            	int choice = in.nextInt();

	  	switch (choice) {


					case 1:// adding edge to the graph  between two nodes
						System.out.println("\nEnter the id of the first node:\n");
						node1 = in.nextInt();
						System.out.println("\nEnter the id of the second node:\n");
						node2 = in.nextInt();
						comp.addEdge(node1, node2);//implement the method on server
						response = "Done!";
						break;

					case 2://remove edge from the graph between two nodes
						System.out.println("\nEnter the id of the first node:\n");
						node1 = in.nextInt();
						System.out.println("\nEnter the id of the second node:\n");
						node2 = in.nextInt();
						comp.deleteEdge(node1,node2);//implement the method on server
						response = "Done!";
						break;

					case 3://calculate the shortest path between two nodes
						System.out.println("\nEnter the id of the first node:\n");
						node1 = in.nextInt();
						System.out.println("\nEnter the id of the second node:\n");
						node2 = in.nextInt();
						response = Integer.toString(comp.shortestPath(node1,node2));
						break;

						// new function from zoz
						case 4://get the graph
						System.out.println(comp.getGraph());
							break;

						case 5://add Initial graph (reading input file)
							BufferedReader reader;
							try {
								reader = new BufferedReader(new FileReader(
										"inputGraph.txt"));
								String line = reader.readLine();
								while (line != null && line != "S") {
									String[] nums = line.split(" ");
									comp.addEdge(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
									line = reader.readLine();
								}
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (Exception e){
								e.printStackTrace();
							}

							break;
						default://close the program
							flag = false;
							break;
					}

		            System.out.print("\033[H\033[2J");
		            System.out.flush();
		            System.out.println( "The return value from the server is: " + response );
								System.out.println("Graph:" +comp.getGraph() );

	    }


	        } catch (Exception e) {
	            System.err.println( "Exception while getting response." );
	            e.printStackTrace();
	        }

	   }

}
