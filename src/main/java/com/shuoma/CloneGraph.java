package com.shuoma;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class CloneGraph {


	public static void main(String[] args) {
		new CloneGraph().main();
	}
	
	public void main(){
		UndirectedGraphNode node=new UndirectedGraphNode(-1);
		UndirectedGraphNode node2=new UndirectedGraphNode(1);
		node.neighbors.add(node2);
		cloneGraph(node);
	}
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return node;
        
        Queue<UndirectedGraphNode> curLvl=new LinkedList<UndirectedGraphNode>();
        curLvl.offer(node);
        HashMap<UndirectedGraphNode, UndirectedGraphNode> cloned=new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        cloned.put(node, new UndirectedGraphNode(node.label));
        
        while(!curLvl.isEmpty()){
            Queue<UndirectedGraphNode> nextLvl=new LinkedList<UndirectedGraphNode>();
            while(!curLvl.isEmpty()){
                UndirectedGraphNode curNode=curLvl.poll();

                for(UndirectedGraphNode neighbor: curNode.neighbors){
                    if(!cloned.containsKey(neighbor)){
                        cloned.put(neighbor, new UndirectedGraphNode(neighbor.label));
                        nextLvl.offer(neighbor);
                    } 
                    cloned.get(curNode).neighbors.add( cloned.get(neighbor) );
                }
            }
            curLvl=nextLvl;
        }
        return cloned.get(node);
    }
}
