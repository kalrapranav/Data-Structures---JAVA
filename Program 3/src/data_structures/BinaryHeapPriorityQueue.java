//WORK IN PROGRESS
package data_structures;
import java.sql.Wrapper;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>, Iterable<E> {

    private Wrapper<E>[] array;
    int modificationCounter = 0;
    int currentSize = 0;
    int totalArraySize;
    public long entryNumber;
    int DEFAULT_CAPACITY = DEFAULT_MAX_CAPACITY;

    public BinaryHeapPriorityQueue() {
        currentSize = 0;
        entryNumber = 0;
        array = new Wrapper[DEFAULT_CAPACITY];
    }

    public BinaryHeapPriorityQueue(int totalArraySize) {
        this.totalArraySize = totalArraySize;
        currentSize = 0;
        entryNumber = 0;
        array = new Wrapper[totalArraySize];
    }

    public class Wrapper<E> implements Comparable<Wrapper<E>> {
        E data;
        public long sequenceNumber;

        public Wrapper(E obj) {
            data = obj;
            sequenceNumber = entryNumber++;
        }

        public int compareTo(Wrapper<E>obj) {
            int tmp = ((Comparable<E>)data).compareTo(obj.data);
            if(tmp == 0)
                return (int) (sequenceNumber - obj.sequenceNumber);
            return tmp;
        }
        public String toString() {
            return "" + data;
        }
    }

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
