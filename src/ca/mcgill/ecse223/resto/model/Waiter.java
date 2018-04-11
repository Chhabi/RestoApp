/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;

// line 26 "../../../../../RestoAppPersistence.ump"
// line 148 "../../../../../RestoApp.ump"
public class Waiter implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Waiter Attributes
  private String name;

  //Autounique Attributes
  private int number;

  //Waiter Associations
  private List<Bill> bill;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Waiter(String aName, RestoApp aRestoApp)
  {
    name = aName;
    number = nextNumber++;
    bill = new ArrayList<Bill>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create waiter due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNumber()
  {
    return number;
  }

  public Bill getBill(int index)
  {
    Bill aBill = bill.get(index);
    return aBill;
  }

  public List<Bill> getBill()
  {
    List<Bill> newBill = Collections.unmodifiableList(bill);
    return newBill;
  }

  public int numberOfBill()
  {
    int number = bill.size();
    return number;
  }

  public boolean hasBill()
  {
    boolean has = bill.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bill.indexOf(aBill);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfBill()
  {
    return 0;
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bill.contains(aBill)) { return false; }
    Waiter existingWaiter = aBill.getWaiter();
    if (existingWaiter == null)
    {
      aBill.setWaiter(this);
    }
    else if (!this.equals(existingWaiter))
    {
      existingWaiter.removeBill(aBill);
      addBill(aBill);
    }
    else
    {
      bill.add(aBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    if (bill.contains(aBill))
    {
      bill.remove(aBill);
      aBill.setWaiter(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBill()) { index = numberOfBill() - 1; }
      bill.remove(aBill);
      bill.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bill.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBill()) { index = numberOfBill() - 1; }
      bill.remove(aBill);
      bill.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeWaiter(this);
    }
    restoApp.addWaiter(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !bill.isEmpty() )
    {
      bill.get(0).setWaiter(null);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeWaiter(this);
    }
  }

  // line 31 "../../../../../RestoAppPersistence.ump"
   public static  void reinitializeAutouniqueNumber(List<Waiter> waiters){
    int nextId = 0; 
    for (Waiter waiter : waiters) {
      if (waiter.getNumber() > nextId) {
        nextId = waiter.getNumber();
      }
    }
    nextId++;
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 29 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = -6819781202254769716L ;

  
}