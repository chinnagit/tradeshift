package com.tradeshift.tree.management.api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeshift.tree.management.model.Node;
/**
 * Helper class to 
 * 1. retrieve the descendants of given node.
 * 2. Change the parent of given node.
 * 
 * @author bala chinna
 *
 */
public class NodeHelper {

	Node root;
	
	public NodeHelper() {
		//initialize with sample data
		this.root = intializeData();
		//set the height of each node.
		setHeight(root, 0);
	}
	
	public NodeHelper(Node root) {
		this.root = root;
		setHeight(root, 0);
	}
	
	private void setHeight(Node root, int height) {
		if(root == null) {
			return;
		}
		//do the level order traversal and set the height for all nodes at each level.
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		queue.offer(null);
		List<Node> list = new ArrayList<>();
		list.add(root);
		while(!queue.isEmpty() && queue.size() > 1) {
			Node node = queue.poll();
			
			if(node == null) {
				++height;
				queue.offer(null);
			}else {
				node.setHeight(height);
				if(node.getChildrens() != null) {
					for(Node children : node.getChildrens()) {
						children.setParent(node);
						queue.offer(children);
					}
				}
			}
		}
	}
	
	/*
	 * Helper method to to get the subtree or descendants of given node
	 *  
	 * @Param String node name
	 * @Return List<Node> entire tree
	 */
	public List<Node> getDescedents(String name){
		List<Node> descedants = new ArrayList<>();
		if(name == null && root != null) {
			descedants.add(root);
			return descedants;
		}
		Node searchNode = getNode(root, name);
		if(searchNode == null) {
			return descedants;
		}
		return searchNode.getChildrens();
	}
	
	/*
	 * Helper method to search the given node and assign to new parent.
	 * at the same time adjust the new heights as per new structure.
	 *  
	 * @Param String name
	 * @Param String parentName
	 * @Return List<Node> entire tree
	 */
	public List<Node> changeParent(String name, String parentName) {
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		
		Node parentNode = getNode(root, parentName);
		Node searchNode = null;
		
		while(!queue.isEmpty()) {
			Node node = queue.remove();
			if(node.getChildrens() != null) {
				for(Node children : node.getChildrens()) {
					if(children.getName().equals(name)) {
						if(children.getParent().getName().equals(parentName)) {
							// as the parent node and given input parent name is same, as there is no change no point in reshuffle the data.
							break;
						}
						List<Node> childrens = node.getChildrens();
						childrens.remove(children);
						searchNode = children;
						break;
					}
					queue.offer(children);
				}
			}
		}
		
		if(parentNode != null && searchNode != null) {
			//add the sub tree to the asked parent. 
			parentNode.getChildrens().add(searchNode);
			//adjust the heights.
			setHeight(parentNode, parentNode.getHeight());
		}
		
		//return the entire tree, so that user can see what got changed.
		return getDescedents(null);
	}
	
	
	private Node getNode(Node root, String name) {
		//Using BFS, serach the given node.
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			Node node = queue.remove();
			if(node.getName().equals(name)) {
				return node;
			}
			if(node.getChildrens() != null) {
				for(Node children : node.getChildrens()) {
					queue.offer(children);
				}
			}
		}
		return null;
	}
	
	public static Node intializeData() {
		List<Node> rootChildrens = new ArrayList<>();
		List<Node> aChildrens = new ArrayList<>();
		List<Node> bChildrens = new ArrayList<>();
		
		Node nodeC = new Node("C", new ArrayList<>());
		
		Node nodeD = new Node("D", new ArrayList<>());
		Node nodeE = new Node("E", new ArrayList<>());
		Node nodeF = new Node("F", new ArrayList<>());
		bChildrens.add(nodeD);
		bChildrens.add(nodeE);
		bChildrens.add(nodeF);
		Node nodeB = new Node("B", bChildrens);
		aChildrens.add(nodeC);
		Node nodeA = new Node("A", aChildrens);
		rootChildrens.add(nodeA);
		rootChildrens.add(nodeB);
		Node root = new Node("root", rootChildrens);
		return root;
	}

	public static void main(String[] args) {

		//sample test scenario
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			NodeHelper ex = new NodeHelper(intializeData());
			List<Node> results = ex.getDescedents("root");
			
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of root: "+jsonInString);
			
			results = ex.getDescedents("A");
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of A: "+jsonInString);
			
			results = ex.getDescedents("B");
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of B: "+jsonInString);
			
			results = ex.getDescedents("C");
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of C: "+jsonInString);
			
			results = ex.getDescedents("E");
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of E: "+jsonInString);
			
			results = ex.getDescedents("N");
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of N: "+jsonInString);
			
			//Test to change the parent
			results = ex.changeParent("C", "root");
		
			// Java objects to JSON string - pretty-print
	        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
	
			System.out.println("\nAfter change parent from A to root for the node C:\n "+jsonInString);
			
			//Test to change the parent
			results = ex.changeParent("D", "root");
		
			// Java objects to JSON string - pretty-print
	        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
	
			System.out.println("\nAfter change parent from B to root for the node D:\n "+jsonInString);
			
			//Test to change the parent -- basically no change, as A's parent is already is root.
			results = ex.changeParent("A", "root");
		
			// Java objects to JSON string - pretty-print
	        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
	
			System.out.println("\nAfter change parent from root to root for the node A:\n "+jsonInString);
		}catch(Exception exp) {
			exp.printStackTrace();
			
		}
		
	}
	
	
//	private static class Node {
//		String name;
//		int height;
//		Node parent;
//		
//
//		List<Node> childrens;
//		
//		public Node(String name, List<Node> childrens) {
//			this.name = name;
//			this.childrens = childrens;
//		}
//		
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public int getHeight() {
//			return height;
//		}
//
//		public void setHeight(int height) {
//			this.height = height;
//		}
//
//		public List<Node> getChildrens() {
//			return childrens;
//		}
//
//		public void setChildrens(List<Node> childrens) {
//			this.childrens = childrens;
//		}
//		
//		public Node getParent() {
//			return parent;
//		}
//
//		public void setParent(Node parent) {
//			this.parent = parent;
//		}
//
//		@Override
//		public String toString() {
//			return "Name: "+name + " Height: "+height+" and it's parent: "+parent.name;
//		}
//	}


}
