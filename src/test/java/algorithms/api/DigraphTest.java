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


public class DigraphTest {
    
	//@Test
    /*
     * Use this method to generate a Digraph randomly.
     */
    public void test_generate_Digraph() {
		
    	int n = 5000;
    	int c = 30000;
    	
        Digraph g = new Digraph(n);
        
        List<DirectedEdge> allDirectedEdges = new ArrayList<DirectedEdge>();
        
        for(int i = 0; i<n; i++) {
        	for(int j=i+1; j<n; j++) {
        		allDirectedEdges.add(new DirectedEdge(i, j));
        	}
        }

        Collections.shuffle(allDirectedEdges);
        
        for(DirectedEdge e : allDirectedEdges.subList(0, c)) {
        	g.addEdge(e);
        }
        
        g.print();
        
    }
    
    @Test
    public void test_read_Digraph() throws NumberFormatException, IOException {
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("api/10x20.txt");
    	Digraph g = new Digraph(is);
    	assertEquals(10, g.n());
    }
    
    @Test
    public void test_adj() throws NumberFormatException, IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("api/10x20.txt");
        Digraph g = new Digraph(is);
        Set<DirectedEdge> adjs = g.adj(0);
        assertEquals(3, adjs.size());
        assertTrue(adjs.contains(new DirectedEdge(0, 6)));
        assertTrue(adjs.contains(new DirectedEdge(0, 8)));
        assertTrue(adjs.contains(new DirectedEdge(0, 1)));
    }
	
}
