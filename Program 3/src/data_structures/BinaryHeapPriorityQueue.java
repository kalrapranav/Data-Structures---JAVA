//WORK IN PROGRESS
package data_structures;



public class BinaryHeapPriorityQueue {
    private int[] array;
    private int pos;
    private int capacity;

    public BinaryHeapPriorityQueue() {
        pos = 1;
        capacity = 10;
        array = new int[capacity];
    }

    public void insert(int object) {
        array[pos++] = object;

        int child = pos-1;
        int parent = child/2;

        while(array[parent] > array[child] && parent > 0) {
            int tmp = array[parent];
            array[parent] = array[child];
            array[child] = tmp;

            child = parent;
            parent = child/2;
        }
    }

    public void print() {
        for(int i=1; i<pos; i++) {
            System.out.print(array[i] + " ");
        }
    }


}
