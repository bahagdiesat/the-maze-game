package Items;

public class Key extends Item {
  private int id;
  private String name = "key";
  private Gold price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gold getPrice() {
    return price;
  }

  public void setPrice(Gold price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return name + " Key" + "   " + price.toString();
  }
}
