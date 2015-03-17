import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
		// List<AVLTreeNode> level = TreeUtils.levelZero(node);
		// final int labelSize = node.getLargest().toString().length();
		//
		// for(int levelNum=0; levelNum<node.getHeight(); levelNum++) {
		// final int nodeSpacing = powerOf2(node.getHeight()-levelNum)-1;
		// writeLevel(nodeSpacing, labelSize, level);
		// level = TreeUtils.nextLevel(level);
		// }
		if (node == null) {
			output.println();
		} else {
			List<AVLTreeNode> level = TreeUtils.levelZero(node);
			final int labelSize = node.getLargest();

			for (int levelNum = 0; levelNum < node.getHeight(); levelNum++) {
				final int nodeSpacing = powerOf2(node.getHeight() - levelNum) - 1;
				writeLevel(nodeSpacing, labelSize, level);
				level = TreeUtils.nextLevel(level);
			}
		}
	}

	private void writeLevel(int nodeSpacing, int labelWidth, List<AVLTreeNode> level) {
		// final String leadingSpace = makeSpacing((nodeSpacing/2)*labelSize);
		// final String interNodeSpace = makeSpacing(nodeSpacing*labelSize);
		//
		// Iterator<AVLTreeNode> iterator = level.iterator();
		// assert(iterator.hasNext());
		//
		// output.print(leadingSpace);
		// writeNode(iterator.next(), labelSize);
		//
		// while (iterator.hasNext()) {
		// output.print(interNodeSpace);
		// writeNode(iterator.next(), labelSize);
		// }
		// output.println();
		int height = 0;
		String leadingSpace = makeSpacing((nodeSpacing / 2) * labelWidth);
		String interNodeSpace = makeSpacing(nodeSpacing * labelWidth);

		final List<Scanner> raster = new ArrayList<Scanner>();
		for (AVLTreeNode node : level) {
			if (node == null) {
				String label = "";
				height = Math.max(height, label.split("\n").length);
				raster.add(new Scanner(""));
			} else {

				String label = node.toString();
				height = Math.max(height, label.split("\n").length);
				raster.add(new Scanner(node.toString()));
			}
		}

		while (height > 0) {
			Iterator<Scanner> iterator = raster.iterator();

			output.print(leadingSpace);
			writeNode(iterator.next(), labelWidth);

			while (iterator.hasNext()) {
				output.print(interNodeSpace);
				writeNode(iterator.next(), labelWidth);
			}
			output.println();
			height--;
		}

	}

	private void writeNode(Scanner scanner, int labelWidth) {
		// final String blankNode = makeSpacing(labelSize);
		// if (TreeUtils.isPlaceHolder(node)) {
		// output.printf(blankNode);
		// }
		// else {
		// output.printf(node.toString(), null);
		// }
		final String blankNode = makeSpacing(labelWidth);
		if (!scanner.hasNextLine()) {
			output.printf(blankNode);
		} else {
			output.printf("%-" + labelWidth + "s", scanner.nextLine());
		}
	}

	private static int powerOf2(int power) {
		if (power == 0) {
			return 1;
		} else {
			return 2 * powerOf2(power - 1);
		}
	}

	private static String makeSpacing(int size) {
		StringBuilder builder = new StringBuilder(size);
		while (size > 0) {
			builder.append(' ');
			size--;
		}
		return builder.toString();
	}
}