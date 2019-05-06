package data_structures;

import java.util.Iterator;
import java.util.LinkedList;

public class Hashtable<K extends Comparable<K>,V> implements DictionaryADT<K,V> {

    class HashElement<K,V> implements Comparable<HashElement<K,V>> {
        K key;
        V value;
        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public int compareTo(HashElement<K,V> O) {
            return (((Comparable<K>)O.key).compareTo(this.key));
        }
    }
    //currentSize and Size of the array
    int numElements, tableSize;
    double maxLoadFactor;
    LinkedList<HashElement<K,V>>[] h_array;

    //Constructor for Hashtable
    public Hashtable (int tableSize) {
        this.tableSize = tableSize;
        h_array = (LinkedList<HashElement<K,V>> []) new LinkedList[tableSize];

        for (int i = 0; i < tableSize; i++) {
            h_array[i] = new LinkedList<HashElement<K,V>>();
        }
        maxLoadFactor = 0.75;
        numElements = 0;
    }


    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        if (loadFactor() > maxLoadFactor)
            resize(tableSize*2);
        HashElement<K,V> he = new HashElement(key, value);
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF;
        hashVal = hashVal % tableSize;

        h_array[hashVal].add(he);
        numElements++;
        return true;
    }

    private void resize(int i) {
    }

    private double loadFactor() {
        double loadFactor = 0;
        loadFactor = numElements/tableSize;
        return loadFactor;
    }

    @Override
    public boolean delete(K key) {
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF;
        hashVal = hashVal % tableSize;

        h_array[hashVal].remove();
        return true;
    }

    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public K getKey(V value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<K> keys() {
        return null;
    }

    @Override
    public Iterator<V> values() {
        return null;
    }
}
