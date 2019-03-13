/**
 *  Program #2
 *  The following program uses the methods from LinearList class
 *  to implement a Stack
 *  CS310-1
 *  03-11-2019
 *  @author  Pranav Kalra cssc1483
 */
package data_structures;

import java.util.Iterator;

public class Stack<E extends Comparable<E>> implements Iterable<E>{
    LinearList<E> linearList = new LinearList<E>();
    /* inserts the object obj into the stack
     */
    public void push(E obj) {
        linearList.addFirst(obj);
    }

    /* pops and returns the element on the top of the stack
     */
    public E pop() {
        return linearList.removeFirst();
    }

    /* returns the number of elements currently in the stack
     */
    public int size() {
        return linearList.size();
}

    /* return true if the stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return linearList.isEmpty();
    }

    /* returns but does not remove the element on the top of the stack
     */
    public E peek() {
        return linearList.peekFirst();
    }

    /* returns true if the object obj is in the stack,
     * otherwise false
     */
    public boolean contains(E obj) {
        return linearList.contains(obj);
    }

    /* returns the stack to an empty state
     */
    public void makeEmpty() {
        linearList.clear();
    }

    /* removes the Object obj if it is in the stack and
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

    /* returns a iterator of the elements in the stack. The elements
     * must be in the same sequence as pop() would return them.
     */
    public Iterator<E> iterator() {
       return linearList.iterator();

    }
}
