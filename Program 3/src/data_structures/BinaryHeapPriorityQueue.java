//WORK IN PROGRESS
package data_structures;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

    /*
    * Variables
    * - arrayList stores the all the entries
    * - capacity is the max capacity of the list
    * - last index the index of the last element of the heap
    * */
    private E[] arrayList;
    private int capacity;
    private int lastIndex;
    private Comparator<E> comparator;

    /*
    * Constructors
    * - One Parameter : comparator
    * - Two Parameter : comparator, capacity
    * */

    /*Constructor with one parameter
      The capacity of the array is DEFAULT: 1000
    */
    BinaryHeapPriorityQueue(Comparator<E> comparator) {
        this.arrayList = (E[]) new Object[DEFAULT_MAX_CAPACITY];
        this.capacity = DEFAULT_MAX_CAPACITY;
        this.lastIndex = -1;
        this.comparator = comparator;
    }

    /*Constructor with two parameter
      The capacity of the array is set according to the user
    */
    BinaryHeapPriorityQueue(int capacity, Comparator<E> comparator) {
        this.arrayList = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.lastIndex = -1;
        this.comparator = comparator;
    }


    public boolean insert(E object) {
        if (isFull()) {
            throw  new RuntimeException("Heap is Full");
        }

        arrayList[lastIndex+1] = object;
        bottomHeapUp(lastIndex);
        return true;
    }

    private void bottomHeapUp(int index) {
        int parentIndex = getParent(index);

        if (index == 0 || !isValidIndex(parentIndex)) {

        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= lastIndex;
    }

    private int getLeftChild(int index) {
        return 2 * index + 1;
    }

    private int getRightChild(int index) {
        return 2 * index + 2;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private void upHeap(E object) {
    }

    public E remove() {
        return null;
    }

    public boolean delete(E obj) {
        return false;
    }

    public E peek() {
        return null;
    }

    public boolean contains(E obj) {
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

    public Iterator<E> iterator() {
        return null;
    }
}
