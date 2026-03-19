package com.star;

import com.star.enums.Direction;
import com.star.utils.LogUtils;

import java.util.Random;


public class Monster extends BaseRole {

    private String name;

    private Integer id;


    public Integer getId() {
        return id;
    }

    private int hp;
    private int attack;
    private int defense;

    @Override
    protected void encounter(BaseRole baseRole) {
        if (baseRole.getType().equals("Player")){
            fight((Player) baseRole);
        }
        else if (baseRole.getType().equals("Monster")){
            LogUtils.logPrintln("Monster already there so can't move");
        }

    }

    private void fight(Player player) {








        if (isHit()){
            int dmgToPlayer = Math.max(0, getAttack() - rand.nextInt(6));
            player.setPlayerHp(player.getPlayerHp() - dmgToPlayer);
            LogUtils.logPrintln("!!HIT!! "+alias+" successfully attacked Player");
            LogUtils.logPrintln("你受到伤害：" + dmgToPlayer);
        }
        else {
            LogUtils.logPrintln("!!MISS!! Player successfully defended attack from "+alias);

        }



    }



    public Monster(Integer id, String name, Random rand) {
        this.id = id;
        this.name = name;
        this.hp = 30 + rand.nextInt(15);
        this.attack = 8 + rand.nextInt(8);
        this.defense = rand.nextInt(3);
        this.rand = rand;
        this.type = "Monster";
        this.alias = "Monster" + id;
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


    public void randomMove() {
        Direction[] directions = Direction.values();
        move(directions[rand.nextInt(directions.length)]);
    }
}
