package Items;

public class FlashLight extends Item {
  public static final String name = "flashLight";
  int Id;
  private boolean status;
  private Gold price;

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void turnOn() {
    setStatus(true);
  }

  public void turnOff() {
    setStatus(false);
  }

  @Override
  public String toString() {
    return "FlashLight  " + price.toString();
  }

  @Override
  public Gold getPrice() {
    return price;
  }

  public void setPrice(Gold gold) {
    this.price = gold;
  }

  @Override
  public String getName() {
    return name;
  }
}
