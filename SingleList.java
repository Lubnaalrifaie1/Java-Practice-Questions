package cp213;

import java.nio.channels.NonReadableChannelException;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author 
 * @version 2021-09-24
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

        SingleNode<T> current = this.front;
        SingleNode<T> previous = null;

        while(current != null){
            if((Integer) current.getData() == (Integer) key){
                return previous;
            }
            else{       
                previous = current;
                current = current.getNext();
            }
        }
	return null;
    }


    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The value to append.
     */
    public void append(final T data) {

        SingleNode<T> current = new SingleNode<T>(data, null);

        if(this.length == 0){
            this.front = current;
        }

        else{
            this.rear.setNext(current);
        }   
        this.rear = current;
        this.length++;      

	return;
    }

    public void _append(final T data) { 
        SingleNode<T> current = new SingleNode<T>(data, null);
        if(this.length == 0){
            this.front = current;
        }
        else{
            this.rear.setNext(current);
        }   
        this.rear = current;
        this.length++;      
	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {

        SingleNode<T> current1 = this.front;
        while(current1 != null){
            T value = current1.getData();
            SingleNode<T> current2 = current1.getNext();
            while(current2 != null){
                if((Integer) current2.getData() == (Integer) value){
                    this.remove(current2.getData());
                }
                current2 = current2.getNext();
            }
            current1 = current1.getNext();
        } 
	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

        SingleNode<T> left_current = left.front;
        SingleNode<T> right_current = right.front;

        this.front = new SingleNode<T>(left_current.getData(), null);//extract from left stack
        this.rear = this.front;

        left_current = left_current.getNext();//update current node

        this.length++;
        
        if(left.getLength() > right.getLength()){//run this if left stack is longer than right stack
            while(right_current != null){//alternate node swapping until right stack is finished
                this._append(right_current.getData());
                right_current = right_current.getNext();
                this._append(left_current.getData());
                left_current = left_current.getNext();
            }
            while(left_current != null){//finish pushing remaining nodes in left stack
                this._append(left_current.getData());
                left_current = left_current.getNext();
            }
        }

        else if(left.getLength() < right.getLength()){//run this if left stack is shorter than right stack
            while(left_current != null){//alternate node swapping until left stack is finished
                this._append(right_current.getData());
                right_current = right_current.getNext();
                this._append(left_current.getData());
                left_current = left_current.getNext();
            }
            while(right_current != null){//finish pushing remaining nodes in right stack
                this._append(right_current.getData());
                right_current = right_current.getNext();
            }
        }

        else{//run this if both stacks are of same length
            while(right_current != null){
                this._append(right_current.getData());
                right_current = right_current.getNext();
                this._append(left_current.getData());
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
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
        
        SingleNode<T> current = this.front;
        boolean found = false;
    
        while(current != null & found == false){
            if((Integer) current.getData() == (Integer) key){
                found = true;
            }
            else{      
                current = current.getNext();
            }
        }


	return found;
    }
    
    public boolean _contains(final T key) {
        
        SingleNode<T> current = this.front;
        boolean found = false;
    
        while(current != null & found == false){
            if((Integer) current.getData() == (Integer) key){
                found = true;
            }
            else{      
                current = current.getNext();
            }
        }


	return found;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {
        int count = 0;
        SingleNode<T> current = this.front;

        while(current != null){
            if((Integer) current.getData() == (Integer) key){
                count++;
            }
            current = current.getNext();
        }

	return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {
        SingleNode<T> current = this.front;
        boolean found = false;
    
        while(current != null & found == false){
            if((Integer) current.getData() == (Integer) key){
                found = true;
            }
            else{      
                current = current.getNext();
            }
        }
        if(found == false){
            return null;
        }
	return current.getData();
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	    int counter = 0;
        SingleNode<T> current = this.front;

        while(counter < n){
            current = current.getNext();
            counter++;
        }

	return current.getData();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {
        SingleNode<T> current1 = this.front;
        SingleNode<T> current2 = source.front;
        boolean result = true;

        if(this.length != source.length){
            result = false;
        }

        while(current1 != null & result){
            if((Integer) current1.getData() != (Integer) current2.getData()){
                result = false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

	return result;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {
	    int counter = 0;
        SingleNode<T> current = this.front;
        boolean found = false;

        while(current != null & found == false){
            if((Integer) current.getData() == (Integer) key){
                found = true;
            }
            else{
                current = current.getNext();
                counter++;
            }
        }
        if(found){
            return counter;
        }
	return -1;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i     The index to insert the new data at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {
        if(i < 0){
            i = this.length + i;
        }

        if(i >= this.length){
            this._append(data);
        }

        else{
            int count = 0;
            SingleNode<T> previous = null;
            SingleNode<T> current = this.front;
            while(count < i){
                previous = current;
                current = current.getNext();
                count++;
            }
            SingleNode<T> node = new SingleNode<T>(data, current);
            previous.setNext(node);
            this.length++;

        }

	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

        SingleNode<T> leftCurr = left.front;
        while(leftCurr!=null){
            if(right._contains(leftCurr.getData())){
                this._append(leftCurr.getData());
            }
            leftCurr = leftCurr.getNext();
        }

	return;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {
        SingleNode<T> current = this.front;
	    T maxx = this.front.getData();

        while(current != null){
            if((Integer) current.getData() > (Integer) maxx){
                maxx = current.getData();
            }
            current = current.getNext();
        }
	return maxx;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {
        SingleNode<T> current = this.front;
	    T minn = this.front.getData();

        while(current != null){
            if((Integer) current.getData() < (Integer) minn){
                minn = current.getData();
            }
            current = current.getNext();
        }

	return minn;
    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {

        this.front = new SingleNode<T>(data, this.front);
        if(this.length == 0){
            this.rear = this.front;
        }

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {

        SingleNode<T> previous = this.linearSearch(key);
        T value = null;
        
        if(previous != null){
            if((Integer) previous.getData() == (Integer) this.rear.getData()){
                this.rear = this.linearSearch(previous.getData());
                this.length--;
                value = previous.getData();
            }
            else{
                SingleNode<T> current = previous.getNext();
                value = current.getData();
                previous.setNext(current.getNext());
                this.length--;
            }
            
        }
        else if(this.front!=null && (Integer) this.front.getData() == (Integer)key){
            this.front = this.front.getNext();
            this.length--;
            if(this.front== null){
                this.rear = null;
            }
            
        } 

	return value;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {
        T value = this.front.getData();
        this.front = this.front.getNext();
        this.length--;
        if(this.front== null){
            this.rear = null;
        }

	return value;
    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {
        SingleNode<T> current = this.front;

	    while(current != null){
            if((Integer) current.getData() == (Integer) key){
                this.remove(current.getData());
            }
            current = current.getNext();
        }

	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {

        this.rear = this.front;
        SingleNode<T> previous = null;
        SingleNode<T> current = this.front;

        while(current != null){
            SingleNode<T> temp = current.getNext();
            current.setNext(previous);
            previous = current;
            current = temp;
        }

       this.front = previous;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {
        SingleNode<T> current = this.front;
        int count = 0;

        if(this.length % 2 == 0){
            while(count < this.length/2){
                left._append(current.getData());
                current = current.getNext();
                count++;
            }
            while(current != null){
                right._append(current.getData());
                current = current.getNext();
            }
        }

        else{
            left._append(current.getData());
            current = current.getNext();
            while(count < this.length/2){
                left._append(current.getData());
                current = current.getNext();
                count++;
            }
            while(current != null){
                right._append(current.getData());
                current = current.getNext();
            }
        }

        this.front = null;
        this.rear = null;
        this.length = 0;
	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

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
            left._append(current.getData());
            current = current.getNext();

            if(current != null){
                right._append(current.getData());
                current = current.getNext();
            }
        }
        this.front = null;
        this.rear = null;
        this.length = 0;

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	    SingleNode<T> curr = left.front;
        while(curr != null){
            if(this._contains(curr.getData()) == false){
                this._append(curr.getData());
            }
            else{
                curr = curr.getNext();
            }
        }
        curr = right.front;
        while(curr != null){
            if(this._contains(curr.getData()) == false){
                this._append(curr.getData());
            }
            else{
                curr = curr.getNext();
            }
        }

	return;
    }
}
