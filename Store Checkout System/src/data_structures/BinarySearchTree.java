package data_structures;

import java.util.Iterator;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    @Override
    public boolean add(Comparable key, Object value) {
        return false;
    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

    @Override
    public Object getValue(Comparable key) {
        return null;
    }

    @Override
    public Comparable getKey(Object value) {
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
    public Iterator keys() {
        return null;
    }

    @Override
    public Iterator values() {
        return null;
    }
}
