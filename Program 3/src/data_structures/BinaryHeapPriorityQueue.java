//WORK IN PROGRESS
package data_structures;
import java.sql.Wrapper;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>, Iterable<E> {

    private Wrapper<E>[] array;
    int modificationCounter = 0;
    int currentSize = 0;
    public long entryNumber;

    public boolean insert(E object) {
        return false;
    }

    public E remove() {
        return null;
    }

    public boolean delete(E obj) {
        return false;
    }

    public E peek() {
        return null;
    }

    public boolean contains(E obj) {
        return false;
    }

    public int size() {
        return 0;
    }

    public void clear() {

    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }
}
