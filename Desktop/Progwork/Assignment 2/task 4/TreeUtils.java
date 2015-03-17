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
			if(node==null) {
			
			}
			else{
			nextLevel.add(node.hasLeft() ? node.getLeft() : null);
			nextLevel.add(node.hasRight() ? node.getRight() : null);
			}
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

	public static String find(AVLTreeNode node, int key) {
		if (node.getKey() == key) {
			ArrayList<String> list = node.getList();
			ArrayList<String> temp_list = new ArrayList<String>();
			for (String temp : list) {
				if (!temp_list.contains(temp)) {
					temp_list.add(temp);
				}
			}
			String values = "";
			for (String temp : temp_list)
				values = values + ", " + temp;
			return "(" + node.getCount() + ")" + "(" + values.substring(2)
					+ ")";
		}
		if (key < node.getKey()) {
			if (node.hasLeft())
				return find(node.getLeft(), key);
		} else if (key > node.getKey()) {
			if (node.hasRight())
				return find(node.getRight(), key);
		}
		return ("No entry found");

	}

	public static int height(AVLTreeNode node) {
		if (node == null)
			return 0;
		return node.getHeight();
	}

	public static AVLTreeNode rotateWithLeftChild(AVLTreeNode k2) {
		AVLTreeNode node_k1 = k2.getLeft();
		k2.setLeft(node_k1.getRight());
		if (k2.getLeft() != null) {
			k2.getLeft().setParent(k2);
		}
		AVLTreeNode temp = k2.getParent();
		node_k1.setRight(k2);
		k2.setParent(node_k1);
		node_k1.setParent(temp);
		return node_k1;
	}

	public static AVLTreeNode rotateWithRightChild(AVLTreeNode k1) {
		AVLTreeNode node_k2 = k1.getRight();
		k1.setRight(node_k2.getLeft());
		if (k1.getRight() != null) {
			k1.getRight().setParent(k1);
		}
		AVLTreeNode temp = k1.getParent();
		node_k2.setLeft(k1);
		k1.setParent(node_k2);
		node_k2.setParent(temp);
		return node_k2;
	}

	public static AVLTreeNode doubleRotateWithLeftChild(AVLTreeNode k3) {
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}

	public static AVLTreeNode doubleRotateWithRightChild(AVLTreeNode k4) {
		k4.setRight(rotateWithLeftChild(k4.getRight()));
		return rotateWithRightChild(k4);
	}

	public static AVLTreeNode delete(AVLTreeNode node, Integer key, String value) {
		if (node.getList().contains(value)) {
			AVLTreeNode temp = node.getParent();
			AVLTreeNode parent = node.getParent();
			if (node.getLeft() == null && node.getRight() == null) {
				if (node.getList().size() == 1) {
					if (parent == null) {
						return null;
					} else if (parent.getLeft() == node) {
						parent.setLeft(null);
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceLeft(parent, key);
						}
					} else {
						parent.setRight(null);
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceRight(parent, key);
						}
					}
					while (temp.getParent() != null) {
						temp = temp.getParent();
					}
					return temp;
				} else {
					node.getList().remove(value);
					node.setCount(-1);
				}
			} else if (node.getLeft() == null && !(node.getRight() == null)) {
				if (node.getList().size() == 1) {
					if (parent == null) {
						node.getRight().setParent(null);
						return node.getRight();
					}
					if (parent.getLeft() == node) {
						parent.setLeft(node.getRight());
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceLeft(parent, key);
						}
					} else {
						parent.setRight(node.getRight());
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceRight(parent, key);
						}
					}
					while (temp.getParent() != null) {
						temp = temp.getParent();
					}
					return temp;
				}
			} else if (!(node.getLeft() == null) && node.getRight() == null) {
				if (node.getList().size() == 1) {
					if (parent == null) {
						node.getLeft().setParent(null);
						return node.getLeft();
					}
					if (parent.getLeft() == node) {
						parent.setLeft(node.getLeft());
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceLeft(parent, key);
						}
					} else {
						parent.setRight(node.getLeft());
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceRight(parent, key);
						}
					}
					while (temp.getParent() != null) {
						temp = temp.getParent();
					}
					return temp;
				}
			} else {
				if (node.getList().size() == 1) {
					AVLTreeNode newNode = node.getRight();
					while (newNode.getLeft() != null) {
						newNode = newNode.getLeft();
					}
					newNode.getParent().setLeft(null);
					AVLTreeNode temp2 = node.getRight();
					newNode.setParent(node.getParent());
					newNode.setLeft(node.getLeft());
					newNode.setHeight(node.getHeight());
					newNode.getLeft().setParent(newNode);
					newNode.setRight(temp2);
					temp2.setParent(newNode);
					if (node.getParent() == null) {
						// no parent setting required
					} 
					else if (node.getParent().getRight() == node) {
						node.getParent().setRight(newNode);
					} else {
						node.getParent().setLeft(newNode);
					}
					if (Math.abs(height(parent.getLeft())
							- height(parent.getRight())) > 1) {
						parent = rebalanceLeft(parent, key);
					} else {
						if (Math.abs(height(parent.getLeft())
								- height(parent.getRight())) > 1) {
							parent = rebalanceRight(parent, key);
						}
					}
					if (parent == null) {

					}
					else if (height(parent.getLeft()) > height(parent.getRight())) {
						parent.setHeight(height(parent.getLeft()) + 1);
					} else {
						parent.setHeight(height(parent.getRight()) + 1);
					}
					temp = newNode;
					while (temp.getParent() != null) {
						temp = temp.getParent();
					}
					return temp;
				}
			}
		} else if (key < node.getKey()) {
			delete(node.getLeft(), key, value);
		} else if (key > node.getKey()) {
			delete(node.getRight(), key, value);
		} else {
			System.out.println("Node not found");
		}
		return node;
	}

	public static AVLTreeNode insert(AVLTreeNode node, Integer key, String value) {
		if (node == null)
			node = new AVLTreeNode(key, value);
		else if (node.getKey() == key) {
			node.setCount(1);
			node.setValue(node, value);
			node.setList(value);
			return node;
		} else if (key < node.getKey()) {
			node.setLeft(insert(node.getLeft(), key, value));
			node.getLeft().setParent(node);
			if (Math.abs(height(node.getLeft()) - height(node.getRight())) > 1) {
				node = rebalanceLeft(node, key);
			}
		} else {
			node.setRight(insert(node.getRight(), key, value));
			node.getRight().setParent(node);
			if (Math.abs(height(node.getLeft()) - height(node.getRight())) > 1) {
				node = rebalanceRight(node, key);
			}
		}
		if (height(node.getLeft()) > height(node.getRight())) {
			node.setHeight(height(node.getLeft()) + 1);
		} else {
			node.setHeight(height(node.getRight()) + 1);
		}
		return node;
	}

	private static AVLTreeNode rebalanceRight(AVLTreeNode node, Integer key) {
		node.setHeight(node.getRight().getHeight() - 1);
		if (key > node.getRight().getKey())
			return rotateWithRightChild(node);
		return doubleRotateWithRightChild(node);
	}

	private static AVLTreeNode rebalanceLeft(AVLTreeNode node, Integer key) {
		node.setHeight(node.getLeft().getHeight() - 1);
		if (key < node.getLeft().getKey()) {
			return rotateWithLeftChild(node);
		} else {
			return doubleRotateWithLeftChild(node);
		}
	}
}