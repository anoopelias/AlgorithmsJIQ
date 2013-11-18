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
            cycle = expandCycle(g, new ArrayList<Integer>());

            if (cycle != null)
                cycle.add(start);

            hasEulerianCycle = (cycle!= null && cycle.size() == (g.E() + 1));
        }
    }

    private List<Integer> expandCycle(EdgeWeightedGraph g, List<Integer> cycle) {
        for (Edge e : g.edges()) {

            if (!marked.contains(e)) {
                int v = e.either();
                if (!cycle.contains(v))
                    v = e.other(v);

                // If Edge e has a point on the cycle OR cycle doesn't exist.
                if (cycle.isEmpty() || cycle.contains(v)) {
                    List<Integer> newLoop = cycle(g, e, v);
                    if (newLoop == null)
                        continue;

                    cycle.addAll(cycle.indexOf(v) + 1, newLoop);
                    
                    return expandCycle(g, cycle);
                }
            }
        }

        // No expansion of cycle possible
        return cycle;
    }
    
    /**
     * Find a cycle on graph 'g' which starts at vertex v towards edge e.
     * 
     * @param g
     * @param e
     * @param v
     * @return
     */
    private List<Integer> cycle(EdgeWeightedGraph g, Edge e, int v) {
    	return walk(g, e, v, v);
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

        // Reached the target
        if (end == target)
            return newPath(end);

        for (Edge out : g.adj(end)) {
            if (!marked.contains(out)) {
                // Got a way out from this end
                List<Integer> path = walk(g, out, end, target);

                // A non-null path means we found a way to reach target.
                if (path != null) {
	                path.add(0, end);
	                return path;
                }
            }
        }

        // No way to reach 'target' through this edge.
        marked.remove(e);
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
