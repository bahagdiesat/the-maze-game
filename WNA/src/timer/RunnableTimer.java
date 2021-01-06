package timer;

public class RunnableTimer implements Runnable {
  private final PlayerTimer time;
  private boolean finished = false;

  public RunnableTimer(PlayerTimer time) {
    this.time = time;
  }

  @Override
  public void run() {
    while (!finished) {
      try {
        Thread.sleep(1000);
        time.setTime(time.getTime() - 1);
        if (time.getTime() == 0) {
          finished = true;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
