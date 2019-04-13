/**
 *  Program #3
 *  Binary heap implementation using an array and the PriorityQueue.java interface
 *  CS310-01
 *  10-April-2019
 *  @author  Pranav Kalra 
 */

package data_structures;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class BinaryHeapPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E>{

    /*=============================================================================
  |----------------------------------------------------------------------------
  |  Citations:
  |  - Supplementary Reader by Prof. Alan Riggins
  |  - Data Structures and Algorithms in Java by Roberto Tamassia
  |  - Prof. Kraft Lecture Notes
  |  - Prof. Rob Edwards Videos
  |  - Introduction to Algorithms by Thomas H. Cormen
  | >> These books and resources were refered to write the insert(), trickleUp(),
  |   trickleDown(), getNextChild(), Wrapper Class, Iterator Class
  |--------------------------------------------------------------------------*/

    /*===================Class=Variables=======================================================*/

    protected  Wrapper<E>[] array;
    protected long inNum;
    protected long modCounter = 0;
    protected int currentSize = 0, size;


    //Default Constructor
    public BinaryHeapPriorityQueue(){
        this(DEFAULT_MAX_CAPACITY);
    }

    //Constructor : Size provided by user
    public BinaryHeapPriorityQueue(int size){
        inNum = currentSize = 0;
        array = new Wrapper[size];
        this.size = size;
    }
    /*===================Class=Variables========================================================*/



    /*===================Wrapper=Class=========================================================*/
    //Cited Above : Supplementary Reader by Prof. Riggins

    public class Wrapper<E> implements Comparable<Wrapper<E>>{
        E data;
        public long sequenceNumber;

        public Wrapper(E obj){
            data = obj;
            sequenceNumber = inNum++;
        }
        public int compareTo(Wrapper<E>obj){
            int tmp = ((Comparable<E>)data).compareTo(obj.data);
            if(tmp == 0)
                return (int)(sequenceNumber - obj.sequenceNumber);
            return tmp;
        }
        public String toString(){
            return "" + data;
        }
    }
    /*===================Wrapper=Class=========================================================*/

    public boolean insert(E object) {
        if(currentSize == size)
            return false;

        array[currentSize] = new Wrapper<>(object);
        int prevSize = currentSize;
        currentSize++;
        modCounter++;
        trickleUp(prevSize);
        return true;
    }


     /*=============================================================================
  |----------------------------------------------------------------------------
  | > Resize methods, this method can be used to increase the size of the array
  | import java.util.Arrays; can be imported to use this method
  | > it will be called when after if(currentSize == size) in insert(E object) method
  |  protected T[] resize() {
  |      return Arrays.copyOf(array, array.length * 2);
  |  }
  |--------------------------------------------------------------------------*/



    public E remove() {
        int tmp = currentSize;
        if(isEmpty())
            return null;

        E top = array[0].data;
        trickleDown(0);

        tmp--;
        modCounter++;
        currentSize--;
        return top;
    }

    public boolean delete(E obj) {
        if(!contains(obj))
            return false;

        Wrapper<E> [] Storage = array;
        boolean hasRemoved = false;
        int size = currentSize;
        currentSize = 0;
        inNum = 0;

        for (int i =0; i < size; i++)
            if ((Storage[i].data).compareTo(obj) != 0)
                insert(Storage[i].data);
            else {
                modCounter++;
                hasRemoved = true;
            }
        return hasRemoved;
    }

    public E peek() {
        if(isEmpty()){
            return null;
        }
        return array[0].data;
    }

    public boolean contains(E obj) {
        if(isEmpty())
            return false;
        for(int i=0; i<currentSize;i++ ){
            if (array[i].data.compareTo(obj) == 0){
                return true;
            }
        }
        return false;
    }

    public int size() {
        return currentSize;
    }

    public void clear() {
        currentSize = 0;
        inNum = 0;
    }

    public boolean isEmpty() {
        if(currentSize == 0)
            return true;
        return false;
    }

    public boolean isFull() {
        if(currentSize == size)
            return true;
        return false;
    }

    /*=============================================================================
|----------------------------------------------------------------------------
|  Helper Methods: Movement of elements in the Tree
|  - TrickleUp()
|  - TrickleDown()
|  - GetNextChild()
|  - Iterator()
|--------------------------------------------------------------------------*/
    //Cited Above : Supplementary Reader by Prof. Riggins
    protected void trickleUp( int CurrentIndex){
        modCounter++;
        int parentElement =  (int)((CurrentIndex - 1) / 2);
        Wrapper<E> currentObject = array[CurrentIndex];

        while ((parentElement >= 0) &&
                (currentObject.compareTo(array[parentElement]) < 0)) {
            array[CurrentIndex] = array[parentElement];
            CurrentIndex =  parentElement;
            //right shift is used instead of dividing by 2
            parentElement = (CurrentIndex - 1) >> 1;
        }
        array[CurrentIndex] = currentObject;
    }

    //Cited Above : Supplementary Reader by Prof. Riggins
    protected void trickleDown(int index){
        modCounter++;
        int current = 0;
        int child = getNextChild(current);

        while (child != -1 &&
                array[current].compareTo(array[child]) < 0 &&
                array[child].compareTo(array[currentSize - 1]) < 0){
            array[current] = array[child];
            current = child;
            child = getNextChild(current);
        }
        array[current] = array[currentSize - 1];
    }

    //Cited Above : Supplementary Reader by Prof. Riggins
    protected int getNextChild(int current){
        int left = (current * 2)+1;
        int right = left+1;
        if(right < currentSize){
            if(array[left].compareTo(array[right]) < 0)
                return left;
            return right;
        }
        if (left < currentSize) return left;
        return -1;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    /*=============================================================================*/


    /*=============================================================================
|----------------------------------------------------------------------------
|  Name: IteratorHelper Class
|  Description: An iterator is an object that enables to traverse a container
|  Methods:
|  - next()
|  - remove()
|  - hasNext()
| > Took help from Supplementary Reader by prof. Riggins
|--------------------------------------------------------------------------*/

    public class IteratorHelper implements Iterator<E>{
        private int current = 0;
        private long stateCheck = modCounter;
        public boolean hasNext() {
            if(stateCheck != modCounter){
                throw new ConcurrentModificationException();
            }
            if(current < currentSize){
                return true;
            }
            return false;
        }

        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return array[current++].data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

