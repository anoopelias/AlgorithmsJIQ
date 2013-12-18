package algorithms;

import java.util.ArrayList;
import java.util.List;

import algorithms.api.Graph;

/**
 * 
 * Diameter and center of a tree.
 * 
 * Given a connected graph with no cycles,
 * 
 * Diameter: design a linear-time algorithm to find the longest simple path in
 * the graph.
 * 
 * Center: design a linear-time algorithm to find a vertex such that its maximum
 * distance from any other vertex is minimized.
 * 
 * @author Anoop Elias
 * 
 */
public class Tree {

	private int[] distTo;
	private boolean[] visited;

	private int diameter;

	private int center;

	/**
	 * Initialize a tree object.
	 * 
	 * Assumption : g is a valid tree. No validation.
	 * 
	 * @param g
	 *            - Graph object
	 */
	public Tree(Graph g) {
		/*
		 * TODO: Need to verify if g is really a tree.
		 */

		if (g.edges().size() > 0) {

			distTo = new int[g.n()];
			visited = new boolean[g.n()];

			/*
			 * Find the farthest from any arbitrary vertex. We can assume that
			 * this point will be one end of the longest path in the tree.
			 */
			int start = farthest(g, 0, 0);

			visited = new boolean[g.n()];

			/*
			 * Find the longest path from the starting vertex.
			 */
			int end = farthest(g, start, 0);

			visited = new boolean[g.n()];
			
			/*
			 * Find the path from start to end.
			 * 
			 */
			List<Integer> longestPath = pathTo(g, start, end);

			diameter = longestPath.size() - 1;
			center = longestPath.get(longestPath.size() / 2);

		}
	}

	private List<Integer> pathTo(Graph g, int from, int to) {

		visited[from] = true;

		if (from == to) {
			List<Integer> path = new ArrayList<Integer>();
			path.add(to);
			return path;
		}

		for (int w : g.adj(from)) {
			if (!visited[w]) {
				List<Integer> path = pathTo(g, w, to);
				if (path != null) {
					path.add(from);
					return path;
				}
			}
		}

		return null;
	}

	private int farthest(Graph g, int v, int d) {
		visited[v] = true;
		distTo[v] = d;
		int far = v;

		for (int w : g.adj(v)) {
			if (!visited[w]) {
				int f = farthest(g, w, d + 1);

				if (distTo[f] > distTo[far])
					far = f;
			}
		}

		return far;
	}

	/**
	 * @return diameter of the tree.
	 */
	public int diameter() {
		return diameter;
	}

	/**
	 * @return center of the tree.
	 */
	public int center() {
		return center;
	}

}
