package ex0;

import java.util.Collection;
import java.util.HashMap;
/**
 * This class implements the graph interface
 * Each graph has a collection of its nodes- using Hashmap to keep them
 * @author Samuel Harroch
 */
public class Graph_DS implements graph{
    private HashMap<Integer, node_data> myNodes;
    private int NumOfEdges=0;
    private int MC=0;

    /** constructor
     */
    public Graph_DS() {
       myNodes=new HashMap<Integer, node_data>();
    }
    /** copy constructor
     */
    public Graph_DS(graph g)
    {
        this.myNodes=new HashMap<Integer, node_data>();
        this.NumOfEdges=g.edgeSize();
        this.MC=g.getMC();
        for (node_data n: g.getV()) {
            myNodes.put(n.getKey(),new NodeData(n));
        }
        for (node_data n : g.getV()) {
            for (node_data neigh:n.getNi()) {
                myNodes.get(n.getKey()).addNi(myNodes.get(neigh.getKey()));
            }
        }
    }

    @Override
    public node_data getNode(int key) {

        return myNodes.get(key);
    }
    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return true if the edge exist false if there is no edge or one or more node doesnt exist
     */
    @Override
    public boolean hasEdge(int node1, int node2) {

        if (!myNodes.containsKey(node1)||!myNodes.containsKey(node2) )
           return false;

        return myNodes.get(node1).hasNi(node2);
    }

    @Override
    public void addNode(node_data n) {
        myNodes.put(n.getKey(),n);
        MC++;
    }
    /**
     * Connect an edge between node1 and node2.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists or one of the node doesn't exist - the method simply does nothing.
     */
    @Override
    public void connect(int node1, int node2) {
        if (!myNodes.containsKey(node1)||!myNodes.containsKey(node2) ) return;

        if(!myNodes.get(node1).hasNi(node2) && node1!=node2){
            myNodes.get(node1).addNi(myNodes.get(node2));
            myNodes.get(node2).addNi(myNodes.get(node1));
            NumOfEdges++;
            MC++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return myNodes.values();
    }
    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id
     * Note: if the node doesn't exist return null .
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if (!myNodes.containsKey(node_id) )
            return null;
        return myNodes.get(node_id).getNi();
    }

    @Override
    public node_data removeNode(int key) {
        if(myNodes.containsKey(key)){
            Collection<node_data> neighbors = getV(key);
            for (node_data n:neighbors) {
                n.removeNode(myNodes.get(key));
                NumOfEdges--;
 }
            MC++;
            return myNodes.remove(key);
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        //check if node1 and node2 are really connected
        if(myNodes.get(node1).hasNi(node2) && node1!=node2) {
            myNodes.get(node1).removeNode(myNodes.get(node2));
            myNodes.get(node2).removeNode(myNodes.get(node1));
            NumOfEdges--;
            MC++;
        }
    }

    @Override
    public int nodeSize() {
        return myNodes.size();
    }

    @Override
    public int edgeSize() {
        return NumOfEdges;
    }

    @Override
    public int getMC() {
        return MC;
    }


}
