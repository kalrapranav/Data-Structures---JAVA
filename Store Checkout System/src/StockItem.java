/**
 *  Program #4
 *  The folloeing program gets different parameteres
 *  about the stock items
 *  CS310-1
 *  May 9, 2019
 *  @author  Pranav Kalra cssc1483
 */


//header files
import java.util.Iterator;
import data_structures.*;

/*=============================================================================
  |----------------------------------------------------------------------------
  |  Citations:
  |  - Prof. Kraft Lecture Notes
  |  - Introduction to Algorithms by Thomas H. Cormen
  | >> The following clas was written by teh help of Prof. Kraft';s lecture notes and
  | >> and from the help of awesome TA's of CS 310 ~ Will and Nhan
  |--------------------------------------------------------------------------*/


public class StockItem implements Comparable<StockItem> {

    /*===================Class=Variables=======================================================*/

    String SKU;
    String description;
    String vendor;
    float cost;
    float retail;

    // Class Constructor.
    public StockItem(String SKU, String description, String vendor, float cost, float retail) {
        this.SKU = SKU;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.retail = retail;
    }

    /*===================Class=Methods====================================================*/

    //Compare the SKU of two stock items
    public int compareTo(StockItem n) {
        return SKU.compareTo(n.SKU);
    }

    // gets the hashcode of a SKU
    public int hashCode() {
        return SKU.hashCode();
    }

    //gets the description
    public String getDescription() {
        return description;
    }

    //returns the vendor
    public String getVendor() {
        return vendor;
    }

    //returns the cost
    public float getCost() {
        return cost;
    }

    //returns the retail
    public float getRetail() {
        return retail;
    }

    /*===================Class=Methods=From=Dictionary=ADT==END=================================================*/


    /*===================toString()=======================================================*/

    //Overriding over the object class toString
    @Override
    public String toString() {
        return "StockItem [SKU=" + SKU + ", description=" + description + ", vendor=" + vendor + ", cost=" + cost
                + ", retail=" + retail + "]";
    }
}

/*===================End=of=StockItem=Class=====================================================*/