package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph algo = new Graph_DS();


    public Graph_Algo() {//constractur
        this.algo = new Graph_DS();

    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(graph g) {
        this.algo = g;

    }


    /**
     * Compute a deep copy of this graph.
     *
     * @return
     */
    @Override
    public graph copy() {
        graph NaAlgo = new Graph_DS();


        for (node_data q : this.algo.getV()
        ) {

            node_data CopyN = new NodeData(q.getKey());
            NaAlgo.addNode(CopyN);
            System.out.println(q.getKey());
            //  System.out.println(q.getNi());
        }

        for (node_data q : this.algo.getV()) {

            if ((q.getNi() != null) && (!q.getNi().isEmpty()))
                for (node_data ni : q.getNi()) {
                    NaAlgo.connect(q.getKey(), ni.getKey());

                }

        }

        return NaAlgo;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if (this.algo.nodeSize() == 0 || this.algo.nodeSize() == 1)
            return true;

        node_data root = getN(this.algo);
        Dfs(root);
        for (node_data i : this.algo.getV()) {
            if (i.getNi().isEmpty())
                return false;
            if (i.getTag() == 0) {
                //  Dfs(i);
                return false;
            }
        }


        return true;
    }


    public void stag(node_data n) {//set all nodes no visited
        for (node_data i : n.getNi())
            i.setTag(0);
    }

    public node_data getN(graph k) {//get the first node in the graph
        for (node_data s : k.getV()) {
            if (s == null)
                continue;
            return s;
        }
        return null;
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public int shortestPathDist(int src, int dest) {

        if (shortestPath(src, dest) == null)
            return -1;
        return shortestPath(src, dest).size() - 1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if ((this.algo.getNode(src) == null) || this.algo.getNode(dest) == null)
            return null;
        Queue<node_data> queue = new LinkedList<node_data>();

        HashMap<node_data, node_data> parents = new HashMap<node_data, node_data>();
        queue.add(this.algo.getNode(src));
        parents.put(this.algo.getNode(src), null);

        while (!queue.isEmpty()) {

            node_data node = queue.remove();
            if (node.getKey() == dest)
                break;

            for (node_data i : node.getNi()) {

                if (!parents.containsKey(i)) {
                    queue.add(i);
                    parents.put(i, node);

                }
            }

        }

        List<node_data> shortestPath = new ArrayList<node_data>();

        node_data i = this.algo.getNode(dest);
        while (i != null) {
            shortestPath.add(0, i);
            i = parents.get(i);
        }
        if (shortestPath.size() == 1)
            return null;

        return shortestPath;
    }

    public void Dfs(node_data node) {// Dfs(deep first search) algo O(V+E)

        if (node == null)
            return;
        List<node_data> ans = new LinkedList<>();

        Stack<node_data> stack = new Stack<>();
        stack.push(node);

        stag(node);
        node.setTag(1);

        while (!stack.isEmpty()) {

            node_data nod = stack.pop();

            for (node_data ni : nod.getNi())
                if (ni.getTag() == 0) {
                    ni.setTag(1);
                    stack.push(ni);
                    ans.add(ni);
                }

        }
    }
}













































