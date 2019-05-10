/**
 *  Program #4
 *  1-2 Line Description of class/program
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

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    /**
     * > Binary Search Tree is gonna use array as a medium to store data
     * > so, the complexity of add, remove and find operation becomes O(1)-O(log(n))
     * > for all these functions we wil use the index of the array
     * > Key and Value will be stored in every node which will be given by the user and
     *   would be different from the index we are storing (unlike hash tables)
     * */

    /*===================Class=Variables=======================================================*/
    private Node<K, V> root;
    private int currentSize;
    private int modCounter;
    private K foundKey;

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


    //Class Constructor
    public BinarySearchTree() {
        root = null;
        currentSize = 0;
        modCounter = 0;
        foundKey = null;
    }



    /*===================Class=Methods=From=Dictionary=ADT=====================================================*/

    //The following method is to check that BST contains the key for not
    public boolean contains(K key) {
        if (find(key, root) == null)
            return false;
        return true;

    }

   //The following method is to add a Key-Value pair to the BST
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

    //The following method deletes a Key-Value pair from the BST
    // according to the key provided
    public boolean delete(K key) {
        if (isEmpty())
            return false;
        if (!erase(key, root, null, false))
            return false;
        currentSize--;
        modCounter++;
        return true;

    }

    //The following method gets a value according to the from the BST
    //according to the Key provided
    public V getValue(K key) {
        if (isEmpty())
            return null;
        return find(key, root);
    }

    //The following value gets the key of an element according to the key provided
    public K getKey(V value) {
        if (isEmpty())
            return null;
        foundKey = null;
        findKey(value, root);
        return foundKey;
    }

    //The following method returns the total number of Key-Value pairs in the BST
    public int size() {
        return currentSize;
    }

    //The following methods checks that the data structure is full or not
    public boolean isFull() {
        return false;
    }

    //he following methods checks that the data structure is empty or not
    public boolean isEmpty() {
        if (currentSize != 0)
            return false;
        return true;
    }

    //The following method sets the data structure to an empty state .
    public void clear() {
        root = null;
        currentSize = 0;
        modCounter++;
    }

    /*===================Class=Methods=From=Dictionary=ADT===END==================================================*/




    /*===================Iterators=======================================================*/
    public Iterator keys() {
        return new KeyIteratorHelper();
    }

    public Iterator values() {
        return new ValueIteratorHelper();
    }

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

    /*===================Iterators=End=======================================================*/



    /*===================Helper=Methods======================================================*/

    /*=============================================================================
  |----------------------------------------------------------------------------
  |  Citations:
  |  - Supplementary Reader by Prof. Alan Riggins
  |  - Data Structures and Algorithms in Java by Roberto Tamassia
  |  - Prof. Kraft Lecture Notes
  |  - Prof. Rob Edwards Videos
  |  - Introduction to Algorithms by Thomas H. Cormen
  | >> The Following methods were written with help provided form the following resources
  | >> especially from "Introduction to Algorithms" by Thomas H. Cormen
  | >> While some methods may also be used for reference or using a different version of implementation
  |--------------------------------------------------------------------------*/

    private boolean erase(K k, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
        if (n == null)
            return false;
        if (((Comparable<K>) k).compareTo(n.key) > 0)
            return erase(k, n.rightChild, n, false);
        else if (((Comparable<K>) k).compareTo(n.key) < 0)
            return erase(k, n.leftChild, n, true);
        else {
            if (n.rightChild == null && n.leftChild == null) {
                if (parent == null)
                    root = null;
                else if (!wasLeft)
                    parent.rightChild = null;
                else
                    parent.leftChild = null;
            } else if (n.rightChild == null && n.leftChild != null) {
                if (parent == null)
                    root = n.leftChild;
                else if (!wasLeft)
                    parent.rightChild = n.leftChild;
                else
                    parent.leftChild = n.leftChild;
            } else if (n.leftChild == null && n.rightChild != null) {
                if (parent == null)
                    root = n.rightChild;
                else if (!wasLeft)
                    parent.rightChild = n.rightChild;
                else
                    parent.leftChild = n.rightChild;
            } else {
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

    //to get the parent fo the node
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

    //to find the key of the node of the node
    private void findKey(V value, Node<K, V> n) {
        if (n == null)
            return;
        if (((Comparable<V>) value).compareTo(n.value) == 0) {
            foundKey = n.key;
        }
        findKey(value, n.leftChild);//
        findKey(value, n.rightChild);
    }


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

    /*===================Helper=Methods======================================================*/

}

/*===================End=of=BinarySearchTree=Class=====================================================*/