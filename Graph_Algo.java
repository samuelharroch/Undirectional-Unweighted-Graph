package ex0;

import java.util.*;
/**
 *This class implements the graph_algorithms interface using the BFS algorithm
 * @author Samuel Harroch
 *
 */
public class Graph_Algo implements graph_algorithms {
    graph myGraph;
/*constructor
 */
    public Graph_Algo() {
        myGraph= new Graph_DS();
    }

    @Override
    public void init(graph g) {
        myGraph= g;
    }

    @Override
    public graph copy() {
        return new Graph_DS(myGraph);
    }

    // This method create a array using the bfs algorithm steps
    private node_data[] MyPath(int scr){
        // using the tag parameters of each node
        int tag=0;
        for (node_data n: myGraph.getV()) {
            n.setTag(tag++);
        }
        // array of boolean representing the nodes we visit using the tag
        boolean [] visited= new boolean[myGraph.nodeSize()];
        visited[myGraph.getNode(scr).getTag()]=true; // we visit in scr node

        // array of nodes using the index as the tag of visited node and
        // prev[i]= The node we pass through to visit the node associate with the tag using the index
        node_data [] prev =new node_data[myGraph.nodeSize()];

        // queue of visited nodes
        Queue<node_data> q = new LinkedList<node_data>();
        q.add(myGraph.getNode(scr)); //push the scr node

        while (!q.isEmpty()){
            node_data node= q.poll(); // poll the first node in the queue, we don't want to visit him, again
            Collection<node_data> neighbours = node.getNi(); // the neighbors of the polled node
            for (node_data n:neighbours) {
                if (!visited[n.getTag()]) // if we didn't visit this neighbor
                {
                    q.add(n);
                    visited[n.getTag()]=true;
                    prev[n.getTag()]=node; //keep the current node as the "source" of the neighbor
                }
            }
        }
        return prev;
    }

    @Override
    public boolean isConnected() {
        if (myGraph.nodeSize()==0) return true; // assume that the empty graph is connected
        //create the visited nodes array using the first node in the graph collection of nodes
        node_data [] prev = MyPath(myGraph.getV().iterator().next().getKey());

        for (int i=1; i< prev.length;i++) // start from 1 cause the tag scr index (0 in this case)is always null
        {
            if (prev[i]== null) return false; // if one node is unvisited
        }
        return true;
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        // if one node doesn't exist
        if (myGraph.getNode(src)==null || myGraph.getNode(dest)==null) return -1;
        // path node to himself
        if (src==dest)return 0;
        //create the visited nodes array
        node_data [] prev = MyPath(src);

        Stack<node_data> s= new Stack<>(); // to reconstruct the path
        s.push(myGraph.getNode(dest)); // push the dest node

        // such of backtracking from the dest to the scr
        for (int i= myGraph.getNode(dest).getTag(); prev[i]!=null; i= prev[i].getTag())
            s.push(prev[i]);
        if (!s.isEmpty() && s.peek().getKey()== src)
            return s.size()-1;
        return -1;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (shortestPathDist(src, dest)==-1)
            return null;

        Stack<node_data> s= new Stack<>();
        s.push(myGraph.getNode(dest));

        node_data [] prev = MyPath(src);

        for (int i= myGraph.getNode(dest).getTag(); prev[i]!=null; i= prev[i].getTag())
            s.push(prev[i]);
        List<node_data> l= new ArrayList<node_data>();
        while (!s.isEmpty())
            l.add(s.pop());

        return l;
    }
}
