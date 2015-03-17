import java.util.ArrayList;

/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

	private Integer key;
	private int height;
	private String value;
	private AVLTreeNode left;
	private AVLTreeNode right;
	private AVLTreeNode parent;
	private int count;
	private ArrayList<String> list=new ArrayList<String>();

	public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();

	private AVLTreeNode() {
		this.key = null;
		this.height = -1;
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.count = 0;
		this.list = null;
	}

	/**
	 * Create an AVLTreeNode that contains the given key
	 * 
	 * @param key
	 */
	public AVLTreeNode(Integer key, String value) {
		this(null, key, value, null, null, 1);
	}

	private AVLTreeNode(AVLTreeNode left, Integer key, String value,
			AVLTreeNode right, AVLTreeNode parent, int count) {
		assert (key != null);
		this.left = left;
		this.right = right;
		this.key = key;
		this.value = value;
		this.height = 0;
		this.parent = parent;
		this.count+=1;
		this.setList(value);
	}

	/* Low level structural operations */
	/**
	 * Determine whether this node has a left branch.
	 */
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * Determine whether this node has a right branch.
	 */
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * Determine whether this node has a key.
	 */
	public boolean hasKey() {
		return key != null;
	}

	/**
	 * Obtain the key stored in this node.
	 */
	public Integer getKey() {
		return key;
	}
	public Integer getCount(){
		return count;
	}
	/**
	 * Obtain the height value stored at this node. (Requirs that ka
	 */
	public ArrayList<String> getList(){
		return list;
	}
	public int getHeight() {
		if (this == null)
			this.setHeight(0);
		return this.height;
	}

	public AVLTreeNode getParent() {
		return this.parent;
	}

	/**
	 * Obtain the balance factor for this node.
	 */
	public int getBalanceFactor() {
		int left = (this.hasLeft() ? this.getLeft().getHeight() : 0);
		int right = (this.hasRight() ? this.getRight().getHeight() : 0);
		return left - right;
	}

	/**
	 * Obtain this node's left branch. Requires that <code>this.hasLeft()</code>
	 * .
	 */
	public AVLTreeNode getLeft() {
		return this.left;
	}

	/**
	 * Obtain this node's right branch. Requires that
	 * <code>this.hasRight()</code>.
	 */
	public AVLTreeNode getRight() {
		return this.right;
	}

	public static String getValue(AVLTreeNode node) {
		return node.value;
	}

	/**
	 * Set the height stored in this node.
	 */
	public void setHeight(int height) {
		assert (this != EMPTY_NODE);
		this.height = height;
	}

	public void setParent(AVLTreeNode node) {
		this.parent = node;
	}
	public void setCount(int num){
		assert (this != EMPTY_NODE);
		this.count+=num;
	}
	public void setList(String value){
		list.add(value);
	}
	/**
	 * Set this node's left branch.
	 */
	public void setLeft(AVLTreeNode tree) {
		assert (this != EMPTY_NODE);
		this.left = tree;
	}

	/**
	 * Set this node's right branch.
	 */
	public void setRight(AVLTreeNode tree) {
		assert (this != EMPTY_NODE);
		this.right = tree;
	}

	public void setValue(AVLTreeNode tree, String value) {
		this.value = value;
	}

	/**
	 * Obtain the longest node label for nodes stored in this tree structure.
	 */
	public Integer getLargest() {
		Integer largest = this.toString().length();
		if (this.hasLeft())
			largest = Math.max(largest, this.getLeft().getLargest());
		if (this.hasRight())
			largest = Math.max(largest, this.getRight().getLargest());

		return largest;
	}

	/**
	 * Obtain a String representation of this node.
	 */
	public String toString() {
		String str = "\n";
		for(String temp: this.getList()){
			if(str.contains(temp)){
				continue;
			}
			str=str+temp+"\n";
		}
		return /* this.getKey().toString()+ */"(" + (value.charAt(0)+"").toUpperCase() + ")"+"("+count+")"+str;
	}

}
