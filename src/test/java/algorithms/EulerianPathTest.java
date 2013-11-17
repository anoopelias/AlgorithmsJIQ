package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.introcs.In;

public class EulerianPathTest {

	private static final String TEST_RESOURCES = "src/test/resources/";

	@Test
	public void test_has_euler_path() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted_small")));
		assertEquals(10, g.V());
		assertEquals(14, g.E());

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertTrue(eulerianPath.hasEulerianCycle());
	}

	@Test
	public void test_euler_path_negative() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted_small")));
		g.addEdge(new Edge(5, 4, 1));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertFalse(eulerianPath.hasEulerianCycle());
	}

	@Test
	public void test_euler_with_orphans() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_with_orphans")));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertTrue(eulerianPath.hasEulerianCycle());
	}

	@Test
	public void test_euler_disconnected() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_with_orphans")));

		// Connect orphan edges to get disconnected graph.
		g.addEdge(new Edge(10, 11, 1));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertFalse(eulerianPath.hasEulerianCycle());
	}

	@Test
	public void test_euler_path() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted_small")));
		assertEquals(10, g.V());
		assertEquals(14, g.E());

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertNotNull(eulerianPath.eulerianCycle());
		
	}

	@Test
	public void test_euler_path2() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted2_small")));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertNotNull(eulerianPath.eulerianCycle());
		
		for(int i : eulerianPath.eulerianCycle()) {
			System.out.print(" : " + i );
		}
	}
	
	@Test
	public void test_euler_path_3v() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_3v.txt")));
		assertEquals(3, g.V());
		assertEquals(3, g.E());

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertTrue(eulerianPath.hasEulerianCycle());

		List<Integer> eulerCycle = eulerianPath.eulerianCycle(); 
		
		assertNotNull(eulerCycle);
		assertEquals(4, eulerCycle.size());
	}

	@Test
	public void test_euler_path_5v() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_5v.txt")));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		assertTrue(eulerianPath.hasEulerianCycle());

		List<Integer> eulerCycle = eulerianPath.eulerianCycle(); 
		
		assertNotNull(eulerCycle);
		assertEquals(7, eulerCycle.size());
	}

	@Test
	public void test_euler_path_4v_neg() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_4v_neg.txt")));

		EulerianCycle eulerianPath = new EulerianCycle(g);
		
		assertFalse(eulerianPath.hasEulerianCycle());
		assertNull(eulerianPath.eulerianCycle());
	}
	
}
