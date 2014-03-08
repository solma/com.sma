package com.shuoma.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.shuoma.alg.graph.BFS;
import com.shuoma.alg.graph.DFS;
import com.shuoma.alg.graph.Dijkstra;
import com.shuoma.alg.graph.Kruskal;
import com.shuoma.ds.graph.Tree.TRAVERSAL_ORDER;



/**
 * Matrix as a graph where each element in the matrix is regarded a node
 *
 * @author Shuo
 *
 */
public class MatrixGraph extends Graph {
	public static final int[][] NEIGHBOR_ELEMENTS={{-1, 0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	class MatrixNode extends Node{
		public static final String ID_DELIMETER="-";
		public MatrixNode(int row, int col, double v) {
			id=row+ID_DELIMETER+col;
			adjacentList=new ArrayList<Edge>();
			value=v;
			dis=Double.MAX_VALUE;
			reset();
		}
		
		
		
		public String toString(){
			return id;
		}
	}
	
	class MatrixEdge extends Edge{
		public MatrixEdge(Node f, Node t){
			from=f;
			to=t;
			reset();
		}
		
		public boolean equals(Edge other){
			if ((from.equals(other.from) && to.equals(other.to))
					|| (from.equals(other.to) && to.equals(other.from))) {
				return true;
			}
			return false;
		}
		
		public String toString(){
			return "("+from+","+to+")";
		}
	}
	
	
	public Node getNode(int row, int col){
		return nodes.get(row+MatrixNode.ID_DELIMETER+col);
	}
	
	/**
	 * Convert a 2d array to a graph
	 * @param board
	 */
	public MatrixGraph(double[][] board){
		//check input 2d array
		if(board==null) throw new IllegalArgumentException();
		int nrow=board.length;
		if(nrow==0) throw new IllegalArgumentException();
		int ncol=board[0].length;
		
		//add nodes;
		nodes=new HashMap<String, Node>();
		for(int i=0;i<nrow;i++){
			for(int j=0;j<ncol;j++){
				MatrixNode node=new MatrixNode(i, j, board[i][j]);
				nodes.put(node.id, node);
			}
		}
		
		//add edges;
		for(Node node: nodes.values()){
			 String[] rowAndCol=node.id.split(MatrixNode.ID_DELIMETER);
			 int row=Integer.parseInt(rowAndCol[0]);
			 int col=Integer.parseInt(rowAndCol[1]);
			 for(int i=0;i<NEIGHBOR_ELEMENTS.length;i++){
				 String adjacentNodeID=(row+NEIGHBOR_ELEMENTS[i][0])+MatrixNode.ID_DELIMETER+(col+NEIGHBOR_ELEMENTS[i][1]);
				 if(nodes.containsKey(adjacentNodeID)){
					 node.adjacentList.add(new MatrixEdge(node, nodes.get(adjacentNodeID)));
				 }
			 }
		 }
	}
	
	public ArrayList<Edge> getEdgeList(){
		HashSet<Edge> edges=new HashSet<Edge>();
		for(Node node: nodes.values()){
			for(Edge edge: node.adjacentList){
				edges.add(edge);
			}
		}
		return new ArrayList<Edge>(edges);
	}
	
	public static void main(String[] args){
        double[][] matrix={
{7   , 2    ,3   , 1},
{2 ,   5   , 1 ,   1},
{3  ,  6   , 7 ,   3},
{3   , 5  ,  3 ,   1}
};
		MatrixGraph graph=new MatrixGraph(matrix);

		/*	for(Edge edge: graph.getEdgeList()){
			System.out.println(edge);
		}*/
		Node start=graph.getNode(0, 0), end=graph.getNode(3,3 );
		
		//bfs
		BFS bfs=new BFS();
		graph.reset();
		bfs.find(graph, start, end );
		graph.printPath( graph.buildPath(start, end) );
		
		//dfs
		DFS dfs=new DFS();
		graph.reset();
		//dfs.find(graph, start, end );
		//graph.printPath( graph.buildPath(start, end) );
		
		//iddfs
		/*for(int depth=1;depth<graph.nodes.size();depth++){
			graph.reset();
			if(dfs.find(graph, start, end,depth)!=null){
				graph.printPath( graph.buildPath(start, end) );
				break;
			}
		}*/
		
		//dijkstra
		Dijkstra dijkstra=new Dijkstra();
		graph.reset();
		//dijkstra.find(graph, start, end );
		//graph.printPath( graph.buildPath(start, end), true );
		
		
		//Kruskal
		Kruskal kruskal=new Kruskal();
		graph.reset();
		//Tree tree=kruskal.buildMST(graph, start);
		//graph.printPath(tree.traverse(TRAVERSAL_ORDER.PREORDER), true);
	}
}
