package ee.taltech.iti0202.generics.animal;

public abstract class Animal {

  private String name;

  public Animal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String sound();
}