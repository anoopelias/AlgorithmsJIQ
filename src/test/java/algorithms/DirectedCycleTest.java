package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Test;

import algorithms.DirectedCycle;
import algorithms.api.Digraph;

/**
 * Test cases for directed cycle.
 * 
 * @author Anoop Elias.
 *
 */
public class DirectedCycleTest {
    
    @Test
    public void test_3v() {
        Digraph g = new Digraph(asStream("DirectedCycle_3v"));
        assertEquals(3, g.n());
        assertEquals(3, g.edges().size());
        
        DirectedCycle dc = new DirectedCycle(g);
        assertEquals(4, dc.cycle().size());
    }

    @Test
    public void test_4v() {
        Digraph g = new Digraph(asStream("DirectedCycle_4v"));
        
        DirectedCycle dc = new DirectedCycle(g);
        assertEquals(4, dc.cycle().size());
    }

    @Test
    public void test_3v_neg() {
        Digraph g = new Digraph(asStream("DirectedCycle_3v_neg"));
        
        DirectedCycle dc = new DirectedCycle(g);
        assertNull(dc.cycle());
    }

    @Test
    public void test_2cycles() {
        Digraph g = new Digraph(asStream("DirectedCycle_2Cycles"));
        
        DirectedCycle dc = new DirectedCycle(g);
        assertEquals(4, dc.cycle().size());
    }

    @Test
    public void test_2cycles_separate() {
        Digraph g = new Digraph(asStream("DirectedCycle_2Cycles_Separated"));
        
        DirectedCycle dc = new DirectedCycle(g);
        assertEquals(4, dc.cycle().size());
    }

    @Test
    public void test_large() {
        Digraph g = new Digraph(asStream("Directed_3000x6000.txt"));
        
        long time = System.nanoTime();
        DirectedCycle dc = new DirectedCycle(g);
        assertEquals(5, dc.cycle().size());
        
        assertTrue(System.nanoTime() - time < 5_000_000_000l);
    }

    @Test
    public void test_large_sparse() {
        Digraph g = new Digraph(asStream("Directed_3000x500.txt"));
        
        DirectedCycle dc = new DirectedCycle(g);
        assertNull(dc.cycle());
    }

    private InputStream asStream(String filename) {
        return this.getClass().getClassLoader().getResourceAsStream(filename);
    }

}
