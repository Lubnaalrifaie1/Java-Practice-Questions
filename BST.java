package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-11-01
 */
public class BST<T extends Comparable<T>> {
    protected int comparisons = 0; // Count of comparisons performed by tree.

    // Attributes.
    protected TreeNode<T> root = null; // Root node of the tree.
    protected int size = 0; // Number of elements in the tree.

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in items and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in items and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

		boolean result = false;

		if (source == null && target == null) {
			result = true;
		}
        else{
            if(source.getItem().compareTo(target.getItem()) == 0){
                if(source.getHeight() == target.getHeight()){
                    if(source.getItem().getCount() == target.getItem().getCount()){
                        result = this.equalsAux(source.getLeft(), target.getLeft()) && this.equalsAux(source.getRight(), target.getRight());
                    }
                }
            }
        } 

	return result;
    }

    /**
     * Auxiliary method for {@code insert}. Inserts data into this BST.
     *
     * @param node the current node (TreeNode)
     * @param data data to be inserted into the node
     * @return the inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

        if(node == null){
            node = new TreeNode<T>(data);
            node.getItem().incrementCount();
            this.size++;
        }
        else{
            if(node.getItem().compareTo(data) < 0){
                node.setRight(this.insertAux(node.getRight(), data));
            }
            else if(node.getItem().compareTo(data) > 0){
                node.setLeft(this.insertAux(node.getLeft(), data));
            }
            else{
                node.getItem().incrementCount();
            }
        }
        node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
		boolean result = false;

		if (node == null) {//Base Case 
			result = true;
		} 

        else{
            int heightCheck = Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight()));
            
            if ( heightCheck != node.getHeight() - 1) {//terminates if height is of incorrect size
                result = false;
            } 

            else if (node.getLeft() != null && node.getLeft().getItem().compareTo(node.getItem()) >= 0) {//terminates if data insertion error occured
                result = false;
            }

            else if(node.getRight() != null && node.getRight().getItem().compareTo(node.getItem()) <= 0){//terminates if data insertion error occured
                result = false;
            }

            else {
                result = this.isValidAux(node.getLeft(), null, null) && this.isValidAux(node.getRight(), null, null);//recursive case, null is a place holder idk what the additional params do
            }
        }
		return result;
    }

    /**
     * Returns the height of a given TreeNode.
     *
     * @param node The TreeNode to determine the height of.
     * @return The item of the height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
        int height = 0;

        if (node != null) {
            height = node.getHeight();
        }
	return height;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this BST contains key, false otherwise.
     */
    public boolean contains(final CountedItem<T> key) {
        boolean result = false;
        ArrayList<CountedItem<T>> queue = this.root.inOrder();
        
        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i).compareTo(key) == 0){
                result = true;
            }
        }
	return result;
    }

    /**
     * Determines whether two BSTs are identical.
     *
     * @param target The BST to compare this BST against.
     * @return true if this BST and that BST contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
        boolean isEqual = false;

        if (this.size == target.size) {
            isEqual = this.equalsAux(this.root, target.root);
        }
	return isEqual;
    }

    /**
     * Get number of comparisons executed by the {@code retrieve} method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this BST.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
        int height = 0;

        if (this.root != null) {
            height = this.root.getHeight();
        }
        return height;
    }

    /**
     * Returns the number of nodes in the BST.
     *
     * @return number of node in this BST.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns an array of copies of CountedItem objects in a linked data
     * structure. The array contents are in data order from smallest to largest.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedItem<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this BST.
     *
     * @param data Data to store.
     */
    public void insert(final CountedItem<T> data) {

        this.root = this.insertAux(this.root, data);

	return;
    }

    /**
     * Determines if this BST is empty.
     *
     * @return true if this BST is empty, false otherwise.
     */
    public boolean isEmpty() {
	return this.root == null;
    }

    /**
     * Determines if this BST is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this BST is a valid BST, false otherwise.
     */
    public boolean isValid() {
	return this.isValidAux(this.root, null, null);
    }

    /**
     * Returns an array of copies of CountedItem objects int a linked data
     * structure. The array contents are in level order starting from the root
     * (this) node. Helps determine the structure of the tree.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedItem<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have item
     * count of 0). Returning a complete CountedItem gives access to the
     * item and count.
     *
     * @param key The key to look for.
     * @return data The complete CountedItem that matches key, null otherwise.
     */
    public CountedItem<T> retrieve(final CountedItem<T> key) {

		TreeNode<T> node = this.root;
		CountedItem<T> data = null;

		while (node != null && data == null) {
			this.comparisons++;//IMPORTANT. INCREMENTS COMPARISON COUNT DONT DELETE
			if (node.getItem().compareTo(key) > 0) {//loop through left side of tree
				node = node.getLeft();
			} 

            else if (node.getItem().compareTo(key) < 0) {//loop through right side of tree
				node = node.getRight();
			} 
            
            else {
				data = node.getItem();//the value was found
			}
		}

	return data;
    }
}
