import org.omg.CosNaming.BindingIteratorHelper;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements LinkedListInterface<E> {

    class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;
        //constructor
        public Node(E obj) {
            data = obj;
            next = null;
            previous = null;
        }
    }

    class IteratorHelper implements Iterator<E> {
        //create a tmp pointer to point at the beginning
        //of the list
        Node<E> index;
        public IteratorHelper() {
            index = head;
        }
        public boolean hasNext(){
            return (index != null);
        }
        public E next(){
            if (!hasNext())
                throw new NoSuchElementException();
            E val = index.data;
            index = index.next;
            return val;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    //constructor
    public LinkedList() {
        head  = tail = null;
        currentSize = 0;
    }

    /*
    * In every method, take care of the five boundary conditions:
    * 1. Empty
    * 2. Single Element
    * 3. Working in Middle
    * 4. Working at the beginning
    * 5. Working at the end
    * */

    @Override
    public void addFirst(E obj) {
        //create a new node
        Node<E> node = new Node<E>(obj);

        //Empty List
        if (head == null) {
            node.next = null;
            head = node;
            currentSize++;
            return;
        }

        node.next = head;
        head = node;
        currentSize++;
        return;
    }

    @Override
    public void addLast(E obj) {
        Node<E> node = new Node<E>(obj);

        //Empty List
        if (head == null) {
            head = tail = node;
            currentSize++;
            return;
        }
        //Single Element
        tail.next = node;
        tail = node;
        currentSize++;
        return;
    }

    @Override
    public E removeFirst() {
        //Empty List
        if (head == null)
            return null;

        //To get head's data
        E tmp = head.data;
        //Single Element
        if (head == tail)
            head = tail = null;
        /*
            head = head.next -> this will point head to the
            successive element and the first element will get
            garbage collected
         */
        else
            head = head.next;
        currentSize--;
        //return's teh data of first element
        return tmp;
    }

    @Override
    public E removeLast() {
        //Empty List
        if (head == null)
            return null;
        //Single Element
        if (head == tail)
            removeFirst(); //head = tail = null;

        Node<E> current = head, previous = null;
        //To iterate to the end of the list
        while (current != null) {
            previous.next = null;
            tail = previous;
        }
        currentSize--;
        return current.data;
    }

    @Override
    public boolean contains(E obj) {
        Node<E> current = head;
        //find the element by iterating through the list
        while (current != null) {
            if (((Comparable<E>) current.data).compareTo(obj) == 0)
                 return true;
        }
        //if not found return false
        return false;
    }

    @Override
    public E find(E obj) {
        Node<E> current = head;
        //find the element by iterating through the list
        while (current != null) {
            if (((Comparable<E>) current.data).compareTo(obj) == 0)
                return current.data;
        }
        //if not found return null
        return null;
    }

    @Override
    public E remove(E obj) {
        Node<E> current = head, previous = null;
        while (current != null) {
            if (((Comparable<E>)current.data).compareTo(obj) == 0) {
                //Single element list
                if (current == head)
                    return removeFirst();
                if (current == tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next;
                return current.data;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) new IteratorHelper();
    }
}











