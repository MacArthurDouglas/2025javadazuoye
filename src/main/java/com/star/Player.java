package com.star;

import com.star.utils.LogUtils;

import java.util.Random;

public class Player extends BaseRole{
    private int playerHp = 100;


    public Player(Random rand) {
        this.type="Player";
        this.alias="Player";
        this.rand=rand;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }

    @Override
    protected void encounter(BaseRole baseRole) {
        if (baseRole.getType().equals("Monster")){
            Monster m = (Monster) baseRole;
            if (m.isDead()){
                LogUtils.logPrintln("Character already dead.");
            }
            else{
                fight(m);

            }
        }
    }


    private void fight(Monster m) {

        if (isHit()){
            LogUtils.logPrintln("!!HIT!! Player successfully attacked "+m.alias);
            int dmgToMonster = Math.max(0, 15 - m.getDefense());
            LogUtils.logPrintln("你造成伤害：" + dmgToMonster);
            m.takeDamage(dmgToMonster);
            if (m.isDead()) {
                LogUtils.logPrintln(m.getName() + " 被击败！");
            }
        }
        else {
            LogUtils.logPrintln("!!MISS!! "+m.alias+" successfully defended attack from Player");

        }


    }
}
