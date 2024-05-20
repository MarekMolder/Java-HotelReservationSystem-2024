package ee.taltech.iti0202.pokemon;

import java.util.List;

public class Pokemon {
    private String name;
    private int attack;
    private int defense;
    private int hp;
    private int speed;
    private int specialAttack;
    private int specialDefense;
    private int height;
    private int weight;
    private int baseExperience;
    private List types;
    private List abilities;
    private List forms;
    private List moves;

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public List getTypes() {
        return types;
    }

    public List getAbilities() {
        return abilities;
    }

    public List getForms() {
        return forms;
    }

    public List getMoves() {
        return moves;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Pokemon(String name, int speed, int attack, int defense, int specialAttack, int specialDefense, int hp,
                   List<String> types, List<String> abilities, List<String> forms, List<String> moves,
                   int height, int weight, int baseExperience) {
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.defense = defense;
        this.hp = hp;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.types = types;
        this.abilities = abilities;
        this.forms = forms;
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "Pokemon{"
                + "name='"
                + name
                + '\''
                + ", attack=" + attack
                + ", defense=" + defense
                + ", hp=" + hp
                + ", speed=" + speed
                + ", specialAttack=" + specialAttack
                + ", specialDefense=" + specialDefense
                + ", height=" + height
                + ", weight=" + weight
                + ", base_experience=" + baseExperience
                + ", types=" + types
                + ", abilities=" + abilities
                + ", forms=" + forms
                + ", moves=" + moves
                + '}';
    }
}
