package com.shuoma.alg.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.shuoma.ds.graph.Edge;
import com.shuoma.ds.graph.Graph;
import com.shuoma.ds.graph.Node;

public class DFS {
	public ArrayList<Node> path=new ArrayList<Node>();
	public static final boolean verbose=true;
	/**
	 * return null if not find
	 * @param g
	 * @param end: target node; if null, then traverse 
	 */
	public Node find(Graph g, Node start, Node end, int depthLimit){
		if(verbose){
			System.out.println("**** DFS Searching Illustration ****");
			if(depthLimit!=Integer.MAX_VALUE) System.out.println("Depth Limit: "+depthLimit);
		}
		if(start==null||start.equals(end)) return start;
		Stack<Node> stack=new Stack<Node>();
		start.status=Node.STATUS.VISITED;
		stack.push(start);
		
		int lvl=0;
		while(stack.size()>0){
			if(lvl>depthLimit) return null;
			Node cur=stack.pop();
			if(verbose){
				System.out.println("level "+lvl+" : "+cur);
			}
			cur.status=Node.STATUS.EXPANED;
			for(Edge e: cur.adjacentList){
				if(e.status==Edge.STATUS.UNVISITED){
					Node oppo=e.opposite(cur);
					if(oppo.status==Node.STATUS.UNVISITED){
						e.status=Edge.STATUS.VISITED;
						oppo.status=Node.STATUS.VISITED;
						oppo.prevs.add(cur);
					
						if(oppo.equals(end)) return oppo;
						stack.add(oppo);
					}else if(oppo.status==Node.STATUS.VISITED){
						e.status=Edge.STATUS.CROSSED;
					}
				}
			}
			lvl++;
		}
		
		return null;
	}
	
	public Node find(Graph g, Node start, Node end){
		return find(g, start, end, Integer.MAX_VALUE);
	}
	
	public void traverse(Graph g, Node start){
		find(g, start, null);
	}
}
