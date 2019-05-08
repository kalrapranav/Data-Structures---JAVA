import java.util.Iterator;
import data_structures.*;

public class StockItem implements Comparable<StockItem> {

    String SKU;
    String description;
    String vendor;
    float cost;
    float retail;

    // Constructor.  Creates a new StockItem instance.
    public StockItem(String SKU, String description, String vendor,
                     float cost, float retail) {
        this.SKU = SKU;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.retail = retail;

    }

    // Follows the specifications of the Comparable Interface.
    // The SKU is always used for comparisons, in dictionary order.
    public int compareTo(StockItem n) {

        return SKU.compareTo(n.SKU);
    }

    // Returns an int representing the hashCode of the SKU.
    public int hashCode() {

        return SKU.hashCode();
    }

    // standard get methods
    public String getDescription() {

        return description;

    }

    public String getVendor() {


        return vendor;

    }

    public float getCost() {

        return cost;
    }

    public float getRetail() {

        return retail;
    }

    @Override
    public String toString() {

        return "StockItem [SKU=" + SKU + ", description=" + description + ", vendor=" + vendor + ", cost=" + cost
                + ", retail=" + retail + "]";

    }
}