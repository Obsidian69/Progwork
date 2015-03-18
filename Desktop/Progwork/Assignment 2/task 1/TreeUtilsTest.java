import static org.junit.Assert.*;

import org.junit.Test;

public class TreeUtilsTest {
	@Test
	public void testContains() {
		AVLTreeNode node = new AVLTreeNode(2);
		AVLTreeNode leftChild = new AVLTreeNode(1);
		AVLTreeNode rightChild = new AVLTreeNode(3);
		node.setLeft(leftChild);
		node.setRight(rightChild);
		assertTrue(TreeUtils.contains(node, 3));
		assertFalse(TreeUtils.contains(node, 5));
	}

	@Test
	public void testDoubleRotateWithLeftChild() { // 100, 150, 175, 125, 90, 99,
													// 95
		AVLTree tree = new AVLTree();
		tree.insert(100);
		tree.insert(150);
		tree.insert(175);
		tree.insert(125);
		tree.insert(90);
		tree.insert(99);
		tree.insert(95);
		assertTrue(tree.contains(100));
	}

	@Test
	public void testDoubleRotateWithRightChild() {
		AVLTree tree = new AVLTree();
		tree.insert(100);
		tree.insert(150);
		tree.insert(175);
		tree.insert(125);
		tree.insert(90);//this test works for both cases
		tree.insert(99);
		tree.insert(95);
		assertTrue(tree.contains(100));
	}

	@Test
	public void testInsert() {
		AVLTreeNode node = new AVLTreeNode(2);
		AVLTreeNode leftChild = new AVLTreeNode(1);
		AVLTreeNode rightChild = new AVLTreeNode(3);
		node.setLeft(leftChild);
		node.setRight(rightChild);
		assertEquals(true, node.hasRight());
		assertEquals(true, node.hasLeft());
	}

}
