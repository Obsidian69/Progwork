import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
/**
 * Write a description of class BinaryTreeNodePrintStream here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter {

	private PrintStream output;

	public SimpleTreeWriterImpl(PrintStream output) {
		this.setDestination(output);
	}

	public void setDestination(PrintStream output) {
		this.output = output;
	}

	public void write(AVLTreeNode node) {
		/*
		 * given code
		 */
		List<AVLTreeNode> level = TreeUtils.levelZero(node);
		final int labelSize = node.getLargest().toString().length() * 2;

		for (int levelNum = 0; levelNum < node.getHeight(); levelNum++) {
			final int nodeSpacing = powerOf2(node.getHeight() - levelNum) - 1;
			writeLevel(nodeSpacing, labelSize, level);
			level = TreeUtils.nextLevel(level);
		}
	}

	private void writeLevel(int nodeSpacing, int labelSize, List<AVLTreeNode> level) {
		/*
		 * given code
		 */
		final String leadingSpace = makeSpacing((nodeSpacing / 2) * labelSize);
		final String interNodeSpace = makeSpacing(nodeSpacing * labelSize);

		Iterator<AVLTreeNode> iterator = level.iterator();
		assert (iterator.hasNext());

		output.print(leadingSpace);
		writeNode(iterator.next(), labelSize);

		while (iterator.hasNext()) {
			output.print(interNodeSpace);
			writeNode(iterator.next(), labelSize);
		}
		output.println();
	}

	private void writeNode(AVLTreeNode node, int labelSize) {
		final String blankNode = makeSpacing(labelSize);
		if (TreeUtils.isPlaceHolder(node)) {
			output.printf(blankNode);
		} else {
			/*
			 * edited the given code here to work with my code
			 */
			output.printf("%" + labelSize + "d", node.getKey());
			output.printf(node.toString(), null);
		}
	}

	private static int powerOf2(int power) {
		/*
		 * given code
		 */
		if (power == 0) {
			return 1;
		} else {
			return 2 * powerOf2(power - 1);
		}
	}

	private static String makeSpacing(int size) {
		/*
		 * given code
		 */
		StringBuilder builder = new StringBuilder(size);
		while (size > 0) {
			builder.append(' ');
			size--;
		}
		return builder.toString();
	}

}
