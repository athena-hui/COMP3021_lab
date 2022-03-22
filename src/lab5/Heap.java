package lab5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
//add by me
import java.lang.IllegalStateException;

/**
 * TODO class declaration
 * We want to accept any type which is comparable to itself
 *
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {
    private ArrayList<T> container;

    public Heap() {
        container = new ArrayList<>();
    }

    /**
     * @return If size is 0, throw {@link IllegalStateException}.
     * Otherwise, return the first element of {@link this#container}
     */
    public T peek() {
        //TODO
		if (this.size() == 0) {
			throw new IllegalStateException("Container size is zero!");
		}
		return container.get(0);// replace this line with implementation
    }

    /**
     *
     * @return If size is 0, throw {@link IllegalStateException}. Otherwise, temporarily save the first element.
     * Afterwards, set the first position to the last element, and remove the last element.
     * Call {@link this#heapifyDown()}, then return the original first element
     */
    public T poll() {
        //TODO
    	if (this.size() == 0) {
			throw new IllegalStateException("Container size is zero!");
		}
    	//temporarily save the first element
		T temp_head = container.get(0);
		// set the first position to the last element
		container.set(0, container.get(container.size()-1));
		//remove the last element
		container.remove(container.size()-1);
		//call heapifyDown for arrange the new head to a correct position 
		this.heapifyDown();
		//return the original first element
        return temp_head; // replace this line with implementation
    }

    private void heapifyDown() {
        int pos = 0;
        while (hasLeft(pos)) {
            int smallerChildIndex = getLeftIndex(pos);
            if (hasRight(pos) && container.get(getRightIndex(pos)).compareTo(container.get(getLeftIndex(pos))) < 0) {
                smallerChildIndex = getRightIndex(pos);
            }
            if (container.get(pos).compareTo(container.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(pos, smallerChildIndex);
            }
            pos = smallerChildIndex;
        }
    }

    /**
     * Add the object into {@link this#container}, then call {@link this#heapifyUp()}
     *
     * @param obj the object to add
     */
    public void add(T obj) {
        //TODO
    	//add object to container
    	container.add(obj);
    	//call heapifyUp to arrange the new added object to the correct position
    	this.heapifyUp();
    }

    public void addAll(Collection<T> list) {
        list.forEach(this::add);
    }

    /**s
     * While the last element has a parent and is smaller than its parent, swap the two elements. Then, check again
     * with the new parent until there's either no parent or we're larger than our parent.
     */
    private void heapifyUp() {
        // TODO
    	//set the index to the last element(must be a child)
    	int index_arraging = size() - 1;
    	//start while loop
    	while (hasParent(index_arraging) && (container.get(index_arraging).compareTo(container.get(getParentIndex(index_arraging))) < 0)) {
    		swap(index_arraging, getParentIndex(index_arraging));
    		//update the index as the parent
    		index_arraging = getParentIndex(index_arraging);
    	}
    }

    public int size() {
        return container.size();
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeft(int i) {
        return getLeftIndex(i) < container.size();
    }

    private boolean hasRight(int i) {
        return getRightIndex(i) < container.size();
    }

    private void swap(int i1, int i2) {
        Collections.swap(container, i1, i2);
    }
}
