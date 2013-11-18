package algorithms;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Eulierian path.
 * 
 * An Eulierian cycle in a graph is a cycle (not necessarily simple) that uses
 * every edge in the graph exactly once.
 * 
 * Show that a graph has an Eulerian cycle if and only if every vertex has even
 * degree and all of the vertices with nonzero degree belong to the same
 * connected component.
 * 
 * Design a linear-time algorithm to determine whether a graph has an Eulerian
 * cycle, and if so, find one.
 * 
 * @author Anoop Elias
 * 
 */
public class EulerianCycle {

    public boolean hasEulerianCycle;
    public List<Integer> cycle;

    public List<Edge> marked;

    public EulerianCycle(EdgeWeightedGraph g) {
        if (g.E() > 0) {
            marked = new ArrayList<Edge>();
            Edge e = g.edges().iterator().next();

            int start = e.either();
            cycle = walk(g, e, start, start);

            while (cycle != null && marked.size() != g.E()) {
                cycle = walkFromCycle(g, cycle);
            }

            if (cycle != null)
                cycle.add(start);

            hasEulerianCycle = (cycle != null && marked.size() == g.E());
        }
    }

    private List<Integer> walkFromCycle(EdgeWeightedGraph g, List<Integer> path) {
        for (Edge e : g.edges()) {

            if (!marked.contains(e)) {
                int v = e.either();
                if (!path.contains(v))
                    v = e.other(v);

                if (path.contains(v)) {
                    // Edge e has a point on the path.
                    List<Integer> newLoop = walk(g, e, v, v);
                    if (newLoop == null)
                        return null;

                    path.addAll(path.indexOf(v) + 1, newLoop);
                    
                    return path;
                }
            }
        }

        return null;
    }

    /**
     * Identify a path from 'from' to 'target' using edge 'e' as the first edge.
     * 
     * Returns null if the path can't be found.
     * 
     * @param g
     * @param e
     * @param from
     * @param target
     * @return 
     */
    private List<Integer> walk(EdgeWeightedGraph g, Edge e, int from, int target) {

        marked.add(e);
        int end = e.other(from);

        // Completed the cycle
        if (end == target)
            return newPath(end);

        for (Edge outEdge : g.adj(end)) {
            if (!marked.contains(outEdge)) {

                // Got a way out from this end
                List<Integer> path = walk(g, outEdge, end, target);

                // No way to complete the cycle
                if (path == null)
                    return null;

                path.add(0, end);
                return path;
            }
        }

        // No way out from 'end' vertex.
        return null;

    }

    /**
     * Create a new single-vertex path.
     * 
     * @param v
     * @return
     */
    private List<Integer> newPath(int v) {
        List<Integer> path = new ArrayList<Integer>();
        path.add(v);
        return path;
    }

    /**
     * @return whether the graph has an eulerian path or not.
     */
    public boolean hasEulerianCycle() {
        return hasEulerianCycle;
    }

    /**
     * @return the eulerian path if exists, else null
     */
    public List<Integer> eulerianCycle() {
        if (hasEulerianCycle)
            return cycle;
        else
            return null;
    }

}
