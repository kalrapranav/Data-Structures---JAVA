//WORK IN PROGRESS
package data_structures;


import java.util.Comparator;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E> implements PriorityQueue {
    private E[] array;
    private int capacity;
    private int lastIndex;
    private Comparator<E> comparator;

    public BinaryHeapPriorityQueue(int capacity, Comparator<E> comparator) {
        this.array = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.lastIndex = -1;
        this.comparator = comparator;
    }

    private void bottomUpHeapify(int index) {
        //@// TODO: 11/12/16 This implementation is not optimized
        int parentIndex = getParent(index);

        /** Checking if we reached at parent or not. **/
        if (index == 0 || !isValidIndex(parentIndex)) {
            return;
        }

        E childElement = array[index];
        E parentElement = array[parentIndex];

        /** Comparing parent element with child **/
        int result = comparator.compare(parentElement, childElement);

        /** If parent is less than or equal to child then don't do anything. **/
        if (result <= 0) {
            return;
        }

        /** If parent is greater than child then, swap value **/
        array[index] = parentElement;
        array[parentIndex] = childElement;

        /** And recursively validate parent with grandparent **/
        bottomUpHeapify(parentIndex);
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= lastIndex;
    }

    public boolean insert(Comparable object) {
        if (isFull()) {
            throw new RuntimeException("Heap is full.");
        }
        array[++lastIndex] = (E) object;
        bottomUpHeapify(lastIndex);
        return true;
    }
    
    public Comparable remove() {
        return null;
    }

    public boolean delete(Comparable obj) {
        return false;
    }

    public Comparable peek() {
        return null;
    }

    public boolean contains(Comparable obj) {
        return false;
    }

    public int size() {
        return 0;
    }

    public void clear() {

    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return lastIndex + 1 == capacity;
    }

    public Iterator iterator() {
        return null;
    }


}
