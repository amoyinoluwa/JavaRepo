package BST;

/**
 * RBTree: Constructs a balanced binary search tree
 * @author Joseph Ajayi
 *
 */

public final class RBTree {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    static int Comparisons = 0;
    private Node root;     // root of the BST
    private class Node
    {
        private int key;           // key
        private Node left, right, parent;  // links to left and right subtrees
        private boolean color;     // color of parent link
        public Node(int key, boolean color)
        {
            this.key = key;
            this.color = color;
            this.left = this.right = null;
            this.parent = null;
        }
    }
    private void RotateLeft(Node x)
    {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }
    private void RotateRight(Node x)
    {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    private void RB_FIX(Node x)
    {
    	Node y;
        while (x != root && x.parent.color == RED)
        {
            if (x.parent == x.parent.parent.left)
            {
                y = x.parent.parent.right;
                if (y != null && y.color == RED)
                {
                    x.parent.color = BLACK;
                    y.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                }
                else
                {
                    if (x == x.parent.right)
                    {
                        x = x.parent;
                        RotateLeft(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    RotateRight(x.parent.parent);
                }
            }
            else
            {
                y = x.parent.parent.left;
                if (y != null && y.color == RED)
                {
                    x.parent.color = BLACK;
                    y.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                }
                else
                {
                    if (x == x.parent.left)
                    {
                        x = x.parent;
                        RotateRight(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    RotateLeft(x.parent.parent);
                }
            }
        }
        root.color = BLACK;
    	}
    public void RB_INSERT(int key)
    {
        Node y = null;
        Node x = root;
        Node z = new Node(key, RED);
        while (x != null)
        {
            y = x;
            Comparisons++;
            if (key < x.key) x = x.left;
            else x = x.right;
        }
        z.parent = y;
        if (y == null) root = z;
        else if (key < y.key) y.left = z;
        else y.right = z;
        if (z.parent == null || z.parent.parent == null) return;
        RB_FIX(z);
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
