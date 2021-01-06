package wall;

import wallObjects.WallObject;

import java.io.Serializable;

public class Wall implements Serializable {
  static int wallsCount = 0;
  boolean door = false,
      chest = false,
      mirror = false,
      painting = false,
      seller = false,
      key = false,
      empty = false;
  int type;
  int id;
  String keyName_wall = "";
  int chestID = 0;
  private WallObject wallObject;

  public Wall() {
    this.id = wallsCount++;
    this.type = 0;
  }

  public WallObject getWallObject() {
    return wallObject;
  }

  public void setWallObject(WallObject wallObj) {
    this.wallObject = wallObj;
  }

  public String look() {
    if (wallObject != null) return wallObject.getObjectName();
    else return "Wall";
  }
}
