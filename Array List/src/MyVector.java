import java.io.IOException;
import java.util.Arrays;

/** MyArrayList
 *
 *  Students should complete all methods in the interface MyList.
 *
 *  Add helper methods (private/protected) where useful.
 *  Suggestions:  shiftUp, shiftDown, checkIndexRange, etc.
 *
 */
public class MyVector<E> extends MyAbstractList<E> implements MyList<E> {

    //Object[] array;
    E[] array;
    protected static final int INITIAL_CAPACITY = 10;
    protected static final int INITIAL_CAPACITY_INCREMENT = 5;
    protected static int increment = INITIAL_CAPACITY_INCREMENT;

    public MyVector() {
        this(INITIAL_CAPACITY, INITIAL_CAPACITY_INCREMENT);
    }

    public MyVector(int initialCapacity) {
        this(initialCapacity, INITIAL_CAPACITY_INCREMENT);
    }

    @SuppressWarnings("unchecked")
    public MyVector(int initialCapacity, int initialIncrement) {
        increment = initialIncrement;
        array = (E[]) new Object[initialCapacity];
        size = 0;// let fail if bad parameter
    }
//getIncrement() which returns the capacity increment of the vector??

    public int lastIndexOf(E data) {
        for(int i = size-1; i > 0; i--) {
            if(array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    private void shiftElementUp(int index) {
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    private void shiftElemetDown(int index) throws Exception {
        if (checkRange(index)) {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
        }
    }

    /**
     * Appends the specified Object to the end of this list
     *
     * @param data element to insert
     * @return boolean success
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean add(E data) {
        if (this.size == this.array.length)
            this.resize();

        array[size] = (E) data;
        size++;
        return true;
    }

    /**
     * Appends the specified Object to the list at the specified index
     *
     * @param index position to insert data
     * @param data  element to insert
     * @return boolean success
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean add(int index, E data) {
        if(data!=null) {
            if(size==array.length) {
                resize();
            }
            if(array[index] == null) {
                array[index] = data;
            }
            else {
                try {
                    shiftElemetDown(index);
                    array[index] = data;
                    size++;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    /**
     * Let the garbage collector do the heavy lifting!
     */
    // @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
    }

    /**
     * Returns true if this list contains the specified Object
     *
     * @param data
     * @return boolean
     */
    @Override
    public boolean contains(E data) {
        //ToDo
        for (int i = 0; i < size; i++)
            if (array[i] == data)
                return true;
        return false;
    }

    /**
     * Returns the Object at the specified position in this list
     *
     * @param index
     * @return Object
     * @throws Exception if index out of range
     */
    @Override
    public E get(int index) throws Exception {
        if (checkRange(index)) {
            for (int i = 0; i < this.array.length; i++) {
                if (i == index)
                    return this.array[i];
            }
        }
        return null;
    }

    /**
     * Returns the Object at the specified position in this list and deletes it
     * from the list
     *
     * @param index element to remove
     * @return Object element removed if found, else null
     * @throws Exception if index out of range
     */
    @Override
    public E remove(int index) throws Exception {
        E temp = null;
        if (checkRange(index)) {
            temp = this.array[index];
            shiftElementUp(index);
    }
        return temp;
    }

    /**
     * Returns the index of the first occurrence of the specified Object in this
     * list, or -1 if this list does not contain the Object
     *
     * @param data element to search for
     * @return int position of data if found, else -1
     */
    @Override
    public int indexOf(E data) {

    if(data!=null) {
        for (int i = 0; i < size; i++) {
                if (array[i].equals(data)) {
                return i;
            }
        }
    }
        return -1;
    }

    /**
     * Trims the capacity of this instance to be the list's current size. An
     * application can use this operation to minimize the storage of an
     * instance.
     */
    @Override
    public void trimToSize() {
        try {
            E[] temp = (E[]) new Object[size];
            temp = Arrays.copyOf(array, size);
            array = temp;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {

        String s = "[";
        if (!isEmpty()) {
            for (int i = 0; i < size - 1; i++)
                s += array[i] + ", ";
            s += array[size - 1];
        }
        return s += "]";
    }

    /**
     * Grow array
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;

    }

    private void resize() {
        try {
            E[] temp = (E[]) new Object[array.length + increment];
            for(int i=0;i<size;i++) {
                temp[i] = array[i];
            }
            array = temp;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return size;
    }

    public int getIncrement() {
        return increment;
    }

    public int getCapacity() {
        return array.length;
    }

    /**
     * Double size and copy elements.
     *
     * @return Object[]
     */
    @SuppressWarnings("unchecked") // uncomment for generic type
    private Object[] doubleArraySizeAndCopy() {
        Object[] temp = (E[]) new Object[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++) {
            temp[i] = this.array[i];
        }
        /* Alternately, could  use:
         *    temp = Arrays.copyOf(array, temp.length);
         */
        return temp;
    }

    private boolean checkRange(int index) throws Exception {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be negative");
        if (index >= this.size)
            throw new IndexOutOfBoundsException(
                    "Cannot access " + index + " element");
        return true;
    }

    public String getID() {
return  "Program 6, Pranav Kalra";
    }

}
