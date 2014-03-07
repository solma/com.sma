package com.shuoma.alg.graph;

import java.util.PriorityQueue;

import com.shuoma.ds.graph.Edge;
import com.shuoma.ds.graph.Graph;
import com.shuoma.ds.graph.Node;
import com.shuoma.ds.graph.Tree;
import com.shuoma.ds.graph.TreeNode;

public class Kruskal {
	public static final boolean verbose=true;
	
	public Tree buildMST(Graph g, Node start){
		if(verbose){
			System.out.println("**** Kruskal Building Illustration ****");
		}
		if(start==null) return null;
		PriorityQueue<Node> pq=new PriorityQueue<Node>();
		start.dis=0;
		start.status=Node.STATUS.VISITED;
		pq.add(start);
		
		Tree tree=null;
		
		int lvl=0;
		while(pq.size()>0){
			Node cur=pq.poll();
			cur.status=Node.STATUS.EXPANED;
			
			if(lvl==0) tree=new Tree(new TreeNode(cur));
			else{
				TreeNode father=(TreeNode) tree.treeNodes.get(cur.prev.id);
				TreeNode child=new TreeNode(cur);
				father.children.add(child);
				tree.treeNodes.put(child.id, child);
				if(verbose){
					System.out.println("edge "+lvl+" :  father:"+father.value+",  child:"+child.value);
				}
			}
			
			
			for(Edge e: cur.adjacentList){
				if(e.status==Edge.STATUS.UNVISITED){
					Node oppo=e.opposite(cur);
					if(oppo.status==Node.STATUS.UNVISITED){
						oppo.status=Node.STATUS.VISITED;
						oppo.prev=cur;
						
						double newDist=oppo.value;
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
		return tree;
	}
	

}
