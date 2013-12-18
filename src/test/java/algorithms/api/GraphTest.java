package algorithms.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class GraphTest {
    
	//@Test
    /*
     * Use this method to generate a graph randomly.
     */
    public void test_generate_graph() {
		
    	int n = 5000;
    	int c = 30000;
    	
        Graph g = new Graph(n);
        
        List<Edge> allEdges = new ArrayList<Edge>();
        
        for(int i = 0; i<n; i++) {
        	for(int j=i+1; j<n; j++) {
        		allEdges.add(new Edge(i, j));
        	}
        }

        Collections.shuffle(allEdges);
        
        for(Edge e : allEdges.subList(0, c)) {
        	g.addEdge(e);
        }
        
        g.print();
        
    }
    
    @Test
    public void test_read_graph() throws NumberFormatException, IOException {
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("api/10x20.txt");
    	Graph g = new Graph(is);
    	assertEquals(10, g.n());
    }
    
    @Test
    public void test_adj() throws NumberFormatException, IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("api/10x20.txt");
        Graph g = new Graph(is);
        Set<Edge> adjs = g.adj(0);
        assertEquals(4, adjs.size());
        assertTrue(adjs.contains(new Edge(0, 5)));
        assertTrue(adjs.contains(new Edge(0, 6)));
        assertTrue(adjs.contains(new Edge(0, 8)));
        assertTrue(adjs.contains(new Edge(0, 1)));
    }
	
}
