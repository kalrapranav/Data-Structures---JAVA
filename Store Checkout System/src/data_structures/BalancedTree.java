
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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;


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

public class BalancedTree<K extends Comparable<K>, V> implements DictionaryADT<K,V> {


    /*===================Class=Variables=======================================================*/
    TreeMap<K, V> tree;


    /*===================Class=Constructor=======================================================*/
    public BalancedTree() {
        tree = new TreeMap<K, V>();
    }

    //The following method checks that the red-black tree contains
    // the following Node according to its key
    public boolean contains(K key) {
        return tree.containsKey(key);
    }

    //The following method adds a node to the red-black tree
    public boolean add(K key, V value) {
        if (isFull() || tree.containsKey(key))
            return false;

        tree.put(key, value);
        return true;
    }

    //The following method deletes a node to the red-black tree
    public boolean delete(K key) {
        return tree.remove(key) != null;
    }

    //The following method gets the value of node from the red-black
    // tree according to the Key provided
    public V getValue(K key) {
        if(isEmpty())
            return null;
        return tree.get(key);

    }

    //The following method gets the key of node from the red-black
    // tree according to the value provided
    public K getKey(V value) {
        for (K key: tree.navigableKeySet()) {
            if (((Comparable<V>) tree.get(key)).compareTo(value) == 0)
                return key;
        }
        return null;
    }

    //The following method gets the size(number of key-value pairs)
    // of the red-black tree
    public int size() {
        return tree.size();
    }

    //The following method checks the red-black tree is full or not
    public boolean isFull() {
        return false;
    }

    ///The following method checks the red-black tree is empty or not
    public boolean isEmpty() {
        if (tree.size() != 0)
            return false;
        return true;
    }

    ///The following method sets the red-black tree to empty state
    public void clear() {
        tree.clear();
    }


    /*===================Iterators=======================================================*/

    public Iterator keys() {
        return tree.keySet().iterator();
    }


    public Iterator values() {
        return tree.values().iterator();
    }

    /*===================Iterators=======================================================*/
}
/*===================End=of=BalancedTree=Class=====================================================*/