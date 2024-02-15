package ee.taltech.iti0202.bookshelf;

public class Person {
    private String name;
    private Integer money;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public boolean buyBook(Book book) {
        return false;
    }

    public boolean sellBook(Book book) {
        return false;
    }
}
