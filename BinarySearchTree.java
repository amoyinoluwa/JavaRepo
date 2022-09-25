package binarySearchInorder;

/**
 * 
 */
/**
 * @author joseph
 *
 */
public final class BinarySearchTree {

	/**
	 * 
	 */
	static int Comparisons = 0;
	class Node 
	{
	    int key;
	    Node left, right;
	    public Node(int key)
	    {
	        this.key = key;
	        left = right = null;
	    }
	}
	Node root;
	public BinarySearchTree() {
		root = null;
	}
    public void bst_insert(int key) {
        root = insert(root, key);
    }
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        Comparisons++;
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }
    public int getComparisons() {
    	return Comparisons;
    }
    public void inorder() {
    	inorder(root);
    }
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }
}