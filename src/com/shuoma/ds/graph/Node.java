package com.shuoma.ds.graph;

import java.util.ArrayList;
import java.util.List;


public abstract class Node implements Comparable<Node> {
	public String id;
	public ArrayList<Edge> adjacentList;
	
	public Node(){
	}
	
	/**
	 * Fields for traversal algorithm
	 */
	public static enum STATUS{
		UNVISITED, VISITED, EXPANED;
	}
	
	public STATUS status;
	public List<Node> prevs;
	
	public void reset(){
		status=STATUS.UNVISITED;
		for(Edge e:adjacentList) e.status=Edge.STATUS.UNVISITED;
		prevs=new ArrayList<Node>();
	}
	
	/**
	 * Shortest path aglorithm
	 */
	public double value;	
	public double dis;
	
	public int compareTo(Node other){
		double diff=dis-other.dis;
		if(diff<0) return -1;
		else if(diff>0) return 1;
		else return 0;
	}
	
}
