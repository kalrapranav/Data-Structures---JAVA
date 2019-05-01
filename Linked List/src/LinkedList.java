public class LinkedList<E> implements LinkedListInterface<E> {

    class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;
        //constructor
        public Node(E obj) {
            data = obj;
            next = null;
            previous = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    //constructor
    public LinkedList() {
        head  = tail = null;
        currentSize = 0;
    }

    /*
    * In every method, take care of the five boundary conditions:
    * 1. Empty
    * 2. Single Element
    * 3. Working in Middle
    * 4. Working at the beginning
    * 5. Working at the end
    * */

    @Override
    public void addFirst(E obj) {
        //create a new node
        Node<E> node = new Node<E>(obj);

        //Empty List
        if (head == null) {
            node.next = null;
            head = node;
            currentSize++;
            return;
        }

        node.next = head;
        head = node;
        currentSize++;
        return;
    }

    @Override
    public void addLast(E obj) {

    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLastt() {
        return null;
    }

    @Override
    public boolean contains(E obj) {
        return false;
    }

    @Override
    public E find(E obj) {
        return null;
    }

    @Override
    public E remove(E obj) {
        return null;
    }
}
