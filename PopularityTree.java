package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-11-01
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for {@code valid}. May force node rotation if the retrieval
     * count of the located node item is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The item to search for. Count is updated to count in matching
     *             node item if key is found.
     * @return the updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

        if(node!=null){
			this.comparisons++;

			if(node.getItem().compareTo(key) > 0) {
				node.setLeft(this.retrieveAux(node.getLeft(), key));

				if(node.getLeft() != null){
                    if(node.getItem().getCount() < node.getLeft().getItem().getCount()){
                        node = this.rotateRight(node);
                    }	
				}
			} 

            else if(node.getItem().compareTo(key) < 0) {
				node.setRight(this.retrieveAux(node.getRight(), key));

				if(node.getRight() != null){
                    if(node.getItem().getCount() < node.getRight().getItem().getCount()){
                        node = this.rotateLeft(node);
                    }
				}
			} 

            else{
				node.getItem().incrementCount();
				key.setCount(node.getItem().getCount());
			}
			node.updateHeight();
		}

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

    	final TreeNode<T> result = parent.getRight();	
		parent.setRight(result.getLeft());
		result.setLeft(parent);
		
		parent.updateHeight();
		result.updateHeight();

	return result;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

		final TreeNode<T> result = parent.getLeft();	
		parent.setLeft(result.getRight());
		result.setRight(parent);
		
		parent.updateHeight();
		result.updateHeight();

	return result;
    }

    /**
     * Replaces BST {@code insertAux} - does not increment count on repeated
     * insertion. Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

        if(node != null){
            if(node.getItem().compareTo(data) > 0){
                node.setLeft(this.insertAux(node.getLeft(), data));
            }
            else if(node.getItem().compareTo(data) < 0){
                node.setRight(this.insertAux(node.getRight(), data));
            }
		}
        
    	else{
			node = new TreeNode<T>(data);
			this.size++;
		} 
		node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree. An Popularity Tree must meet the BST validation conditions,
     * and additionally the counts of any node data must be greater than or equal to
     * the counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
    	boolean result = false;
		
        if (node == null) {
			result = true;
		} 

        else{
            int heightCheck = Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight()));      
            
            if ( heightCheck != node.getHeight() - 1) {
                result = false;
            } 

            else if (node.getLeft() != null && node.getLeft().getItem().compareTo(node.getItem()) >= 0) {
                result = false;
            } 

            else if(node.getRight() != null && node.getRight().getItem().compareTo(node.getItem()) <= 0){
                result = false;
            }

            else if (node.getLeft() != null && node.getItem().getCount() < node.getLeft().getItem().getCount()) {
                result = false;
            } 

            else if(node.getRight() != null && node.getItem().getCount() < node.getRight().getItem().getCount()){
                result = false;
            }
            else {
                result = this.isValidAux(node.getLeft(), null, null) && this.isValidAux(node.getRight(), null, null);
            }
        }
		return result;
    }

    /**
     * Very similar to the BST retrieve, but increments the character count here
     * instead of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {

    	this.root = this.retrieveAux(this.root, key);
		
    	if (key.getCount() == 0) {
			key = null;
		} 
        
        else {
			key = new CountedItem<T>(key);
		}
		return key;
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, item, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

}
