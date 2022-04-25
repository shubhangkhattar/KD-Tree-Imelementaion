package com;

import java.util.Scanner;

public class KDTree {
	Node root = null;
	int dimention;
	
	public KDTree(int dim) {
		dimention = dim;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int dim = 2;
		KDTree tree = new KDTree(dim);
		
		int option = -1;
		while(option != 4) {
			System.out.println("Select operation:");
			System.out.println("1. Add a Record.");
			System.out.println("2. Delete a Record");
			System.out.println("3. Display the tree ");
			System.out.println("4. Exit ");
			
			option = sc.nextInt();
			
			if(option == 1) {
				int arr[] = new int[dim];
				for(int i=0; i<dim; i++) {
					arr[i] = sc.nextInt();
				}
				tree.addRecord(arr);
				tree.display();
			}
			else if(option == 2) {
				int arr[] = new int[dim];
				for(int i=0; i<dim; i++) {
					arr[i] = sc.nextInt();
				}
				tree.deleteRecord(arr);
				tree.display();
			}
			else if(option == 3) {
				tree.display();
			}
			else if(option == 4) {
				break;
			}
		}
		sc.close(); 
	}

	private void addRecord(int[] arr) {
		root = insert(arr, root, 0);
	}

	private Node insert(int[] arr, Node node, int dim) {
		if(node == null) {
			return new Node(0, arr);
			
		}
		
		if(node.values[dim] > arr[dim]) {
			node.left = insert(arr, node.left, (dim+1)%dimention);
		}
		else {
			node.right = insert(arr, node.right, (dim+1)%dimention);
		}
		return node;
	}

	private void deleteRecord(int[] point) {
		root = deleteNodeRec(root, point, 0);
	}

	private Node deleteNodeRec(Node node, int[] point, int depth) {
		if (node == null)
			return null;

		int cd = depth % dimention;

		if (arePointsSame(node.values, point))
		{
			if (node.right != null)
			{

				Node min = findMin(node.right, cd);
				copyPoint(node, min);

				node.right = deleteNodeRec(node.right, min.values, depth+1);
			}
			else if (node.left != null) 
			{
				Node min = findMin(node.left, cd);
				copyPoint(node, min);
				node.right = deleteNodeRec(node.left, min.values, depth+1);
			}
			else 
			{
				return null;
			}
			return node;
		}

		if (point[cd] < node.values[cd])
			node.left = deleteNodeRec(node.left, point, depth+1);
		else
			node.right = deleteNodeRec(node.right, point, depth+1);
		return node;
	}

	private boolean arePointsSame(int[] pt1, int[] pt2) {
		for (int i = 0; i < dimention; ++i)
			if (pt1[i] != pt2[i])
				return false;
		return true;
	}

	private void copyPoint(Node a, Node b) {
		for (int i=0; i<dimention; i++)
			a.values[i] = b.values[i];
		
	}

	private Node findMin(Node node, int d) {
		return findMinRec(node, d, 0);
	}

	private Node findMinRec(Node node, int d, int depth) {
		if (node == null)
			return null;
		
		int cd = depth % dimention;

		if (cd == d)
		{
			if (node.left == null)
				return node;
			return findMinRec(node.left, d, depth+1);
		}

		return minNode(node,
				findMinRec(node.left, d, depth+1),
				findMinRec(node.right, d, depth+1), d);
	}

	private Node minNode(Node x, Node y, Node z, int d) {
		Node res = x;
		if (y != null && y.values[d] < res.values[d])
		res = y;
		if (z != null && z.values[d] < res.values[d])
		res = z;
		return res;
	}

	private void display() {
		System.out.println(root);
	}
}
