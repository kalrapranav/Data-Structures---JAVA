package data_structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.ConcurrentModificationException;

public class Hashtable<K extends Comparable<K>,V> implements DictionaryADT<K,V> {

    /*
    * This class allows to create a hashElement which
    * will take two arguments and Key and Value and each hashElement
    * would have those two values which can be assesed by the .key/value
    * */
    class HashElement<K,V> implements Comparable<HashElement<K,V>> {
        K key;
        V value;
        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }
        //compareTo method compares the keys of two hashElements
        public int compareTo(HashElement<K,V> O) {
            return (((Comparable<K>)O.key).compareTo(this.key));
        }
    }
    //currentSize and Size of the array
    int numElements, tableSize, currentSize, modCounter, maxSize;
    double maxLoadFactor;

    /*
    * h_array array will have a elements of type LinkedList<hashElement<K,V>>
    * and every node of LinkedList will be of type hashElement<K,V>
    * */
    LinkedList<HashElement<K,V>>[] h_array;

    //Constructor for Hashtable
    public Hashtable (int tableSize) {
        currentSize = 0;
        modCounter = 0;
        maxSize = tableSize;
        tableSize = (int) (tableSize * 1.5f);

        //this.tableSize = tableSize;
        h_array = (LinkedList<HashElement<K,V>> []) new LinkedList[tableSize];

        for (int i = 0; i < tableSize; i++) {
            //sets a LinkedList<HashElement<K,V>> at every element of the array
            h_array[i] = new LinkedList<HashElement<K,V>>();
        }
        //maxLoadFactor = 0.75;
        //numElements = 0;
    }

    /**
     * Provided Prof. Rob Edwards,
     * To get a corresponding hashCode for a key
     * */
    private int getHashCode(K key) {

        return (key.hashCode() & 0x7FFFFFFF) % tableSize;
    }

    @Override
    public boolean contains(K key) {
        int temp = getHashCode(key);

        for (HashElement<K, V> node : h_array[temp]) {
            if (key.compareTo(node.key) == 0)
                return true;
        }

        return false;
    }

    @Override
    public boolean add(K key, V value) {
        if (isFull())
            return false;
        if (contains(key) == true)
            return false;

        h_array[getHashCode(key)].add(new HashElement<K, V>(key, value));// insert the given element.

        currentSize++;
        modCounter++;
        return true;
    }

//    private void resize(int newSize) {
//        //Creates a new array of newSize and type LinkedList<HashElement<K,V>>
//        LinkedList<HashElement<K,V>> [] new_array =
//                (LinkedList<HashElement<K,V>> []) new LinkedList[newSize];
//        for (int i = 0; i < newSize; i++) {
//            //set LinkedList<HashElement<K,V>> to every element of new_array
//            new_array[i] = new LinkedList<HashElement<K,V>>();
//        }
//        for (K key : this) {
//            V value = getValue(K key);
//            HashElement<K,V> he = new HashElement<K,V>(key, value);
//            int hashVal = ((key.hashCode()) & 0x7FFFFFFF) % newSize;
//            new_array[hashVal].add(he);
//            h_array = new_array;
//            tableSize = newSize;
//        }
//    }

    private double loadFactor() {
        double loadFactor = 0;
        loadFactor = numElements/tableSize;
        return loadFactor;
    }

    @Override
    public boolean delete(K key) {
        if (isEmpty())
            return false;
        if (contains(key) == false)
            return false;

        h_array[getHashCode(key)].remove(new HashElement<K, V>(key, null));

        currentSize--;
        modCounter++;
        return true;
    }

    @Override
    public V getValue(K key) {
        if(!contains(key))
            return null;
        int temp = getHashCode(key);

        for (HashElement<K, V> node : h_array[temp]) {
            if (key.compareTo(node.key) == 0)
                return node.value;
        }

        return null;
    }

    @Override
    public K getKey(V value) {
        for (int i = 0; i < tableSize; i++) {

            for (HashElement<K, V> node : h_array[i]) {
                if (value.toString().compareTo(node.value.toString()) == 0) {
                    return node.key;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        return currentSize == maxSize;
    }

    @Override
    public boolean isEmpty() {
        if (currentSize == 0)
            return true;
        return false;
    }

    @Override
    public void clear() {
        currentSize = 0;
        modCounter++;

        // Given from the book.
        h_array = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            h_array[i] = new LinkedList<HashElement<K,V>>();
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }

    // From the book.
    abstract class IteratorHelper<E> implements Iterator<E> {
        protected HashElement<K, V>[] nodes;
        protected int idx;
        protected long modCheck;

        public IteratorHelper() {
            nodes = new HashElement[currentSize];
            idx = 0;
            int j = 0;
            modCheck = modCounter;

            for (int i = 0; i < tableSize; i++)
                for (HashElement<K, V> n : h_array[i])
                    nodes[j++] = n;
            sort();
        }

        public boolean hasNext() {
            if (modCheck != modCounter)
                throw new ConcurrentModificationException();

            return (idx < currentSize);
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void sort() {
            quickSortArray(0, nodes.length - 1);
        }

        private void quickSortArray(int left, int right) {
            if (right - left <= 0)
                return;

            HashElement pivot = nodes[right];
            int partition = getPartition(left, right, pivot);
            quickSortArray(left, partition - 1);
            quickSortArray(partition + 1, right);
        }

        private int getPartition(int left, int right, HashElement pivot) {
            int lPtr = left - 1;
            int rPtr = right;

            for (;;) {
                while (pivot.compareTo(nodes[++lPtr]) > 0)
                    ;
                while (rPtr > 0 && (pivot.compareTo(nodes[--rPtr]) < 0))
                    ;
                if (lPtr >= rPtr)
                    break;
                else
                    swap(lPtr, rPtr);
            }

            swap(lPtr, right);
            return lPtr;
        }

        private void swap(int one, int two) {
            HashElement temp = nodes[one];
            nodes[one] = nodes[two];
            nodes[two] = temp;
        }
    }

    class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper() {
            super();
        }

        public K next() {
            return (K) nodes[idx++].key;
        }
    }

    class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper() {
            super();
        }

        public V next() {
            return (V) nodes[idx++].value;
        }

        public HashElement<K, V> iterNode() {
            return (HashElement<K, V>) nodes[idx];
        }
    }

}


    //---------------------------------------------------------------------------------------
    //----------LINKED_LIST------------------------------------------------------------------
    /*  LinkedList
     *  Singly linked list
     *  PKraft Spring 2019
     */

    class LinkedListDS<E> implements ListADT<E> {
        /////////////////////////////////////////////////////////////////
        class Node<T> {
            T data;
            Node<T> next;

            public Node(T obj) {
                data = obj;
                next = null;
            }
        }
        // END CLASS NODE ///////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////
        class ListIteratorHelper implements Iterator<E> {
            Node<E> index;

            public ListIteratorHelper() {
                index = head;
            }

            public boolean hasNext() {
                return index != null;
            }

            public E next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                E tmp = index.data;
                index = index.next;
                return tmp;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        }
        // END CLASS LIST_ITERATOR_HELPER //////////////////////////////


        private Node<E> head, tail;
        private int currentSize;

        public LinkedListDS() {
            head = tail = null;
            currentSize = 0;
        }

        public void addFirst(E obj) {
            Node<E> newNode = new Node<E>(obj);
            if(isEmpty())
                head = tail = newNode;
            else {
                newNode.next = head;
                head = newNode;
            }
            currentSize++;
        }

        public void addLast(E obj) {
            Node<E> newNode = new Node<E>(obj);
            if(isEmpty())
                head = tail = newNode;
            else {
                tail.next = newNode;
                tail = newNode;
            }
            currentSize++;
        }

        public E removeFirst() {
            if(isEmpty())
                return null;
            E tmp = head.data;
            head = head.next;
            if(head == null)
                head = tail = null;
            currentSize--;
            return tmp;
        }

        public E removeLast() {
            if(isEmpty())
                return null;
            E tmp = tail.data;
            if(head == tail) // only one element in the list
                head = tail = null;
            else {
                Node<E> previous = null, current = head;
                while(current != tail) {
                    previous = current;
                    current = current.next;
                }
                previous.next = null;
                tail = previous;
            }

            currentSize--;
            return tmp;
        }

        public E peekFirst() {
            if(head == null)
                return null;
            return head.data;
        }

        public E peekLast() {
            if(tail == null)
                return null;
            return tail.data;
        }

        public E find(E obj) {
            if(head == null) return null;
            Node<E> tmp = head;
            while(tmp != null) {
                if(((Comparable<E>)obj).compareTo(tmp.data) == 0)
                    return tmp.data;
                tmp = tmp.next;
            }
            return null;
        }

        public boolean remove(E obj) {
            if(isEmpty())
                return false;
            Node<E> previous = null, current = head;
            while(current != null) {
                if( ((Comparable<E>)current.data).compareTo(obj) == 0) {
                    if(current == head)
                        removeFirst();
                    else if(current == tail)
                        removeLast();
                    else {
                        previous.next = current.next;
                        currentSize--;
                    }
                    return true;
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

        // not in the interface.  Removes all instances of the key obj
        public boolean removeAllInstances(E obj) {
            Node<E> previous = null, current = head;
            boolean found = false;
            while(current != null) {
                if(((Comparable<E>)obj).compareTo(current.data) == 0) {
                    if(previous == null) { // node to remove is head
                        head = head.next;
                        if(head == null) tail = null;
                    }
                    else if(current == tail) {
                        previous.next = null;
                        tail = previous;
                    }
                    else
                        previous.next = current.next;
                    found = true;
                    currentSize--;
                    current = current.next;
                }
                else {
                    previous = current;
                    current = current.next;
                }
            } // end while
            return found;
        }

        public void makeEmpty() {
            head = tail = null;
            currentSize = 0;
        }

        public boolean contains(E obj) {
            Node current = head;
            while(current != null) {
                if( ((Comparable<E>)current.data).compareTo(obj) == 0)
                    return true;
                current = current.next;
            }
            return false;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public boolean isFull() {
            return false;
        }

        public int size() {
            return currentSize;
        }

        public Iterator<E> iterator() {
            return new ListIteratorHelper();
        }
    }

     interface ListADT<E> extends Iterable<E> {


        //  Adds the Object obj to the beginning of the list
        public void addFirst(E obj);

        //  Adds the Object obj to the end of the list
        public void addLast(E o);

        //  Removes the first Object in the list and returns it.
        //  Returns null if the list is empty.
        public E removeFirst();

        //  Removes the last Object in the list and returns it.
        //  Returns null if the list is empty.
        public E removeLast();

        //  Returns the first Object in the list, but does not remove it.
        //  Returns null if the list is empty.
        public E peekFirst();

        //  Returns the last Object in the list, but does not remove it.
        //  Returns null if the list is empty.
        public E peekLast();

        //  Finds and returns the Object obj if it is in the list, otherwise
        //  returns null.  Does not modify the list in any way
        public E find(E obj);

        //  Removes the first instance of thespecific Object obj from the list, if it exists.
        //  Returns true if the Object obj was found and removed, otherwise false
        public boolean remove(E obj);

        //  The list is returned to an empty state.
        public void makeEmpty();

        //  Returns true if the list contains the Object obj, otherwise false
        public boolean contains(E obj);

        //  Returns true if the list is empty, otherwise false
        public boolean isEmpty();

        //  Returns true if the list is full, otherwise false
        public boolean isFull();

        //  Returns the number of Objects currently in the list.
        public int size();

        //  Returns an Iterator of the values in the list, presented in
        //  the same order as the list.
        public Iterator<E> iterator();

    }



