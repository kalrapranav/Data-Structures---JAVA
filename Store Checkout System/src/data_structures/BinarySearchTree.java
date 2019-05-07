package data_structures;

import java.util.Iterator;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    /**
     * Binary Search Tree is gonna use array as a medium to store data
     * so, the complexity of add, remove and find operation becomes O(1)
     * for all these functions we will use the key as teh index
     * */

    /**
     * Node class will be used to assign the Key and value for the different
     * positions of the tree. As, the constructor of the class takes K,V and
     * assigns it to Key and Value
     * */
    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int count;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

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
