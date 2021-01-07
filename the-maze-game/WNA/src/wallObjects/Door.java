package wallObjects;

import Items.Item;
import Items.Key;

import java.util.ArrayList;
import java.util.List;

public class Door extends WallObject implements Openable {
  private String nameNeededKey;
  private Key keyNeeded;
  private boolean open = true;

  private void setKeyNeeded(Key keyNeeded) {
    this.keyNeeded = keyNeeded;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  public void setNameNeededKey(String nameNeededKey) {
    this.nameNeededKey = nameNeededKey;
  }

  public void setOpenKey(Key open) {
    if (open.getName() == nameNeededKey) {
      keyNeeded = open;
      this.open = true;
      System.out.println("Door now is open.");
    }
  }

  @Override
  public List<Item> check() {
    if (!isOpen()) {
      System.out.println("Door closed <" + nameNeededKey + "> key is needed to unlock.");
      return new ArrayList<>();
    } else {
      System.out.println("Door is Open.");
    }
    return new ArrayList<>();
  }

  @Override
  public String getObjectName() {
    return "Door";
  }

  @Override
  public void open() {
    check();
  }
}
