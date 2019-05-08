/*  DictionaryTester.java
    Class for testing your DictionaryADT implementation.
    
    NOTE:  Not all public methods in the interface are tested.
    You are responsible for testing your classes.  A test program
    not available to you will be used for grading your project.
    
    If your BST class crashes with ordered data, then try setting 
    a larger stack size with the -Xss switch.  ie
    
    java -Xss128M DictionaryTester
    
    CS310  Spring 2018
    Alan Riggins
*/

import data_structures.*;

public class DictionaryTester {


    public static void main(String [] args) {
        int DICTIONARY_SIZE = 10000;
        String [] testArray = getRandomStrings(DICTIONARY_SIZE);
        long start, stop;
        DictionaryADT<String,Integer> dictionary;

        System.out.println("Testing the Hashtable with randomly ordered data...\n");
        System.out.println("Now Testing HashTable");
        //dictionary = new Hashtable<String,Integer>(DICTIONARY_SIZE);
        //System.out.println("Now Testing BinarySearchTree");
        dictionary = new BinarySearchTree<String,Integer>();
        //System.out.println("Now Testing BalancedTree");
        //dictionary = new BalancedTree<String,Integer>();


        for(int i=0; i < DICTIONARY_SIZE; i++)  {
            if(!dictionary.add(testArray[i], new Integer(i))){
                System.out.println("insert fucked " + testArray[i] + " " + dictionary.size());
            }
        }


        System.out.println("Now performing " + (DICTIONARY_SIZE*10) +
                " searches.");
        start = System.currentTimeMillis();
        for(int i=0; i < DICTIONARY_SIZE*10; i++) {
            int index = ( (int) (DICTIONARY_SIZE*Math.random()));
            Integer val = (Integer) dictionary.getValue(testArray[index]);
            if(val == null)
                throw new RuntimeException(
                        "ERROR, key " + testArray[index] + " was not found");
            if(val.intValue() !=index)
                throw new RuntimeException(
                        "ERROR, wrong value returned, expected " + index +
                                " but got " + val);
        }
        stop = System.currentTimeMillis();
        System.out.println("TIME: " + (stop-start) + "\n");

        System.out.println("Reverse lookups...");
        System.out.println("Now performing " + (DICTIONARY_SIZE/10) +
                " searches.");
        start = System.currentTimeMillis();
        for(int i=0; i < DICTIONARY_SIZE/10; i++) {
            int index = ( (int) (DICTIONARY_SIZE*Math.random()));
            String key = (String) dictionary.getKey(new Integer(index));
            if(key == null)
                throw new RuntimeException(
                        "ERROR, key " + testArray[index] + " was not found");
            if(!testArray[index].equals(key))
                throw new RuntimeException(
                        "ERROR, wrong key returned, expected " + testArray[index] +
                                " but got " + key);
        }
        stop = System.currentTimeMillis();
        System.out.println("TIME: " + (stop-start) + "\n");




        System.out.println("\nNow testing with ORDERED data...\n");

        testArray = getOrderedStrings(DICTIONARY_SIZE);

        System.out.println("***************Testing the Hashtable...");

        dictionary = new Hashtable<String,Integer>(DICTIONARY_SIZE);
        for(int i=0; i < DICTIONARY_SIZE; i++)
            dictionary.add(testArray[i], new Integer(i));

        System.out.println("Now performing " + (DICTIONARY_SIZE*10) +
                " searches.");
        start = System.currentTimeMillis();
        for(int i=0; i < DICTIONARY_SIZE*10; i++) {
            int index = ( (int) (DICTIONARY_SIZE*Math.random()));
            Integer val = (Integer) dictionary.getValue(testArray[index]);
            if(val == null)
                throw new RuntimeException(
                        "ERROR, key " + testArray[index] + " was not found");
            if(val.intValue() !=index)
                throw new RuntimeException(
                        "ERROR, wrong value returned, expected " + index +
                                " but got " + val);
        }
        stop = System.currentTimeMillis();
        System.out.println("TIME: " + (stop-start) + "\n");

        System.out.println("Reverse lookups...");
        System.out.println("Now performing " + (DICTIONARY_SIZE/10) +
                " searches.");
        start = System.currentTimeMillis();
        for(int i=0; i < DICTIONARY_SIZE/10; i++) {
            int index = ( (int) (DICTIONARY_SIZE*Math.random()));
            String key = (String) dictionary.getKey(new Integer(index));
            if(key == null)
                throw new RuntimeException(
                        "ERROR, key " + testArray[index] + " was not found");
            if(!testArray[index].equals(key))
                throw new RuntimeException(
                        "ERROR, wrong key returned, expected " + testArray[index] +
                                " but got " + key);
        }
        stop = System.currentTimeMillis();
        System.out.println("TIME: " + (stop-start) + "\n");



    }



    private static String[] getOrderedStrings(int size) {
        String [] array = getRandomStrings(size);
        array = shellSort(array);
        return array;
    }

    //This method generates a random string of the form:
    //  ABC123.  The digits on the end will be mostly zero.
    private static String[] getRandomStrings(int size) {

        String [] str = new String[size];
        String temp = "";
        int where=0;
        byte [] b = {0x41,0x41,0x41,0x30,0x30,0x30};

        for(int i=0; i < 10; i++)
            for(int j=0; j < 10; j+=(int)2*Math.random()+1)
                for(int k=0; k < 10; k+=(int)2*Math.random()+1)
                    for(int l=0; l < 26; l+=(int)2*Math.random()+1)
                        for(int m=0; m < 26; m+=(int) 2*Math.random()+1)
                            for(int n=0; n < 26; n++) {
                                if(where >= size) break;
                                str[where++] = ""+
                                        ((char)(b[0]+n)) +
                                        ((char)(b[1]+m)) +
                                        ((char)(b[2]+l)) +
                                        ((char)(b[3]+k)) +
                                        ((char)(b[4]+j)) +
                                        ((char)(b[5]+i));
                            }
        for(int i=0; i < size; i++) {
            int index = ( (int) (size*Math.random()));
            String tmp = str[index];
            str[index] = str[i];
            str[i] = tmp;
        }

        return str;
    }

    private static String[] shellSort(String n[]) {

        if(n.length < 2)
            return n;
        int in=0, out=0, h=1;
        String temp=null;
        int size = n.length;

        while(h <= size/3)
            h = h*3+1;
        while(h > 0) {
            for(out=h; out < size; out++) {
                temp = n[out];
                in = out;
                while(in > h-1 &&
                        ((Comparable)n[in-h]).compareTo(temp) >= 0) {
                    n[in] = n[in-h];
                    in -= h;
                }
                n[in] = temp;

            } // end for
            h = (h-1)/3;
        } // end while

        return n;
    }
}        