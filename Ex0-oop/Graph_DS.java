package ex0;

import java.util.*;

public class Graph_DS implements graph {

    private int MC;
    private int E;
    private static int vertex = 0;
    private HashMap<Integer, node_data> gr = new HashMap<Integer, node_data>();

    public Graph_DS() {//constractur
        this.gr = new HashMap<Integer, node_data>();
        this.vertex = 0;
        MC = 0;
    }

    @Override
    public String toString() {

        int counter = 0;
        String string = "";
        Set<Integer> keys = gr.keySet();
        for (Integer key : keys) {
            if (counter == 0) {
                string = string + key + "--->" + gr.get(key).toString();
            } else {
                string = string + "\n" + key + "--->" + gr.get(key).toString();
            }
            counter++;
        }
        return string;
    }


    public Graph_DS(HashMap<Integer, node_data> gr1, int ver, int mc, int c) {
        this.gr = gr1;
        this.vertex = ver;
        this.MC = mc;
        this.gr = new HashMap<Integer, node_data>();
        this.E = c;
    }

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */

    @Override
    public node_data getNode(int key) {
        if (this.gr.containsKey(key))
            return this.gr.get(key);
        return null;

    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {

        if (getNode(node1) == null || getNode(node2) == null)
            return false;
        if ((getNode(node1).getNi() == null) || getNode(node2).getNi() == null)
            return false;
        if ((!getNode(node1).getNi().isEmpty()))
            return false;
        if (!getNode(node2).getNi().isEmpty())
            return false;

        else if (gr.get(node1).hasNi(node2) || gr.get(node2).hasNi(node1)) {

            return true;
        }
        return false;
    }

    /**
     * add a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(node_data n) {

        if (n == null) {
            return;
        }
        gr.put(n.getKey(), n);
        this.vertex++;
        this.MC++;

    }

    /**
     * Connect an edge between node1 and node2.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply does nothing.
     */
    @Override
    public void connect(int node1, int node2) {
        if (getNode(node1) == null || getNode(node2) == null)
            return;
        if (this.gr.containsKey(node1) && this.gr.containsKey(node2) && node1 != node2) {
            if (!this.gr.get(node1).hasNi(node2) && !this.gr.get(node2).hasNi(node1)) {
                gr.get(node1).addNi(gr.get(node2));
                gr.get(node2).addNi(gr.get(node1));
                this.E++;

            }
            this.MC++;

        }
        return;
    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return gr.values();
    }

    /**
     * This method returns a collection containing all the
     * nodes connected to node_id
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if ((getNode(node_id) == null)||(!getNode(node_id).getNi().isEmpty()))
            return null;
        //  if (!gr.containsKey(node_id))
        //    return null;
        return getNode(node_id).getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        if (getNode(key) == null)
            return null;
        if (gr.containsKey(key)) {
            if (!this.gr.get(key).getNi().isEmpty() && this.gr.get(key).getNi() != null) {
                for (node_data i : this.gr.get(key).getNi()) {
                    i.removeNode(getNode(key));
                    this.E--;
                    this.MC++;
                }
            }
        }
        gr.remove(key);
        return gr.remove(key);
    }



    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if ((node1 == node2) || (this.gr.get(node1) == null || this.gr.get(node2) == null))
            return;
        if (gr.containsKey(node1) && gr.containsKey(node2))
            if (gr.get(node1).hasNi(node2) || gr.get(node2).hasNi(node1)) {

                this.gr.get(node1).removeNode(getNode(node2));
                this.gr.get(node2).removeNode(getNode(node1));
                this.E--;
                this.MC++;
            }
        //   throw new NullPointerException(" doesn't have a edge between this nodes");
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return gr.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return this.E;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return this.MC;
    }


}
