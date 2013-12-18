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
 * Digraph representation.
 * 
 * @author Anoop Elias
 *
 */
public class Digraph {
    
    private int n;

    private List<Set<DirectedEdge>> adjacencyList;

    /**
     * Initialize graph with the number of vertices.
     * 
     * @param n
     */
    public Digraph(int n) {
        this.n = n;
        init();
    }

    private void init() {
        adjacencyList = new ArrayList<Set<DirectedEdge>>();
        for (int i = 0; i < this.n; i++) {
            adjacencyList.add(new HashSet<DirectedEdge>());
        }
    }

    /**
     * Initialize graph from an input stream.
     * 
     * @param is
     */
    public Digraph(InputStream is) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            this.n = Integer.parseInt(reader.readLine());
            init();

            int c = Integer.parseInt(reader.readLine());
            for (int i = 0; i < c; i++)
                addEdge(reader.readLine());

        } catch(NumberFormatException e) {
            throw new InvalidFileException(e);
        } catch(IOException e) {
            throw new InvalidFileException(e);
        }

    }
    
    private boolean addEdge(String edgeStr) {
        StringTokenizer st = new StringTokenizer(edgeStr);
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        
        return addEdge(new DirectedEdge(from, to));
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
    public Set<DirectedEdge> adj(int v) {
        return adjacencyList.get(v);
    }

    
    /**
     * Add an edge to the graph.
     * 
     * @param e
     * @return
     */
    public boolean addEdge(DirectedEdge e) {
        return adjacencyList.get(e.from()).add(e);
    }

    /**
     * Get all the edges.
     * 
     * @return
     */
    public Set<DirectedEdge> edges() {
        Set<DirectedEdge> allEdges = new HashSet<DirectedEdge>();

        // All duplicate edges will be eliminated as we are adding it to a set.
        for (Set<DirectedEdge> s : adjacencyList)
            allEdges.addAll(s);

        return allEdges;
    }

    /**
     * Print the graph to console.
     * 
     */
    public void print() {
        System.out.println(n);
        Set<DirectedEdge> edges = edges();
        System.out.println(edges.size());
        for (DirectedEdge e : edges) {
            System.out.println(e.from() + " " + e.to());
        }
    }


}
