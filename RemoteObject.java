import java.rmi.RemoteException;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Ziyad ELbanna
 */
public class RemoteObject implements QueriesInterface {

	static int maxNodes;
	private ArrayList<LinkedList<Integer>> graph;
	private Graph graph2 = null;

// constructor
// this is the implementation of a graph
	RemoteObject(int max){
	        maxNodes = max;
	        graph = new ArrayList<LinkedList<Integer>>();
	        for(int i=1;i<=maxNodes;i++)
	            graph.add(new LinkedList<Integer>());
	    }
			RemoteObject(){
				maxNodes=2;
			  graph = new ArrayList<LinkedList<Integer>>();
				for(int i=1;i<=maxNodes;i++)
						graph.add(new LinkedList<Integer>());
			    }

/// I replaced ID with node#
	@Override
	public void addEdge(int node1, int node2) throws RemoteException {

		// if node is not here: create new node and add a new LinkedList

				while (graph.size() < node1)
					{graph.add(new LinkedList<Integer>());}

				while (graph.size() < node2)
				{	graph.add(new LinkedList<Integer>()); }


		graph.get(node1-1).add(node2);
	}

	@Override
	public void deleteEdge(int node1, int node2) throws RemoteException {
		graph.get(node1-1).remove(graph.get(node1-1).indexOf(node2));
	}

// used dijkstra's algorithm online to return the cost of shortest path, nasser the required is 'int=' not 'string'
	@Override
	public int shortestPath(int node1, int node2) throws RemoteException {

		int ret = -1;
        Integer s;
        boolean flag = true;
        int visited[] = new int[graph.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();
				//base condition
        if(node1 == node2)  return 0;
				//else
        queue.add(node1);
        while (queue.size() != 0 && flag){
            s = queue.poll();
            Iterator<Integer> i = graph.get(s-1).listIterator();
            while (i.hasNext()){
                int n = i.next();
                if(n == node2){
                    ret = visited[s-1] + 1;
                    flag = false;
                    break;
                }
                if (visited[n-1] == 0){
                    visited[n-1] = visited[s-1] + 1;
                    queue.add(n);
                }
            }
        }
        return ret;

	}
    @Override
    public String executeBatch(String s)throws RemoteException{
	    String res = "";
	    String []q = s.split("\n");
	    for (int i =0 ; i<q.length ;i++){
	        if(q[i].equals("F")){
	            break;
            }
                String[] cs = q[i].split(" ");
                if (cs[0].equals("A")) {
                    this.addEdge(Integer.parseInt(cs[1]), Integer.parseInt(cs[2]));
                } else if (cs[0].equals("D")) {
                    this.deleteEdge(Integer.parseInt(cs[1]), Integer.parseInt(cs[2]));
                } else {
                    res += this.shortestPath(Integer.parseInt(cs[1]), Integer.parseInt(cs[2]));
                    res += "\n";
                }

        }
	    return res;
    }

	public void constructGraph()
	{
		graph2 = new Graph(graph.size());
		for (int i =0; i < graph.size(); i++)
			{
				for(int u=0; u< graph.get(i).size(); u++)
						{
							graph2.addEdge(i, u);
						}
			}
	}

	public String getGraph(){
        String ret = "";
        for(int i=1 ;i<= graph.size();i++){
					// when the graph is not  empty
            if(!graph.get(i-1).isEmpty()){
                String arr = "[ ";
                Iterator<Integer> t = graph.get(i-1).listIterator();
                while (t.hasNext())
                    arr += Integer.toString(t.next());
                arr += " ]";
                ret += i + " --> " + arr + "\n";
            }
        }
			constructGraph();

        return ret;
    }



	public ArrayList<Integer> doBFSShortestPath(int source, int dest)
	{
		ArrayList<Integer> shortestPathList = new ArrayList<Integer>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();

		if (source == dest)
			return null;
		Queue<Integer> queue = new LinkedList<Integer>();
		Stack<Integer> pathStack = new Stack<Integer>();

		queue.add(source);
		pathStack.add(source);
		visited.put(source, true);

		while(!queue.isEmpty())
		{
			int u = queue.poll();
			ArrayList<Integer> adjList = graph2.getOutEdges(u);

			for(int v : adjList)
			{
				if(!visited.containsKey(v))
				{
					queue.add(v);
					visited.put(v, true);
					pathStack.add(v);
					if(u == dest)
						break;
				}
			}
		}


		//To find the path
		int node, currentSrc=dest;
		shortestPathList.add(dest);
		while(!pathStack.isEmpty())
		{
			node = pathStack.pop();
			if(graph2.isNeighbor(currentSrc, node))
			{
				shortestPathList.add(node);
				currentSrc = node;
				if(node == source)
					break;
			}
		}

		return shortestPathList;
	}





}
