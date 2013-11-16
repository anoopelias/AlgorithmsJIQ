package algorithms;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.introcs.In;


public class TreeTest {
	
	@Test
	public void test_diameter() {
		Graph graph = new Graph(new In(new File("src/test/resources/diameter_small.txt")));
		assertEquals(10, graph.V());
		assertEquals(7, graph.E());
		
		Tree tree = new Tree(graph);
		assertEquals(4, tree.diameter());
		assertEquals(8, tree.center());
		
	}

}
