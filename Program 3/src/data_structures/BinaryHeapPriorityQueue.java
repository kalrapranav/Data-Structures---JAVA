//WORK IN PROGRESS
package data_structures;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

//    /*
//    * Variables
//    * - arrayList stores the all the entries
//    * - capacity is the max capacity of the list
//    * - last index the index of the last element of the heap
//    * */
//    private E[] arrayList;
//    private int capacity;
//    private int lastIndex;
//    private Comparator<E> comparator;
//
//    /*
//    * Constructors
//    * - One Parameter : comparator
//    * - Two Parameter : comparator, capacity
//    * */
//
//    BinaryHeapPriorityQueue(Comparator<E> comparator) {
//        this.arrayList = (E[]) new Object[DEFAULT_MAX_CAPACITY];
//        this.capacity = DEFAULT_MAX_CAPACITY;
//        this.lastIndex = -1;
//        this.comparator = comparator;
//    }
//    BinaryHeapPriorityQueue(int capacity, Comparator<E> comparator) {
//        this.arrayList = (E[]) new Object[capacity];
//        this.capacity = capacity;
//        this.lastIndex = -1;
//        this.comparator = comparator;
//    }

    private E[] theHeap;
    private int capacity;
    private int pos;

    public BinaryHeapPriorityQueue() {
        pos = 1;
        capacity = 10;
        theHeap = (E[]) new Object[capacity];
    }



    public boolean insert(E object) {
//        if (isFull()) {return false;}
//        arrayList[lastIndex+1] = object;
//        //if-heapViolation then upHeap
//        upHeap(object);
        if (pos == capacity) {
            //increase capacity
        } else {
            theHeap[pos++] = object;

            int child = pos-1;
            int parent = child/2;

            //This is in accordance to the Heap Violation
            //compare parent position with child and parent > 0
            // if yes then swap the parent with the child

            //child = parent
            //parent = child/2
        }

        return false;
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
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }
}
