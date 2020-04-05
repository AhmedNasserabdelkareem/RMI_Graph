import java.rmi.RemoteException;
import java.util.*;
/**
 * @author Ziyad ELbanna
 *
 */
public class RemoteObject implements QueriesInterface {

	static int maxNodes;
	private ArrayList<LinkedList<Integer>> graph;

// constructor
// this is the implementation of a graph
	RemoteObject(int max){
	        maxNodes = max;
	        graph = new ArrayList<LinkedList<Integer>>();
	        for(int i=0;i<maxNodes;i++)
	            graph.add(new LinkedList<Integer>());
	    }
			RemoteObject(){
				maxNodes=2;
			  graph = new ArrayList<LinkedList<Integer>>();
				for(int i=0;i<maxNodes;i++)
						graph.add(new LinkedList<Integer>());
			    }


/// I replaced ID with node#
	@Override
	public void addEdge(int node1, int node2) throws RemoteException {
		graph.get(node1).add(node2);
		graph.get(node2).add(node1);
	}

	@Override
	public void deleteEdge(int node1, int node2) throws RemoteException {
		graph.get(node1).remove(node2);
		graph.get(node2).remove(node1);
	}
// used dijkstra's algorithm online to return the cost of shortest path, nasser the required is 'int=' not 'string'
	@Override
	public int shortestPath(int node1, int node2) throws RemoteException {

		int ret = -1;
        Integer s;
        boolean flag = true;
        int visited[] = new int[maxNodes];
        LinkedList<Integer> queue = new LinkedList<Integer>();
				//base condition
        if(node1 == node2)  return 0;
				//else
        queue.add(node1);
        while (queue.size() != 0 && flag){
            s = queue.poll();
            Iterator<Integer> i = graph.get(s).listIterator();
            while (i.hasNext()){
                int n = i.next();
                if(n == node2){
                    ret = visited[s] + 1;
                    flag = false;
                    break;
                }
                if (visited[n] == 0){
                    visited[n] = visited[s] + 1;
                    queue.add(n);
                }
            }
        }
        return ret;

	}

	public String getGraph(){
        String ret = "";
        for(int i=1;i<=maxNodes;i++){
            if(!graph.get(i).isEmpty()){
                String arr = "[ ";
                Iterator<Integer> t = graph.get(i).listIterator();
                while (t.hasNext())
                    arr += Integer.toString(t.next());
                arr += " ]";
                ret += i + " --> " + arr + "\n";
            }
        }
        return ret;
    }



}
