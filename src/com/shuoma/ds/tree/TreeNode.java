package com.shuoma.ds.tree;

import java.util.ArrayList;

import com.shuoma.ds.graph.Node;


public class TreeNode extends Node{
	public ArrayList<TreeNode> children;
	
	public TreeNode(Node n){
		id=n.id;
		status=n.status;
		value=n.value;
		dis=n.dis;
		
		children=new ArrayList<TreeNode>();
		/*for(Edge e:n.adjacentList){
			children.add(new TreeNode(e.opposite(n)) );
		}*/
	}
	
	public String toString(){
		return id;
	}
}
