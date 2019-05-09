
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K, V> {

    private LinkedList<DictionaryNode<K, V>>[] list;
    int maxSize;
    int tableSize;
    int currentSize;
    int modCounter;

    // Given from the book
    private class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>> {
        K key;
        V value;

        public DictionaryNode(K key, V value) {

            this.key = key;
            this.value = value;
        }

        public int compareTo(DictionaryNode<K, V> node) {

            return ((Comparable<K>) key).compareTo((K) node.key);
        }
    }

    public Hashtable(int max) {
        currentSize = 0;
        modCounter = 0;
        maxSize = max;
        tableSize = (int) (max * 1.5f);

        // Given from the book.
        list = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new LinkedList<DictionaryNode<K, V>>();
    }

    private int getHashCode(K key) {

        return (key.hashCode() & 0x7FFFFFFF) % tableSize;// provided in the book
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key) {
        int temp = getHashCode(key);

        for (DictionaryNode<K, V> node : list[temp]) {
            if (key.compareTo(node.key) == 0)
                return true;
        }

        return false;
    }

    // Adds the given key/value pair to the dictionary. Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value) {

        if (isFull())
            return false;
        if (contains(key) == true)
            return false;

        list[getHashCode(key)].insert(new DictionaryNode<K, V>(key, value));// insert the given element.

        currentSize++;
        modCounter++;
        return true;
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key) {
        if (isEmpty())
            return false;
        if (contains(key) == false)
            return false;

        list[getHashCode(key)].delete(new DictionaryNode<K, V>(key, null));

        currentSize--;
        modCounter++;
        return true;
    }

    // Returns the value associated with the parameter key. Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key) {
        if(!contains(key))
            return null;
        int temp = getHashCode(key);

        for (DictionaryNode<K, V> node : list[temp]) {
            if (key.compareTo(node.key) == 0)
                return node.value;
        }

        return null;
    }

    // Returns the key associated with the parameter value. Returns
    // null if the value is not found in the dictionary. If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value) {

        for (int i = 0; i < tableSize; i++) {

            for (DictionaryNode<K, V> node : list[i]) {
                if (value.toString().compareTo(node.value.toString()) == 0) {
                    return node.key;
                }
            }
        }
        return null;
    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size() {

        return currentSize;
    }

    // Returns true if the dictionary is at max capacity
    public boolean isFull() {
        return currentSize == maxSize;
		/*
		if (0.75 <= ((double) currentSize / tableSize)) //use load factor
			return true;

		return false;
		*/
    }

    // Returns true if the dictionary is empty
    public boolean isEmpty() {

        if (currentSize == 0)
            return true;

        return false;

    }

    // Returns the Dictionary object to an empty state.
    public void clear() {
        currentSize = 0;
        modCounter++;

        // Given from the book.
        list = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new LinkedList<DictionaryNode<K, V>>();

    }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order. The iterator must be fail-fast.
    public Iterator<K> keys() {

        return new KeyIteratorHelper();

    }

    // Returns an Iterator of the values in the dictionary. The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator<V> values() {

        return new ValueIteratorHelper();

    }

    // From the book.
    abstract class IteratorHelper<E> implements Iterator<E> {
        protected DictionaryNode<K, V>[] nodes;
        protected int idx;
        protected long modCheck;

        public IteratorHelper() {
            nodes = new DictionaryNode[currentSize];
            idx = 0;
            int j = 0;
            modCheck = modCounter;

            for (int i = 0; i < tableSize; i++)
                for (DictionaryNode<K, V> n : list[i])
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

            DictionaryNode pivot = nodes[right];
            int partition = getPartition(left, right, pivot);
            quickSortArray(left, partition - 1);
            quickSortArray(partition + 1, right);
        }

        private int getPartition(int left, int right, DictionaryNode pivot) {
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
            DictionaryNode temp = nodes[one];
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

        public DictionaryNode<K, V> iterNode() {
            return (DictionaryNode<K, V>) nodes[idx];
        }
    }

}

class LinkedList<E extends Comparable<E>> implements Iterable<E> {
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    private Node<E> head;
    private int modCounter, currentSize;

    public LinkedList() {
        currentSize = 0;
        head = null;
    }

    // Inserts a new object into the priority queue. Returns true if
    // the insertion is successful. If the PQ is full, the insertion
    // is aborted, and the method returns false.
    public boolean insert(E object) {
        Node<E> newNode = new Node<E>(object);
        newNode.next = head;
        head = newNode;
        currentSize++;
        modCounter++;
        return true;
    }

    // Deletes all instances of the parameter obj from the PQ if found, and
    // returns true. Returns false if no match to the parameter obj is found.
    public boolean delete(E obj) {
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null &&current.data.compareTo(obj) == 0 ) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if(current == head) {
            head = head.next;
        }
        else {
            previous.next = current.next;
        }
        currentSize--;
        modCounter++;
        return true;
    }

    // Returns the object of highest priority that has been in the
    // PQ the longest, but does NOT remove it.
    // Returns null if the PQ is empty.
    public E peek() {
        Node<E> current = head;
        E minVal = head.data;
        if (isEmpty()) {
            return null;
        }

        while (current != null) {
            if (current.data.compareTo(minVal) <= 0)
                minVal = current.data;
            current = current.next;
        }
        return minVal;
    }

    // Returns true if the priority queue contains the specified element
    // false otherwise.
    public boolean contains(E obj) {
        return find(obj) != null;

    }

    public E find(E obj) {
        for (E element : this)
            if (obj.compareTo(element) == 0)
                return element;
        return null;
    }

    // Returns the number of objects currently in the PQ.
    public int size() {
        return currentSize;

    }

    // Returns the PQ to an empty state.
    public void clear() {
        head = null;
        currentSize = 0;

    }

    // Returns true if the PQ is empty, otherwise false
    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;

    }

    // Returns true if the PQ is full, otherwise false. List based
    // implementations should always return false.
    public boolean isFull() {
        return false;

    }

    // Returns an iterator of the objects in the PQ, in no particular
    // order.
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E> {
        private Node<E> iterPtr;
        private long modCheck;

        public IteratorHelper() {
            modCheck = modCounter;
            iterPtr = head;
        }

        public boolean hasNext() {
            if (modCheck != modCounter)
                throw new ConcurrentModificationException();
            return iterPtr != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E temp = iterPtr.data;
            iterPtr = iterPtr.next;
            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}