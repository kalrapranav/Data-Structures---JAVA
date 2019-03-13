/**
 *  Program #2
 *  The following program implements implements a Doubly Linked List
 *  and uses the Doubly Linked List class in order to implement Stack and Queue
 *  CS310-1
 *  03-11-2019
 *  @author  Pranav Kalra cssc1483
 */

/*I tried running at least too insert more than 99999999 elements in the list and it turns out if we go
* more than 99999999 elements it returns a out of memory error as all the space in teh java heap has been
* filled
* */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E>, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;
    private int modificationCounter;


    private class Node<E> {
        private E data;
        private Node next;
        private Node previous;

        public Node(E data) {
            this.data = data;
        }
    }

    public LinearList() {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }

    public boolean addFirst(E obj) {
        Node<E> node = new Node<E>(obj);
        if (isFull()) {
            return false;
        } else if (isEmpty())
            tail = node;
        else
            head.previous = node;

        node.next = head;
        head = node;
        currentSize++;
        modificationCounter++;
        return true;
    }

    public boolean addLast(E obj) {
        Node<E> node = new Node<E>(obj);
        if (isFull()) {
            return false;
        }
        if (isEmpty())
            head = node;
        else {
            tail.next = node;
            node.previous = tail;
        }
        tail = node;
        currentSize++;
        modificationCounter++;
        return true;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> temp = head;
        E temp1 = head.data;
        if (head == tail)
            tail = null;
        else
            head.next.previous = null;
        head = head.next;
        temp.next = null;
        currentSize--;
        modificationCounter++;
        return temp1;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node temp = tail;
        E temp1 = tail.data;
        if (head == tail)
            head = null;
        else
            tail.previous.next = null;
        tail = tail.previous;
        temp.previous = null;
        currentSize--;
        modificationCounter++;
        return temp1;
    }

    public E remove(E obj) {
        Node<E> currentNode = head, previousNode = null;
        while (currentNode != null) {
            if (((Comparable<E>) obj).compareTo(currentNode.data) == 0) {
                if (currentNode == head)
                    return removeFirst();
                else if (currentNode == tail)
                    return removeLast();
                currentSize--;
                modificationCounter++;
                previousNode.next = currentNode.next;
                currentNode.next.previous = previousNode;
                return currentNode.data;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }

    public boolean contains(E obj) {
        if (find(obj) == null) {return false;}
        return true;
    }

    public E find(E obj) {
        Node<E> currentNode = head;
        while (currentNode != null) {
            if (((Comparable<E>) obj).compareTo(currentNode.data) == 0)
                return currentNode.data;
            currentNode = currentNode.next;
        }
        return null;
    }

    public void clear() {
        head = tail = null;
        currentSize = 0;
        modificationCounter++;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return currentSize;
    }

    public Iterator<E> iterator() {
        return new LinearListIterator();
    }

    class LinearListIterator<E> implements Iterator<E> {
        Node<E> currentNode = null;
        long getStateCheck, stateCheck = modificationCounter;

        public LinearListIterator() {
            currentNode = (Node<E>) head;
            getStateCheck = 0;
            stateCheck = modificationCounter;
        }

        public boolean hasNext() {
            if (stateCheck != modificationCounter)
                throw new ConcurrentModificationException();
            return (getStateCheck != currentSize);
            //return (currentNode != null);
        }

        public E next() {
            E temp = currentNode.data;
            if (!hasNext())
                throw new NoSuchElementException();
            else {
                currentNode = currentNode.next;
                getStateCheck += 1;
            }
            return temp;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

//-----------------------------------END-OF-LinearList-Class-------------------------------------------
