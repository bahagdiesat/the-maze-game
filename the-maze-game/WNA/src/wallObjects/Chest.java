package wallObjects;

import Items.Item;
import Items.Key;

import java.util.ArrayList;
import java.util.List;

public class Chest extends WallObject implements Openable {
  Key keyNeeded;
  int id = 0;
  boolean isOpen = false;
  private List<Item> chestItems = new ArrayList<>();

  public Key getKeyNeeded() {
    return keyNeeded;
  }

  public void setKeyNeeded(Key keyNeededId) {
    this.keyNeeded = keyNeededId;
  }

  @Override
  public void open() {}

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  @Override
  public void setOpenKey(Key item) {}

  public void Open(Key k) {
    if (k.getId() == keyNeeded.getId()) setOpen(true);
  }

  public void addItems(Item item) {
    chestItems.add(item);
  }

  @Override
  public List<Item> check() {
    if (!isOpen()) {
      System.out.println("“chest closed <" + keyNeeded.getName() + "> key is needed to unlock.");
      return new ArrayList<>();
    } else if (chestItems.isEmpty()) {
      System.out.println("No item in mirror.");
      return new ArrayList<>();
    }
    ArrayList<Item> clonedItems = new ArrayList(chestItems);
    chestItems = new ArrayList<>();
    printAcquiredItems();
    return clonedItems;
  }

  private void printAcquiredItems() {
    for (Item item : chestItems) {
      System.out.println(item.toString() + " was acquired”");
    }
  }

  @Override
  public String getObjectName() {
    return "wallObjects.Chest";
  }

  public void printItems() {
    for (Item item : chestItems) {
      System.out.println(item.toString());
    }
  }
}
