/**
 *  Program #4
 *  The following program is the implementation of DictionaryADT
 *  by using chain hashes
 *  CS310-1
 *  May 9, 2019
 *  @author  Pranav Kalra cssc1483
 */



package data_structures;


//header files
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/*=============================================================================
  |----------------------------------------------------------------------------
  |  Citations:
  |  - Supplementary Reader by Prof. Alan Riggins
  |  - Data Structures and Algorithms in Java by Roberto Tamassia
  |  - Prof. Kraft Lecture Notes
  |  - Prof. Rob Edwards Videos
  |  - Introduction to Algorithms by Thomas H. Cormen
  | >> These books and resources were refered to write the HashElement inner class, add(K key),
  |    delete(K key), getValue(K key), getKey(V value), contains Iterator Class both Value and Key
  |--------------------------------------------------------------------------*/

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K, V> {

    /*===================Class=Variables=======================================================*/
    //Class Variables
    private LinkedList<HashElement<K, V>>[] list;
    int maxSize;
    int tableSize;
    int currentSize;
    int modCounter;

    // Hash Element is the inner class that is a bucket to store Key-Value pair
    private class HashElement<K, V> implements Comparable<HashElement<K, V>> {
        K key;
        V value;

        //Inner Class Constructor
        //To initialize the Key adn Value
        public HashElement(K key, V value) {

            this.key = key;
            this.value = value;
        }

        //To Compare key of two hashElements as they are distinct or not
        public int compareTo(HashElement<K, V> node) {

            return ((Comparable<K>) key).compareTo((K) node.key);
        }
    }

    //Class Constructor
    // To initialize the currentSize, ,modCounter, maxSize and tableSize
    public Hashtable(int max) {
        currentSize = 0;
        modCounter = 0;
        maxSize = max;
        tableSize = (int) (max * 1.5f);

        list = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new LinkedList<HashElement<K, V>>();
    }


    //To get HashCode
    // The following method takes the, gets its hashcode
    //then, get the absolute value of the hashcode and mod
    //it to the table size
    private int getHashCode(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % tableSize;
    }


    /*===================Class=Methods=From=Dictionary=ADT===================================================*/

    //To check if the data structure as a HashElement with the
    //provided key
    public boolean contains(K key) {
        int temp = getHashCode(key);

        for (HashElement<K, V> node : list[temp]) {
            if (key.compareTo(node.key) == 0)
                return true;
        }
        return false;
    }

    //The following method adds a key-value pair to the chain hash
    public boolean add(K key, V value) {
        //If the array is Full
        //Currently the data structure is returning False but we can resize the array of the
        //data structure is full
        //So, if loadFactor > maxLoadFactor
        //(where maxLoadFactor =  0.75 and loadFactor = size of the array/number of elements)
        if (isFull())
            return false;
        //If the key is already there we cant add as keys should be distinct
        if (contains(key))
            return false;

        //gets an element's hashcode and and creates and add  a new with that K-V pair
        list[getHashCode(key)].insert(new HashElement<K, V>(key, value));

        currentSize++;
        modCounter++;
        return true;
    }

    //Deletes a hashElement with the corresponding key
    public boolean delete(K key) {
        //If the data structure is empty
        if (isEmpty())
            return false;
        //If the follwing key is not present
        if (contains(key) == false)
            return false;

        //gets the hashcode fo the key and deletes
        list[getHashCode(key)].delete(new HashElement<>(key, null));

        currentSize--;
        modCounter++;
        return true;
    }

    //To get the value of a HashElement according to its key
    public V getValue(K key) {
        //If the data structure is empty
        if(!contains(key))
            return null;
        int temp = getHashCode(key);

        for (HashElement<K, V> node : list[temp]) {
            if (key.compareTo(node.key) == 0)
                return node.value;
        }
        return null;
    }

    //TO get the key of the hashElement according to the value
    //For teh following method the complexity will be big-omega(n) as
    //we would have to traverse through the array adn then multiple nodes of a linkedList too
    public K getKey(V value) {
        for (int i = 0; i < tableSize; i++) {
            for (HashElement<K, V> node : list[i]) {
                if (value.toString().compareTo(node.value.toString()) == 0) {
                    return node.key;
                }
            }
        }
        return null;
    }

    //To get the total number of hashElements in the data structure
    public int size() {
        return currentSize;
    }

    //To see if the data structure is full or not
    public boolean isFull() {
        return currentSize == maxSize;
    }

    //To see if the data structure is empty
    public boolean isEmpty() {
        if (currentSize == 0)
            return true;
        return false;
    }

    //To set the data structurek to empty state
    public void clear() {
        currentSize = 0;
        modCounter++;

        list = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new LinkedList<HashElement<K, V>>();

    }

    /*===================Class=Methods=From=Dictionary=ADT==END================================================*/


    /*===================Iterators=======================================================*/

    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }

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
                for (HashElement<K, V> n : list[i])
                    nodes[j++] = n;
            sort();
        }

        public boolean hasNext() {
            if (modCheck != modCounter)
                throw new ConcurrentModificationException();

            return (idx < currentSize);
        }

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

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }


    /*===================Iterators====END===================================================*/

}

    /*===================Linked=List=======================================================*/

    /*=============================================================================
    |----------------------------------------------------------------------------
    |  Citations:
    |  - Prof. Kraft Lecture Notes
    |  - Prof. Rob Edwards Videos
    |  - Introduction to Algorithms by Thomas H. Cormen
    |  - www.hakerrank.com
    | >> The following Linked List class was taken from Introduction to Algorithms by Thomas H Cormen
    | >> Some from excerpts from www.hakerrank.com and notes from Prof. Rob Edwards video and Prof. Kraft's
    | lectures
    |--------------------------------------------------------------------------*/


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
        private int modificationCounter, currentSize;

        public LinkedList() {
            currentSize = 0;
            head = null;
        }

        public boolean insert(E object) {
            Node<E> newNode = new Node<E>(object);
            newNode.next = head;
            head = newNode;
            currentSize++;
            modificationCounter++;
            return true;
        }


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
            modificationCounter++;
            return true;
        }


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


        public boolean contains(E obj) {
            return find(obj) != null;
        }

        public E find(E obj) {
            for (E element : this)
                if (obj.compareTo(element) == 0)
                    return element;
            return null;
        }

        public int size() {
            return currentSize;

        }

        public void clear() {
            head = null;
            currentSize = 0;
        }

        public boolean isEmpty() {
            if (head == null)
                return true;
            return false;
        }


        public boolean isFull() {
            return false;
        }


        public Iterator<E> iterator() {
            return new IteratorHelper();
        }

        class IteratorHelper implements Iterator<E> {
            private Node<E> iterPtr;
            private long modificationCheck;

            public IteratorHelper() {
                modificationCheck = modificationCounter;
                iterPtr = head;
            }

            public boolean hasNext() {
                if (modificationCheck != modificationCounter)
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
            /*===================Linked=List====END===================================================*/

      

    }

/*===================End=of=HashTable=Class=====================================================*/