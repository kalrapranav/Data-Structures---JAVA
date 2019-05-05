package data_structures;

import java.util.Iterator;

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K,V> {

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        return false;
    }

    @Override
    public boolean delete(K key) {
        return false;
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
