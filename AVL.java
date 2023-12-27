package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author 
 * @author David Brown
 * @version 2021-07-05
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

  /**
   * Returns the balance value of node. If greater than 1, then left heavy, if
   * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
   * is balanced. Used to determine whether to rotate a node upon insertion.
   *
   * @param node The TreeNode to analyze for balance.
   * @return A balance number.
   */
  private int balance(final TreeNode<T> node) {
    return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
  }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

      final TreeNode<T> result = node.getRight();
      node.setRight(result.getLeft());
      result.setLeft(node);
      
      node.updateHeight();
      result.updateHeight();
  
    return result;
      }
  
      /**
       * Performs a right rotation around {@code node}.
       *
       * @param node The subtree to rotate.
       * @return The new root of the subtree.
       */
      private TreeNode<T> rotateRight(final TreeNode<T> node) {
  
      final TreeNode<T> result = node.getLeft();
      node.setLeft(result.getRight());
      result.setRight(node);
      
      node.updateHeight();
  
    return result;
      }

  /**
   * Auxiliary method for {@code insert}. Inserts data into this AVL.
   *
   * @param node the current node (TreeNode)
   * @param data Data to be inserted into the node
   * @return The inserted node.
   */
  @Override
  protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

    if(node == null) {
      node = new TreeNode<T>(data);
      node.getItem().incrementCount();
    }

    else{
      if(node.getItem().compareTo(data) < 0) {//check the right side of the tree for insertion
        node.setRight(this.insertAux(node.getRight(), data));
        node.updateHeight();

        if(this.balance(node) < -1) {
          if (this.balance(node.getRight()) <= 0) {
            node = this.rotateLeft(node);
          } 

          else if(this.balance(node.getRight()) > 0) {
            node.setRight(this.rotateRight(node.getRight()));
            node = this.rotateLeft(node);
          }
        }
      }
      
      else if(node.getItem().compareTo(data) > 0) {//check the left side of the tree for insertion
        node.setLeft(this.insertAux(node.getLeft(), data));
        node.updateHeight();

        if(this.balance(node) > 1) {
          if (this.balance(node.getLeft()) >= 0) {
            node = this.rotateRight(node);
          } 

          else if(this.balance(node.getLeft()) < 0) {
            node.setLeft(this.rotateLeft(node.getLeft()));
            node = this.rotateRight(node);
          }
        }
      } 
      
      else {
        node.getItem().incrementCount();
      }
    }
    return node;
  }

  /**
   * Determines whether two AVLs are identical.
   *
   * @param target The AVL to compare this AVL against.
   * @return true if this AVL and target contain nodes that match in position,
   *         value, count, and height, false otherwise.
   */
  public boolean equals(final AVL<T> target) {
    return super.equals(target);
  }

}
