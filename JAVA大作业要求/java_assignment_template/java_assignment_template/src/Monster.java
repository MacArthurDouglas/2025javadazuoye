import java.util.Random;

public class Monster {

    private String name;
    private int hp;
    private int attack;
    private int defense;

    public Monster(String name, Random rand) {
        this.name = name;
        this.hp = 70 + rand.nextInt(31);
        this.attack = 8 + rand.nextInt(8);
        this.defense = rand.nextInt(6);
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    @Override
    public String toString() {
        return name + " [HP=" + hp + ", ATK=" + attack + ", DEF=" + defense + "]";
    }
}
