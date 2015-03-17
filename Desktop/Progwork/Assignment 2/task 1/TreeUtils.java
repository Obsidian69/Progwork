import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility procedures for binary tree structures.
 * 
 * @author Stephan Jamieson
 * @version 25/2/2015
 */
public class TreeUtils {

	/**
	 * Determine whether one tree node structure is similar (has the same
	 * structure) as another.
	 * 
	 * /** Obtain a list containing the root node of the given structure i.e.
	 * tNode itself.
	 */
	public static List<AVLTreeNode> levelZero(AVLTreeNode tNode) {
		List<AVLTreeNode> level = new ArrayList<AVLTreeNode>();
		level.add(tNode);
		return level;
	}

	/**
	 * Given a list of nodes, obtain the next level.
	 * 
	 * <p>
	 * If the tree structure is incomplete, <code>AVLTreeNode.EMPTY_NODE</code>
	 * is inserted as a place holder for each missing node.
	 * </p>
	 */
	public static List<AVLTreeNode> nextLevel(List<AVLTreeNode> level) {
		List<AVLTreeNode> nextLevel = new ArrayList<AVLTreeNode>();

		for (AVLTreeNode node : level) {
			nextLevel.add(node.hasLeft() ? node.getLeft() : AVLTreeNode.EMPTY_NODE);
			nextLevel.add(node.hasRight() ? node.getRight() : AVLTreeNode.EMPTY_NODE);
		}
		return nextLevel;
	}

	/**
	 * Determine whether node is a place holder i.e.
	 * <code>node==AVLTreeNode.EMPTY_NODE</code>
	 */
	public static boolean isPlaceHolder(AVLTreeNode node) {
		return node == AVLTreeNode.EMPTY_NODE;
	}

	public static boolean contains(AVLTreeNode node, Integer key) {
		if (node.getKey() == (int) key)
			return true;// base case: value is value
		if (isPlaceHolder(node))
			return false;// if empty
		if (key < node.getKey()) {// recurse left
			if (node.hasLeft())
				return contains(node.getLeft(), key);
			return false;
		} else if (key > node.getKey()) {// recurse right
			if (node.hasRight())
				return contains(node.getRight(), key);
			return false;
		}
		return false;// key not in tree
	}

	public static int height(AVLTreeNode node) {
		if (node == null)
			return 0;
		return node.getHeight();
	}

	public static AVLTreeNode rotateWithLeftChild(AVLTreeNode k2) {
		AVLTreeNode node_k1 = k2.getLeft();// k1 is k2' left child
		k2.setLeft(node_k1.getRight());// k2's new left is the right subtree of
										// k1
		node_k1.setRight(k2);// k1's new right child is k2
		return node_k1;
	}

	public static AVLTreeNode rotateWithRightChild(AVLTreeNode k1) {
		AVLTreeNode node_k2 = k1.getRight();// reverse of above
		k1.setRight(node_k2.getLeft());
		node_k2.setLeft(k1);
		return node_k2;
	}

	public static AVLTreeNode doubleRotateWithLeftChild(AVLTreeNode k3) {
		k3.setLeft(rotateWithRightChild(k3.getLeft()));// recursive rotation
		return rotateWithLeftChild(k3);
	}

	public static AVLTreeNode doubleRotateWithRightChild(AVLTreeNode k4) {
		k4.setRight(rotateWithLeftChild(k4.getRight()));// recursive rotation
		return rotateWithRightChild(k4);
	}

	public static AVLTreeNode insert(AVLTreeNode node, Integer key) {
		if (node == null) // base case. node is empty so insert here
			node = new AVLTreeNode(key);
		else if (node.getKey() == key) // if node exists, do nothing
			return node;
		else if (key < node.getKey()) {// recurse left
			node.setLeft(insert(node.getLeft(), key));
			if (Math.abs(height(node.getLeft()) - height(node.getRight())) > 1)
				node = rebalanceLeft(node, key);// if unbalanced, rebalance
		} else {
			node.setRight(insert(node.getRight(), key));// recurse right
			if (Math.abs(height(node.getLeft()) - height(node.getRight())) > 1)
				node = rebalanceRight(node, key);// see above
		}
		if (height(node.getLeft()) > height(node.getRight()))// calculation of
																// max height to
																// assign it to
																// the node
			node.setHeight(height(node.getLeft()) + 1);
		else {
			node.setHeight(height(node.getRight()) + 1);
		}
		return node;
	}

	private static AVLTreeNode rebalanceRight(AVLTreeNode node, Integer key) {
		node.setHeight(node.getRight().getHeight() - 1);// height change
														// calculations
		if (key > node.getRight().getKey()) {
			return rotateWithRightChild(node);
		} else {
			return doubleRotateWithRightChild(node);
		}
	}

	private static AVLTreeNode rebalanceLeft(AVLTreeNode node, Integer key) {
		node.setHeight(node.getLeft().getHeight() - 1);// see above
		if (key < node.getLeft().getKey()) {
			return rotateWithLeftChild(node);
		} else {
			return doubleRotateWithLeftChild(node);
		}
	}

}
