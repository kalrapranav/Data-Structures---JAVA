package data_structures;

//import java.security.Key;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    /**
     * > Binary Search Tree is gonna use array as a medium to store data
     * > so, the complexity of add, remove and find operation becomes O(1)-O(log(n))
     * > for all these functions we wil use the index of the array
     * > Key and Value will be stored in every node which will be given by the user and
     *   would be different from the index we are storing (unlike hash tables)
     * */

    private Node<K, V> root;
    private int currentSize;
    private int modCounter;
    private K foundKey;

    public BinarySearchTree() {// Constructor.

        root = null;
        currentSize = 0;
        modCounter = 0;
        foundKey = null;
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key) {

        if (find(key, root) == null)
            return false;

        return true;

    }

    // Adds the given key/value pair to the dictionary. Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    // From the book.
    public boolean add(K key, V value) {
        if (contains(key))
            return false;
        if (root == null)
            root = new Node<K, V>(key, value);
        else
            insert(key, value, root, null, false);

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
        if (!erase(key, root, null, false))
            return false;
        currentSize--;
        modCounter++;
        return true;

    }

    // Returns the value associated with the parameter key. Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key) {

        if (isEmpty())
            return null;

        return find(key, root);
    }

    // Returns the key associated with the parameter value. Returns
    // null if the value is not found in the dictionary. If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value) {

        if (isEmpty())
            return null;
        foundKey = null;
        findKey(value, root);
        return foundKey;

    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size() {

        return currentSize;

    }

    // Returns true if the dictionary is at max capacity
    public boolean isFull() {
        return false;

    }

    // Returns true if the dictionary is empty
    public boolean isEmpty() {

        if (currentSize != 0)
            return false;

        return true;
    }

    // Returns the Dictionary object to an empty state.
    public void clear() {

        root = null;
        currentSize = 0;
        modCounter++;

    }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order. The iterator must be fail-fast.
    public Iterator keys() {

        return new KeyIteratorHelper();
    }

    // Returns an Iterator of the values in the dictionary. The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator values() {

        return new ValueIteratorHelper();
    }

    private boolean erase(K k, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
        if (n == null)
            return false;

        if (((Comparable<K>) k).compareTo(n.key) > 0)
            return erase(k, n.rightChild, n, false);// goes to the right

        else if (((Comparable<K>) k).compareTo(n.key) < 0)
            return erase(k, n.leftChild, n, true);// goes to the left

        else {
            if (n.rightChild == null && n.leftChild == null) {// node with no
                // children.
                if (parent == null)
                    root = null;
                else if (!wasLeft)
                    parent.rightChild = null;
                else
                    parent.leftChild = null;

            } else if (n.rightChild == null && n.leftChild != null) {// With one
                // right
                // child.

                if (parent == null)
                    root = n.leftChild;
                else if (!wasLeft)
                    parent.rightChild = n.leftChild;
                else
                    parent.leftChild = n.leftChild;
            } else if (n.leftChild == null && n.rightChild != null) {// With one left Child. // child.

                if (parent == null)
                    root = n.rightChild;
                else if (!wasLeft)
                    parent.rightChild = n.rightChild;
                else
                    parent.leftChild = n.rightChild;
            } else {// With both left child and right child.

                Node<K, V> tmp = getParent(n.rightChild);
                if (tmp == null) {

                    n.key = n.rightChild.key;
                    n.value = n.rightChild.value;
                    n.rightChild = n.rightChild.rightChild;
                } else {

                    n.key = tmp.key;
                    n.value = tmp.value;
                }
            }

        }
        return true;
    }

    private Node<K, V> getParent(Node<K, V> n) {
        Node<K, V> parent = null;
        while (n.leftChild != null) {
            parent = n;
            n = n.leftChild;
        }
        if (parent == null)
            return null;
        else
            parent.leftChild = n.rightChild;
        return n;
    }

    private void findKey(V value, Node<K, V> n) {
        if (n == null)
            return;

        if (((Comparable<V>) value).compareTo(n.value) == 0) {
            foundKey = n.key;
        }
        findKey(value, n.leftChild);//
        findKey(value, n.rightChild);

    }

    // Node
    // From the
    // book========================================================================
    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;

        public Node(K k, V v) {
            key = k;
            value = v;
            leftChild = rightChild = null;
        }
    }
    // ==============================================================================

    // From the
    // book===================================================================================
    private void insert(K key, V value, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
        if (n == null) {
            if (wasLeft)
                parent.leftChild = new Node<K, V>(key, value);
            else
                parent.rightChild = new Node<K, V>(key, value);
        } else if (((Comparable<K>) key).compareTo((K) n.key) < 0)
            insert(key, value, n.leftChild, n, true); // go left
        else
            insert(key, value, n.rightChild, n, false);// go right
    }
    // ================================================================================================

    // From the
    // Book====================================================================================
    private V find(K key, Node<K, V> n) {
        if (n == null)
            return null;
        int comp = ((Comparable<K>) key).compareTo(n.key);
        if (comp < 0)
            return find(key, n.leftChild); // go left
        else if (comp > 0)
            return find(key, n.rightChild); // go right
        else
            return (V) n.value; // found it
    }
    // =================================================================================================

    // From the
    // Book==================================================================================
    abstract class IteratorHelper<E> implements Iterator<E> {
        protected int idx, index;
        protected long modCheck;
        protected Node<K, V>[] nodes;

        public IteratorHelper() {
            nodes = new Node[currentSize];
            index = idx = 0;
            modCheck = modCounter;
            orderArray(root);
        }

        public boolean hasNext() {
            if (modCheck != modCounter)
                throw new ConcurrentModificationException();
            return idx < currentSize;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void orderArray(Node<K, V> n) {
            if (n != null) {
                orderArray(n.leftChild);
                nodes[index++] = n;
                orderArray(n.rightChild);
            }
        }
    }

    class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper() {
            super();
        }

        public K next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (K) nodes[idx++].key;
        }
    }

    class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper() {
            super();
        }

        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (V) nodes[idx++].value;
        }
    }
}
