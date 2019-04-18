public class test {
    public void main (String args[]) {
        int array[] = new int[0], index;

        insert(array,1);
    }

    static void insert(int array[], int object)
    {
        int index = 0;
        if (index == 0) {array[index] = object;}
        else {
            array[++index] = object;
            //Initialize parent and child to pass through heap violation
            int parent = index/2;
            int child = index;
            //check heap violation
            heapViolation(parent, child);

            printArray(array);
        }
    }

    private static void printArray(int[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static void heapViolation(int parent, int child) {
        // if- parent>child -> swap
        if(parent > child) {
            //swap
            swap(parent, child);
        }
    }

    static void swap(int parent, int child) {
        int temp = 0;
        temp = parent;
        parent = child;
        child = temp;
    }
}
