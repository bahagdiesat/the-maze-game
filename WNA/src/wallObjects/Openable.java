package wallObjects;

import Items.Key;

public interface Openable extends Checkable {
  void open();

  boolean isOpen();

  void setOpenKey(Key item);
}
