package ee.taltech.iti0202.generics.animal;

public class SphynxCat extends Animal {

    public SphynxCat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "Meeeeeoooooww!";
    }
}
