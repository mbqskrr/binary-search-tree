package binarySearchTree;

public class BinarySearchTree<K extends Comparable<K>, T> {

	private BSTNode<K, T> root;

	public BinarySearchTree() {
		root = null;
	}

	public T search(K k) {

		BSTNode<K, T> current = root;

		while (current != null && !current.getKey().equals(k) ) {

			if (k.compareTo(current.getKey()) < 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		if (current != null)
			return current.getInfo();
		else
			return null;
	}
	
	//Pre: k!=null and t!=null
	public boolean add(K k, T t) {
		if(search(k) != null) {
			return false;
		}
		
		BSTNode<K, T> tA = new BSTNode<>(k,t);
		if(root == null) {
			root = tA;
			return true;
		}
		
		BSTNode<K, T> current = root;
		boolean added = false;
		
		while(!added){
			if(k.compareTo(current.getKey())<0) {
				if(current.getLeft() != null) {
					current = current.getLeft();
				}else {
					current.setLeft(tA);
					tA.setParent(current);
					added = true;
				}
			}else {
				if(current.getRight() != null){
					current = current.getRight();
				}else {
					current.setRight(tA);
					tA.setParent(current);
					added = true;
				}
			}
		}
		return added;
		
	}

	
	public T max() {
		if(root == null) {
			return null;
		}else {
			BSTNode<K, T> current = root;
			while(current.getRight() != null) {
				current = current.getRight();
			}
			return current.getInfo();
		}
	}
	
	public T min() {
		if(root == null) {
			return null;
		}else {
			BSTNode<K, T> current = root;
			while(current.getLeft() != null) {
				current = current.getLeft();
			}
			return current.getInfo();
		}
	}
	
	//pre: node != null and node.right != null
	protected void leftRotation(BSTNode<K, T> node) {
		BSTNode<K, T> y = node.getRight();
		node.setRight(y.getLeft());
		if (y.getLeft() != null) {
			node = y.getLeft().getParent();
		}
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else if(node == node.getParent().getLeft()){
			node.getParent().setLeft(y);
		}else {
			node.getParent().setRight(y);
		}
		y.setLeft(node);
		node.setParent(y);
	}
}
