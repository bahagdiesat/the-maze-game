package timer;

import java.util.Observable;

public class PlayerTimer extends Observable {
  private long time;

  public PlayerTimer(long time) {
    this.time = time;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
    setChanged();
    notifyObservers(time);
  }
}
