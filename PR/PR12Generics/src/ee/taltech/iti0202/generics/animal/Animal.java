package ee.taltech.iti0202.generics.animal;

public abstract class Animal {

  private String name;


  /**
   * Constructs a new Animal with the given name.
   * @param name the name of the animal
   */
  public Animal(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the animal.
   * @return the name of the animal
   */
  public String getName() {
    return name;
  }

  /**
   * Produces the sound associated with this animal. This method is abstract
   * as different animals have different sounds.
   * @return the sound of the animal as a {@code String}
   */
  public abstract String sound();
}