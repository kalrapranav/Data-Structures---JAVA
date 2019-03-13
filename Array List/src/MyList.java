/**
 * Partial implementation in the manner of Java List API
 *
 * @author Student
 *
 */
public interface MyList<E> {

    /**
     * Appends the specified Object to the end of this list
     *
     * @param data
     *            element to insert
     * @return boolean success
     */
    boolean add(E data);

    /**
     * Appends the specified Object to the list at the specified index
     *
     * @param index
     *            position to insert data
     * @param data
     *            element to insert
     * @return boolean success
     */
     boolean add(int index, E data);

    /**
     * Removes all of the Objects from this list
     */
    void clear();

    /**
     * Returns true if this list contains the specified Object
     *
     * @param data
     * @return boolean
     */
    boolean contains(E data);

    /**
     * Returns the Object at the specified position in this list
     *
     * @param index
     * @return Object
     * @throws Exception
     *             if index out of range
     */
    E get(int index) throws Exception;

    /**
     * Returns the Object at the specified position in this list and deletes it
     * from the list
     *
     * @param index
     *            element to remove
     * @return Object element removed if found, else null
     * @throws Exception
     *             if index out of range
     */
    E remove(int index) throws Exception;

    /**
     * Returns the index of the first occurrence of the specified Object in this
     * list, or -1 if this list does not contain the Object
     *
     * @param data
     *            element to search for
     * @return int position of data if found, else -1
     */
    int indexOf(E data);

    /**
     * Returns true if this list contains no Objects
     *
     * @return boolean
     */
    boolean isEmpty();

    /**
     * Returns the number of Objects in this list
     *
     * @return int
     */
    int size();

    /**
     * Trims the capacity of this instance to be the list's current size. An
     * application can use this operation to minimize the storage of an
     * instance.
     */
    void trimToSize();

}
