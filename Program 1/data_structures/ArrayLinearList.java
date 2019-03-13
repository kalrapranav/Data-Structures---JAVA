/**
 *  Program 1
 *  The following program is an implementation of the Double Ended queue using
 *  circular arrays
 *  CS310-01
 *  Date: 18-Feb-2019
 *  @author  Pranav Kalra cssc1483
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayLinearList<E> implements LinearListADT<E> {
    /**
     * Fields: Object E type array, int type currentSize, maxSize
     * */
    private E[] list;
    private int currentSize, maxSize;

    public ArrayLinearList(){
        currentSize=0;
        maxSize=DEFAULT_MAX_CAPACITY;
        list= (E[]) new Object[maxSize+1];
    }
    public ArrayLinearList(int capacity){
        currentSize=0;
        maxSize=capacity;
        list= (E[]) new Object[maxSize+1];
    }


    /**
     *  Adds the Object obj to the end of list and returns true if the list is not full.
     * returns false and aborts the insertion if the list is full.
     */
    public boolean addLast(E e){

        int index = currentSize+1;
        if(isFull())
            return false;
        else if(currentSize == maxSize)
            resizeArray(1);
        for(int i=currentSize; i >= index; i--)
            list[i+1] = list[i];
        list[index]=e;
        currentSize++;
        return true;
    }

    /**
     * Helper method to resize teh array according to the need
     * */
    private void resizeArray(int type){
        if(type== 1)
            maxSize <<=1; //shifts the bits left by 1
        if(type == 2)
            maxSize >>=1; //shift the bits right by 1
        E[] tmp=(E[])new Object[maxSize+1];
        for(int i=1; i<=currentSize; i++)
            tmp[i]=list[i];
        list=tmp;
    }

    /**
     *  Adds the Object obj to the beginning of list and returns true if the list is not
     * full.
     * returns false and aborts the insertion if the list is full.
     */
    public boolean addFirst(E e){
        int index = 1;
        if(isFull()){return false;}
        if(currentSize == maxSize)
            resizeArray(1);
        for(int i=currentSize; i >= index; i--)
            list[i+1] = list[i];
        list[index]=e;
        currentSize++;
        return true;
    }


    /**
     * Helper method in order to remove the object at a location specifoed by the user
     * and if the location if out of bounds so throws a RunTimeException
     * */
    private E removeLocation(int location){
        if(location>currentSize)
            throw new RuntimeException("The following location: " + location + " was not found in the list");
        if(currentSize-1 < maxSize/4)
            resizeArray(2);
        E object= list[location];
        for(int i=location+1;i<=currentSize;i++)
            list[i-1]=list[i];
        list[currentSize--]=null;
        return object;
    }

    /**
     *  Removes and returns the parameter object obj from the list if the list contains
     * it, null otherwise. The ordering of the list is preserved. The list may contain
     * duplicate elements. This method removes and returns the first matching element
     * found when traversing the list from first position.
     * Note that you may have to shift elements to fill in the slot where the deleted
     * element was located.
     */
    public E remove(E e){
        int i= findIndex(e);
        if(i==-1)
            return null;
        removeLocation(i);
        return e;

    }

    /**
     * Helper method to find the index of the object e speciped by teh user and returns an integer
     * value of teh following index
     * */
    private int findIndex(E e){
        for(int i=1;i<=currentSize;i++)
            if(((Comparable<E>)e).compareTo(list[i]) == 0) {
                return i;
            }
        return -1;
    }

    /**
     * Removes and returns the parameter object obj in first position in list if the list
     * is not empty, null if the list is empty.
     */
    public E removeFirst(){
//        E removed= removeLocation(1);
//        return removed;
        if (isEmpty())
        {return null;}
        else{
            E temp = list[1];
            currentSize--;
            //list[0]=temp;


             for(int i=currentSize; i >= 1; i--)
             list[i+1] = list[i];
             //list[0]=e;


            return temp;
        }
    }

    /**
     *  Removes and returns the parameter object obj in last position in list if the list
     * is not empty, null if the list is empty.
     */
    public E removeLast(){
//        E removed= removeLocation(currentSize);
        if (isEmpty())
        {return null;}
        else{
            E temp = list[currentSize];
            currentSize--;

            return temp;
        }
    }



    /**
     *  Returns true if the parameter object obj is in the list, false otherwise.
     * The list is not modified.
     */
    public boolean contains(E e){
        int i= findIndex(e);
         if(i!=-1){return true;}
         else{return false;}
    }

    /**
     *  Returns the element matching obj if it is in the list, null otherwise.
     * In the case of duplicates, this method returns the element closest to front.
     * The list is not modified.
     */
    public E find(E e){
        for(int i=1;i<=currentSize;i++)
            if(((Comparable<E>)e).compareTo(list[i]) == 0) {
                return list[i];
            }
        return null;
    }

    /**
     *  The list is returned to an empty state.
     */
    public void clear(){
        list= (E[]) new Object[maxSize+1];
        currentSize = 0;
    }

    /**
     *  Returns true if the list is empty, otherwise false
     */
    public boolean isEmpty(){
        return currentSize==0;
    }

    /**
     *  Returns true if the list is full, otherwise false
     */
    public boolean isFull(){
        return currentSize==maxSize;
    }

    /**
     *  Returns the first element in the list, null if the list is empty.
     * The list is not modified.
     */
    public E peekFirst(){
        if (isEmpty())
            return null;
        return list[1];
    }

    /**
     *  Returns the last element in the list, null if the list is empty.
     * The list is not modified.
     */
    public E peekLast(){
        if (isEmpty())
            return null;
        return list[currentSize];
    }

    /**
     *  /* Outputs teh rear ends of the list
     */
    public void ends(){
        int indexFront=1,indexRear=currentSize;
        System.out.println("Front: "+indexFront+" Rear: "+indexRear);
    }

    /**
     *  Returns the number of Objects currently in the list.
     */
    public int size(){
        //System.out.println(currentSize);
        return currentSize;
    }


    /**------------------------------------------------------------------------------------------------------------*/

    /**
     *  Returns an Iterator of the values in the list, presented in
     * the same order as the underlying order of the list. (front first, rear last)
     */
    public Iterator<E> iterator(){
        return new IteratorHelper();
    }

    /**
     * Helper class for teh Iterator
     * */
    class IteratorHelper implements Iterator<E> {
        int iterIndex;

        public IteratorHelper() {
            iterIndex = 1;
        }

        public boolean hasNext() {
            return iterIndex <= currentSize;
        }

        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            return list[iterIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

/**------------------------------------END-OF-PROGRAM----------------------------------------------------------------------*/
