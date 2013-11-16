package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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

		EulerianPath eulerianPath = new EulerianPath(g);
		assertTrue(eulerianPath.hasEulerianPath());
	}

	@Test
	public void test_euler_path_negative() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted_small")));
		g.addEdge(new Edge(5, 4, 1));

		EulerianPath eulerianPath = new EulerianPath(g);
		assertFalse(eulerianPath.hasEulerianPath());
	}

	@Test
	public void test_euler_with_orphans() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_with_orphans")));

		EulerianPath eulerianPath = new EulerianPath(g);
		assertTrue(eulerianPath.hasEulerianPath());
	}

	@Test
	public void test_euler_disconnected() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPath_with_orphans")));

		// Connect orphan edges to get disconnected graph.
		g.addEdge(new Edge(10, 11, 1));

		EulerianPath eulerianPath = new EulerianPath(g);
		assertFalse(eulerianPath.hasEulerianPath());
	}

	@Test
	public void test_euler_path() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted_small")));
		assertEquals(10, g.V());
		assertEquals(14, g.E());

		EulerianPath eulerianPath = new EulerianPath(g);
		assertNotNull(eulerianPath.eulerianPath());
		
		for(int i : eulerianPath.eulerianPath()) {
			System.out.print(" : " + i );
		}
		
		System.out.println();
	}

	@Test
	public void test_euler_path2() {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(
				TEST_RESOURCES + "EulerPathWeighted2_small")));
		assertEquals(10, g.V());
		assertEquals(14, g.E());

		EulerianPath eulerianPath = new EulerianPath(g);
		assertNotNull(eulerianPath.eulerianPath());
		
		for(int i : eulerianPath.eulerianPath()) {
			System.out.print(" : " + i );
		}
	}

	
}
