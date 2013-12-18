package algorithms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import algorithms.api.Graph;

public class TreeTest {

    @Test
    public void test_diameter() throws NumberFormatException, IOException {
        Graph graph = new Graph(this.getClass().getClassLoader()
                .getResourceAsStream("diameter_small.txt"));
        
        assertEquals(10, graph.n());
        assertEquals(7, graph.edges().size());

        Tree tree = new Tree(graph);
        assertEquals(4, tree.diameter());
        assertEquals(8, tree.center());

    }

}
