package player;

import Items.FlashLight;
import Items.Gold;
import Items.Item;
import Items.Key;
import room.Room;
import wall.Wall;
import wallObjects.Checkable;
import wallObjects.Door;
import wallObjects.WallObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Player implements Serializable, Observer {
  private static final Player playerInstance = new Player();
  private int xPosition = 0;
  private int yPosition = 0;
  private Direction direction = Direction.east;
  private long time;
  private int directionInt = 1;
  private Gold gold = new Gold(500);
  private List<Item> playerItems = new ArrayList<Item>();

  private Player() {
    gold = new Gold();
  }

  public static synchronized Player getPlayerInstance() {
    return playerInstance;
  }

  public int getDirectionInt() {
    return directionInt;
  }

  public Gold getGold() {
    for (Item t : playerItems) {}
    return gold;
  }

  public void setGold(Gold gold) {
    this.gold = gold;
  }

  public int getxPosition() {
    return xPosition;
  }

  public void setxPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  public int getyPosition() {
    return yPosition;
  }

  public void setyPosition(int yPosition) {
    this.yPosition = yPosition;
  }

  public void increasePlayerGold(int amount) {
    this.gold.setAmount(gold.getAmount() + amount);
  }

  public void decreasePlayerGold(int amount) {
    this.gold.setAmount(gold.getAmount() - amount);
  }

  public void addItem(Item item) {
    if (item instanceof Gold) {
      increasePlayerGold(((Gold) item).getAmount());
    } else this.playerItems.add(item);
  }

  public boolean DosePlayerHasItem(String item) {
    for (Item t : playerItems) {
      if (t.toString() == item) return true;
    }
    return false;
  }

  public List<Item> getPlayerItems() {
    return playerItems;
  }

  public void setPlayerItems(List<Item> playerItems) {
    this.playerItems = playerItems;
  }

  public void useKey(String keyName, Wall wall) {
    for (Item item : playerItems) {
      if (item instanceof Key) {
        if (item.getName() == keyName) {
          WallObject wallObject = wall.getWallObject();
          if (wallObject instanceof Door) {
            ((Door) wallObject).setOpenKey((Key) item);
          }
        }
      }
    }
  }

  public void turnLeft() {
    if (direction == Direction.north) direction = Direction.west;
    else if (direction == Direction.west) direction = Direction.south;
    else if (direction == Direction.south) direction = Direction.east;
    else if (direction == Direction.east) direction = Direction.north;
    directionToInt();
  }

  public void turnRight() {
    if (direction == Direction.north) direction = Direction.east;
    else if (direction == Direction.east) direction = Direction.south;
    else if (direction == Direction.south) direction = Direction.west;
    else if (direction == Direction.west) direction = Direction.north;
    directionToInt();
  }

  public void forward(Door door) {
    if (door.isOpen()) {
      if (direction == Direction.north) xPosition--;
      else if (direction == Direction.west) yPosition--;
      else if (direction == Direction.south) xPosition++;
      else if (direction == Direction.east) yPosition++;
    } else System.out.println("can't go forward because door is closed");
  }

  public void backward(Door d) {
    if (direction == Direction.north) xPosition++;
    else if (direction == Direction.west) yPosition++;
    else if (direction == Direction.south) xPosition--;
    else if (direction == Direction.east) yPosition--;
  }

  public void playerStatus() {
    System.out.println("You Are Facing: " + direction);
    System.out.println("You Have " + gold.getAmount() + " gold.");
    System.out.println("x " + xPosition + " y" + yPosition);
    System.out.println("Time left: " + time / 60 + " minutes " + time % 60 + " seconds.");
  }

  public String look(Room room) {
    if (room.isDark()) return "Dark";
    else this.directionToInt();
    return room.getWalls()[directionInt].look();
  }

  public void directionToInt() {
    if (direction == Direction.north) directionInt = 0;
    else if (direction == Direction.east) directionInt = 1;
    else if (direction == Direction.south) directionInt = 2;
    else if (direction == Direction.west) directionInt = 3;
  }

  public void check(Room room) {
    if (room.getWalls()[directionInt].getWallObject() instanceof Checkable) {
      List list = ((Checkable) room.getWalls()[directionInt].getWallObject()).check();
      this.playerItems.addAll(list);
    }
  }

  @Override
  public String toString() {
    return "player.Player{"
        + "X_Position="
        + xPosition
        + ", Y_Position="
        + yPosition
        + ", direction="
        + direction
        + ", directionInt="
        + directionInt
        + ", gold="
        + gold
        + ", playerItems="
        + playerItems
        + '}';
  }

  public void PressSwitchLight(Room r) {
    if (r.isSwitchLightExists()) {
      r.setDark(false);
    } else System.out.println("No switchLight in room.Room.");
  }

  public void useFlashLight(Room r) {
    boolean flag = false;
    for (int i = 0; i < playerItems.size(); i++) {
      if (playerItems.get(i).getName() == FlashLight.name) {
        FlashLight f = (FlashLight) playerItems.get(i);
        f.setStatus(true);
        r.setDark(true);
        System.out.println("Flash Light On , room.Room is Lit.");
        flag = true;
      }
    }
    if (!flag) System.out.println("You don't have flash light.");
  }

  public void listAllItem() {
    for (Item i : playerItems) {
      System.out.println(i.toString());
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    time = (long) arg;
    if (time == 0) {
      System.out.println("you lost. time is over.");
      System.exit(0);
    }
  }
}
