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
	 * return all paths; and null if not find
	 * @param g
	 * @param end: target node; if null, then traverse 
	 */
	public ArrayList<ArrayList<Node>> find(Graph g, Node start, Node end){
		if(verbose){
			System.out.println("**** BFS Searching Illustration ****");
		}
		if(start==null||end==null) return new ArrayList<ArrayList<Node>>();
		
		LinkedList<Node> curLvl=new LinkedList<Node>();
		start.status=Node.STATUS.VISITED;
		start.dis=0;
		curLvl.add(start);
		
		int lvl=0;
		while(curLvl.size()>0){
			if(verbose){
				System.out.println("level "+lvl+" : "+curLvl);
			}
			LinkedList<Node> nextLvl=new LinkedList<Node>();
			while(curLvl.size()>0){
				Node cur=curLvl.poll();
				if(cur.equals(end)) break;
				
				cur.status=Node.STATUS.EXPANED;
				for(Edge e: cur.adjacentList){
					if(e.status==Edge.STATUS.UNVISITED){
						Node oppo=e.opposite(cur);
						
//						if(oppo.status==Node.STATUS.UNVISITED){
//							e.status=STATUS.VISITED;
//							oppo.status=Node.STATUS.VISITED;
//							nextLvl.add(oppo);
//						}else 
						if(oppo.dis<Integer.MAX_VALUE){
							e.status=Edge.STATUS.CROSSED;
							if(verbose){
								System.out.print("Cycle Detected : ");
								//g.printPath(g.buildAllPath(oppo, cur),true);
								System.out.println(" --> "+ oppo.value);
							}
						}
						double newDist=lvl+1;
						if(newDist<=oppo.dis){
							if(newDist<oppo.dis){
								oppo.dis=newDist;
								oppo.prevs.clear();
								nextLvl.add(oppo);
							}
							oppo.prevs.add(cur);
						}
					}
				}
			}
			
			curLvl=nextLvl;
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
