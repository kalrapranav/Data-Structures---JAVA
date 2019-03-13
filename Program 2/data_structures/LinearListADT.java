package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
public interface LinearListADT<E extends Comparable<E>> extends Iterable<E> {
 public static final int DEFAULT_MAX_CAPACITY = 100;

 /* Adds the Object obj to the beginning of list and returns true if the list
 * is not full.
 * returns false and aborts the insertion if the list is full.
 */
 public boolean addFirst(E obj);

 /* Adds the Object obj to the end of list and returns true if the list is
 * not full.
 * returns false and aborts the insertion if the list is full.
 */
 public boolean addLast(E obj);

 /* Removes and returns the parameter object obj in first position in list
 * if the list is not empty, null if the list is empty.
 */
 public E removeFirst();

 /* Removes and returns the parameter object obj in last position in list if
 * the list is not empty, null if the list is empty.
 */
 public E removeLast();

 /* Removes and returns the parameter object obj from the list if the list
 * contains it, null otherwise. The ordering of the list is preserved. 
 * The list may contain duplicate elements. This method removes and returns
 * the first matching element found when traversing the list from first
 * position. Note that you may have to shift elements to fill in the slot
 * where the deleted element was located.
 */
 public E remove(E obj);

 /* Returns the first element in the list, null if the list is empty.
 * The list is not modified.
 */
 public E peekFirst();

 /* Returns the last element in the list, null if the list is empty.
 * The list is not modified.
 */
 public E peekLast();
 /* Returns true if the parameter object obj is in the list, false otherwise.
 * The list is not modified.
 */
 public boolean contains(E obj);

 /* Returns the element matching obj if it is in the list, null otherwise.
 * In the case of duplicates, this method returns the element closest to
 * front. The list is not modified.
 */
 public E find(E obj);
 /* The list is returned to an empty state.
 */
 public void clear();
 /* Returns true if the list is empty, otherwise false
 */
 public boolean isEmpty();

 /* Returns true if the list is full, otherwise false
 */
 public boolean isFull();
 /* Returns the number of Objects currently in the list.
 */
 public int size();

 /* Returns an Iterator of the values in the list, presented in the same
 * order as the underlying order of the list. (front first, rear last)
 */
 public Iterator<E> iterator();
}