package ex0;


import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {

    private static int numVertices = 0;
    private String info;
    private int tag = 0;
    private int keyId;
    private HashMap<Integer, node_data> ni = new HashMap<>();


    public NodeData() {//constractur
        this.info = "";
        this.ni = new HashMap<Integer, node_data>();
        this.keyId = this.numVertices++;
        this.tag = 0;


    }

    public NodeData(HashMap<Integer, node_data> node1, int ver, String s, int key, int tag) {
        this.ni = node1;
        this.keyId = this.numVertices++;
        this.info = s;
        this.ni = new HashMap<Integer, node_data>();
        this.tag = tag;
    }


    public NodeData(int key) {//constractur
        this.keyId = key;
    }

    public int getKey(int key) {
        return this.keyId;
    }

    public void setKey(int key) {
        this.keyId = key;
    }

    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     *
     * @return
     */
    @Override
    public int getKey() {
        return this.keyId;
    }

    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi() {
        if (this.ni != null)
            return this.ni.values();
        return null;
    }

    /**
     * return true iff this<==>key are adjacent, as an edge between them.
     *
     * @param key
     * @return
     */
    @Override
    public boolean hasNi(int key) {
        if (this.ni != null) {
            for (node_data neighbour : this.ni.values()) {
                if (neighbour.getKey() == key)
                    return true;
            }
        }
        return false;
    }


    /**
     * This method adds the node_data (t) to this node_data.
     * This method is wrongly designed! and was used mainly for educational example - to be improved in Ex1
     */
    @Override
    public void addNi(node_data t) {
        if (t != null) {
           // if (this.ni != null)
                //if (!this.ni.containsKey(t.getKey()))
                this.ni.put(t.getKey(), t);
            return;
        }

    }

    /**
     * Removes the edge this-node,
     *
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
        if (node == null)
            return;
        if (this.ni.containsKey(node.getKey())) {
            this.getNi().remove(node);
        }
        return;
    }

    /**
     * return the remark (meta data) associated with this node.
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "NodeData{" + " key = " + keyId + " }";


    }
}
