package algorithms.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Graph representation.
 * 
 * @author anoopelias
 * 
 */
public class Graph {

    private int n;

    private List<Set<Edge>> adjacencyList;

    /**
     * Initialize graph with the number of vertices.
     * 
     * @param n
     */
    public Graph(int n) {
        this.n = n;
        init();
    }

    private void init() {
        adjacencyList = new ArrayList<Set<Edge>>();
        for (int i = 0; i < this.n; i++) {
            adjacencyList.add(new HashSet<Edge>());
        }
    }

    /**
     * Initialize graph from an input stream.
     * 
     * @param is
     * @throws NumberFormatException
     * @throws IOException
     */
    public Graph(InputStream is) throws NumberFormatException, IOException {

        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new InputStreamReader(is));
            this.n = Integer.parseInt(reader.readLine());
            init();

            int c = Integer.parseInt(reader.readLine());
            for (int i = 0; i < c; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                addEdge(new Edge(from, to));
            }

        } finally {
            if (reader != null)
                reader.close();
        }

    }

    /**
     * Number of indices on the graph.
     * 
     * @return
     */
    public int n() {
        return n;
    }

    /**
     * Get all the adjacent edges of a given vertex.
     * 
     * @param v
     * @return
     */
    public Set<Integer> adj(int v) {
        Set<Integer> adjV = new HashSet<>();
        for(Edge e : adjacencyList.get(v))
            adjV.add(e.other(v));
        
        return adjV;
    }

    /**
     * Get all the edges.
     * 
     * @return
     */
    public Set<Edge> edges() {
        Set<Edge> allEdges = new HashSet<Edge>();

        // All duplicate edges will be eliminated as we are adding it to a set.
        for (Set<Edge> s : adjacencyList)
            allEdges.addAll(s);

        return allEdges;
    }

    /**
     * Add an edge to the graph.
     * 
     * @param e
     * @return
     */
    public boolean addEdge(Edge e) {
        int from = e.either();
        int to = e.other(from);

        adjacencyList.get(from).add(e);
        return adjacencyList.get(to).add(e);
    }

    /**
     * Print the graph to console.
     * 
     */
    public void print() {
        System.out.println(n);
        Set<Edge> edges = edges();
        System.out.println(edges.size());
        for (Edge e : edges) {
            System.out.println(e.either() + " " + e.other(e.either()));
        }
    }

}
