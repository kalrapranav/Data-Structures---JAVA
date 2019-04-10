//WORK IN PROGRESS
package data_structures;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (currentSize == totalArraySize)
            return false;
        array[currentSize] = new Wrapper<>(object);
        int oldSize = currentSize;
        currentSize++;
        trickleUp(oldSize);
        return true;
    }



    public E remove() {
        if(isEmpty())
            return null;
        E top = array[0].data;
        trickleDown(0);
        currentSize = currentSize--;
        modificationCounter++;
        return top;
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
            if ((tempStorage[i].data).compareTo(obj) < 0) {
                insert(tempStorage[i].data);
            } else {
                modificationCounter++;
                removed = true;
            }
        }

        return false;
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
        private int oldModificationCounter = modificationCounter;

        public boolean hasNext() {
            if (oldModificationCounter != modificationCounter) {
                throw new ConcurrentModificationException("");
            }
            if (current < currentSize)
                return true;
            return false;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[current++].data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void trickleUp(int oldSize) {
        int parent = (int) ((oldSize - 1)/2);
        Wrapper<E> currentObject = array[oldSize];
        while ((parent >= 0) && (currentObject.compareTo(array[parent]) < 0)) {
            array[oldSize] = array[parent];
            oldSize = parent;
            parent = (oldSize - 1)>>1;
        }
        array[oldSize] = currentObject;
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
}
