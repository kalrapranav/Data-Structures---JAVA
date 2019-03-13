/*  LinkedListTester.java
    CS310 spring 2019
    Patty Kraft
    This test driver program will help you find errors in your linked
    list class.  Note that this driver does not guarantee that your 
    linked list is error free.  Place in the folder above data_structures.
*/

import java.util.Iterator;
import data_structures.*;

public class LinkedListTester {
    private LinearListADT<Integer> list;
    private int [] array;
    private int [] scrambled_array;
    private static final int STRUCTURE_SIZE = 50000;
    
    public LinkedListTester() {
        list = new LinearList<Integer>();
        array = new int[STRUCTURE_SIZE];
        scrambled_array = new int[STRUCTURE_SIZE];                 
        for(int i=0; i < STRUCTURE_SIZE; i++) 
            array[i] = scrambled_array[i] = (i+1);
            
        // randomly rearrange array elements for better testing
        for(int i=0; i < STRUCTURE_SIZE; i++) {
            int tmp = scrambled_array[i];
            int new_index = (int) (STRUCTURE_SIZE * Math.random());
            scrambled_array[i] = scrambled_array[new_index];
            scrambled_array[new_index] = tmp;
            }
        }
            
    public void runTests() {
        // addFirst/removeFirst check     
        for(int i=0; i < STRUCTURE_SIZE; i++) // add elements
            list.addFirst(array[i]);
        // remove and check the order
        for(int i=STRUCTURE_SIZE-1; i >= 0 ; i--)
            if(list.removeFirst() != (i+1))
                System.out.println(
                    "ERROR, out of order removal with addFirst/removeFirst"); 
                    
        // addLast/removeLast check
        for(int i=0; i < STRUCTURE_SIZE; i++) // add elements
            list.addLast(array[i]);
        // remove and check the order
        for(int i=0; i < STRUCTURE_SIZE; i++)
            if(list.removeFirst() != (i+1))
                System.out.println(
                    "ERROR, out of order removal with addLast/removeLast");
          
        // check handling of empty state, no exception should be thrown
        // nor anything printed to screen            
        if(list.removeFirst() != null)
            System.out.println("ERROR, removed from empty list"); 
            
        if(list.removeLast() != null)
            System.out.println("ERROR, removed from empty list");
            
        if(list.size() != 0)
            System.out.println("ERROR, size should be 0"); 
            
        for(Integer i : list)
            System.out.println("ERROR in iterator");
           
            
        // now testing the remove method            
        for(int i=0; i < STRUCTURE_SIZE; i++) // add elements
            list.addFirst(array[i]);
        
        int mark = 1000;    
        for(int i=0; i < STRUCTURE_SIZE; i++) { 
            if(i==mark) {
                System.out.println( (mark/500) + "% completed");           
                mark += 1000;
                }
            if(!list.remove(scrambled_array[i]))
                System.out.println("Failure in remove, returned false");
            if(list.contains(scrambled_array[i]))                             
                System.out.println(
                "Failure in contains, found an element that was removed.");        
            }
            
        // check handling of empty state, no exception should be thrown
        // nor anything printed to screen            
        if(list.removeFirst() != null)
            System.out.println("ERROR, removed from empty list"); 
            
        if(list.removeLast() != null)
            System.out.println("ERROR, removed from empty list");
            
        if(list.size() != 0)
            System.out.println("ERROR, size should be 0"); 
            
        for(Integer i : list)
            System.out.println("ERROR in iterator");
            
        // now verifying iterator
        for(int i=0; i < 10; i++)
            list.addFirst(i+1); 
           
        // should print 10 9 8 .. 1 
        for(Integer i : list)
            System.out.print(i + "  ");
        System.out.println();
        
        for(int i=0; i < 10; i++) 
            list.removeFirst();
        
        for(int i=0; i < 10; i++)
            list.addLast(i+1); 
        
        // should print 1 2 3 4 .. 10    
        for(Integer i : list)
            System.out.print(i + "  ");
        System.out.println();                                          
    }
    
    public static void main(String [] args) { 
        LinkedListTester tester = new LinkedListTester();
        tester.runTests();
    }
}

