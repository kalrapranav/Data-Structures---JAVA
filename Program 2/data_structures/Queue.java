/**
 *  Program #2
 *  The following program uses the methods from LinearList class
 *  to implement a Queue
 *  CS310-1
 *  03-11-2019
 *  @author  Pranav Kalra cssc1483
 */
package data_structures;

import java.util.Iterator;

public class Queue<E extends Comparable<E>> implements Iterable<E> {
    private LinearList<E> linearList = new LinearList<E>();
    public Queue() {}
    /*inserts the object obj into the queue
     */
    public void enqueue(E obj) {
        linearList.addLast(obj);
    }

    /* removes and returns the object at the front of the queue
     */
    public E dequeue() {
        return linearList.removeFirst();
    }

    /* returns the number of objects currently in the queue
     */
    public int size() {
        return linearList.size();
    }

    /* returns true if the queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return linearList.isEmpty();
    }

    /* returns but does not remove the object at the front of the queue
     */
    public E peek() {
        return linearList.peekFirst();
    }

    /* returns true if the Object obj is in the queue
     */
    public boolean contains(E obj) {
        return linearList.contains(obj);
    }

    /* returns the queue to an empty state
     */
    public void makeEmpty() {
        linearList.clear();
    }

    /* removes the Object obj if it is in the queue and
     * returns true, otherwise returns false.
     */
    public boolean remove(E obj) {
        if (linearList.remove(obj) == null)
            return false;
        else {
            linearList.remove(obj);
            return true;
        }
    }

    /* returns an iterator of the elements in the queue. The elements
     * must be in the same sequence as dequeue would return them.
     */
    public Iterator<E> iterator() {
        return linearList.iterator();
    }
}


//-----------------------------------END-OF-Queue-Class-------------------------------------------
