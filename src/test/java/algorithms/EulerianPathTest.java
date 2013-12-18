package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import algorithms.api.Edge;
import algorithms.api.Graph;

public class EulerianPathTest {

    @Test
    public void test_has_euler_path() {
        Graph g = new Graph(asStream("EulerPathWeighted_small"));
        assertEquals(10, g.n());
        assertEquals(12, g.edges().size());

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertTrue(eulerianPath.hasEulerianCycle());
    }

    @Test
    public void test_euler_path_negative() {
        Graph g = new Graph(asStream("EulerPathWeighted_small"));
        g.addEdge(new Edge(5, 4));

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertFalse(eulerianPath.hasEulerianCycle());
    }

    @Test
    public void test_euler_with_orphans() {
        Graph g = new Graph(asStream("EulerPath_with_orphans"));

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertTrue(eulerianPath.hasEulerianCycle());
    }

    @Test
    public void test_euler_disconnected() {
        Graph g = new Graph(asStream("EulerPath_with_orphans"));

        // Connect orphan edges to get disconnected graph.
        g.addEdge(new Edge(10, 11));

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertFalse(eulerianPath.hasEulerianCycle());
    }

    @Test
    public void test_euler_path() {
        Graph g = new Graph(asStream("EulerPathWeighted_small"));
        assertEquals(10, g.n());
        assertEquals(12, g.edges().size());

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertNotNull(eulerianPath.eulerianCycle());

    }

    @Test
    public void test_euler_path2() {
        Graph g = new Graph(asStream("EulerPathWeighted2_small"));

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertNotNull(eulerianPath.eulerianCycle());
    }

    @Test
    public void test_euler_path_3v() {
        Graph g = new Graph(asStream("EulerPath_3v.txt"));
        assertEquals(3, g.n());
        assertEquals(3, g.edges().size());

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertTrue(eulerianPath.hasEulerianCycle());

        List<Integer> eulerCycle = eulerianPath.eulerianCycle();

        assertNotNull(eulerCycle);
        assertEquals(4, eulerCycle.size());
    }

    @Test
    public void test_euler_path_5v() {
        Graph g = new Graph(asStream("EulerPath_5v.txt"));

        EulerianCycle eulerianPath = new EulerianCycle(g);
        assertTrue(eulerianPath.hasEulerianCycle());

        List<Integer> eulerCycle = eulerianPath.eulerianCycle();

        assertNotNull(eulerCycle);
        assertEquals(7, eulerCycle.size());
    }

    @Test
    public void test_euler_path_4v_neg() {
        Graph g = new Graph(asStream("EulerPath_4v_neg.txt"));

        EulerianCycle eulerianPath = new EulerianCycle(g);

        assertFalse(eulerianPath.hasEulerianCycle());
        assertNull(eulerianPath.eulerianCycle());
    }

    private InputStream asStream(String filename) {
        return this.getClass().getClassLoader().getResourceAsStream(filename);
    }

}
