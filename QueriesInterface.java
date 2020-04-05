import java.rmi.Remote;
import java.rmi.RemoteException;

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
	   String shortestPath(int firstID,int secondID) throws RemoteException;  

}