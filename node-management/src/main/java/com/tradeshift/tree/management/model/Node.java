package com.tradeshift.tree.management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Node {

	String name;
	int height;
	
	@JsonIgnore
	Node parent;
	

	List<Node> childrens;
	
	public Node(String name, List<Node> childrens) {
		this.name = name;
		this.childrens = childrens;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<Node> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Node> childrens) {
		this.childrens = childrens;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Name: "+name + " Height: "+height+" and it's parent: "+parent.name;
	}
}
