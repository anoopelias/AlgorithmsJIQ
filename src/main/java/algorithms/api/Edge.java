package algorithms.api;

/**
 * Edge data structure.
 * 
 * @author anoopelias
 *
 */
public class Edge {
	
	private int from;
	private int to;
	
	/**
	 * Initialize Edge with a from and to.
	 * 
	 * @param from
	 * @param to
	 */
	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//TODO : Is there a better way?
		result = prime * result + from + to;
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
		Edge other = (Edge) obj;
		
		// Edge is undirected. So edge A-B is same as edge B-A
		if(
				(from == other.from && to == other.to) ||
				(from == other.to && to == other.from)
				)
			return true;
		
		return false;
	}
	
	/**
	 * To get any one vertex on the edge.
	 * 
	 * @return any one of the vertices on the edge.
	 */
	public int either() {
		return from;
	}
	
	/**
	 * To get the other vertex on the edge given one.
	 * 
	 * @param v
	 * @return 
	 */
	public int other(int v) {
		if(from == v)
			return to;
		
		return from;
	}

}
