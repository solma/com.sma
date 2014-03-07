package com.shuoma.alg.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.shuoma.ds.graph.Edge;
import com.shuoma.ds.graph.Graph;
import com.shuoma.ds.graph.Node;

public class Dijkstra {
	public ArrayList<Node> path=new ArrayList<Node>();
	public static final boolean verbose=true;
	/**
	 * return null if not find
	 * @param g
	 * @param end: target node; if null, then traverse 
	 */
	public Node find(Graph g, Node start, Node end){
		if(verbose){
			System.out.println("**** Dijkstra Searching Illustration ****");
		}
		if(start==null||start.equals(end)) return start;
		PriorityQueue<Node> pq=new PriorityQueue<Node>();
		start.dis=0;
		start.status=Node.STATUS.VISITED;
		pq.add(start);
		
		int lvl=0;
		while(pq.size()>0){
			Node cur=pq.poll();
			cur.status=Node.STATUS.EXPANED;
			if(verbose){
				System.out.println("level "+lvl+" :  pos:"+cur+" , value:"+cur.value+",  dis:"+cur.dis);
			}
			for(Edge e: cur.adjacentList){
				if(e.status==Edge.STATUS.UNVISITED){
					Node oppo=e.opposite(cur);
					if(oppo.status==Node.STATUS.UNVISITED){
						oppo.status=Node.STATUS.VISITED;
						oppo.prev=cur;
						if(oppo.equals(end)) return oppo;
						double newDist=oppo.value+cur.dis;
						if(oppo.dis>newDist){
							pq.remove(oppo);
							oppo.dis=newDist;
							pq.add(oppo);
						}
						
					}
				}
			}
			lvl++;
		}
		return null;
	}
	
	public void traverse(Graph g, Node start){
		find(g, start, null);
	}
}
