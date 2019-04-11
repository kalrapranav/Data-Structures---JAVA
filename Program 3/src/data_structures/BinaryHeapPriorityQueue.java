//WORK IN PROGRESS
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E>, Iterable<E> {

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
        int parent = (int) ((index - 1)/2);
        Wrapper<E> currentObject = array[index];
        while ((parent >= 0) && (currentObject.compareTo(array[parent]) < 0)) {
            array[index] = array[parent];
            index = parent;
            parent = (index - 1)>>1;
        }
        array[index] = currentObject;
    }

    public E remove() {
        if(isEmpty())
            return null;
        E top = array[0].data;
        //bring last element at the top

        trickleDown(0);
        currentSize = currentSize--;
        modificationCounter++;
        return top;
    }

    public void trickleDown(int oldSize) {
        int current = 0;
        int child = getNextChild(current);
        while (child != -1 && array[current].compareTo(array[child]) < 0 && array[child].compareTo(array[currentSize - 1]) < 0) {
            array[current] = array[child];
            current = child;
            child = getNextChild(current);
        }
        array[current] = array[currentSize - 1];
    }

    private int getNextChild(int current) {
        int left = (current * 2) + 1;
        int right = left + 1;
        if (right < currentSize) {
            if (array[left].compareTo(array[right]) < 0)
                return left;
            return right;
        }
        if (left < currentSize)
            return left;
        return -1;
    }

    public boolean delete(E obj) {
        if(!contains(obj))
            return false;
        Wrapper<E> [] tempStorage = array;
        int size = currentSize;
        boolean removed = false;
        currentSize = 0;
        entryNumber = 0;
        for (int i = 0; i < size; i++){
            if ((tempStorage[i].data).compareTo(obj) != 0) {
                insert(tempStorage[i].data);
            }
            else {
                modificationCounter++;
                removed = true;
            }
        }

        return removed;
    }

    public E peek() {
        if (isEmpty())
            return null;
        return array[0].data;
    }

    public boolean contains(E obj) {
        if(isEmpty())
            return false;
        for(Wrapper<E> anyObject : array) {
            if (anyObject.data.compareTo(obj) == 0)
                return true;
        }
        return false;
    }

    public int size() {
        return currentSize;
    }

    public void clear() {
        currentSize = 0;
        entryNumber = 0;
    }

    public boolean isEmpty() {
        if (currentSize == 0)
            return true;
        return false;
    }

    public boolean isFull() {
        if (currentSize == totalArraySize)
            return true;
        return false;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E> {
        private int current = 0;
        private long oldModificationCounter = modificationCounter;

        @Override
        public boolean hasNext() {
            if (oldModificationCounter != modificationCounter) {
                throw new ConcurrentModificationException("");
            }
            if (current < currentSize)
                return true;
            return false;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[current++].data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
