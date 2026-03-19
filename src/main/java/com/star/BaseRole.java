package com.star;

import com.star.enums.Direction;
import com.star.utils.LogUtils;

import java.util.Random;

public abstract class BaseRole {

    protected int x;
    protected int y;
    protected String type;
    protected Random rand;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected String alias;


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }




    public void move(Direction direction) {
        LogUtils.logPrintln(alias+" is moving "+direction.getDesc());
        //先从棋盘上移除自己
        Game.chessboard[x][y] = null;
        if (!changePosition(direction)) {
            //把自己放回棋盘
            Game.chessboard[x][y] = this;
            return;
        }
        if (Game.chessboard[x][y]!=null){
            encounter(Game.chessboard[x][y]);
            resetPosition(direction);
            Game.chessboard[x][y] = this;
            return;
        }
        else{
            Game.chessboard[x][y] = this;

        }



    }

    protected abstract void encounter(BaseRole baseRole);

    private void resetPosition(Direction direction) {
        switch (direction) {
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:

                x++;
                break;
            case RIGHT:

                x--;
                break;
        }
    }

    private boolean changePosition(Direction direction) {
        switch (direction) {
            case UP:
                if (y==0){
                    LogUtils.logPrintln("You can't go up. You lose a move.");
                    return false;
                }
                y--;
                break;
            case DOWN:
                if (y==Game.width-1){
                    LogUtils.logPrintln("You can't go down. You lose a move.");
                    return false;
                }
                y++;
                break;
            case LEFT:
                if (x==0){
                    LogUtils.logPrintln("You can't go left. You lose a move.");
                    return false;
                }
                x--;
                break;
            case RIGHT:
                if (x==Game.length-1){
                    LogUtils.logPrintln("You can't go right. You lose a move.");
                    return false;
                }
                x++;
                break;
        }
        return true;
    }


    /**
     * 是否命中
     * @return
     */
    protected boolean isHit(){
        return rand.nextInt(100)<50;
//        return true;

    }


}
