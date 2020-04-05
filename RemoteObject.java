import java.rmi.RemoteException;

public class RemoteObject implements QueriesInterface {

	@Override
	public void addEdge(int firstID, int secondID) throws RemoteException {
		// TODO implement adding edge to the graph as you like
		
	}

	@Override
	public void deleteEdge(int firstID, int secondID) throws RemoteException {
		// TODO implement deleting edge to the graph as you like
		
	}

	@Override
	public String shortestPath(int firstID, int secondID) throws RemoteException {
		// TODO implement any shortest path you like

		return null;
	}



}
