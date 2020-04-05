import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
/**
 *
 */

/**
 * @author Ahmednasser
 *
 */
public interface QueriesInterface extends Remote{
	   void addEdge(int firstID,int secondID) throws RemoteException;
	   void deleteEdge(int firstID,int secondID) throws RemoteException;
	   int shortestPath(int firstID,int secondID) throws RemoteException;
		 ArrayList<Integer> BFSShortestPath(int source, int dest)  throws RemoteException;
	   String getGraph() throws RemoteException;

}
