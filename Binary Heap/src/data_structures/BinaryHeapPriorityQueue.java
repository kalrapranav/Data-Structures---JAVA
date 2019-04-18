package data_structures;

import java.util.Iterator;
import java.util.PriorityQueue;

public class BinaryHeapPriorityQueue <E extends Comparable<E>> implements data_structures.PriorityQueue<E>, Iterable<E> {
    private Wrapper<E>[] array;
    long modificationCounter = 0;
    int currentSize = 0;
    int totalArraySize;
    public long entryNumber;
    //int DEFAULT_CAPACITY = DEFAULT_MAX_CAPACITY;

    public BinaryHeapPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public BinaryHeapPriorityQueue(int size) {
        totalArraySize = size;
        currentSize = 0;
        entryNumber = 0;
        array = new Wrapper[size];
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
        if (currentSize == totalArraySize)
            return false;
        array[currentSize] = new Wrapper<>(object);
        int oldSize = currentSize;
        currentSize++;
        trickleUp(oldSize);
        return true;
    }


    private void trickleUp(int index) {
//        int parent = (int) ((index - 1)/2);

//        index = currentSize-1;
        int parent = (int) ((index - 1) /2);
        Wrapper<E> currentObject = array[index];

        while ((parent >= 0) &&
                (currentObject.compareTo(array[parent]) < 0)) {
            array[index] = array[parent];
            index = parent;
            parent = (parent - 1)>>1;
        }
        array[index] = currentObject;
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
