package algorithms.api;

/**
 * Directed edge representation.
 * 
 * @author Anoop Elias
 *
 */
public class DirectedEdge {
    
    private int from;
    private int to;
    
    public DirectedEdge(int from, int to) {
        this.from = from;
        this.to = to;
    }
    
    public int from() {
        return from;
    }
    
    public int to() {
        return to;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + from;
        result = prime * result + to;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DirectedEdge other = (DirectedEdge) obj;
        if (from != other.from)
            return false;
        if (to != other.to)
            return false;
        return true;
    }
    
}
