package ex0;

import java.util.Collection;
import java.util.HashMap;
/**
 * This class implements the node_data interface
 * Each node has a unique Key, and a collection of neighbors - using HashMap to keep them
 * @author Samuel Harroch
 */

public class NodeData implements node_data {
private static int countKeys;
private int key;
private HashMap<Integer, node_data> neigh;
private String info;
private int tag=0;

    /**
     * Constructor
     * @return - NodeData object
     */
    public NodeData() {
        this.key= countKeys;
        countKeys++;
        this.neigh= new HashMap<Integer, node_data>();

    }
    /**
     * Copy constructor
     * @return - NodeData object
     */
    public NodeData(node_data other) {
        this.key= other.getKey();
        this.info=other.getInfo();
        this.neigh= new HashMap<Integer, node_data>();
        this.tag=other.getTag();

    }
    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     * @return
     */
    @Override
    public int getKey() {
        return this.key;
    }
    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi()
    {
        return this.neigh.values();
    }
    /**
     * return true iff this<==>key are adjacent, as an edge between them.
     * @param key
     * @return true if they are adjacent else false
     */
    @Override
    public boolean hasNi(int key) {
        return (neigh.containsKey(key)||this.key==key);
    }
    /** This method adds the node_data (t) to this node_data.*/
    @Override
    public void addNi(node_data t) {
      if (t.getKey()== this.key)return; // trying to connect to himself
      neigh.put(t.getKey(),t);

    }
    /**
     * Removes the edge this-node,
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
        neigh.remove(node.getKey());
    }
    /**
     * return the remark (meta data) associated with this node.
     * @return String of metaData
     */
    @Override
    public String getInfo() {
        return this.info;
    }
    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info=s;
    }
    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used by algorithms
     * @return
     */
    @Override
    public int getTag() {
        return this.tag;
    }
    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
