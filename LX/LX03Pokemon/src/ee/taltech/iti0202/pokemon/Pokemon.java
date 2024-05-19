package ee.taltech.iti0202.pokemon;

import com.google.gson.JsonArray;

import java.util.ArrayList;
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
    private int base_experience;
    private List types;
    private List abilities;
    private List forms;
    private List moves;

    public Pokemon(String name, int speed, int attack, int defense, int specialAttack, int specialDefense, int hp
            , List<String> types, List<String> abilities, List<String> forms, List<String> moves
    , int height, int weight, int base_experience) {
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.defense = defense;
        this.hp = hp;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.height = height;
        this.weight = weight;
        this.base_experience = base_experience;
        this.types = types;
        this.abilities = abilities;
        this.forms = forms;
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", hp=" + hp +
                ", speed=" + speed +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", height=" + height +
                ", weight=" + weight +
                ", base_experience=" + base_experience +
                ", types=" + types +
                ", abilities=" + abilities +
                ", forms=" + forms +
                ", moves=" + moves +
                '}';
    }
}
