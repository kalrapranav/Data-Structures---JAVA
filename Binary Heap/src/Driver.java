/*  Driver.java by Nhan Phan
    CS310 Spring 2018
    A modified version with more comprehensive tests than from Alan Riggins' simple driver
	
	"Program testing can be a very effective way to show the presence of bugs, but is hopelessly inadequate for showing their absence." 
	- Edsger W. Dijkstra    

	^ In case you don't know, you will "see" him (at least) in your CS560 and CS570
    
	DISCLAIMER: This driver tries its best at testing every single public methods. Many cases will be tested but it doesn't imply that your implementation will be bug-free. 
*/


import data_structures.*;
import java.util.concurrent.TimeUnit;

public class Driver {
    private int [] array;
    private static final int SIZE = 1000;
    private PriorityQueue<Integer> pq;
    private PriorityQueue<PrioritizedItem> pq2;

    public Driver() {
        array = new int[SIZE];
        pq = new BinaryHeapPriorityQueue<Integer>(SIZE);
        pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
        initArray();
        try {
            test1();
            Thread.sleep(1000);
            test2();
            Thread.sleep(1000);
            test3();
            Thread.sleep(1000);
            test4();
            Thread.sleep(1000);
            test5();
            Thread.sleep(1000);
            System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n");
            test6();
            Thread.sleep(1000);
            test7_1();
            Thread.sleep(1000);
            test7_2();
            Thread.sleep(1000);
            test8_1();
            Thread.sleep(1000);
            test8_2();
            Thread.sleep(1000);
            test9_1();
            Thread.sleep(1000);
            test9_2();
            Thread.sleep(1000);
            test10();
            Thread.sleep(1000);
            test11();
            Thread.sleep(1000);
            test12();
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void initArray() {
        for(int i=0; i < SIZE; i++)
            array[i] = i+1;
        // now scramble array order
        for(int i=0; i < SIZE; i++) {
            int idx = (int) (SIZE*Math.random());
            int tmp = array[i];
            array[i] = array[idx];
            array[idx] = tmp;
        }
    }

    private void test1() {
        pq.clear();
        for(Integer i : pq)
            throw new RuntimeException("Failed test #1, value returned in empty iterator");

        for(int i=0; i < SIZE; i++)
            if(!pq.insert(array[i]))
                throw new RuntimeException("Failed test #1");
//////////////////////////////////////////////////////////////////////////////                
//  Comment this block for linked list based implementations         
        if(!pq.isFull())
            throw new RuntimeException("Failed test #1, isFull reports false, but pq should be full");
        //try to exceed the capacity
        if(pq.insert(0))
            throw new RuntimeException("Failed test1, exceeded capacity");
//////////////////////////////////////////////////////////////////////////////       
        System.out.println("Passed test #1, simple insert");
    }

    private void test2() {
        try {
            for(int i=0; i < SIZE; i++) {
                //displayIntegers();
                if(pq.remove() != (i+1))
                    throw new RuntimeException("Failed test #2, out of order removal");
            }

            if(pq.remove() != null)
                throw new RuntimeException("Failed test #2, removal from empty pq did not return null");

            if(!pq.isEmpty())
                throw new RuntimeException("Failed test #2, isEmpty reports false, but pq is empty");

            System.out.println("Passed test #2, simple removal");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test3() { // check FIFO behavior
        int size=10;
        pq2.clear();
        int sequenceNumber = 0;
        int midPoint = size >> 1;

        for(int i=0; i < midPoint; i++)
            pq2.insert(new PrioritizedItem(2,sequenceNumber++));
        for(int i=midPoint; i < size; i++)
            pq2.insert(new PrioritizedItem(1,sequenceNumber++));

        PrioritizedItem item = pq2.peek();
        if(item.getPriority() != 1 || item.getSequenceNumber() != 5)
            throw new RuntimeException("Failed test #3, peek returns wrong element");

        sequenceNumber = midPoint;
        for(int i=0; i < midPoint; i++) {
            PrioritizedItem tmp = pq2.remove();
            if(tmp.getPriority() != 1)
                throw new RuntimeException("Failed test #3, out of order removal");
            if(tmp.getSequenceNumber() != (sequenceNumber++))
                throw new RuntimeException("Failed test #3, out of order removal");
        }

        sequenceNumber = 0;
        for(int i=midPoint; i < size; i++) {
            PrioritizedItem tmp = pq2.remove();
            if(tmp.getPriority() != 2)
                throw new RuntimeException("Failed test #3, out of order removal");
            if(tmp.getSequenceNumber() != (sequenceNumber++))
                throw new RuntimeException("Failed test #3, out of order removal");
        }
        System.out.println("Passed test #3, FIFO check");

    }

    private void test4() {
        pq2.clear();
        int sequenceNumber = 0;
        System.out.println("\nNow checking iterators, output is below.");
        System.out.println("NOTE: No specific order is required for these iterators.");
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(10,sequenceNumber++));
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(1,sequenceNumber++));
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(5,sequenceNumber++));

        for(PrioritizedItem item : pq2)
            System.out.println(item);

        System.out.println("Now removing elements with priority=5");
        pq2.delete(new PrioritizedItem(5,100));

        System.out.println("\nNow removing items, they should be in proper order,\n"+
                "with all priority=5 items removed.");
        while(!pq2.isEmpty())
            System.out.println(pq2.remove());

        if(pq2.size() != 0)
            System.out.println("Failed test #4, size is wrong.");
        else
            System.out.println("Passed test #4");
    }

    private void test5() {
        final int TEST_5_SIZE = 1000;
        pq = new BinaryHeapPriorityQueue<Integer>(TEST_5_SIZE);

        for(int i=0; i<TEST_5_SIZE; i++) {
            int someInteger = (int) (TEST_5_SIZE*Math.random());
            if(!pq.insert(someInteger)) {
                System.out.println("ERROR in test 5, insertion failed!");
                System.exit(1);
            }
        }
        int removed = pq.remove();
        for(int i=1; i < TEST_5_SIZE; i++) {
            int removed2 = pq.remove();
            // System.out.println(removed2);
            if(removed2 < removed) {
                System.out.println("ERROR, out of order removal in test 5");
                System.exit(1);
            }
            removed = removed2;
        }
        System.out.println("Passed test #5");
    }

    private void test6() {
        try {
            pq.clear();
            pq2.clear();

            if (!pq.isEmpty() || !pq2.isEmpty())
                throw new RuntimeException("AT LEAST 1 PQ IS CLEARED BUT NOT EMPTY");

            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("AT LEAST 1 PQ IS CLEARED BUT SIZE IS NON-ZERO");

            if (pq.delete(1) || pq2.delete(new PrioritizedItem(1,1)))
                throw new RuntimeException("DELETE SUCCESSFUL ON A CLEARED PQ");

            if (pq.peek() != null || pq2.peek() != null)
                throw new RuntimeException("PEEK RETURN SOMETHING ON EMPTY PQ");
            if (pq.isFull() || pq2.isFull())
                throw new RuntimeException("isFull() RETURN TRUE");

            if (pq.remove() != null || pq2.remove() != null)
                throw new RuntimeException("REMOVE SUCCESSFUL ON CLEARED PQ");
            System.out.println("Passed test #6: On Empty\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test7_1() {
        pq = new BinaryHeapPriorityQueue<Integer>(SIZE);
        //pq = new UnorderedLinkedListPriorityQueue<Integer>();

        pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
        //pq2 = new UnorderedLinkedListPriorityQueue<PrioritizedItem>();

        try {
            System.out.print("+ Test case 0 - Insert and Remove: ");
            int size = 100000;
            for(int i=0; i < size; i++) {
                pq.insert(i);
                pq.remove();
                if (i == size / 2)
                    pq2.insert(new PrioritizedItem(1,i));
                else pq2.insert(new PrioritizedItem(2,i));
                pq2.remove();
                if (pq.size() != 0 || pq2.size() != 0)
                    throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            }
            System.out.println("Done!");

            System.out.println("Now insert duplicates: ");
            for(int i=0; i < 100; i++) {
                if (i < 50) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }

            System.out.print("+ Test case 1 - Duplicates at the first part: ");
            if (!pq.delete(1) || !pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("INSERTED 1 BUT CAN'T DELETE 1");
            else if (pq.delete(1) || pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("ALREADY DELETED 1 AND YET CAN STILL DELETE ANOTHER 1(s)");
            while (!pq.isEmpty())
                pq.remove();
            while (!pq2.isEmpty())
                pq2.remove();
            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            System.out.println("Done!");


            System.out.print("+ Test case 2 - Duplicates at the end part: ");
            for(int i=0; i < 100; i++) {
                if (i > 50) {
                    pq.insert(60);
                    pq2.insert(new PrioritizedItem(60,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }
            if (!pq.delete(60) || !pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("INSERTED 60 BUT CAN'T DELETE 60");
            else if (pq.delete(60) || pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("ALREADY DELETED 60 AND YET CAN STILL DELETE ANOTHER 60(s)");
            System.out.println("Done!");

            System.out.print("+ Test case 3 - Duplicates at the middle: ");
            for(int i=0; i < 100; i++) {
                if (i >= 50 && i <= 70) {
                    pq.insert(50);
                    pq2.insert(new PrioritizedItem(50,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }
            if (!pq.delete(50) || !pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("INSERTED 50 BUT CAN'T DELETE 50");
            else if (pq.delete(50) || pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("ALREADY DELETED 50 AND YET CAN STILL DELETE ANOTHER 50(s)");
            System.out.println("Done!");
            System.out.println("Passed test #7_1: Insert ordered items on BinaryHeapPQ - 1\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test7_2() {
        //pq = new BinaryHeapPriorityQueue<Integer>(SIZE);
        pq = new BinaryHeapPriorityQueue<Integer>(SIZE);

        //pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
        pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);

        try {
            System.out.print("+ Test case 0 - Insert and Remove: ");
            int size = 100000;
            for(int i=0; i < size; i++) {
                pq.insert(i);
                pq.remove();
                if (i == size / 2)
                    pq2.insert(new PrioritizedItem(1,i));
                else pq2.insert(new PrioritizedItem(2,i));
                pq2.remove();
                if (pq.size() != 0 || pq2.size() != 0)
                    throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            }
            System.out.println("Done!");


            System.out.println("Now insert duplicates: ");
            for(int i=0; i < 100; i++) {
                if (i < 50) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }

            System.out.print("+ Test case 1 - Duplicates at the first part: ");
            if (!pq.delete(1) || !pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("INSERTED 1 BUT CAN'T DELETE 1");
            else if (pq.delete(1) || pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("ALREADY DELETED 1 AND YET CAN STILL DELETE ANOTHER 1(s)");
            while (!pq.isEmpty())
                pq.remove();
            while (!pq2.isEmpty())
                pq2.remove();
            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            System.out.println("Done!");


            System.out.print("+ Test case 2 - Duplicates at the end part: ");
            for(int i=0; i < 100; i++) {
                if (i > 50) {
                    pq.insert(60);
                    pq2.insert(new PrioritizedItem(60,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }
            if (!pq.delete(60) || !pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("INSERTED 60 BUT CAN'T DELETE 60");
            else if (pq.delete(60) || pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("ALREADY DELETED 60 AND YET CAN STILL DELETE ANOTHER 60(s)");
            System.out.println("Done!");

            System.out.print("+ Test case 3 - Duplicates at the middle: ");
            for(int i=0; i < 100; i++) {
                if (i >= 50 && i <= 70) {
                    pq.insert(50);
                    pq2.insert(new PrioritizedItem(50,9));
                }
                else {
                    pq.insert(i);
                    pq2.insert(new PrioritizedItem(i,i));
                }
            }
            if (!pq.delete(50) || !pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("INSERTED 50 BUT CAN'T DELETE 50");
            else if (pq.delete(50) || pq2.delete(new PrioritizedItem(50,9)) || pq2.contains(new PrioritizedItem(50,9)))
                throw new RuntimeException("ALREADY DELETED 50 AND YET CAN STILL DELETE ANOTHER 50(s)");
            System.out.println("Done!");
            System.out.println("Passed test #7_2: Insert ordered items on BinaryHeapPQ - 2\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private void test8_1() {
        pq = new BinaryHeapPriorityQueue<Integer>(SIZE);
        //pq = new UnorderedLinkedListPriorityQueue<Integer>();

        pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
        //pq2 = new UnorderedLinkedListPriorityQueue<PrioritizedItem>();
        try {
            System.out.print("+ Test case 0 - Insert and Remove: ");
            int size = 100000;
            for(int i=0; i < size; i++) {
                pq.insert((int)(size * Math.random()));
                pq.remove();
                if (i == size / 2)
                    pq2.insert(new PrioritizedItem(1, (int)(size*Math.random())));
                else pq2.insert(new PrioritizedItem(2, (int)(size*Math.random())));
                pq2.remove();
                if (pq.size() != 0 || pq2.size() != 0)
                    throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            }
            System.out.println("Done!");

            System.out.println("Now insert duplicates: ");
            for(int i=0; i < 100; i++) {
                if (i < 50) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }

            System.out.print("+ Test case 1 - Duplicates at the first part: ");
            if (!pq.delete(1) || !pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("INSERTED 1 BUT CAN'T DELETE 1");
            else if (pq.delete(1) || pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("ALREADY DELETED 1 AND YET CAN STILL DELETE ANOTHER 1(s)");
            while (!pq.isEmpty())
                pq.remove();
            while (!pq2.isEmpty())
                pq2.remove();
            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            System.out.println("Done!");


            System.out.print("+ Test case 2 - Duplicates at the end part: ");
            for(int i=0; i < 100; i++) {
                if (i > 50) {
                    pq.insert(60);
                    pq2.insert(new PrioritizedItem(60,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }
            if (!pq.delete(60) || pq.contains(60) || !pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("INSERTED 60 BUT CAN'T DELETE 60");
            else if (pq.delete(60) || pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("ALREADY DELETED 60 AND YET CAN STILL DELETE ANOTHER 60(s)");
            System.out.println("Done!");

            System.out.print("+ Test case 3 - Duplicates at the middle: ");
            for(int i=0; i < 100; i++) {
                if (i >= 50 && i <= 70) {
                    pq.insert(50);
                    pq2.insert(new PrioritizedItem(50,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }
            if (!pq.delete(50) || !pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("INSERTED 50 BUT CAN'T DELETE 50");
            else if (pq.delete(50) || pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("ALREADY DELETED 50 AND YET CAN STILL DELETE ANOTHER 50(s)");
            System.out.println("Done!");
            System.out.println("Passed test #8_1: Insert random items on BinaryHeapPQ - 1\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    private void test8_2() {
        //pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new BinaryHeapPriorityQueue<Integer>(SIZE);

        //pq2 = new OrderedLinkedListPriorityQueue<PrioritizedItem>();
        pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
        try {
            System.out.print("+ Test case 0 - Insert and Remove: ");
            int size = 100000;
            for(int i=0; i < size; i++) {
                pq.insert((int)(size * Math.random()));
                pq.remove();
                if (i == size / 2)
                    pq2.insert(new PrioritizedItem(1, (int)(size*Math.random())));
                else pq2.insert(new PrioritizedItem(2, (int)(size*Math.random())));
                pq2.remove();
                if (pq.size() != 0 || pq2.size() != 0)
                    throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            }
            System.out.println("Done!");

            System.out.println("Now insert duplicates: ");
            for(int i=0; i < 100; i++) {
                if (i < 50) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }

            System.out.print("+ Test case 1 - Duplicates at the first part: ");
            if (!pq.delete(1) || !pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("INSERTED 1 BUT CAN'T DELETE 1");
            else if (pq.delete(1) || pq2.delete(new PrioritizedItem(1,9)))
                throw new RuntimeException("ALREADY DELETED 1 AND YET CAN STILL DELETE ANOTHER 1(s)");
            while (!pq.isEmpty())
                pq.remove();
            while (!pq2.isEmpty())
                pq2.remove();
            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("REMOVED ALL BUT SIZE IS NON-ZERO");
            System.out.println("Done!");


            System.out.print("+ Test case 2 - Duplicates at the end part: ");
            for(int i=0; i < 100; i++) {
                if (i > 50) {
                    pq.insert(60);
                    pq2.insert(new PrioritizedItem(60,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }
            if (!pq.delete(60) || !pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("INSERTED 60 BUT CAN'T DELETE 60");
            else if (pq.delete(60) || pq2.delete(new PrioritizedItem(60,9)))
                throw new RuntimeException("ALREADY DELETED 60 AND YET CAN STILL DELETE ANOTHER 60(s)");
            System.out.println("Done!");

            System.out.print("+ Test case 3 - Duplicates at the middle: ");
            for(int i=0; i < 100; i++) {
                if (i >= 50 && i <= 70) {
                    pq.insert(50);
                    pq2.insert(new PrioritizedItem(50,9));
                }
                else {
                    pq.insert((int)(size * Math.random()));
                    pq2.insert(new PrioritizedItem((int)(size * Math.random()), (int)(size*Math.random())));
                }
            }
            if (!pq.delete(50) || !pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("INSERTED 50 BUT CAN'T DELETE 50");
            else if (pq.delete(50) || pq.contains(50) || pq2.delete(new PrioritizedItem(50,9)))
                throw new RuntimeException("ALREADY DELETED 50 AND YET CAN STILL DELETE ANOTHER 50(s)");
            System.out.println("Done!");
            System.out.println("Passed test #8_2: Insert random items on BinaryHeapPQ - 2\n");

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
    private void test9_1() {
        try {
            System.out.println("Iterator should print something after test#8_2");
            int i = 0;
            for(Integer x : pq) {
                if (pq.size() == 0)
                    throw new RuntimeException("NOTHING TO PRINT");
                else if (i > 5) {
                    System.out.println("...\nChecking pq: Done");
                    break;
                }
                System.out.println(x);
                i++;
            }
            pq.clear();
            System.out.println("PQ is cleared, should return nothing");
            for(Integer x : pq) System.out.println(x);
            System.out.println("Passed test #9_1: Iterators on BinaryHeapPQ - 1\n");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAILED test #9_1: Iterators on BinaryHeapPQ - 1\n");
        }
    }

    private void test9_2() {
        try {
            System.out.println("Iterator should print something after test#8_2");
            int i = 0;
            for(PrioritizedItem x : pq2) {
                if (pq2.size() == 0)
                    throw new RuntimeException("NOTHING TO PRINT");
                else if (i > 5) {
                    System.out.println("...\nChecking pq2: Done");
                    break;
                }
                System.out.println(x);
                i++;
            }
            pq2.clear();
            System.out.println("PQ is cleared, should return nothing");
            for(PrioritizedItem x : pq2) System.out.println(x);
            System.out.println("Passed test #9_2: Iterators on BinaryHeapPQ - 2\n");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAILED test #9_2: Iterators on BinaryHeapPQ - 2\n");
        }
    }

    private void test10() {
        try {
            //pq = new OrderedLinkedListPriorityQueue<Integer>();
            pq = new BinaryHeapPriorityQueue<Integer>(SIZE);

            //pq2 = new OrderedLinkedListPriorityQueue<PrioritizedItem>();
            pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);

            int j = 0;
            for(int i=1; i < 10; i++) {
                if (j == 0) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,i));
                }
                else if (j == 1) {
                    pq.insert(2);
                    pq2.insert(new PrioritizedItem(2,i));
                }
                else {
                    pq.insert(3);
                    pq2.insert(new PrioritizedItem(3,i));
                    j = 0;
                    continue;
                }
                j++;
            }

            System.out.println("Printing the pq:");
            for(Integer x : pq) System.out.println(x);
            System.out.println("\nNow, printing the pq2:");
            for(PrioritizedItem x : pq2) System.out.println(x);

            //pq.delete(3);
            //pq2.delete(new PrioritizedItem(3,0));

            pq.remove();
            pq.remove();
            if (pq.remove() != 1)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if ((pq.remove()) - (pq.remove()) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if (pq2.remove().compareTo(new PrioritizedItem(3,0)) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if (pq2.remove().compareTo(pq2.remove()) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");

            pq.clear();
            pq2.clear();
            j = 0;
            for(int i=1; i < 10; i++) {
                if (j == 0) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,i));
                }
                else if (j == 1) {
                    pq.insert(2);
                    pq2.insert(new PrioritizedItem(2,i));
                }
                else {
                    pq.insert(3);
                    pq2.insert(new PrioritizedItem(3,i));
                    j = 0;
                    continue;
                }
                j++;
            }
            for (int i = 0; i < 6; i++) {
                pq.remove();
                pq2.remove();
            }

            if (pq2.remove().compareTo(new PrioritizedItem(3,0)) > 0 || pq2.remove().compareTo(pq2.remove()) > 0 || pq.remove() - pq.remove() > 0 || pq.remove() != 3)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");

            System.out.println("Passed test #10: Removal on BinaryHeapPQ - 1");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAILED test #10: Removal on BinaryHeapPQ - 1");
        }
    }

    private void test11() {
        try {
            pq = new BinaryHeapPriorityQueue<Integer>(SIZE);
            //pq = new UnorderedLinkedListPriorityQueue<Integer>();

            pq2 = new BinaryHeapPriorityQueue<PrioritizedItem>(SIZE);
            //pq2 = new UnorderedLinkedListPriorityQueue<PrioritizedItem>();

            int j = 0;
            for(int i=1; i < 10; i++) {
                if (j == 0) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,i));
                }
                else if (j == 1) {
                    pq.insert(2);
                    pq2.insert(new PrioritizedItem(2,i));
                }
                else {
                    pq.insert(3);
                    pq2.insert(new PrioritizedItem(3,i));
                    j = 0;
                    continue;
                }
                j++;
            }

            System.out.println("Printing the pq:");
            for(Integer x : pq) System.out.println(x);
            System.out.println("\nNow, printing the pq2:");
            for(PrioritizedItem x : pq2) System.out.println(x);

            //pq.delete(3);
            //pq2.delete(new PrioritizedItem(3,0));

            pq.remove();
            pq.remove();
            if (pq.remove() != 1)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if ((pq.remove()) - (pq.remove()) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if (pq2.remove().compareTo(new PrioritizedItem(3,0)) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            if (pq2.remove().compareTo(pq2.remove()) > 0)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");

            pq.clear();
            pq2.clear();
            j = 0;
            for(int i=1; i < 10; i++) {
                if (j == 0) {
                    pq.insert(1);
                    pq2.insert(new PrioritizedItem(1,i));
                }
                else if (j == 1) {
                    pq.insert(2);
                    pq2.insert(new PrioritizedItem(2,i));
                }
                else {
                    pq.insert(3);
                    pq2.insert(new PrioritizedItem(3,i));
                    j = 0;
                    continue;
                }
                j++;
            }
            for (int i = 0; i < 6; i++) {
                pq.remove();
                pq2.remove();
            }

            if (pq2.remove().compareTo(new PrioritizedItem(3,0)) > 0 || pq2.remove().compareTo(pq2.remove()) > 0 || pq.remove() - pq.remove() > 0 || pq.remove() != 3)
                throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            System.out.println("Passed test #11: Removal on BinaryHeapPQ - 2");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAILED test #11: Removal on BinaryHeapPQ - 2");
        }
    }

    private void test12() {
        try {
            if (pq.size() != 0 || pq2.size() != 0)
                throw new RuntimeException("LEFTOVER FOUND");

            int j = 0;
            for(int i=1; i < 10; i++) {
                if (j == 0) {
                    pq.insert(5);
                    pq2.insert(new PrioritizedItem(5,i));
                }
                else if (j == 1) {
                    pq.insert(9);
                    pq2.insert(new PrioritizedItem(9,i));
                }
                else {
                    pq.insert(56);
                    pq2.insert(new PrioritizedItem(56,i));
                    j = 0;
                    continue;
                }
                j++;
            }
            while (pq.size() > 1 || pq2.size() > 1) {
                if ((pq.remove() > pq.peek()) || pq2.remove().compareTo(pq2.peek()) > 0)
                    throw new RuntimeException("pq HAS OUT OF ORDER REMOVAL");
            }

            if (pq.size() != 1 || pq.peek() == null || !pq.contains(pq.peek()) || pq.remove() == null || pq2.size() != 1 || pq2.peek() == null || !pq2.contains(pq2.peek()) || pq2.remove() == null)
                throw new RuntimeException("MISSING LAST ELEMENTS");



        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAILED test #12: Leftover test");
        }

        try {
            for (int k = 0; k < 5; k++) {
                pq.insert(k);
            }
            displayIntegers();
            System.out.println("Checking fail-fast feature in printing:");
            for (Integer l : pq) {
                System.out.println(l);
                if ((int)l == 3) {
                    System.out.println("Attempting to add while iterating...");
                    System.out.println("Should pop up some exception...\n");
                    pq.insert(1);
                    pq.remove();
                    pq.clear();
                }
            }

            System.out.println("No exceptions found...\nFAILED test #12: Leftover test");
        }


        catch (Exception e){
            e.printStackTrace();
            System.out.println("\nPassed test #12: Leftover test");
        }
    }


    ///////////////////////////////////////////////////////////////////////
    private class PrioritizedItem implements Comparable<PrioritizedItem>{
        private int priority;
        private int itemNumber;

        public PrioritizedItem(int p, int n) {
            priority = p;
            itemNumber = n;
        }

        public int compareTo(PrioritizedItem item) {
            return priority - item.priority;
        }

        public String toString() {
            return "Priority: " + priority + " Item Number: " + itemNumber;
        }

        public int getPriority() {
            return priority;
        }

        public int getSequenceNumber() {
            return itemNumber;
        }
    }
    ///////////////////////////////////////////////////////////////////////

    private void displayIntegers() {
        System.out.println("\nPrinting contents of integer array-------------------");
        int m = 1;
        for(Integer i  : pq) {
            System.out.printf("%4d ", i);
            if(m % 10 == 0)
                System.out.println();
            m++;
        }
        System.out.println("\n------------------------------------------------");
    }

    public static void main(String [] args) {
        new Driver();
    }
}