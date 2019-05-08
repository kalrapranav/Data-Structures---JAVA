import data_structures.*;
import java.util.Iterator;

public class ProductLookup {
    private DictionaryADT<String, StockItem> book;


    // Constructor.  There is no argument-less constructor, or default size
    public ProductLookup(int maxSize) {
        book = new Hashtable<String, StockItem>(maxSize);
    }

    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item) {

        book.add(SKU, item);

    }

    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU) {
        if(!book.contains(SKU))
            return null;


        return book.getValue(SKU);
    }

    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {
        if(!book.contains(SKU))
            return (float) -.01;

        return book.getValue(SKU).retail;
    }

    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU) {
        if(!book.contains(SKU))
            return (float) -.01;

        return book.getValue(SKU).cost;

    }

    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU) {

        if(!book.contains(SKU))
            return null;
        return book.getValue(SKU).getDescription();
    }

    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup.  Returns true if it was found and
    // deleted, otherwise false.
    public boolean deleteItem(String SKU) {

        if(!book.contains(SKU))
            return false;
        book.delete(SKU);
        return true;
    }

    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll() {
        Iterator<StockItem> iter = book.values();

        while(iter.hasNext()) {

            System.out.println(iter.next());
        }
    }

    // Prints a directory of all StockItems from the given vendor,
    // in sorted order (ordered by SKU).
    public void print(String vendor) {
        Iterator<StockItem> iter = book.values();

        while(iter.hasNext()) {
            StockItem tmpVal = iter.next();

            if(tmpVal.vendor.compareTo(vendor) == 0) {
                System.out.println(iter.next());
            }

        }

    }


    // An iterator of the SKU keys.
    public Iterator<String> keys(){
        return book.keys();

    }

    // An iterator of the StockItem values.
    public Iterator<StockItem> values(){
        return book.values();
    }
}
