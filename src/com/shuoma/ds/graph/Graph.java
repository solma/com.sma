package com.shuoma.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Graph {
	public HashMap<String, Node> nodes;
		
	public void reset(){
		for(Node node: nodes.values()) node.reset();
	}
	
	public ArrayList<Node> buildPath(Node start, Node end){
		ArrayList<Node> path=new ArrayList<Node>();
		Node cur=end;
		while(cur!=null&&!cur.equals(start)){
			path.add(0, cur);
			cur=cur.prev;
		}
		if(start.equals(cur)) path.add(0,cur);
		return path;
	}
	
	public void printPath(ArrayList<Node> path){
		printPath(path,false);
	}
	
	public void printPath(ArrayList<Node> path, boolean byValue){
		for(int i=0;i<path.size();i++){
			if(byValue) System.out.print(path.get(i).value);
			else System.out.print(path.get(i));
			if(i<path.size()-1) System.out.print(" --> ");
		}
		System.out.println();
	}
}
