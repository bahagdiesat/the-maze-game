package wallObjects;

import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Painting extends WallObject implements Checkable {
  private final int id = 0;
  private List<Item> paintingItems = new ArrayList<>();

  public List<Item> getItems() {
    return paintingItems;
  }

  public void addItems(Item key1) {
    paintingItems.add(key1);
  }

  private void printAcquiredItems() {
    for (Item item : paintingItems) {
      System.out.println(item.toString() + " was acquired”");
    }
  }

  @Override
  public List<Item> check() {
    if (paintingItems.isEmpty()) {
      System.out.println("No item in mirror.");
      return new ArrayList<>();
    }
    ArrayList<Item> clonedItems = new ArrayList(paintingItems);
    paintingItems = new ArrayList<>();
    printAcquiredItems();
    return clonedItems;
  }

  @Override
  public String getObjectName() {
    return "painting";
  }

  public void printItems() {
    for (Item item : paintingItems) {
      System.out.println(item.toString() + " was acquired.");
    }
  }
}
