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
public class EulerianPath {

	public boolean hasEulerianPath;

	public List<Integer> path;
	public List<Edge> marked;

	public EulerianPath(EdgeWeightedGraph g) {
		if (g.E() > 0) {
			marked = new ArrayList<Edge>();
			Edge e = g.edges().iterator().next();

			int start = e.either();

			path = walk(g, e, start);
			path.add(start);

			hasEulerianPath = (marked.size() == g.E());
		}
	}

	private List<Integer> walk(EdgeWeightedGraph g, Edge e, int from) {

		marked.add(e);

		int end = e.other(from);

		for (Edge out : g.adj(end)) {
			if (!marked.contains(out)) {
				
				// Got a way out from this end
				path = walk(g, out, end);
				path.add(end);
				return path;
			}
		}

		// No way out.
		List<Integer> path = new ArrayList<Integer>();
		path.add(end);
		return path;

	}

	/**
	 * @return whether the graph has an eulerian path or not.
	 */
	public boolean hasEulerianPath() {
		return hasEulerianPath;
	}

	/**
	 * @return the eulerian path if exists, else null
	 */
	public Iterable<Integer> eulerianPath() {
		if (hasEulerianPath)
			return path;
		else
			return null;
	}

}
