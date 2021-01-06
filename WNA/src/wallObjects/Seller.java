package wallObjects;

import Items.Gold;
import Items.Item;
import player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends WallObject implements Serializable {
  private final List<Item> availableItems = new ArrayList<Item>();
  private final int id = 0;
  private final List<String> priceList = new ArrayList<>();
  private Gold sellerGold;

  public void addToPriceList(String itemDescription) {
    priceList.add(itemDescription);
  }

  public Gold getSellerGold() {
    return sellerGold;
  }

  public void setSellerGold(Gold sellerGold) {
    this.sellerGold = sellerGold;
  }

  public List<Item> getAvailableItems() {
    return availableItems;
  }

  public void addToSellerList(Item item) {
    availableItems.add(item);
  }

  public void sell(Player player) {

    player.listAllItem();
    System.out.println("Enter Item number to sell:");
    Scanner scanner = new Scanner(System.in);
    int itemNumber = scanner.nextInt();

    Item item = player.getPlayerItems().get(itemNumber);
    if (CouldBeBought(item, sellerGold)) {
      player.increasePlayerGold(item.getPrice().getAmount());
      this.decreaseSellerGold(item.getPrice().getAmount());
      availableItems.add(item);
      player.getPlayerItems().remove(item);
      System.out.println(item.toString() + "Info: Item Sold.");
    } else System.out.println("player.Player: Bought and acquired.");
  }

  private void decreaseSellerGold(int amount) {
    this.sellerGold.setAmount(sellerGold.getAmount() - amount);
  }

  private void Buy(Player player) {
    listSellerItems();
    System.out.println("Enter item number to buy:");
    Scanner scanner = new Scanner(System.in);
    int itemNumber = scanner.nextInt();
    Item item = availableItems.get(itemNumber);
    if (CouldBeBought(item, player.getGold())) {
      increaseSellerGold(item.getPrice().getAmount());
      player.decreasePlayerGold(item.getPrice().getAmount());
      availableItems.remove(item);
      player.addItem(item);
      System.out.println(item.toString() + "wallObjects.Seller: Bought and acquired.");
    } else {
      System.out.println("return when you have enough gold");
    }
  }

  private void increaseSellerGold(int amount) {
    sellerGold.setAmount(sellerGold.getAmount() + amount);
  }

  public boolean CouldBeBought(Item item, Gold gold) {
    return gold.compareTo(item.getPrice()) != -1;
  }

  private void listAllItems() {
    for (String s : priceList) {
      System.out.println(s);
    }
  }

  public void startTrade(Player player) {

    Scanner scanner = new Scanner(System.in);
    boolean isTradingOn = true;
    while (isTradingOn) {
      System.out.println("1- sell item. \n2-buy item.\n 3-list seller item.\n4-quit trade.");
      int userChoice = Integer.parseInt(scanner.next());
      switch (userChoice) {
        case 1:
          sell(player);
          break;
        case 2:
          Buy(player);
          break;
        case 3:
          listSellerItems();
          System.out.println("enter Item number to buy:");
          break;
        case 4:
          isTradingOn = false;
          break;
        case 5:
          System.out.println(player.toString());
          break;
      }
    }
    System.out.println("Trading finished.");
  }

  private boolean DosePlayerHasItem(Player player, int itemNumber) {
    return player.getPlayerItems().contains(priceList.get(itemNumber));
  }

  @Override
  public String getObjectName() {
    return "wallObjects.Seller";
  }

  public void listSellerItems() {
    for (int i = 0; i < getAvailableItems().size(); i++) {
      System.out.println(getAvailableItems().get(i).toString());
    }
  }
}
