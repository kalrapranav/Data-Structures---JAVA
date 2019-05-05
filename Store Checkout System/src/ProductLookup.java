import data_structures.*;
import java.util.Iterator;

public class ProductLookup {
    // Constructor.  There is no argument-less constructor, or default size
    public ProductLookup(int maxSize){}

    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item){}

    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU)

    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {}

    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU) {}

    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU){}

    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup.  Returns true if it was found and
    // deleted, otherwise false.
    public boolean deleteItem(String SKU){}

    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll(){}

    // Prints a directory of all StockItems from the given vendor,
    // in sorted order (ordered by SKU).
    public void print(String vendor){}

    // An iterator of the SKU keys.
    public Iterator<String> keys(){}

    // An iterator of the StockItem values.
    public Iterator<StockItem> values()}
}
