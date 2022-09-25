package binarySearchInorder;
import java.util.*;
/**
 * 
 */

/**
 * @author Joseph Ajayi
 * @version 1.0
 * @since 2021-09-25
 */
class BST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
		System.out.println("Enter a size: ");
		int size = input.nextInt();
		int value;
		Random number = new Random();
		RBTree RBT = new RBTree();
		BinarySearchTree BST = new BinarySearchTree();
		for (int i = 0; i < size; i++)
		{
			value = number.nextInt(1000 - 100) + 100; //generates random numbers between 100 and 1000
			RBT.RB_INSERT(value);
			BST.bst_insert(value);
		}
		System.out.println("Size of input: " + size);
		System.out.println("RBT number of comparisons: " + RBT.getComparisons());
		System.out.println("BST number of comparisons: " + BST.getComparisons());
		}
	}
}
