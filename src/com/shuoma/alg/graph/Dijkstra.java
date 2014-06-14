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
	 * return all shortest paths; null if not find
	 * @param g
	 * @param end: target node; if null, then traverse 
	 */
	public ArrayList<ArrayList<Node>> find(Graph g, Node start, Node end){
		if(verbose){
			System.out.println("**** Dijkstra Searching Illustration ****");
		}
		
		if(start==null||end==null) return new ArrayList<ArrayList<Node>>();

		PriorityQueue<Node> pq=new PriorityQueue<Node>();
		start.dis=0;
		start.status=Node.STATUS.VISITED;
		pq.add(start);
		
		int lvl=0;
		while(pq.size()>0){
			Node cur=pq.poll();
			if(cur.equals(end)) 
				break;
			
			cur.status=Node.STATUS.EXPANED;
			if(verbose){
				System.out.println("level "+lvl+" :  pos:"+cur+" , value:"+cur.value+",  dis:"+cur.dis);
			}
			for(Edge e: cur.adjacentList){
				if(e.status==Edge.STATUS.UNVISITED){
					Node oppo=e.opposite(cur);
					
					double newDist=oppo.value+cur.dis;
					if(newDist<=oppo.dis){
						if(newDist<oppo.dis){
							pq.remove(oppo);
							oppo.dis=newDist;
							pq.add(oppo);
							oppo.prevs.clear();//remove old prevs
						}
						oppo.prevs.add(cur);
					}
						
				}
			}
			lvl++;
		}
		
		ArrayList<Node> path=new ArrayList<Node>();
		path.add(end);
		return g.buildAllPaths(start, end, path);
	}
	

	
	public void traverse(Graph g, Node start){
		find(g, start, null);
	}
}
