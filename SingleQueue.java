package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> value contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author 
 * @version 2021-09-24
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to values in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

        SingleNode<T> left_current = left.front;
        SingleNode<T> right_current = right.front;

        this.front = new SingleNode<T>(left_current.getData(), null);//extract from left stack
        this.rear = this.front;

        left_current = left_current.getNext();//update current node

        this.length++;
        
        if(left.getLength() > right.getLength()){//run this if left stack is longer than right stack
            while(right_current != null){//alternate node swapping until right stack is finished
                this._insert(right_current.getData());
                right_current = right_current.getNext();
                this._insert(left_current.getData());
                left_current = left_current.getNext();
            }
            while(left_current != null){//finish pushing remaining nodes in left stack
                this._insert(left_current.getData());
                left_current = left_current.getNext();
            }
        }

        else if(left.getLength() < right.getLength()){//run this if left stack is shorter than right stack
            while(left_current != null){//alternate node swapping until left stack is finished
                this._insert(right_current.getData());
                right_current = right_current.getNext();
                this._insert(left_current.getData());
                left_current = left_current.getNext();
            }
            while(right_current != null){//finish pushing remaining nodes in right stack
                this._insert(right_current.getData());
                right_current = right_current.getNext();
            }
        }

        else{//run this if both stacks are of same length
            while(right_current != null){
                this._insert(right_current.getData());
                right_current = right_current.getNext();
                this._insert(left_current.getData());
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
     * Adds value to the rear of the queue. Increments the queue length.
     *
     * @param data The value to added to the rear of the queue.
     */
    public void insert(final T data) {

        if(this.getLength() == 0){
            this.rear = new SingleNode<T>(data,null);
            this.front = this.rear;
        }          
            
        else{
            SingleNode<T> noodle = new SingleNode<T>(data,null);
            this.rear.setNext(noodle);
            this.rear = noodle;
        }
            
        this.length++;
	return;
    }

    public void _insert(final T data) {//hidden insert
        if(this.getLength() == 0){
            this.front = this.rear = new SingleNode<T>(data,null);
        }                   
        else{
            SingleNode<T> noodle = new SingleNode<T>(data,null);
            this.rear.setNext(noodle);
            this.rear = noodle;
        }          
        this.length++;
	return;
    }

    /**
     * Returns the front value of the queue and removes that value from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The value at the front of the queue.
     */
    public T remove() {
        T result = this.front.getData();

        if(this.getLength() == 1){
            this.front = null;
            this.rear = null;
            this.length = 0;
        }
        else{
            this.front = this.front.getNext();
            this.length--;
        }

	return result;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move value or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain values.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

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
            left._insert(current.getData());
            current = current.getNext();

            if(current != null){
                right._insert(current.getData());
                current = current.getNext();
            }
        }
        this.front = null;
        this.rear = null;
        this.length = 0;

	return;
    }
}
