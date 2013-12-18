package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.api.Digraph;
import algorithms.api.DirectedEdge;

/**
 * Shortest directed cycle. Given a digraph G, design an efficient algorithm to
 * find a directed cycle with the minimum number of edges (or report that the
 * graph is acyclic). The running time of your algorithm should be at most
 * proportional to V(E + V) and use space proportional to E + V, where V is the
 * number of vertices and E is the number of edges.
 * 
 * @author Anoop Elias
 * 
 */
public class DirectedCycle {

    Digraph g = null;
    Deque<Integer> q = null;
    Map<Integer, Integer> edgeTo = null;

    List<Integer> cycle = null;

    /**
     * Idea : For each vertex 
     *  1. Find the smallest cycle (if at all), starting from that vertex.
     *  2. Find the smallest of all these smallest.
     * 
     * @param g
     */
    public DirectedCycle(Digraph g) {
        this.g = g;

        for (int start = 0; start < g.n(); start++) {
            q = new ArrayDeque<>();
            edgeTo = new HashMap<>();

            visit(start);
            while (!q.isEmpty()) {
                int v = q.pollFirst();

                if (v == start)
                    break;

                visit(v);
            }

            List<Integer> cycle = drain(start);

            if (cycle.size() > 0
                    && (this.cycle == null || this.cycle.size() > cycle.size()))
                this.cycle = cycle;

        }

    }

    private List<Integer> drain(int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        if (edgeTo.containsKey(start)) {
            stack.addFirst(start);

            int v = edgeTo.get(start);
            while (v != start) {
                stack.addFirst(v);
                v = edgeTo.get(v);
            }

            stack.addFirst(start);
        }

        List<Integer> cycle = new ArrayList<Integer>();
        cycle.addAll(stack);

        return cycle;
    }

    private void visit(int v) {
        for (DirectedEdge e : g.adj(v)) {
            if (!edgeTo.containsKey(e.to())) {
                q.addLast(e.to());
                edgeTo.put(e.to(), v);
            }
        }
    }

    /**
     * Get the smallest cycle on the graph.
     * 
     * @return the vertices to be visited in order. 'null' if the graph is acyclic.
     */
    public List<Integer> cycle() {
        return cycle;
    }
}
