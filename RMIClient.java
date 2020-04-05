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
	            Registry registry = LocateRegistry.getRegistry("localhost");
	            QueriesInterface comp = (QueriesInterface) registry.lookup(name);
	            boolean flag = true;
            	System.out.println("Welcome");
							System.out.println("Please enter number of nodes in the graph");
							int nodes = in.nextInt();
	            while(flag) {
		            String response = "";
		            int node1=0;int node2=0;
	            	System.out.println("To add initial graph enter (5)\n to add edge between two nodes enter (1) \n to delete edge between two nodes enter (2) \n to calculate shortest path between two nodes enter (3) \nto get graph press (4)\nPress anykey for closing");
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
						response = "";
						System.out.println(comp.shortestPath(node1,node2));//implement the method on server
						break;

						// new function from zoz
						case 4://get the graph
						System.out.println(comp.getGraph());
							break;

					case 5://add Initial graph (reading input file)
						//TODO implement reading from file
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

	            }


	        } catch (Exception e) {
	            System.err.println( "Exception while getting response." );
	            e.printStackTrace();
	        }

	   }

}
