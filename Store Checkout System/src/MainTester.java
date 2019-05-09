import java.util.Iterator;

import data_structures.*;

public class MainTester {
    public static void main(String[] args) throws Exception {

        //A Basic Test of all methods
        primaryTest();

        //A enhanced test checking edge cases
        secondaryTest();

        //Some tests onto the hash and some more edge cases
        thirdTest();
    }
    public static void primaryTest() {
        //TODO prior to testing MAKE SURE YOU ARE TESTING THE RIGHT THING

        //Note to test with other programs: switch out BST with desired test

        DictionaryADT<String,Integer> dictionary= new BalancedTree<String,Integer>();
        //DictionaryADT<String,Integer> dictionary= new Hashtable<String,Integer>(10);	//For Hashtable

        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Testing add, isEmpty, size, and isFull");
        System.out.println("-------------------------------------------------------------------------------------------------");
        dictionary.add("Steven",20);
        dictionary.add("Mike",15);
        dictionary.add("Earl",4);
        dictionary.add("Bobby",23);
        dictionary.add("Brandon",12);
        dictionary.add("Will",29);
        dictionary.add("Nhan",13);
        dictionary.add("Sid",11);
        dictionary.add("John",1);
        dictionary.add("DJ",3);
        System.out.println("CurrentSize is 10 = " + dictionary.size());
        System.out.println("isEmpty(): false = " + dictionary.isEmpty());
        System.out.println("isFull(): false for non hashtable, true for hashtable = " + dictionary.isFull());

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Testing add, clear, isEmpty, size, and isFull");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Clearing...");
        dictionary.clear();
        System.out.println("CurrentSize is 0 = " + dictionary.size());
        System.out.println("isEmpty(): true = " + dictionary.isEmpty());
        System.out.println("isFull(): false = " + dictionary.isFull());

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Testing contains");
        System.out.println("-------------------------------------------------------------------------------------------------");
        dictionary.add("Steven",20);
        dictionary.add("Mike",15);
        dictionary.add("Earl",4);
        dictionary.add("Bobby",23);
        dictionary.add("Brandon",12);
        dictionary.add("Will",29);
        dictionary.add("Nhan",13);
        dictionary.add("Sid",11);
        dictionary.add("John",1);
        dictionary.add("DJ",3);
        System.out.println("Contains 'Steven', true = " + dictionary.contains("Steven"));
        System.out.println("Contains 'Obama', false = " + dictionary.contains("Obama"));
        System.out.println("Contains 'Dj', false = " + dictionary.contains("Dj"));
        System.out.println("Contains 'DJ', true = " + dictionary.contains("DJ"));

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("getValue and getKey ");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("getValue:");
        System.out.println("Testing getValue(Muffin), null = " + dictionary.getValue("Muffin"));
        System.out.println("Testing getValue(Nandan), null = " + dictionary.getValue("Nandan"));
        System.out.println("Testing getValue(DJ), 3 = " + dictionary.getValue("DJ"));
        System.out.println("Testing getValue(Steven), 20 = " + dictionary.getValue("Steven"));
        System.out.println("\n" + "getKey:");
        System.out.println("Testing getKey(6), null = " + dictionary.getKey(6));
        System.out.println("Testing getKey(9), null = " + dictionary.getKey(9));
        System.out.println("Testing getKey(20), Steven = " + dictionary.getKey(20));
        System.out.println("Testing getKey(12), Nhan = " + dictionary.getKey(13));
        dictionary.clear();

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Testing delete");
        System.out.println("-------------------------------------------------------------------------------------------------");
        dictionary.add("Steven",20);
        dictionary.add("Mike",15);
        dictionary.add("Earl",4);
        dictionary.add("Bobby",23);
        dictionary.add("Brandon",12);
        dictionary.add("Bro",16);
        System.out.println("Deleting stuff that doesnt exist, should return false = " + dictionary.delete("Aliens"));
        System.out.println("Deleting stuff that doesnt exist, should return false = " + dictionary.delete("People who got an A in discrete"));
        System.out.println("Deleting stuff that doesnt exist, should return false = " + dictionary.delete("Dab"));
        System.out.println("Deleting 'Mike', should return true = " + dictionary.delete("Mike"));
        System.out.println("Deleting 'Steven', should return true = " + dictionary.delete("Steven"));
        System.out.println("Testing the contains on a deleted person: false = " + dictionary.contains("Steven"));
        System.out.println("Deleting 'Steven', should return false = " + dictionary.delete("Steven"));
    }

    public static void secondaryTest() {
        DictionaryADT<String,Integer> dictionary= new BalancedTree<String,Integer>();
        //DictionaryADT<String,Integer> dictionary= new Hashtable<String,Integer>(10);	//For Hashtable


        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Adding tests");
        System.out.println("-------------------------------------------------------------------------------------------------");

        dictionary.add("Steven",20);
        dictionary.add("Steven", 19);

        System.out.println("Should be size 1 = " + dictionary.size());
        System.out.println("getValue should be 20 = " + dictionary.getValue("Steven"));

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Iterators");
        System.out.println("-------------------------------------------------------------------------------------------------");

        dictionary.add("Steven",20);
        dictionary.add("Mike",15);
        dictionary.add("Earl",4);
        dictionary.add("Bobby",23);
        dictionary.add("Brandon",12);
        dictionary.add("Will",29);
        dictionary.add("Nhan",13);
        dictionary.add("Sid",11);
        dictionary.add("John",1);
        dictionary.add("DJ",3);

        Iterator iter = dictionary.keys();

        System.out.println("Size 10 = " + dictionary.size());
        System.out.println("It should be: ");
        System.out.println("Bobby Brandon DJ Earl John Mike Nhan Sid Steven Will ");

        while(iter.hasNext()){
            String str = (String) iter.next();
            System.out.print(str + " ");
        }

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Extra Deletion");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Now deleting Mike");
        dictionary.delete("Mike");
        System.out.println("Now deleting Will");
        dictionary.delete("Will");
        System.out.println("Size 8 = " + dictionary.size());
        System.out.println("It should be: ");
        System.out.println("Bobby Brandon DJ Earl John Nhan Sid Steven ");
        iter = dictionary.keys();
        while(iter.hasNext()){
            String str = (String) iter.next();
            System.out.print(str + " ");
        }

    }

    public static void thirdTest() throws Exception {
        DictionaryADT<String,Integer> dictionary= new BalancedTree<String,Integer>();
        //DictionaryADT<String,Integer> dictionary= new Hashtable<String,Integer>(10);	//For Hashtable

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Deleting when 0 and 1");
        System.out.println("-------------------------------------------------------------------------------------------------");

        dictionary.clear();
        System.out.println("Deleting when 0, false = " + dictionary.delete("Kyle"));
        System.out.println("Size, 0 = " + dictionary.size());
        dictionary.add("Based Will", 100);
        dictionary.delete("Based Will");
        System.out.println("Does it contain 'Based Will', false = " + dictionary.contains("Based Will"));
        System.out.println("Size, 0 = " + dictionary.size());

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Hashtable edge cases");
        System.out.println("-------------------------------------------------------------------------------------------------");
        hashTest();
        hashTest2();
        //TODO
//		System.out.println();
//		System.out.println("-------------------------------------------------------------------------------------------------");
//		System.out.println("Checking value of root in BST when clear");
//		System.out.println("-------------------------------------------------------------------------------------------------");
//		dictionary.clear();
//		dictionary.get(root);

    }


    private static void hashTest() {
        DictionaryADT<String,Integer> dictionary= new Hashtable<String,Integer>(10);	//For Hashtable

        dictionary.clear();
        System.out.println("Adding one item to check root, true = " + dictionary.add("Based Will", 100));
        System.out.println();

        Iterator iter = dictionary.keys();
        while(iter.hasNext()){
            String str = (String) iter.next();
            System.out.println(str + " ");
        }
    }

    private static void hashTest2() throws Exception {
        DictionaryADT<String,Integer> dictionary= new Hashtable<String,Integer>(10);	//For Hashtable
        dictionary.clear();
        System.out.println("Trying to add 11 items to a capped 10 size hashtable");
        dictionary.add("Mike",15);
        dictionary.add("Earl",4);
        dictionary.add("Bobby",23);
        dictionary.add("Brandon",12);
        dictionary.add("Will",29);
        dictionary.add("Nhan",13);
        dictionary.add("Sid",11);
        dictionary.add("John",1);
        dictionary.add("DJ",3);
        dictionary.add("Omega",11);
        dictionary.add("LUL",3);
        Iterator iter = dictionary.keys();
        while(iter.hasNext()){
            String str = (String) iter.next();
            System.out.print(str + " ");
        }

        System.out.println();
        System.out.println("Size should be 10 = " + dictionary.size());
        if(dictionary.contains("Lul")) {
            throw new Exception("Added 11th item: LUL");
        }

    }

}
