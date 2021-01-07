package Items;

public class Gold extends Item implements Comparable<Gold> {
  private final String name = "Gold";
  private int Id;
  private int amount;

  public Gold() {}

  public Gold(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return amount + name;
  }

  @Override
  public Gold getPrice() {
    return this;
  }

  @Override
  public String getName() {
    return name;
  }

  //    @Override
  //    public int compareTo(Object o) {
  //        if (o instanceof Gold)
  //            if (this.getAmount() > ((Gold) o).getAmount())
  //                return 1;
  //            else if (this.getAmount() == ((Gold) o).getAmount())
  //                return 0;
  //        return -1;
  //    }

  @Override
  public int compareTo(Gold o) {
    if (this.getAmount() > o.getAmount()) return 1;
    else if (this.getAmount() == o.getAmount()) return 0;
    return -1;
  }
}
