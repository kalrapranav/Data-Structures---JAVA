/**
 *  Program #4
 *  The following productLookup class provides multiple methods
 *  to lookup different product parameteres
 *  CS310-1
 *  May 9, 2019
 *  @author  Pranav Kalra cssc1483
 */



//header files
import data_structures.*;
import java.util.Iterator;


/*=============================================================================
  |----------------------------------------------------------------------------
  |  Citations:
  |  - Prof. Kraft Lecture Notes
  |  - Introduction to Algorithms by Thomas H. Cormen
  | >> The following clas was written by teh help of Prof. Kraft';s lecture notes and
  | >> and from the help of awesome TA's of CS 310 ~ Will and Nhan
  |--------------------------------------------------------------------------*/

public class ProductLookup {

    /*===================Class=Variables=======================================================*/

    private DictionaryADT<String, StockItem> book;



    /*===================Class=Methods=From=Dictionary=ADT===================================================*/

    //Class Constructor.
    public ProductLookup(int maxSize) {
        book = new Hashtable<String, StockItem>(maxSize);
    }

    //The follwoing method adds an item with SKU and item
    public void addItem(String SKU, StockItem item) {
        book.add(SKU, item);
    }

    ///The following method gets the Item according to the SKU
    public StockItem getItem(String SKU) {
        if(!book.contains(SKU))
            return null;
        return book.getValue(SKU);
    }

    ///The following method gets the Retail according to the SKU
    public float getRetail(String SKU) {
        if(!book.contains(SKU))
            return (float) -.01;
        return book.getValue(SKU).retail;
    }

    //The following method gets the Cost according to the SKU
    public float getCost(String SKU) {
        if(!book.contains(SKU))
            return (float) -.01;
        return book.getValue(SKU).cost;
    }

    ///The following method gets the Description according to the SKU
    public String getDescription(String SKU) {
        if(!book.contains(SKU))
            return null;
        return book.getValue(SKU).getDescription();
    }

    ///The following method deletes the Item according to the SKU.
    public boolean deleteItem(String SKU) {
        if(!book.contains(SKU))
            return false;
        book.delete(SKU);
        return true;
    }

    ///The following method prints all the values
    public void printAll() {
        Iterator<StockItem> iter = book.values();
        while(iter.hasNext())
            System.out.println(iter.next());
    }

    //The following method prints all the values according to teh value
    // of the vendor
    public void print(String vendor) {
        Iterator<StockItem> iter = book.values();
        while(iter.hasNext()) {
            StockItem tmpVal = iter.next();
            if(tmpVal.vendor.compareTo(vendor) == 0)
                System.out.println(iter.next());
        }
    }

    /*===================Class=Methods=From=Dictionary=ADT====END===============================================*/


    /*===================Iterators====END===================================================*/


    public Iterator<String> keys(){
        return book.keys();
    }

    public Iterator<StockItem> values(){
        return book.values();
    }

    /*===================Iterators====END===================================================*/
}


/*===================End=of=ProductLookup=Class=====================================================*/