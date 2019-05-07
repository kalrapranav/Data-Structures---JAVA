package data_structures;

import java.util.Iterator;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    /**
     * > Binary Search Tree is gonna use array as a medium to store data
     * > so, the complexity of add, remove and find operation becomes O(1)-O(log(n))
     * > for all these functions we wil use the index of the array
     * > Key and Value will be stored in every node which will be given by the user and
     *   would be different from the index we are storing (unlike hash tables)
     * */

    /**
     * Node class will be used to assign the Key and value for the different
     * positions of the tree. As, the constructor of the class takes K,V and
     * assigns it to Key and Value
     * */
    private class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> leftChild;
        private Node<K,V> rightChild;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            leftChild = rightChild = null;
        }
    }
    private Node<K,V> root;
    private int currentSize;

    public BinarySearchTree() {
        root = null;
        currentSize = 0;
    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    /**
     * Add to the last available space of teh tree > check heap violation
     * and trickleUp()
     * */
    @Override
    public boolean add(Comparable key, Object value) {

        return false;
    }

    public void trickleUp(int node){}

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

    @Override
    public Object getValue(Comparable key) {
        if (root == null)
            return null;
        Node<K,V> current = root;
        while(((Comparable<K>) current.key).compareTo((K)key) != 0) {
            if (((Comparable<K>)key).compareTo((K)current.key) < 0)
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null)
                return null;
        }
        return current.value;
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
