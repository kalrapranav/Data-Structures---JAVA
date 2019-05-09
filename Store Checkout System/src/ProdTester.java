import java.util.Iterator;

public class ProdTester {
    public static void main(String[] args) throws Exception {
        //TODO note to test the other data trees you must change prodlookup
        ProductLookup prodLook = new ProductLookup(10);
        //
        StockItem stock = new StockItem("Memes", "haha", "Reddit", 0, 101);

        prodLook(prodLook, stock);
    }

    private static void prodLook(ProductLookup prodLook, StockItem stock) throws Exception {

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Adding, then printing in sorted order ");
        System.out.println("-------------------------------------------------------------------------------------------------");
        prodLook.addItem("Steven", stock);
        prodLook.addItem("Nhan", stock);
        prodLook.addItem("Obama", stock);
        prodLook.addItem("Le", stock);
        prodLook.addItem("John", stock);


        Iterator iter = prodLook.keys();
        while(iter.hasNext()){
            String str = (String) iter.next();
            System.out.print(str + " ");
        }

        System.out.println("\n");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Checking if the values correspond to the price ");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println();
        prodLook.printAll();

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Deleting all of the items except one ");
        System.out.println("-------------------------------------------------------------------------------------------------");

        if(prodLook.deleteItem("Steven") == false)
            throw new Exception("Error in deleting");
        if(prodLook.deleteItem("Nhan") == false)
            throw new Exception("Error in deleting");
        if(prodLook.deleteItem("Obama") == false)
            throw new Exception("Error in deleting");
//		Iterator iter = prodLook.keys();
//		 while(iter.hasNext()){
//	            String str = (String) iter.next();
//	            System.out.print(str + " ");
//	        }

        if(prodLook.deleteItem("Le") == false)				//Error here
            throw new Exception("Error in deleting");
//		System.out.println(prodLook.getRetail("Steven"));
//		System.out.println(prodLook.getRetail("Nhan"));
//		System.out.println(prodLook.getRetail("Obama"));		//Error?
        //Here for testing


        System.out.println("Success");

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Looking at what the last item contains");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Item: " + prodLook.getItem("John"));

        if(prodLook.getRetail("John") != 101.0)			//Error here
            throw new Exception("Error at getRetail");
        if(prodLook.getCost("John") != 0.0)
            throw new Exception("Error at getCost");
        if(prodLook.getDescription("John") != "haha")
            throw new Exception("Error at getRetail");
        System.out.println("Success");


        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Checking if it deleted the item");
        System.out.println("-------------------------------------------------------------------------------------------------");

        if(prodLook.deleteItem("Steven") == false)
            System.out.println("Does not exist");
        //TODO check to see if deleted items dont give vals

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Checking vendor");
        System.out.println("-------------------------------------------------------------------------------------------------");

        prodLook.print("Reddit");

//		 System.out.println();
//		 System.out.println("-------------------------------------------------------------------------------------------------");
//		 System.out.println("Checking if something is added when size for hash is 10");
//		 System.out.println("-------------------------------------------------------------------------------------------------");
//		 prodLook.deleteItem("John");
//		 prodLook.addItem("Steven", stock);
//		 prodLook.addItem("Nhan", stock);
//		 prodLook.addItem("Obama", stock);
//		 prodLook.addItem("Le", stock);
//		 prodLook.addItem("John", stock);
//		 prodLook.addItem("Bob", stock);
//		 prodLook.addItem("Joe", stock);
//		 prodLook.addItem("Michelle", stock);
//		 prodLook.addItem("Omega", stock);
//		 prodLook.addItem("Lul", stock);
//		 prodLook.addItem("KAPPA", stock);
//
//		 Iterator iter2 = prodLook.keys();
//		 while(iter2.hasNext()){
//	            String str = (String) iter2.next();
//	            System.out.print(str + " ");
//	     }


    }
}
