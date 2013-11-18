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
	public EdgeWeightedGraph g;

	/**
	 * We use EdgeWeightedGraph so that the edges are available as an object.
	 * The weights on the edges are unused in this implementation.
	 * 
	 * The algorithm is like this,
	 * 
	 * 1. Find a cycle from any arbitrary point on any edge. 
	 * 2. On each vertex of the cycle, (recursively,) 
	 *     a. Try to find another cycle and 
	 *     b. Append to the original cycle.
	 * 
	 * 
	 * @param g graph
	 */
	public EulerianCycle(EdgeWeightedGraph g) {
		if (g.E() > 0) {
			this.g = g;
			marked = new ArrayList<Edge>();

			// Find a vertex on an edge. Any arbitrary vertex might end up being
			// a non-connected vertex.
			int v = g.edges().iterator().next().either();

			cycle = cycle(v, v);

			hasEulerianCycle = (marked.size() == g.E());
		}
	}

	/**
	 * Find an Eulerian cycle with vertex 'v' continuing at 'at'.
	 * 
	 * TODO: The 'contains' operation here is linear which would increase the
	 * algorithm complexiety from O(E + V) to O( E^2 + V).
	 * 
	 * Ideally edges should be indexed and 'marked' should be an array.
	 * 
	 * @param v
	 * @param at
	 * @return
	 */
	private List<Integer> cycle(int v, int at) {

		for (Edge out : g.adj(at)) {
			if (!marked.contains(out)) {

				// Got a way out from 'at'
				marked.add(out);

				// Found v at the other end of 'out' edge.
				if (v == out.other(at)) {
					List<Integer> path = newPath(v);
					addToPath(path, at);
					return path;
				}

				// Continue with the cycle.
				List<Integer> path = cycle(v, out.other(at));

				// A non-null path means we found a way to reach v back.
				if (path != null) {
					addToPath(path, at);
					return path;
				}

				// There is no way we can get an Euilerian cycle if we leave
				// this edge out, so,
				marked.remove(out);
				break;
			}
		}

		// No way out from 'at'.
		return null;

	}

	/**
	 * Add the vertex v to the path. Since 'v' is safely added to the cycle, we
	 * will try to find if another cycle exists with 'v' as the vertex.
	 * 
	 * @param path
	 * @param v
	 */
	private void addToPath(List<Integer> path, int v) {
		List<Integer> subPath = cycle(v, v);

		if (subPath != null && subPath.size() > 0) {

			// Removing the first element as the first and last element will be
			// the same.
			subPath.remove(0);
			path.addAll(0, subPath);
		}

		path.add(0, v);
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
