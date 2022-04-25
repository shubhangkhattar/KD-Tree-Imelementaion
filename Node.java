package com;

import java.util.Arrays;

public class Node {
	int dimention;
	int[] values;
	Node left = null;
	Node right= null;
	
	
	public Node(int dimention, int[] values) {
		super();
		this.dimention = dimention;
		this.values = values;
	}


	@Override
	public String toString() {
		return "Node [ " + "values = " + Arrays.toString(values) +
				"\n, left = " + left 
				+ "\n, right = "
				+ right + "\n]";
	}

}
