package cp213;

/**
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> value contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author 
 * @version 2021-09-24
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleStacks into the current
     * SingleStack. Moves nodes only - does not refer to values in any way, or call
     * the high-level methods pop or push. left and right SingleStacks are empty
     * when done. Nodes are moved alternately from left and right to this
     * SingleStack.
     *
     * You have two source stacks named left and right. Move all nodes from these
     * two stacks to the current stack. It does not make a difference if the current
     * stack is empty or not, just get nodes from the right and left stacks and add
     * them to the current stack. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left stacks are of the same length.
     *
     * @param left  The first SingleStack to extract nodes from.
     * @param right The second SingleStack to extract nodes from.
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {

        SingleNode<T> left_current = left.front;
        SingleNode<T> right_current = right.front;

        this.front = new SingleNode<T>(left_current.getData(), null);//extract from left stack
        this.rear = this.front;

        left_current = left_current.getNext();//update current node

        this.length++;
        
        if(left.getLength() > right.getLength()){//run this if left stack is longer than right stack
            while(right_current != null){//alternate node swapping until right stack is finished
                this._push(right_current.getData());
                right_current = right_current.getNext();
                this._push(left_current.getData());
                left_current = left_current.getNext();
            }
            while(left_current != null){//finish pushing remaining nodes in left stack
                this._push(left_current.getData());
                left_current = left_current.getNext();
            }
        }

        else if(left.getLength() < right.getLength()){//run this if left stack is shorter than right stack
            while(left_current != null){//alternate node swapping until left stack is finished
                this._push(right_current.getData());
                right_current = right_current.getNext();
                this._push(left_current.getData());
                left_current = left_current.getNext();
            }
            while(right_current != null){//finish pushing remaining nodes in right stack
                this._push(right_current.getData());
                right_current = right_current.getNext();
            }
        }

        else{//run this if both stacks are of same length
            while(right_current != null){
                this._push(right_current.getData());
                right_current = right_current.getNext();
                this._push(left_current.getData());
                left_current = left_current.getNext();
            }
        }

        left.length = 0;
        left.front = null;
        left.rear = null;

        right.length = 0;
        right.front = null;
        right.rear = null;

	return;
    }

    /**
     * Returns the top value of the stack and removes that value from the stack. The
     * next node in the stack becomes the new top node. Decrements the stack length.
     *
     * @return The value at the top of the stack.
     */
    public T pop() {

        T data = this.front.getData();
        this.front = this.front.getNext();
        this.length --;

    return data;
    }

    /**
     * Adds data to the top of the stack. Increments the stack length.
     *
     * @param data The value to add to the top of the stack.
     */
    public void push(final T data) {
    	if (this.getLength() == 0) {
    		this.front = new SingleNode<T>(data,null);
    		this.rear = this.front;
    		  		
    	} else {
    		SingleNode<T> x = new SingleNode<T> (data,this.front);
    		this.front = x;
    		
    	}
        this.length++;

	return;
    }

    public void _push(final T data) {//hidden push
    	if (this.getLength() == 0) {
    		this.rear = this.front = new SingleNode<T>(data,null);
    	} else {
    		this.front = new SingleNode<T> (data,this.front);  		
    	}
        this.length++;

	return;
    }

    /**
     * Splits the contents of the current SingleStack into the left and right
     * SingleStacks. Moves nodes only - does not move value or call the high-level
     * methods insert or remove. this SingleStack is empty when done. Nodes are
     * moved alternately from this SingleStack to left and right. left and right may
     * already contain values.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleStack to move nodes to.
     * @param right The second SingleStack to move nodes to.
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {

        SingleNode<T> current = this.front;
        
        left.front = new SingleNode<T>(current.getData(), null);
        left.rear = left.front;

        current = this.front.getNext();

        right.front = new SingleNode<T>(current.getData(), null);
        right.rear = right.front;

        current = current.getNext();

        left.length++;
        right.length++;
        this.length = this.length-2;

        while(current != null){
            left._push(current.getData());
            current = current.getNext();

            if(current != null){
                right._push(current.getData());
                current = current.getNext();
            }
        }
        this.front = null;
        this.rear = null;
        this.length = 0;

	return;
    }
}