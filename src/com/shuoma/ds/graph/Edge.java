package com.shuoma.ds.graph;

public abstract class Edge {
	public Node from;
	public Node to;
	
	/**
	 * For graph traversal algorithms
	 */
	public static enum STATUS{
		UNVISITED, VISITED, CROSSED;
	}
	public STATUS status;
	
	
	public Node opposite(Node one){
		if(one.equals(from)) return to;
		if(one.equals(to)) return from;
		return null;
	}
}
