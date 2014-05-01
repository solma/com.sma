package com.shuoma.alg.graph;

import java.util.ArrayList;
import java.util.LinkedList;

import sun.net.www.content.text.plain;

import com.shuoma.ds.graph.Edge;
import com.shuoma.ds.graph.Graph;
import com.shuoma.ds.graph.Node;
import com.shuoma.ds.graph.Edge.STATUS;

public class BFS {
	public ArrayList<Node> path=new ArrayList<Node>();
	public static final boolean verbose=true;
	/**
	 * return null if not find
	 * @param g
	 * @param end: target node; if null, then traverse 
	 */
	public Node find(Graph g, Node start, Node end){
		if(verbose){
			System.out.println("**** BFS Searching Illustration ****");
		}
		if(start==null||start.equals(end)) return start;
		LinkedList<Node> curLvl=new LinkedList<Node>();
		start.status=Node.STATUS.VISITED;
		curLvl.add(start);
		
		int lvl=0;
		while(curLvl.size()>0){
			if(verbose){
				System.out.println("level "+lvl+" : "+curLvl);
			}
			LinkedList<Node> nextLvl=new LinkedList<Node>();
			while(curLvl.size()>0){
				Node cur=curLvl.poll();
				cur.status=Node.STATUS.EXPANED;
				for(Edge e: cur.adjacentList){
					if(e.status==Edge.STATUS.UNVISITED){
						Node oppo=e.opposite(cur);
						if(oppo.status==Node.STATUS.UNVISITED){
							e.status=STATUS.VISITED;
							oppo.status=Node.STATUS.VISITED;
							oppo.prev=cur;
							if(oppo.equals(end)) return oppo;
							nextLvl.add(oppo);
						}else if(oppo.status==Node.STATUS.VISITED){
							e.status=Edge.STATUS.CROSSED;
							if(verbose){
								System.out.print("Cycle Detected : ");
								g.printPath(g.buildPath(oppo, cur),true);
								System.out.println(" --> "+ oppo.value);
							}
						}
						
					}
				}
			}
			
			curLvl=nextLvl;
			lvl++;
		}
		return null;
	}
	
	public void traverse(Graph g, Node start){
		find(g, start, null);
	}
}
