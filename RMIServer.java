import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends RemoteObject{
	   public static void main(String args[]) { 
	        RemoteObject implementation = new RemoteObject();

	        try {
	            // Export the object.
	            QueriesInterface stub = (QueriesInterface) UnicastRemoteObject.exportObject(implementation, 0);
	            Registry registry = LocateRegistry.getRegistry();
	            registry.rebind("graph", stub);
	        } catch (RemoteException ex) {
	            ex.printStackTrace();
	            return;
	        }
	        System.out.println( "Server is running" );
	   } 

}
